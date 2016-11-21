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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Type for TRANSFER RESTRICTION.
 * 
 * <p>Java class for TransferRestriction_VersionStructure complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TransferRestriction_VersionStructure">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.netex.org.uk/netex}Assignment_VersionStructure">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.netex.org.uk/netex}TransferRestrictionGroup"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransferRestriction_VersionStructure", propOrder = {
    "typeOfTransferRef",
    "bothWays",
    "restrictionType",
    "fromPointRef",
    "toPointRef"
})
@XmlSeeAlso({
    TransferRestriction.class
})
public class TransferRestriction_VersionStructure
    extends Assignment_VersionStructure
{

    protected TypeOfTransferRefStructure typeOfTransferRef;
    protected Boolean bothWays;
    @XmlSchemaType(name = "string")
    protected TransferConstraintTypeEnumeration restrictionType;
    protected ScheduledStopPointRefStructure fromPointRef;
    protected ScheduledStopPointRefStructure toPointRef;

    /**
     * Gets the value of the typeOfTransferRef property.
     * 
     * @return
     *     possible object is
     *     {@link TypeOfTransferRefStructure }
     *     
     */
    public TypeOfTransferRefStructure getTypeOfTransferRef() {
        return typeOfTransferRef;
    }

    /**
     * Sets the value of the typeOfTransferRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeOfTransferRefStructure }
     *     
     */
    public void setTypeOfTransferRef(TypeOfTransferRefStructure value) {
        this.typeOfTransferRef = value;
    }

    /**
     * Gets the value of the bothWays property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isBothWays() {
        return bothWays;
    }

    /**
     * Sets the value of the bothWays property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBothWays(Boolean value) {
        this.bothWays = value;
    }

    /**
     * Gets the value of the restrictionType property.
     * 
     * @return
     *     possible object is
     *     {@link TransferConstraintTypeEnumeration }
     *     
     */
    public TransferConstraintTypeEnumeration getRestrictionType() {
        return restrictionType;
    }

    /**
     * Sets the value of the restrictionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransferConstraintTypeEnumeration }
     *     
     */
    public void setRestrictionType(TransferConstraintTypeEnumeration value) {
        this.restrictionType = value;
    }

    /**
     * Gets the value of the fromPointRef property.
     * 
     * @return
     *     possible object is
     *     {@link ScheduledStopPointRefStructure }
     *     
     */
    public ScheduledStopPointRefStructure getFromPointRef() {
        return fromPointRef;
    }

    /**
     * Sets the value of the fromPointRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link ScheduledStopPointRefStructure }
     *     
     */
    public void setFromPointRef(ScheduledStopPointRefStructure value) {
        this.fromPointRef = value;
    }

    /**
     * Gets the value of the toPointRef property.
     * 
     * @return
     *     possible object is
     *     {@link ScheduledStopPointRefStructure }
     *     
     */
    public ScheduledStopPointRefStructure getToPointRef() {
        return toPointRef;
    }

    /**
     * Sets the value of the toPointRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link ScheduledStopPointRefStructure }
     *     
     */
    public void setToPointRef(ScheduledStopPointRefStructure value) {
        this.toPointRef = value;
    }

}
