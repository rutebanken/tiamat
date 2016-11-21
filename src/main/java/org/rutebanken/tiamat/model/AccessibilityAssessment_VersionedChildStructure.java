//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.05 at 07:41:01 PM CET 
//


package org.rutebanken.tiamat.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import java.util.List;


/**
 * Type for ACCESSIBILITY ASSESSMENT.
 * 
 * <p>Java class for AccessibilityAssessment_VersionedChildStructure complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccessibilityAssessment_VersionedChildStructure">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.netex.org.uk/netex}VersionedChildStructure">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.netex.org.uk/netex}AccessibilityAssessmentGroup"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccessibilityAssessment_VersionedChildStructure", propOrder = {
    "mobilityImpairedAccess",
    "limitations",
    "suitabilities",
    "comment"
})
@XmlSeeAlso({
    AccessibilityAssessment.class
})
@MappedSuperclass
public class AccessibilityAssessment_VersionedChildStructure
    extends VersionedChildStructure
{

    @XmlSchemaType(name = "string")
    @Enumerated(EnumType.STRING)
    protected LimitationStatusEnumeration mobilityImpairedAccess;

    @OneToMany(cascade = CascadeType.ALL)
    protected List<AccessibilityLimitation> limitations;

    @Transient
    protected Suitabilities_RelStructure suitabilities;

    @Transient
    protected MultilingualStringEntity comment;

    /**
     * Gets the value of the mobilityImpairedAccess property.
     * 
     * @return
     *     possible object is
     *     {@link LimitationStatusEnumeration }
     *     
     */
    public LimitationStatusEnumeration getMobilityImpairedAccess() {
        return mobilityImpairedAccess;
    }

    /**
     * Sets the value of the mobilityImpairedAccess property.
     * 
     * @param value
     *     allowed object is
     *     {@link LimitationStatusEnumeration }
     *     
     */
    public void setMobilityImpairedAccess(LimitationStatusEnumeration value) {
        this.mobilityImpairedAccess = value;
    }

    /**
     * Gets the value of the limitations property.
     * 
     * @return
     *     possible object is
     *     {@link List<AccessibilityAssessment> }
     *     
     */
    public List<AccessibilityLimitation> getLimitations() {
        return limitations;
    }

    /**
     * Sets the value of the limitations property.
     * 
     * @param value
     *     allowed object is
     *     {@link List<AccessibilityAssessment> }
     *     
     */
    public void setLimitations(List<AccessibilityLimitation> value) {
        this.limitations = value;
    }

    /**
     * Gets the value of the suitabilities property.
     * 
     * @return
     *     possible object is
     *     {@link Suitabilities_RelStructure }
     *     
     */
    public Suitabilities_RelStructure getSuitabilities() {
        return suitabilities;
    }

    /**
     * Sets the value of the suitabilities property.
     * 
     * @param value
     *     allowed object is
     *     {@link Suitabilities_RelStructure }
     *     
     */
    public void setSuitabilities(Suitabilities_RelStructure value) {
        this.suitabilities = value;
    }

    /**
     * Gets the value of the comment property.
     * 
     * @return
     *     possible object is
     *     {@link MultilingualStringEntity }
     *     
     */
    public MultilingualStringEntity getComment() {
        return comment;
    }

    /**
     * Sets the value of the comment property.
     * 
     * @param value
     *     allowed object is
     *     {@link MultilingualStringEntity }
     *     
     */
    public void setComment(MultilingualStringEntity value) {
        this.comment = value;
    }

}
