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
 * Type for WIRE ELEMENT.
 * 
 * <p>Java class for WireElement_VersionStructure complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WireElement_VersionStructure">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.netex.org.uk/netex}InfrastructureLink_VersionStructure">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.netex.org.uk/netex}WireElementGroup"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WireElement_VersionStructure", propOrder = {
    "fromPointRef",
    "toPointRef"
})
@XmlSeeAlso({
    WireElement.class
})
public class WireElement_VersionStructure
    extends InfrastructureLink_VersionStructure
{

    protected WirePointRefStructure fromPointRef;
    protected WirePointRefStructure toPointRef;

    /**
     * Gets the value of the fromPointRef property.
     * 
     * @return
     *     possible object is
     *     {@link WirePointRefStructure }
     *     
     */
    public WirePointRefStructure getFromPointRef() {
        return fromPointRef;
    }

    /**
     * Sets the value of the fromPointRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link WirePointRefStructure }
     *     
     */
    public void setFromPointRef(WirePointRefStructure value) {
        this.fromPointRef = value;
    }

    /**
     * Gets the value of the toPointRef property.
     * 
     * @return
     *     possible object is
     *     {@link WirePointRefStructure }
     *     
     */
    public WirePointRefStructure getToPointRef() {
        return toPointRef;
    }

    /**
     * Sets the value of the toPointRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link WirePointRefStructure }
     *     
     */
    public void setToPointRef(WirePointRefStructure value) {
        this.toPointRef = value;
    }

}
