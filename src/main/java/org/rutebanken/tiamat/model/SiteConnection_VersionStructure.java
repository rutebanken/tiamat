//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.05 at 07:41:01 PM CET 
//


package org.rutebanken.tiamat.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Type for a SITE CONNECTION.
 * 
 * <p>Java class for SiteConnection_VersionStructure complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SiteConnection_VersionStructure">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.netex.org.uk/netex}Transfer_VersionStructure">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.netex.org.uk/netex}SiteConnectionGroup"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SiteConnection_VersionStructure", propOrder = {
    "from",
    "to",
    "navigationPaths"
})
@XmlSeeAlso({
    SiteConnection.class
})
public class SiteConnection_VersionStructure
    extends Transfer_VersionStructure
{

    protected SiteConnectionEndStructure from;
    protected SiteConnectionEndStructure to;
    protected NavigationPaths_RelStructure navigationPaths;

    /**
     * Gets the value of the from property.
     * 
     * @return
     *     possible object is
     *     {@link SiteConnectionEndStructure }
     *     
     */
    public SiteConnectionEndStructure getFrom() {
        return from;
    }

    /**
     * Sets the value of the from property.
     * 
     * @param value
     *     allowed object is
     *     {@link SiteConnectionEndStructure }
     *     
     */
    public void setFrom(SiteConnectionEndStructure value) {
        this.from = value;
    }

    /**
     * Gets the value of the to property.
     * 
     * @return
     *     possible object is
     *     {@link SiteConnectionEndStructure }
     *     
     */
    public SiteConnectionEndStructure getTo() {
        return to;
    }

    /**
     * Sets the value of the to property.
     * 
     * @param value
     *     allowed object is
     *     {@link SiteConnectionEndStructure }
     *     
     */
    public void setTo(SiteConnectionEndStructure value) {
        this.to = value;
    }

    /**
     * Gets the value of the navigationPaths property.
     * 
     * @return
     *     possible object is
     *     {@link NavigationPaths_RelStructure }
     *     
     */
    public NavigationPaths_RelStructure getNavigationPaths() {
        return navigationPaths;
    }

    /**
     * Sets the value of the navigationPaths property.
     * 
     * @param value
     *     allowed object is
     *     {@link NavigationPaths_RelStructure }
     *     
     */
    public void setNavigationPaths(NavigationPaths_RelStructure value) {
        this.navigationPaths = value;
    }

}
