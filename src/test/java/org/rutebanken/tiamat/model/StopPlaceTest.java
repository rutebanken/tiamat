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

package org.rutebanken.tiamat.model;

import org.junit.Ignore;
import org.junit.Test;
import org.locationtech.jts.geom.Coordinate;
import org.rutebanken.tiamat.TiamatIntegrationTest;
import org.rutebanken.tiamat.exporter.params.ExportParams;
import org.rutebanken.tiamat.exporter.params.StopPlaceSearch;
import org.rutebanken.tiamat.netex.id.NetexIdHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@Commit
public class StopPlaceTest extends TiamatIntegrationTest {


    @Autowired
    private NetexIdHelper netexIdHelper;

    @Test
    public void persistStopPlaceWithTariffZone() {

        StopPlace stopPlace = new StopPlace();
        TariffZone tariffZone = new TariffZone();
        tariffZone = tariffZoneRepository.save(tariffZone);
        stopPlace.getTariffZones().add(new TariffZoneRef(tariffZone));

        StopPlace actualStopPlace  = stopPlaceRepository.save(stopPlace);
        assertThat(actualStopPlace.getTariffZones()).isNotEmpty();
    }

    @Test
    public void persistStopPlaceWithAdjacentSites() {

        StopPlace firstStopPlace = new StopPlace();
        firstStopPlace.setVersion(1L);
        stopPlaceRepository.save(firstStopPlace);

        StopPlace secondStopPlace = new StopPlace();
        secondStopPlace.setVersion(1L);
        secondStopPlace.getAdjacentSites().add(new SiteRefStructure(firstStopPlace.getNetexId()));
        stopPlaceRepository.save(secondStopPlace);

        firstStopPlace.getAdjacentSites().add(new SiteRefStructure(secondStopPlace.getNetexId()));
        stopPlaceRepository.save(firstStopPlace);

        StopPlace actualFirstStopPlace = stopPlaceRepository.findFirstByNetexIdAndVersion(firstStopPlace.getNetexId(), firstStopPlace.getVersion());

        StopPlace actualSecondStopPlace = stopPlaceRepository.findFirstByNetexIdAndVersion(secondStopPlace.getNetexId(), secondStopPlace.getVersion());


        assertThat(actualFirstStopPlace.getAdjacentSites()).hasSize(1);
        assertThat(actualSecondStopPlace.getAdjacentSites()).hasSize(1);
    }

    @Test
    public void persistStopPlaceAutoGeneratedId() {
        StopPlace stopPlace = new StopPlace();
        StopPlace actualStopPlace  = stopPlaceRepository.save(stopPlace);
        assertThat(actualStopPlace.getNetexId()).isNotNull();
    }

    @Test
    public void fillGapsInStopPlaces() {
        long explicitIdPostfix = 20000;
        String explicitId = netexIdHelper.getNetexId(StopPlace.class.getSimpleName(), explicitIdPostfix);
        StopPlace explicitIdStopPlace = new StopPlace();
        explicitIdStopPlace.setNetexId(explicitId);
        explicitIdStopPlace = stopPlaceRepository.save(explicitIdStopPlace);

        StopPlace giveMeAnyId = stopPlaceRepository.save(new StopPlace());

        StopPlace giveMeAnyId2 = stopPlaceRepository.save(new StopPlace());

        assertThat(explicitIdStopPlace.getNetexId()).isEqualTo(explicitId);
        assertThat(netexIdHelper.extractIdPostfixNumeric(giveMeAnyId.getNetexId())).isLessThan(explicitIdPostfix);
        assertThat(netexIdHelper.extractIdPostfixNumeric(giveMeAnyId2.getNetexId())).isLessThan(explicitIdPostfix);
    }

    @Test
    public void saveTwoVersionsOfStopFindLastVersion() {

        String netexId = "NSR:StopPlace:60000";

        StopPlace firstVersion = new StopPlace();
        firstVersion.setVersion(1L);
        firstVersion.setNetexId(netexId);
        firstVersion = stopPlaceRepository.save(firstVersion);
        stopPlaceRepository.flush();

        StopPlace secondVersion = new StopPlace();
        secondVersion.setVersion(2L);
        secondVersion.setNetexId(netexId);
        stopPlaceRepository.save(secondVersion);
        stopPlaceRepository.flush();

        StopPlace actualActualSecondVersion = stopPlaceRepository.findFirstByNetexIdOrderByVersionDesc(secondVersion.getNetexId());
        assertThat(actualActualSecondVersion.getVersion()).isEqualTo(2L);

        StopPlace actualFirstVersion = stopPlaceRepository.findFirstByNetexIdAndVersion(firstVersion.getNetexId(), firstVersion.getVersion());
        assertThat(actualFirstVersion.getVersion()).isEqualTo(1L);
    }

    @Test
    public void createTwoVersionsOfNewStop() {

        StopPlace firstVersion = new StopPlace();
        firstVersion.setVersion(1L);
        firstVersion = stopPlaceRepository.save(firstVersion);
        stopPlaceRepository.flush();

        StopPlace secondVersion = new StopPlace();
        secondVersion.setNetexId(firstVersion.getNetexId());
        secondVersion.setVersion(2L);

        stopPlaceRepository.save(secondVersion);
        stopPlaceRepository.flush();

        StopPlace actualSecondVersion = stopPlaceRepository.findFirstByNetexIdOrderByVersionDesc(secondVersion.getNetexId());
        assertThat(actualSecondVersion.getVersion()).isEqualTo(2L);

        StopPlace actualFirstVersion = stopPlaceRepository.findFirstByNetexIdAndVersion(firstVersion.getNetexId(), firstVersion.getVersion());
        assertThat(actualFirstVersion.getVersion()).isEqualTo(1L);

        // This must be true as both actual objects has been retrieved from netexId
        assertThat(actualSecondVersion.getNetexId()).isEqualTo(firstVersion.getNetexId());
    }

    @Test
    public void persistStopPlaceWithAccessSpace() {
        StopPlace stopPlace = new StopPlace();

        AccessSpace accessSpace = new AccessSpace();
        accessSpace.setShortName(new EmbeddableMultilingualString("Østbanehallen", "no"));

        stopPlace.getAccessSpaces().add(accessSpace);

        stopPlaceRepository.save(stopPlace);
        StopPlace actualStopPlace = stopPlaceRepository.findFirstByNetexIdOrderByVersionDesc(stopPlace.getNetexId());

        assertThat(actualStopPlace.getAccessSpaces()).isNotEmpty();
        assertThat(actualStopPlace.getAccessSpaces().get(0).getShortName().getValue()).isEqualTo(accessSpace.getShortName().getValue());
    }

    @Test
    public void persistStopPlaceWithEquipmentPlace() {

        StopPlace stopPlace = new StopPlace();

        EquipmentPlace equipmentPlace = new EquipmentPlace();
        List<EquipmentPlace> equipmentPlaces = new ArrayList<>();
        equipmentPlaces.add(equipmentPlace);

        stopPlace.setEquipmentPlaces(equipmentPlaces);

        stopPlaceRepository.save(stopPlace);

        StopPlace actualStopPlace = stopPlaceRepository.findFirstByNetexIdOrderByVersionDesc(stopPlace.getNetexId());

        assertThat(actualStopPlace.getEquipmentPlaces()).isNotEmpty();
        assertThat(actualStopPlace.getEquipmentPlaces().get(0).getNetexId()).isEqualTo(equipmentPlace.getNetexId());
    }

    @Test
    public void persistStopPlaceWithPlaceEquipment() {

        StopPlace stopPlace = new StopPlace();

        PlaceEquipment equipments = new PlaceEquipment();


        ShelterEquipment leskur = new ShelterEquipment();
            leskur.setEnclosed(false);
            leskur.setSeats(BigInteger.valueOf(2));

        WaitingRoomEquipment venterom = new WaitingRoomEquipment();
            venterom.setSeats(BigInteger.valueOf(25));

        TicketingEquipment billettAutomat = new TicketingEquipment();
        billettAutomat.setTicketMachines(true);
        billettAutomat.setNumberOfMachines(BigInteger.valueOf(2));

        SanitaryEquipment toalett = new SanitaryEquipment();
        toalett.setNumberOfToilets(BigInteger.valueOf(2));

        equipments.getInstalledEquipment().add(venterom);
        equipments.getInstalledEquipment().add(billettAutomat);
        equipments.getInstalledEquipment().add(toalett);
        equipments.getInstalledEquipment().add(leskur);

        stopPlace.setPlaceEquipments(equipments);

        stopPlaceRepository.save(stopPlace);
        String netexId = stopPlace.getNetexId();

        StopPlace actualStopPlace = stopPlaceRepository.findFirstByNetexIdOrderByVersionDesc(netexId);

        assertThat(actualStopPlace.getPlaceEquipments().getNetexId()).isNotNull();
        assertThat(actualStopPlace.getPlaceEquipments().getInstalledEquipment()).isNotEmpty();
//        assertThat(((SiteElement)actualStopPlace.getFacilities().getSiteFacilitySetRefOrSiteFacilitySet().get(0)).getNetexId()).isEqualTo(luggageLockerEquipment.getNetexId());
    }

    @Ignore // level is transient
    @Test
    public void persistStopPlaceWithLevels() {
        StopPlace stopPlace = new StopPlace();

        Level level = new Level();
        level.setName(new MultilingualStringEntity("Erde", "fr"));
        level.setPublicCode("E");
        level.setVersion(1L);
        stopPlace.getLevels().add(level);

        stopPlaceRepository.save(stopPlace);
        StopPlace actualStopPlace = stopPlaceRepository.findFirstByNetexIdOrderByVersionDesc(stopPlace.getNetexId());

        assertThat(actualStopPlace.getLevels()).isNotEmpty();
        assertThat(actualStopPlace.getLevels().get(0).getName().getValue()).isEqualTo(level.getName().getValue());
        assertThat(actualStopPlace.getLevels().get(0).getPublicCode()).isEqualTo(level.getPublicCode());
        assertThat(actualStopPlace.getLevels().get(0).getVersion()).isEqualTo(level.getVersion());
    }

    @Test
    public void persistStopPlaceWithValidBetween() {

        StopPlace stopPlace = new StopPlace();

        ValidBetween validBetween = new ValidBetween();
        validBetween.setFromDate(Instant.now());
        validBetween.setToDate(Instant.now().plus(70, ChronoUnit.DAYS));
        
        stopPlace.setValidBetween(validBetween);

        stopPlaceRepository.save(stopPlace);

        StopPlace actualStopPlace = stopPlaceRepository.findFirstByNetexIdOrderByVersionDesc(stopPlace.getNetexId());

        assertThat(actualStopPlace.getValidBetween())
                .isNotNull();

        ValidBetween actualValidBetween = actualStopPlace.getValidBetween();
        assertThat(actualValidBetween.getFromDate()).isEqualTo(validBetween.getFromDate());
        assertThat(actualValidBetween.getToDate()).isEqualTo(validBetween.getToDate());
    }

    @Ignore
    @Test
    public void persistStopPlaceWithParentReference() {
        StopPlace stopPlace = new StopPlace();

        StopPlaceReference stopPlaceReference = new StopPlaceReference();
        stopPlaceReference.setRef("id-to-another-stop-place");
        stopPlaceReference.setVersion("001");

        stopPlace.setParentSiteRef(stopPlaceReference);

        stopPlaceRepository.save(stopPlace);

        StopPlace actualStopPlace = stopPlaceRepository.findFirstByNetexIdOrderByVersionDesc(stopPlace.getNetexId());
        assertThat(actualStopPlace.getParentSiteRef().getRef()).isEqualTo(stopPlaceReference.getRef());
    }

    @Ignore // other vehicle mode is transient
    @Test
    public void persistStopPlaceWithOtherVehicleMode() {
        StopPlace stopPlace = new StopPlace();
        stopPlace.getOtherTransportModes().add(VehicleModeEnumeration.AIR);
        stopPlaceRepository.save(stopPlace);
        StopPlace actualStopPlace = stopPlaceRepository.findFirstByNetexIdOrderByVersionDesc(stopPlace.getNetexId());
        assertThat(actualStopPlace.getOtherTransportModes()).contains(VehicleModeEnumeration.AIR);
    }

    @Test
    public void persistStopPlaceWithDataSourceRef() {
        StopPlace stopPlace = new StopPlace();
        stopPlace.setDataSourceRef("dataSourceRef");
        stopPlaceRepository.save(stopPlace);
        StopPlace actualStopPlace = stopPlaceRepository.findFirstByNetexIdOrderByVersionDesc(stopPlace.getNetexId());
        assertThat(actualStopPlace.getDataSourceRef()).isEqualTo(stopPlace.getDataSourceRef());
    }

    @Test
    public void persistStopPlaceWithCreatedDate() {
        StopPlace stopPlace = new StopPlace();
        stopPlace.setCreated(Instant.ofEpochMilli(10000));
        stopPlaceRepository.save(stopPlace);
        StopPlace actualStopPlace = stopPlaceRepository.findFirstByNetexIdOrderByVersionDesc(stopPlace.getNetexId());
        assertThat(actualStopPlace.getCreated()).isEqualTo(stopPlace.getCreated());
    }

    @Test
    public void persistStopPlaceWithChangedDate() {
        StopPlace stopPlace = new StopPlace();
        stopPlace.setChanged(Instant.ofEpochMilli(10000));
        stopPlaceRepository.save(stopPlace);
        StopPlace actualStopPlace = stopPlaceRepository.findFirstByNetexIdOrderByVersionDesc(stopPlace.getNetexId());
        assertThat(actualStopPlace.getChanged()).isEqualTo(stopPlace.getChanged());
    }

    @Test
    public void persistStopPlaceWitAlternativeName() {
        StopPlace stopPlace = new StopPlace();

        AlternativeName alternativeName = new AlternativeName();
        alternativeName.setShortName(new EmbeddableMultilingualString("short name", "en"));
        stopPlace.getAlternativeNames().add(alternativeName);
        stopPlaceRepository.save(stopPlace);
        StopPlace actualStopPlace = stopPlaceRepository.findFirstByNetexIdOrderByVersionDesc(stopPlace.getNetexId());
        assertThat(actualStopPlace.getAlternativeNames()).isNotEmpty();
        assertThat(actualStopPlace.getAlternativeNames().get(0).getShortName().getValue()).isEqualTo(alternativeName.getShortName().getValue());
    }

    @Test
    public void persistStopPlaceWithDescription() {
        StopPlace stopPlace = new StopPlace();
        stopPlace.setDescription(new EmbeddableMultilingualString("description", "en"));
        stopPlaceRepository.save(stopPlace);
        StopPlace actualStopPlace = stopPlaceRepository.findFirstByNetexIdOrderByVersionDesc(stopPlace.getNetexId());
        assertThat(actualStopPlace.getDescription().getValue()).isEqualTo(stopPlace.getDescription().getValue());
    }

    @Test
    public void persistStopPlaceShortNameAndPublicCode() {
        StopPlace stopPlace = new StopPlace();
        stopPlace.setPublicCode("public-code");
        stopPlace.setName(new EmbeddableMultilingualString("Skjervik", "no"));
        stopPlace.setShortName(new EmbeddableMultilingualString("short name"));

        stopPlaceRepository.save(stopPlace);
        StopPlace actualStopPlace = stopPlaceRepository.findFirstByNetexIdOrderByVersionDesc(stopPlace.getNetexId());

        assertThat(actualStopPlace.getPublicCode()).isEqualTo(stopPlace.getPublicCode());
        assertThat(actualStopPlace.getNetexId()).isEqualTo(stopPlace.getNetexId());
        assertThat(actualStopPlace.getShortName().getValue()).isEqualTo(stopPlace.getShortName().getValue());
    }

    @Test
    public void persistStopPlaceEnums() {
        StopPlace stopPlace = new StopPlace();

        stopPlace.setStopPlaceType(StopTypeEnumeration.RAIL_STATION);
        stopPlace.setTransportMode(VehicleModeEnumeration.RAIL);
        stopPlace.setAirSubmode(AirSubmodeEnumeration.UNDEFINED);
        stopPlace.setCoachSubmode(CoachSubmodeEnumeration.REGIONAL_COACH);
        stopPlace.setFunicularSubmode(FunicularSubmodeEnumeration.UNKNOWN);
        stopPlace.getOtherTransportModes().add(VehicleModeEnumeration.AIR);
        stopPlace.setWeighting(InterchangeWeightingEnumeration.RECOMMENDED_INTERCHANGE);
        stopPlace.setBusSubmode(BusSubmodeEnumeration.DEMAND_AND_RESPONSE_BUS);
        stopPlace.setCovered(CoveredEnumeration.INDOORS);
        stopPlace.setGated(GatedEnumeration.OPEN_AREA);
        stopPlace.setModification(ModificationEnumeration.NEW);
        stopPlace.setRailSubmode(RailSubmodeEnumeration.HIGH_SPEED_RAIL);
        stopPlace.setMetroSubmode(MetroSubmodeEnumeration.METRO);
        stopPlace.setSiteType(SiteTypeEnumeration.OFFICE);
        stopPlace.setStatus(StatusEnumeration.OTHER);
        stopPlace.setWaterSubmode(WaterSubmodeEnumeration.CABLE_FERRY);
        stopPlace.setTramSubmode(TramSubmodeEnumeration.REGIONAL_TRAM);
        stopPlace.setTelecabinSubmode(TelecabinSubmodeEnumeration.TELECABIN);

        stopPlaceRepository.save(stopPlace);

        StopPlace actualStopPlace = stopPlaceRepository.findFirstByNetexIdOrderByVersionDesc(stopPlace.getNetexId());

        assertThat(actualStopPlace).usingRecursiveComparison().comparingOnlyFields("stopPlaceType", "transportMode", "airSubmode", "coachSubmode",
                "funicularSubmode", "otherTransportModes",
                "weighting", "busSubmode", "covered", "gated", "modification",
                "railSubmode", "metroSubmode", "siteType", "status", "waterSubmode",
                "tramSubmode", "telecabinSubmode").isEqualTo(stopPlace);
    }

    @Test
    public void testAttachingQuaysToStopPlace() throws Exception {
        Quay quay = new Quay();
        quay.setName(new EmbeddableMultilingualString("q", "en"));

        StopPlace stopPlace = new StopPlace();
        stopPlace.setQuays(new HashSet<>());
        stopPlace.getQuays().add(quay);

        stopPlaceRepository.save(stopPlace);

        assertThat(quay.getNetexId()).isNotNull();
        Quay actualQuay = quayRepository.findFirstByNetexIdOrderByVersionDesc(quay.getNetexId());
        assertThat(actualQuay).isNotNull();
    }

    @Test
    @Ignore
    public void saveModifiedStopPlaceWithModifiedQuayAndNewQuay() throws Exception {
        Quay quay = new Quay();
        quay.setName(new EmbeddableMultilingualString("existing quay"));

        StopPlace stopPlace = new StopPlace(new EmbeddableMultilingualString("existing stop"));
        stopPlace.setStopPlaceType(StopTypeEnumeration.BUS_STATION);
        stopPlace.getQuays().add(quay);

        //Calling saveAndFlush to ensure fields are saved before updating
        stopPlaceRepository.saveAndFlush(stopPlace);

        modifyStopPlaceAndAddQuay(stopPlace.getNetexId());
    }

    private void modifyStopPlaceAndAddQuay(String stopPlaceNetexId) {
        StopPlace existingStopPlace = stopPlaceRepository.findFirstByNetexIdOrderByVersionDesc(stopPlaceNetexId);

        existingStopPlace.getQuays().forEach(q -> {
            q.setDescription(new EmbeddableMultilingualString("a new description for quay"));
            q.setCentroid(geometryFactory.createPoint(new Coordinate(12.345, 56.789)));
        });

        existingStopPlace.setStopPlaceType(StopTypeEnumeration.AIRPORT);

        Quay newQuay = new Quay(new EmbeddableMultilingualString("New quay"));
        existingStopPlace.getQuays().add(newQuay);

        stopPlaceRepository.save(existingStopPlace);

        StopPlace actualStopPlace = stopPlaceRepository.findFirstByNetexIdOrderByVersionDesc(existingStopPlace.getNetexId());

        assertThat(actualStopPlace.getStopPlaceType()).isEqualTo(StopTypeEnumeration.AIRPORT);

        assertThat(actualStopPlace.getQuays()).hasSize(2);
    }

    @Test
    public void testKeyValueStructure() throws Exception {
        StopPlace stopPlace = new StopPlace();
        List<String> ids = Arrays.asList("OPP:StopArea:123123", "TEL:StopArea:3251321");
        Value value = new Value(ids);
        stopPlace.getKeyValues().put("ORIGINAL_ID", value);

        stopPlaceRepository.save(stopPlace);

        StopPlace actual = stopPlaceRepository.findFirstByNetexIdOrderByVersionDesc(stopPlace.getNetexId());

        assertThat(actual.getKeyValues().get("ORIGINAL_ID").getItems().containsAll(ids));
    }

    @Test
    public void testAddKeyValueAndRemove() throws Exception {
        StopPlace stopPlace = new StopPlace();
        // Add two
        List<String> ids = Arrays.asList("OPP:StopArea:1337", "TEL:StopArea:666");
        Value value = new Value(ids);
        stopPlace.getKeyValues().put("ORIGINAL_ID", value);

        stopPlaceRepository.save(stopPlace);

        stopPlace.getKeyValues().get("ORIGINAL_ID").getItems().remove("TEL:StopArea:666");

        stopPlaceRepository.save(stopPlace);

        StopPlace actual = stopPlaceRepository.findFirstByNetexIdOrderByVersionDesc(stopPlace.getNetexId());

        assertThat(actual.getKeyValues().get("ORIGINAL_ID").getItems()).hasSize(1);
    }

    @Test
    public void searchForStopWithFutureVersion() {
        StopPlace currentVersion = new StopPlace();

        Instant from = Instant.from(LocalDate.now().minusDays(1L).atStartOfDay(ZoneOffset.systemDefault()).toInstant());
        Instant to = Instant.from(LocalDate.now().plusDays(1L).atStartOfDay(ZoneOffset.systemDefault()).toInstant());

        currentVersion.setValidBetween(new ValidBetween(from, to));
        currentVersion.setVersion(1);
        stopPlaceRepository.save(currentVersion);

        StopPlace futureVersion = versionCreator.createCopy(currentVersion, StopPlace.class);

        futureVersion.setValidBetween(new ValidBetween(to));
        futureVersion.setVersion(2);
        stopPlaceRepository.save(futureVersion);


        Page<StopPlace> actual = stopPlaceRepository.findStopPlace(ExportParams.newExportParamsBuilder()
                .setStopPlaceSearch(StopPlaceSearch.newStopPlaceSearchBuilder()
                        .setPointInTime(Instant.now())
                        .build())
                .build());


        assertThat(actual).isNotNull();
        assertThat(actual.getTotalElements()).isEqualTo(1);


        StopPlace actualStopPlace = actual.getContent().get(0);
        assertThat(actualStopPlace.getVersion()).isEqualTo(currentVersion.getVersion());

    }
}
