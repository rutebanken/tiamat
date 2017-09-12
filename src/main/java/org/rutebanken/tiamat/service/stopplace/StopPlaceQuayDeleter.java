package org.rutebanken.tiamat.service.stopplace;

import com.google.api.client.util.Preconditions;
import org.rutebanken.helper.organisation.ReflectionAuthorizationService;
import org.rutebanken.tiamat.auth.UsernameFetcher;
import org.rutebanken.tiamat.model.Quay;
import org.rutebanken.tiamat.model.StopPlace;
import org.rutebanken.tiamat.repository.StopPlaceRepository;
import org.rutebanken.tiamat.versioning.CopiedEntity;
import org.rutebanken.tiamat.versioning.StopPlaceVersionedSaverService;
import org.rutebanken.tiamat.versioning.util.StopPlaceCopyHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

import static org.rutebanken.helper.organisation.AuthorizationConstants.ROLE_EDIT_STOPS;

@Component
public class StopPlaceQuayDeleter {

    private static final Logger logger = LoggerFactory.getLogger(StopPlaceQuayDeleter.class);

    @Autowired
    private StopPlaceVersionedSaverService stopPlaceVersionedSaverService;

    @Autowired
    private StopPlaceRepository stopPlaceRepository;

    @Autowired
    private ReflectionAuthorizationService authorizationService;

    @Autowired
    private UsernameFetcher usernameFetcher;

    @Autowired
    private StopPlaceCopyHelper stopPlaceCopyHelper;

    public StopPlace deleteQuay(String stopPlaceId, String quayId, String versionComment) {

        logger.warn("{} is deleting quay {} from stop place {} with comment {}", usernameFetcher.getUserNameForAuthenticatedUser(), quayId, stopPlaceId, versionComment);

        StopPlace stopPlace = stopPlaceRepository.findFirstByNetexIdOrderByVersionDesc(stopPlaceId);

        Preconditions.checkArgument(stopPlace != null, "Attempting to delete StopPlace [id = %s], but StopPlace does not exist.", stopPlaceId);

        Preconditions.checkArgument(!stopPlace.isParentStopPlace(), "Cannot merge quays of parent stop place: [id = %s].", stopPlaceId);

        Optional<Quay> optionalQuay = stopPlace.getQuays().stream().filter(quay -> quay.getNetexId().equals(quayId)).findFirst();
        Preconditions.checkArgument(optionalQuay.isPresent(), "Attempting to delete Quay [id = %s], but Quay does not exist on StopPlace [id = %s].", quayId, stopPlaceId);

        authorizationService.assertAuthorized(ROLE_EDIT_STOPS, Arrays.asList(stopPlace));

        CopiedEntity<StopPlace> stopPlaceCopies = stopPlaceCopyHelper.createCopies(stopPlace);

        stopPlaceCopies.getCopiedEntity().getQuays().removeIf(quay -> quay.getNetexId().equals(quayId));

        if(stopPlaceCopies.hasParent()) {
            stopPlaceCopies.getCopiedParent().setVersionComment(versionComment);
        } else {
            stopPlaceCopies.getCopiedEntity().setVersionComment(versionComment);
        }


        if(stopPlaceCopies.hasParent()) {
            logger.info("Saving parent stop place {}. Returning parent of child: {}", stopPlaceCopies.getCopiedParent().getNetexId(), stopPlace.getNetexId());
            return stopPlaceVersionedSaverService.saveNewVersion(stopPlaceCopies.getExistingParent(), stopPlaceCopies.getCopiedParent());
        } else {
            return stopPlaceVersionedSaverService.saveNewVersion(stopPlaceCopies.getExistingEntity(), stopPlaceCopies.getCopiedEntity());
        }
    }
}