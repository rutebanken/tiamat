/*
 * Licensed under the EUPL, Version 1.2 or – as soon they will be approved by
 * the European Commission - subsequent versions of the EUPL (the "Licence");
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 *
 *   https://joinup.ec.europa.eu/software/page/eupl
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and
 * limitations under the Licence.
 */

/*
 * Licensed under the EUPL, Version 1.2 or – as soon they will be approved by
 * the European Commission - subsequent versions of the EUPL (the "Licence");
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 *
 *   https://joinup.ec.europa.eu/software/page/eupl
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and
 * limitations under the Licence.
 */

package org.rutebanken.tiamat.rest.graphql.fetchers;

import com.google.api.client.repackaged.com.google.common.base.Strings;
import com.google.api.client.util.Preconditions;
import graphql.language.Field;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.rutebanken.helper.organisation.ReflectionAuthorizationService;
import org.rutebanken.tiamat.model.GroupOfStopPlaces;
import org.rutebanken.tiamat.model.StopPlace;
import org.rutebanken.tiamat.repository.GroupOfStopPlacesRepository;
import org.rutebanken.tiamat.repository.StopPlaceRepository;
import org.rutebanken.tiamat.rest.graphql.helpers.CleanupHelper;
import org.rutebanken.tiamat.rest.graphql.mappers.StopPlaceMapper;
import org.rutebanken.tiamat.service.MutateLock;
import org.rutebanken.tiamat.versioning.StopPlaceVersionedSaverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.rutebanken.helper.organisation.AuthorizationConstants.ROLE_EDIT_STOPS;
import static org.rutebanken.tiamat.rest.graphql.GraphQLNames.*;

@Service("groupOfStopPlacesUpdater")
@Transactional
class GroupOfStopPlacesUpdater implements DataFetcher<GroupOfStopPlaces> {

    private static final Logger logger = LoggerFactory.getLogger(GroupOfStopPlacesUpdater.class);

    @Autowired
    private StopPlaceVersionedSaverService stopPlaceVersionedSaverService;

    @Autowired
    private StopPlaceRepository stopPlaceRepository;

    @Autowired
    private GroupOfStopPlacesRepository groupOfStopPlacesRepository;

    @Autowired
    private ReflectionAuthorizationService authorizationService;

    @Autowired
    private StopPlaceMapper stopPlaceMapper;

    @Autowired
    private MutateLock mutateLock;

    @Override
    public GroupOfStopPlaces get(DataFetchingEnvironment environment) {
        List<Field> fields = environment.getFields();
        CleanupHelper.trimValues(environment.getArguments());
        for (Field field : fields) {
            if (field.getName().equals(MUTATE_GROUP_OF_STOP_PLACES)) {
                return createOrUpdateGroupOfStopPlaces(environment, false);
            }
        }
        return null;
    }


    private GroupOfStopPlaces createOrUpdateGroupOfStopPlaces(DataFetchingEnvironment environment, boolean mutateParent) {
        GroupOfStopPlaces groupOfStopPlaces;
        GroupOfStopPlaces existingVersion = null;

        Map input = environment.getArgument(OUTPUT_TYPE_GROUP_OF_STOPPLACES);

        if (input != null) {

            String netexId = (String) input.get(ID);
            if (netexId != null) {

                logger.info("About to update GroupOfStopPlaces {}", netexId);

                existingVersion = findAndVerify(netexId);

            } else {

                logger.info("Creating new GroupOfStopPlaces");
                groupOfStopPlaces = new GroupOfStopPlaces();
            }

        }
        return existingVersion;
    }

    private GroupOfStopPlaces findAndVerify(String netexId) {
        GroupOfStopPlaces existingGroupOfStopPlaces = groupOfStopPlacesRepository.findFirstByNetexIdOrderByVersionDesc(netexId);
        verifyStopPlaceNotNull(existingGroupOfStopPlaces, netexId);
        return existingGroupOfStopPlaces;
    }

    private void verifyStopPlaceNotNull(GroupOfStopPlaces existingGroupOfStopPlaces, String netexId) {
        Preconditions.checkArgument(existingGroupOfStopPlaces != null, "Attempting to update StopPlace [id = %s], but StopPlace does not exist.", netexId);
    }
}