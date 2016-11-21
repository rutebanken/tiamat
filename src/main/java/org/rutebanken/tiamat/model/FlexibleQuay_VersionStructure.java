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
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Type for FLEXIBLE QUAY.
 * 
 * <p>Java class for FlexibleQuay_VersionStructure complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FlexibleQuay_VersionStructure">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.netex.org.uk/netex}Place_VersionStructure">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.netex.org.uk/netex}FlexibleQuayGroup"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FlexibleQuay_VersionStructure", propOrder = {
    "nameSuffix",
    "alternativeNames",
    "flexibleStopPlaceRef",
    "transportMode",
    "boardingUse",
    "alightingUse",
    "publicCode"
})
@XmlSeeAlso({
    FlexibleQuay.class,
    FlexibleArea_VersionStructure.class,
    HailAndRideArea_VersionStructure.class
})
public class FlexibleQuay_VersionStructure
    extends Place_VersionStructure
{

    protected MultilingualStringEntity nameSuffix;
    protected AlternativeNames_RelStructure alternativeNames;
    protected FlexibleStopPlaceRefStructure flexibleStopPlaceRef;
    @XmlSchemaType(name = "NMTOKEN")
    protected VehicleModeEnumeration transportMode;
    protected Boolean boardingUse;
    protected Boolean alightingUse;
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String publicCode;
    
    /**
     * Gets the value of the nameSuffix property.
     * 
     * @return
     *     possible object is
     *     {@link MultilingualStringEntity }
     *     
     */
    public MultilingualStringEntity getNameSuffix() {
        return nameSuffix;
    }

    /**
     * Sets the value of the nameSuffix property.
     * 
     * @param value
     *     allowed object is
     *     {@link MultilingualStringEntity }
     *     
     */
    public void setNameSuffix(MultilingualStringEntity value) {
        this.nameSuffix = value;
    }

    /**
     * Gets the value of the alternativeNames property.
     * 
     * @return
     *     possible object is
     *     {@link AlternativeNames_RelStructure }
     *     
     */
    public AlternativeNames_RelStructure getAlternativeNames() {
        return alternativeNames;
    }

    /**
     * Sets the value of the alternativeNames property.
     * 
     * @param value
     *     allowed object is
     *     {@link AlternativeNames_RelStructure }
     *     
     */
    public void setAlternativeNames(AlternativeNames_RelStructure value) {
        this.alternativeNames = value;
    }

    /**
     * Gets the value of the flexibleStopPlaceRef property.
     * 
     * @return
     *     possible object is
     *     {@link FlexibleStopPlaceRefStructure }
     *     
     */
    public FlexibleStopPlaceRefStructure getFlexibleStopPlaceRef() {
        return flexibleStopPlaceRef;
    }

    /**
     * Sets the value of the flexibleStopPlaceRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link FlexibleStopPlaceRefStructure }
     *     
     */
    public void setFlexibleStopPlaceRef(FlexibleStopPlaceRefStructure value) {
        this.flexibleStopPlaceRef = value;
    }

    /**
     * Gets the value of the transportMode property.
     * 
     * @return
     *     possible object is
     *     {@link VehicleModeEnumeration }
     *     
     */
    public VehicleModeEnumeration getTransportMode() {
        return transportMode;
    }

    /**
     * Sets the value of the transportMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link VehicleModeEnumeration }
     *     
     */
    public void setTransportMode(VehicleModeEnumeration value) {
        this.transportMode = value;
    }

    /**
     * Gets the value of the boardingUse property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isBoardingUse() {
        return boardingUse;
    }

    /**
     * Sets the value of the boardingUse property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBoardingUse(Boolean value) {
        this.boardingUse = value;
    }

    /**
     * Gets the value of the alightingUse property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAlightingUse() {
        return alightingUse;
    }

    /**
     * Sets the value of the alightingUse property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAlightingUse(Boolean value) {
        this.alightingUse = value;
    }

    /**
     * Gets the value of the publicCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPublicCode() {
        return publicCode;
    }

    /**
     * Sets the value of the publicCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPublicCode(String value) {
        this.publicCode = value;
    }

}
