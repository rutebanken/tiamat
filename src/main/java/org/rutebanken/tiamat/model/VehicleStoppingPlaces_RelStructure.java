//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.06 at 10:37:32 AM CET 
//


package org.rutebanken.tiamat.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * Type for a list of VEHICLE STOPPING PLACEs.
 * 
 * <p>Java class for vehicleStoppingPlaces_RelStructure complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="vehicleStoppingPlaces_RelStructure">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.netex.org.uk/netex}containmentAggregationStructure">
 *       &lt;choice maxOccurs="unbounded">
 *         &lt;element ref="{http://www.netex.org.uk/netex}VehicleStoppingPlaceRef"/>
 *         &lt;element ref="{http://www.netex.org.uk/netex}VehicleStoppingPlace"/>
 *       &lt;/choice>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "vehicleStoppingPlaces_RelStructure", propOrder = {
    "vehicleStoppingPlaceRefOrVehicleStoppingPlace"
})
@Entity
public class VehicleStoppingPlaces_RelStructure
    extends ContainmentAggregationStructure
{

    }   )
    @ElementCollection(targetClass = VehicleStoppingPlaceRefStructure.class)
    protected List<VehicleStoppingPlaceRefStructure> vehicleStoppingPlaceRefOrVehicleStoppingPlace;

    /**
     * Gets the value of the vehicleStoppingPlaceRefOrVehicleStoppingPlace property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the vehicleStoppingPlaceRefOrVehicleStoppingPlace property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVehicleStoppingPlaceRefOrVehicleStoppingPlace().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VehicleStoppingPlaceRefStructure }
     * {@link VehicleStoppingPlace }
     * 
     * 
     */
    public List<VehicleStoppingPlaceRefStructure> getVehicleStoppingPlaceRefOrVehicleStoppingPlace() {
        if (vehicleStoppingPlaceRefOrVehicleStoppingPlace == null) {
            vehicleStoppingPlaceRefOrVehicleStoppingPlace = new ArrayList<VehicleStoppingPlaceRefStructure>();
        }
        return this.vehicleStoppingPlaceRefOrVehicleStoppingPlace;
    }

}
