package org.rutebanken.tiamat.repository;

import com.vividsolutions.jts.geom.Envelope;
import org.rutebanken.tiamat.model.Parking;
import org.rutebanken.tiamat.model.ParkingTypeEnumeration;

import java.util.Set;

public interface ParkingRepositoryCustom extends DataManagedObjectStructureRepository<Parking>  {

    String findByKeyValue(String key, Set<String> value);

    String findNearbyParking(Envelope boundingBox, String value, ParkingTypeEnumeration parkingType);
}
