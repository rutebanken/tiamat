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
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Type for RETAIL SERVICE.
 * 
 * <p>Java class for RetailService_VersionStructure complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RetailService_VersionStructure">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.netex.org.uk/netex}LocalService_VersionStructure">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.netex.org.uk/netex}RetailServiceGroup"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RetailService_VersionStructure", propOrder = {
    "serviceList"
})
@XmlSeeAlso({
    RetailService.class
})
public class RetailService_VersionStructure
    extends LocalService_VersionStructure
{

    @XmlSchemaType(name = "anySimpleType")
    protected List<RetailServiceEnumeration> serviceList;

    /**
     * Gets the value of the serviceList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the serviceList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServiceList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RetailServiceEnumeration }
     * 
     * 
     */
    public List<RetailServiceEnumeration> getServiceList() {
        if (serviceList == null) {
            serviceList = new ArrayList<RetailServiceEnumeration>();
        }
        return this.serviceList;
    }

}
