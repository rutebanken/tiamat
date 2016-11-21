//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.05 at 07:41:01 PM CET 
//


package org.rutebanken.tiamat.model;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Type for a LEFT LUGGAGE SERVICE.
 * 
 * <p>Java class for LeftLuggageService_VersionStructure complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LeftLuggageService_VersionStructure">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.netex.org.uk/netex}CustomerService_VersionStructure">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.netex.org.uk/netex}LeftLuggageServiceGroup"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LeftLuggageService_VersionStructure", propOrder = {
    "counterService",
    "selfServiceLockers",
    "feePerBag",
    "lockerFee",
    "maximumBagWidth",
    "maximumBagHeight",
    "maximumBagDepth"
})
@XmlSeeAlso({
    LeftLuggageService.class
})
public class LeftLuggageService_VersionStructure
    extends CustomerService_VersionStructure
{

    protected Boolean counterService;
    protected Boolean selfServiceLockers;
    protected Boolean feePerBag;
    protected Boolean lockerFee;
    protected BigDecimal maximumBagWidth;
    protected BigDecimal maximumBagHeight;
    protected BigDecimal maximumBagDepth;

    /**
     * Gets the value of the counterService property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCounterService() {
        return counterService;
    }

    /**
     * Sets the value of the counterService property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCounterService(Boolean value) {
        this.counterService = value;
    }

    /**
     * Gets the value of the selfServiceLockers property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSelfServiceLockers() {
        return selfServiceLockers;
    }

    /**
     * Sets the value of the selfServiceLockers property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSelfServiceLockers(Boolean value) {
        this.selfServiceLockers = value;
    }

    /**
     * Gets the value of the feePerBag property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFeePerBag() {
        return feePerBag;
    }

    /**
     * Sets the value of the feePerBag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFeePerBag(Boolean value) {
        this.feePerBag = value;
    }

    /**
     * Gets the value of the lockerFee property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isLockerFee() {
        return lockerFee;
    }

    /**
     * Sets the value of the lockerFee property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLockerFee(Boolean value) {
        this.lockerFee = value;
    }

    /**
     * Gets the value of the maximumBagWidth property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMaximumBagWidth() {
        return maximumBagWidth;
    }

    /**
     * Sets the value of the maximumBagWidth property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMaximumBagWidth(BigDecimal value) {
        this.maximumBagWidth = value;
    }

    /**
     * Gets the value of the maximumBagHeight property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMaximumBagHeight() {
        return maximumBagHeight;
    }

    /**
     * Sets the value of the maximumBagHeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMaximumBagHeight(BigDecimal value) {
        this.maximumBagHeight = value;
    }

    /**
     * Gets the value of the maximumBagDepth property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMaximumBagDepth() {
        return maximumBagDepth;
    }

    /**
     * Sets the value of the maximumBagDepth property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMaximumBagDepth(BigDecimal value) {
        this.maximumBagDepth = value;
    }

}
