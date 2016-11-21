//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.05 at 07:41:01 PM CET 
//


package org.rutebanken.tiamat.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * Collection of TOPOGRAPHIC PLACEs.
 * 
 * <p>Java class for topographicPlaces_RelStructure complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="topographicPlaces_RelStructure">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.netex.org.uk/netex}containmentAggregationStructure">
 *       &lt;sequence maxOccurs="unbounded">
 *         &lt;element ref="{http://www.netex.org.uk/netex}TopographicPlaceRef"/>
 *         &lt;element ref="{http://www.netex.org.uk/netex}TopographicPlace"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "topographicPlaces_RelStructure", propOrder = {
    "topographicPlaceRefAndTopographicPlace"
})
public class TopographicPlaces_RelStructure
    extends ContainmentAggregationStructure
{

    })
    protected List<Object> topographicPlaceRefAndTopographicPlace;

    /**
     * Gets the value of the topographicPlaceRefAndTopographicPlace property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the topographicPlaceRefAndTopographicPlace property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTopographicPlaceRefAndTopographicPlace().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TopographicPlaceRefStructure }
     * {@link TopographicPlace }
     * 
     * 
     */
    public List<Object> getTopographicPlaceRefAndTopographicPlace() {
        if (topographicPlaceRefAndTopographicPlace == null) {
            topographicPlaceRefAndTopographicPlace = new ArrayList<Object>();
        }
        return this.topographicPlaceRefAndTopographicPlace;
    }

}
