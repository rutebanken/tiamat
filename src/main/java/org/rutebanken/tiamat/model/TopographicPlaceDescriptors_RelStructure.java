//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.06 at 10:37:32 AM CET 
//


package org.rutebanken.tiamat.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Type for containment of TOPOGRAPHIC PLACE DESCRIPTORs.
 * 
 * <p>Java class for topographicPlaceDescriptors_RelStructure complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="topographicPlaceDescriptors_RelStructure">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.netex.org.uk/netex}strictContainmentAggregationStructure">
 *       &lt;sequence>
 *         &lt;element name="TopographicPlaceDescriptor" type="{http://www.netex.org.uk/netex}TopographicPlaceDescriptor_VersionedChildStructure" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "topographicPlaceDescriptors_RelStructure", propOrder = {
    "topographicPlaceDescriptor"
})
@XmlSeeAlso({
    AlternativeDescriptors.class
})
public class TopographicPlaceDescriptors_RelStructure
    extends StrictContainmentAggregationStructure
{

    protected List<TopographicPlaceDescriptor_VersionedChildStructure> topographicPlaceDescriptor;

    /**
     * Gets the value of the topographicPlaceDescriptor property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the topographicPlaceDescriptor property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTopographicPlaceDescriptor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TopographicPlaceDescriptor_VersionedChildStructure }
     * 
     * 
     */
    public List<TopographicPlaceDescriptor_VersionedChildStructure> getTopographicPlaceDescriptor() {
        if (topographicPlaceDescriptor == null) {
            topographicPlaceDescriptor = new ArrayList<TopographicPlaceDescriptor_VersionedChildStructure>();
        }
        return this.topographicPlaceDescriptor;
    }

}
