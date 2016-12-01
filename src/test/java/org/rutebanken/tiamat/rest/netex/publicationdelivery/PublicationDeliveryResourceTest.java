package org.rutebanken.tiamat.rest.netex.publicationdelivery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.rutebanken.netex.model.*;
import org.rutebanken.tiamat.TiamatApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xml.sax.SAXException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.rutebanken.tiamat.netex.mapping.mapper.NetexIdMapper.ORIGINAL_ID_KEY;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TiamatApplication.class)
@ActiveProfiles("geodb")
public class PublicationDeliveryResourceTest {

    @Autowired
    private PublicationDeliveryResource publicationDeliveryResource;

    /**
     * When sending a stop place with the same ID twice, the same stop place must be returned.
     * When importing multiple stop places and those exists, make sure no Lazy Initialization Exception is thrown.
     */
    @Test
    public void publicationDeliveryWithDuplicateStopPlace() throws Exception {

        StopPlace stopPlace = new StopPlace()
                .withId("RUT:StopPlace:123123")
                .withCentroid(new SimplePoint_VersionStructure()
                    .withLocation(new LocationStructure()
                            .withLatitude(new BigDecimal("9"))
                            .withLongitude(new BigDecimal("71"))));

        StopPlace stopPlace2 = new StopPlace()
                .withId("RUT:StopPlace:123123")
                .withCentroid(new SimplePoint_VersionStructure()
                        .withLocation(new LocationStructure()
                                .withLatitude(new BigDecimal("10"))
                                .withLongitude(new BigDecimal("72"))));


        PublicationDeliveryStructure publicationDelivery = createPublicationDeliveryWithStopPlace(stopPlace, stopPlace2);

        PublicationDeliveryStructure response = publicationDeliveryResource.importPublicationDelivery(publicationDelivery);

        List<StopPlace> result = extractStopPlace(response);

        assertThat(result).as("Expecting one stop place in return, as there is no need to return duplicates").hasSize(1);
    }

    @Test
    public void publicationDeliveryWithStopPlaceAndQuay() throws Exception {

        StopPlace stopPlace = new StopPlace()
                .withId("NSR:StopPlace:123123")
                .withCentroid(new SimplePoint_VersionStructure()
                        .withLocation(new LocationStructure()
                                .withLatitude(new BigDecimal("9"))
                                .withLongitude(new BigDecimal("71"))))
                .withQuays(new Quays_RelStructure()
                    .withQuayRefOrQuay(new Quay()
                        .withName(new MultilingualString().withValue("quay"))
                        .withCentroid(new SimplePoint_VersionStructure()
                            .withLocation(new LocationStructure()
                                    .withLatitude(new BigDecimal("9.1"))
                                    .withLongitude(new BigDecimal("71.2"))))));

        PublicationDeliveryStructure publicationDelivery = createPublicationDeliveryWithStopPlace(stopPlace);

        PublicationDeliveryStructure firstResponse = publicationDeliveryResource.importPublicationDelivery(publicationDelivery);

        StopPlace actualStopPlace = findFirstStopPlace(firstResponse);

        assertThat(actualStopPlace.getQuays()).isNotNull().as("quays should not be null");

        Quay quay = actualStopPlace.getQuays()
                .getQuayRefOrQuay().stream()
                    .filter(object -> object instanceof Quay)
                    .map(object -> ((Quay) object))
                    .findFirst()
                    .get();


        assertThat(quay.getName().getValue()).isEqualTo("quay");
        assertThat(quay.getId()).isNotNull();

    }

    /**
     * https://rutebanken.atlassian.net/browse/NRP-830
     */
    @Test
    public void handleChangesToQuaysWithoutSavingDuplicates() throws Exception {

        /**
         * StopPlace{name=Fredheimveien (no),
         *      quays=[Quay{name=Fredheimveien (no), centroid=POINT (11.142676854561447 59.83314448493502), keyValues={imported-id=Value{id=0, items=[RUT:StopArea:0229012201]}}},
         *        Quay{name=Fredheimveien (no), centroid=POINT (11.142897636770531 59.83297022041692), keyValues={imported-id=Value{id=0, items=[RUT:StopArea:0229012202]}}}],
         *    centroid=POINT (11.142676854561447 59.83314448493502),
         *    keyValues={imported-id=Value{id=0, items=[RUT:StopArea:02290122]}}}
         */
        MultilingualString name = new MultilingualString().withValue("Fredheimveien").withLang("no");


        StopPlace stopPlace1 = new StopPlace()
                .withId("RUT:StopArea:02290122")
                .withName(name)
                .withCentroid(new SimplePoint_VersionStructure()
                        .withLocation(new LocationStructure()
                                .withLatitude(new BigDecimal("59.83314448493502"))
                                .withLongitude(new BigDecimal("11.142676854561447"))))
                .withQuays(new Quays_RelStructure()
                        .withQuayRefOrQuay(new Quay()
                                        .withId("RUT:StopArea:0229012201")
                                        .withName(name)
                                        .withCentroid(new SimplePoint_VersionStructure()
                                                .withLocation(new LocationStructure()
                                                        .withLatitude(new BigDecimal("59.83314448493502"))
                                                        .withLongitude(new BigDecimal("11.142676854561447")))),
                                new Quay()
                                        .withId("RUT:StopArea:0229012202")
                                        .withName(name)
                                        .withCentroid(new SimplePoint_VersionStructure()
                                                .withLocation(new LocationStructure()
                                                        .withLatitude(new BigDecimal("59.83297022041692"))
                                                        .withLongitude(new BigDecimal("11.142897636770531"))))
                        ));

        /**
         * StopPlace{name=Fredheimveien (no),
         *      quays=[Quay{name=Fredheimveien (no), centroid=POINT (11.142902250197631 59.83304200609072), keyValues={imported-id=Value{id=0, items=[RUT:StopArea:0229012201]}}},
         *          Quay{name=Fredheimveien (no), centroid=POINT (11.14317535486387 59.832848923825956), keyValues={imported-id=Value{id=0, items=[RUT:StopArea:0229012202]}}}],
         *
         *  centroid=POINT (11.142902250197631 59.83304200609072),
         *  keyValues={imported-id=Value{id=0, items=[RUT:StopArea:02290122]}}}
         *
         */
        StopPlace stopPlace2 = new StopPlace()
                .withId("RUT:StopArea:02290122")
                .withName(name)
                .withCentroid(new SimplePoint_VersionStructure()
                        .withLocation(new LocationStructure()
                                .withLatitude(new BigDecimal("59.83304200609072"))
                                .withLongitude(new BigDecimal("11.142902250197631"))))
                .withQuays(new Quays_RelStructure()
                        .withQuayRefOrQuay(
                                new Quay()
                                        .withId("RUT:StopArea:0229012201")
                                        .withName(name)
                                        .withCentroid(new SimplePoint_VersionStructure()
                                                .withLocation(new LocationStructure()
                                                        .withLatitude(new BigDecimal("59.83304200609072"))
                                                        .withLongitude(new BigDecimal("11.142902250197631")))),
                                new Quay()
                                        .withId("RUT:StopArea:0229012202")
                                        .withName(name)
                                        .withCentroid(new SimplePoint_VersionStructure()
                                                .withLocation(new LocationStructure()
                                                        .withLatitude(new BigDecimal("59.832848923825956"))
                                                        .withLongitude(new BigDecimal("11.14317535486387"))))
                        ));

        List<PublicationDeliveryStructure> publicationDeliveryStructures = new ArrayList<>();

        publicationDeliveryStructures.add(createPublicationDeliveryWithStopPlace(stopPlace1, stopPlace2));
        publicationDeliveryStructures.add(createPublicationDeliveryWithStopPlace(stopPlace1));
        publicationDeliveryStructures.add(createPublicationDeliveryWithStopPlace(stopPlace2));
        publicationDeliveryStructures.add(createPublicationDeliveryWithStopPlace(stopPlace2, stopPlace2));

        for(PublicationDeliveryStructure pubde : publicationDeliveryStructures) {
            PublicationDeliveryStructure response = publicationDeliveryResource.importPublicationDelivery(pubde);
            StopPlace actualStopPlace = findFirstStopPlace(response);
            assertThat(actualStopPlace.getQuays().getQuayRefOrQuay()).hasSize(2);
            List<Quay> quays = extractQuays(actualStopPlace);

            long matches = quays
                    .stream()
                    .map(quay -> quay.getKeyList())
                    .flatMap(keyList -> keyList.getKeyValue().stream())
                    .map(keyValue -> keyValue.getValue())
                    .filter(value -> value.equals("RUT:StopArea:0229012202") || value.equals("RUT:StopArea:0229012201"))
                    .count();
            assertThat(matches)
                    .as("Expecting quay to contain orignal ID in key val")
                    .isEqualTo(2);

//            assertThat(quays)
//                    .extracting(Quay::getKeyList)
//                    .extracting(KeyListStructure::getKeyValue)
//                    .extracting(KeyValueStructure::getValue)
//                    .contains("RUT:StopArea:0229012202");
        }
    }

    // Import stop place StopPlace{name=Skaret (no), quays=
    // [Quay{name=Skaret (no), centroid=POINT (7.328336965528884 62.799557598196465), keyValues={imported-id=Value{id=0, items=[MOR:StopArea:1548612801]}}},
    // Quay{name=Skaret (no), keyValues={imported-id=Value{id=0, items=[MOR:StopArea:1548575301]}}}],
    // keyValues={imported-id=Value{id=0, items=[MOR:StopArea:15485753]}}}
    @Test
    public void importStopWithoutCoordinatesWithQuays1() throws Exception {

        StopPlace stopPlace = new StopPlace()
                .withId("MOR:StopArea:15485753")
                .withName(new MultilingualString().withValue("Skaret").withLang("no"))
                .withQuays(new Quays_RelStructure()
                        .withQuayRefOrQuay(new Quay()
                                .withId("MOR:StopArea:1548612801")
                                        .withName(new MultilingualString().withValue("Skaret").withLang("no"))
                                .withCentroid(new SimplePoint_VersionStructure().withLocation(new LocationStructure()
                                        .withLatitude(new BigDecimal("62.799557598196465"))
                                        .withLongitude(new BigDecimal("7.328336965528884")))),
                        new Quay()
                            .withId("MOR:StopArea:1548575301")
                                .withName(new MultilingualString().withValue("Skaret").withLang("no"))));

        PublicationDeliveryStructure publicationDelivery = createPublicationDeliveryWithStopPlace(stopPlace);

        PublicationDeliveryStructure response = publicationDeliveryResource.importPublicationDelivery(publicationDelivery);

        // Exception should not have been thrown
        StopPlace actualStopPlace = findFirstStopPlace(response);

        assertThat(actualStopPlace.getQuays()).isNotNull().as("quays should not be null");
    }

    @Test
    public void importStopPlaceWithoutCoordinates() throws Exception {

        String chouetteId = "OPP:StopArea:123";

        StopPlace stopPlace = new StopPlace()
                .withId(chouetteId)
                .withQuays(new Quays_RelStructure()
                        .withQuayRefOrQuay(new Quay()
                                .withName(new MultilingualString().withValue("quay"))));

        PublicationDeliveryStructure firstPublicationDelivery = createPublicationDeliveryWithStopPlace(stopPlace);
        PublicationDeliveryStructure response = publicationDeliveryResource.importPublicationDelivery(firstPublicationDelivery);

        StopPlace actualStopPlace = findFirstStopPlace(response);

        assertThat(actualStopPlace).isNotNull();

    }

    @Test
    public void matchStopPlaceWithoutCoordinates() throws Exception {

        String chouetteId = "HED:StopArea:321321";

        StopPlace stopPlace = new StopPlace()
                .withId(chouetteId)
                .withCentroid(new SimplePoint_VersionStructure()
                        .withLocation(new LocationStructure()
                                .withLatitude(new BigDecimal("9"))
                                .withLongitude(new BigDecimal("71"))))
                .withQuays(new Quays_RelStructure()
                        .withQuayRefOrQuay(new Quay()
                                .withName(new MultilingualString().withValue("quay"))
                                .withCentroid(new SimplePoint_VersionStructure()
                                        .withLocation(new LocationStructure()
                                                .withLatitude(new BigDecimal("9.1"))
                                                .withLongitude(new BigDecimal("71.2"))))));

        PublicationDeliveryStructure firstPublicationDelivery = createPublicationDeliveryWithStopPlace(stopPlace);
        PublicationDeliveryStructure firstResponse = publicationDeliveryResource.importPublicationDelivery(firstPublicationDelivery);
        StopPlace firstStopPlaceReturned = findFirstStopPlace(firstResponse);
        // Same ID, but no coordinates
        StopPlace stopPlaceWithoutCoordinates = new StopPlace()
                .withId(chouetteId)
                .withQuays(new Quays_RelStructure()
                        .withQuayRefOrQuay(new Quay()
                                .withName(new MultilingualString().withValue("quay"))));

        PublicationDeliveryStructure secondPublicationDelivery = createPublicationDeliveryWithStopPlace(stopPlaceWithoutCoordinates);
        PublicationDeliveryStructure secondResponse = publicationDeliveryResource.importPublicationDelivery(secondPublicationDelivery);

        StopPlace secondStopPlaceReturned = findFirstStopPlace(secondResponse);
        assertThat(secondStopPlaceReturned.getId()).isEqualTo(firstStopPlaceReturned.getId())
                .as("Expecting IDs to be the same, because the chouette ID is the same");

    }

    @Test
    public void importPublicationDeliveryAndExpectMappedIdInReturn() throws Exception {

        String originalQuayId = "XYZ:Quay:321321";

        StopPlace stopPlace = new StopPlace()
                .withId("XYZ:StopPlace:123123")
                .withCentroid(new SimplePoint_VersionStructure()
                        .withLocation(new LocationStructure()
                                .withLatitude(new BigDecimal("9"))
                                .withLongitude(new BigDecimal("71"))))
                .withQuays(new Quays_RelStructure()
                        .withQuayRefOrQuay(new Quay()
                                .withId(originalQuayId)
                                .withName(new MultilingualString().withValue("quay"))
                                .withCentroid(new SimplePoint_VersionStructure()
                                        .withLocation(new LocationStructure()
                                                .withLatitude(new BigDecimal("9.1"))
                                                .withLongitude(new BigDecimal("71.2"))))));

        PublicationDeliveryStructure publicationDelivery = createPublicationDeliveryWithStopPlace(stopPlace);
        PublicationDeliveryStructure firstResponse = publicationDeliveryResource.importPublicationDelivery(publicationDelivery);

        StopPlace actualStopPlace = findFirstStopPlace(firstResponse);

        hasOriginalId(stopPlace.getId(), actualStopPlace);

        Quay quay = actualStopPlace.getQuays()
                .getQuayRefOrQuay()
                .stream()
                .peek(object -> System.out.println(object))
                .filter(object -> object instanceof Quay)
                .map(object -> ((Quay) object))
                .peek(q-> System.out.println(q))
                .findFirst().get();

        hasOriginalId(originalQuayId, quay);
    }

    @Test
    public void importPublicationDeliveryAndExpectCertainWordsToBeRemovedFromNames() throws Exception {
        StopPlace stopPlace = new StopPlace()
                .withName(new MultilingualString().withValue("Steinerskolen Moss (Buss)"))
                .withCentroid(new SimplePoint_VersionStructure()
                        .withLocation(new LocationStructure()
                                .withLatitude(new BigDecimal("9"))
                                .withLongitude(new BigDecimal("71"))))
                .withQuays(new Quays_RelStructure()
                        .withQuayRefOrQuay(new Quay()
                                .withName(new MultilingualString().withValue("Steinerskolen Moss [tog]"))
                                .withCentroid(new SimplePoint_VersionStructure()
                                        .withLocation(new LocationStructure()
                                                .withLatitude(new BigDecimal("9.1"))
                                                .withLongitude(new BigDecimal("71.2"))))));

        PublicationDeliveryStructure publicationDelivery = createPublicationDeliveryWithStopPlace(stopPlace);
        PublicationDeliveryStructure firstResponse = publicationDeliveryResource.importPublicationDelivery(publicationDelivery);

        StopPlace actualStopPlace = findFirstStopPlace(firstResponse);

        Quay quay = actualStopPlace.getQuays()
                .getQuayRefOrQuay()
                .stream()
                .peek(object -> System.out.println(object))
                .filter(object -> object instanceof Quay)
                .map(object -> ((Quay) object))
                .peek(q-> System.out.println(q))
                .findFirst().get();

        assertThat(actualStopPlace.getName().getValue()).isEqualTo("Steinerskolen Moss");
        assertThat(quay.getName().getValue()).isEqualTo("Steinerskolen Moss");

    }

    @Test
    public void computeStopPlaceCentroid() throws Exception {

        StopPlace stopPlace = new StopPlace()
                .withId("CentroidStopPlace")
                .withCentroid(new SimplePoint_VersionStructure()
                        .withLocation(new LocationStructure()
                                .withLatitude(new BigDecimal("1"))
                                .withLongitude(new BigDecimal("2"))))
                .withQuays(new Quays_RelStructure()
                        .withQuayRefOrQuay(new Quay()
                                .withName(new MultilingualString().withValue("quay number one"))
                                .withCentroid(new SimplePoint_VersionStructure()
                                        .withLocation(new LocationStructure()
                                                .withLatitude(new BigDecimal("10"))
                                                .withLongitude(new BigDecimal("20")))),
                                new Quay()
                                        .withName(new MultilingualString().withValue("quay number two"))
                                        .withCentroid(new SimplePoint_VersionStructure()
                                                .withLocation(new LocationStructure()
                                                        .withLatitude(new BigDecimal("12"))
                                                        .withLongitude(new BigDecimal("22"))))));

        PublicationDeliveryStructure publicationDelivery = createPublicationDeliveryWithStopPlace(stopPlace);

        PublicationDeliveryStructure firstResponse = publicationDeliveryResource.importPublicationDelivery(publicationDelivery);

        StopPlace actualStopPlace = findFirstStopPlace(firstResponse);

        assertThat(actualStopPlace.getCentroid().getLocation().getLongitude().doubleValue()).isEqualTo(21.0);
        assertThat(actualStopPlace.getCentroid().getLocation().getLatitude().doubleValue()).isEqualTo(11.0);
    }


    @Test
    public void receivePublicationDelivery() throws Exception {

        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<PublicationDelivery version=\"1.0\" xmlns=\"http://www.netex.org.uk/netex\"\n" +
                "                     xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "                     xsi:schemaLocation=\"http://www.netex.org.uk/netex ../../xsd/NeTEx_publication.xsd\">\n" +
                "    <PublicationTimestamp>2016-05-18T15:00:00.0Z</PublicationTimestamp>\n" +
                "    <ParticipantRef>NHR</ParticipantRef>\n" +
                "    <dataObjects>\n" +
                "        <SiteFrame version=\"01\" id=\"nhr:sf:1\">\n" +
                "            <stopPlaces>\n" +
                "                <StopPlace version=\"01\" created=\"2016-04-21T09:00:00.0Z\" id=\"nhr:sp:1\">\n" +
                "                    <Name lang=\"no-NO\">Krokstien</Name>\n" +
                "                    <Centroid>\n" +
                "                        <Location srsName=\"WGS84\">\n" +
                "                            <Longitude>10.8577903</Longitude>\n" +
                "                            <Latitude>59.910579</Latitude>\n" +
                "                        </Location>\n" +
                "                    </Centroid>\n" +
                "                    <TransportMode>bus</TransportMode>\n" +
                "                    <StopPlaceType>onstreetBus</StopPlaceType>\n" +
                "                    <quays>\n" +
                "                        <Quay version=\"01\" created=\"2016-04-21T09:01:00.0Z\" id=\"nhr:sp:1:q:1\">\n" +
                "                            <Centroid>\n" +
                "                                <Location srsName=\"WGS84\">\n" +
                "                                    <Longitude>10.8577903</Longitude>\n" +
                "                                    <Latitude>59.910579</Latitude>\n" +
                "                                </Location>\n" +
                "                            </Centroid>\n" +
                "                            <Covered>outdoors</Covered>\n" +
                "                            <Lighting>wellLit</Lighting>\n" +
                "                            <QuayType>busStop</QuayType>\n" +
                "                        </Quay>\n" +
                "                    </quays>\n" +
                "                </StopPlace>\n" +
                "            </stopPlaces>\n" +
                "        </SiteFrame>\n" +
                "    </dataObjects>\n" +
                "</PublicationDelivery>";


        InputStream stream = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));


        Response response = publicationDeliveryResource.receivePublicationDelivery(stream);

        assertThat(response.getStatus()).isEqualTo(200);
    }

    /**
     * Make stop places exported in publication deliveries are valid according to the xsd.
     * It should be validated when streaming out.
     */
    @Test
    public void exportStopPlaces() throws JAXBException, IOException, SAXException {

        // Import stop to make sure we have something to export, allthough other tests might have populated the test database.
        StopPlace stopPlace = new StopPlace()
                .withName(new MultilingualString().withValue("Østre gravlund"))
                .withCentroid(new SimplePoint_VersionStructure()
                        .withLocation(new LocationStructure()
                                .withLatitude(new BigDecimal("59.914353"))
                                .withLongitude(new BigDecimal("10.806387"))));

        PublicationDeliveryStructure publicationDelivery = createPublicationDeliveryWithStopPlace(stopPlace);
        publicationDeliveryResource.importPublicationDelivery(publicationDelivery);


        Response response = publicationDeliveryResource.exportStopPlaces(1, 10, "Østre gravlund", null, null, null);
        assertThat(response.getStatus()).isEqualTo(200);

        StreamingOutput streamingOutput = (StreamingOutput) response.getEntity();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        streamingOutput.write(byteArrayOutputStream);
        System.out.println(byteArrayOutputStream.toString());
    }

    /**
     * Partially copied from https://github.com/rutebanken/netex-norway-examples/blob/master/examples/stops/BasicStopPlace_example.xml
     */
    @Test
    public void importBasicStopPlace() throws JAXBException, IOException, SAXException {

        String xml = "<PublicationDelivery\n" +
                "\tversion=\"1.0\"\n" +
                "\txmlns=\"http://www.netex.org.uk/netex\"\n" +
                "\txmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "\txsi:schemaLocation=\"http://www.netex.org.uk/netex ../../xsd/NeTEx_publication.xsd\">\n" +
                "\t<!-- Når denne dataleveransen ble generert -->\n" +
                "\t<PublicationTimestamp>2016-05-18T15:00:00.0Z</PublicationTimestamp>\n" +
                "\t<ParticipantRef>NHR</ParticipantRef>\n" +
                "\t<dataObjects>\n" +
                "\t\t<SiteFrame version=\"any\" id=\"nhr:sf:1\">\n" +
                "\t\t\t<stopPlaces>\n" +
                "\t\t\t\t<!--===Stop=== -->\n" +
                "\t\t\t\t<!-- Merk: Holdeplass-ID vil komme fra Holdeplassregisteret -->\n" +
                "\t\t\t\t<StopPlace version=\"any\" created=\"2016-04-21T09:00:00.0Z\" id=\"nhr:sp:2\">\n" +
                "\t\t\t\t\t<Name lang=\"no-NO\">Krokstien</Name>\n" +
                "\t\t\t\t</StopPlace>\n" +
                "\t\t\t</stopPlaces>\n" +
                "\t\t</SiteFrame>\n" +
                "\t</dataObjects>\n" +
                "</PublicationDelivery>\n" +
                "\n";

        InputStream stream = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));


        Response response = publicationDeliveryResource.receivePublicationDelivery(stream);
        assertThat(response.getStatus()).isEqualTo(200);

        StreamingOutput streamingOutput = (StreamingOutput) response.getEntity();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        streamingOutput.write(byteArrayOutputStream);
        System.out.println(byteArrayOutputStream.toString());
    }


    private PublicationDeliveryStructure createPublicationDeliveryWithStopPlace(StopPlace... stopPlace) {
        SiteFrame siteFrame = new SiteFrame();
        siteFrame.setId(UUID.randomUUID().toString());
        siteFrame.withStopPlaces(new StopPlacesInFrame_RelStructure()
                .withStopPlace(stopPlace));

        PublicationDeliveryStructure publicationDelivery = new PublicationDeliveryStructure()
                .withDataObjects(new PublicationDeliveryStructure.DataObjects()
                        .withCompositeFrameOrCommonFrame(new ObjectFactory().createSiteFrame(siteFrame)));

        return publicationDelivery;
    }

    private void hasOriginalId(String expectedId, DataManagedObjectStructure object) {
        assertThat(object).isNotNull();
        assertThat(object.getKeyList()).isNotNull();
        List<String> list = object.getKeyList().getKeyValue()
                .stream()
                .peek(keyValueStructure -> System.out.println(keyValueStructure))
                .filter(keyValueStructure -> keyValueStructure.getKey().equals(ORIGINAL_ID_KEY))
                .map(keyValueStructure -> keyValueStructure.getValue())
                .collect(Collectors.toList());
        assertThat(list).hasSize(1);
        String originalIdString = list.get(0);
        assertThat(originalIdString).isNotEmpty();
        assertThat(originalIdString).isEqualTo(expectedId);
    }

    private List<StopPlace> extractStopPlace(PublicationDeliveryStructure publicationDeliveryStructure) {
        return  publicationDeliveryStructure.getDataObjects()
                .getCompositeFrameOrCommonFrame()
                .stream()
                .map(JAXBElement::getValue)
                .filter(commonVersionFrameStructure -> commonVersionFrameStructure instanceof SiteFrame)
                .flatMap(commonVersionFrameStructure -> ((SiteFrame) commonVersionFrameStructure).getStopPlaces().getStopPlace().stream())
                .collect(toList());
    }

    private List<Quay> extractQuays(StopPlace stopPlace) {
        return stopPlace
                .getQuays()
                .getQuayRefOrQuay()
                .stream()
                .filter(object -> object instanceof Quay)
                .map(object -> ((Quay) object))
                .collect(toList());
    }

    private StopPlace findFirstStopPlace(PublicationDeliveryStructure publicationDeliveryStructure) {
        return publicationDeliveryStructure.getDataObjects()
                .getCompositeFrameOrCommonFrame()
                .stream()
                .map(JAXBElement::getValue)
                .filter(commonVersionFrameStructure -> commonVersionFrameStructure instanceof SiteFrame)
                .flatMap(commonVersionFrameStructure -> ((SiteFrame) commonVersionFrameStructure).getStopPlaces().getStopPlace().stream())
                .findFirst().get();
    }
}