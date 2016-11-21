//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.05 at 07:41:01 PM CET 
//


package org.rutebanken.tiamat.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Type for a PURPOSE OF GROUPING.
 * 
 * <p>Java class for PurposeOfGrouping_ValueStructure complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PurposeOfGrouping_ValueStructure">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.netex.org.uk/netex}TypeOfValue_VersionStructure">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.netex.org.uk/netex}PurposeOfGroupingGroup"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PurposeOfGrouping_ValueStructure", propOrder = {
    "classes",
    "typeOfEntity"
})
@XmlSeeAlso({
    PurposeOfGrouping.class
})
public class PurposeOfGrouping_ValueStructure
    extends TypeOfValue_VersionStructure
{

    protected ClassRefs_RelStructure classes;
    protected JAXBElement<? extends TypeOfEntity_VersionStructure> typeOfEntity;

    /**
     * Gets the value of the classes property.
     * 
     * @return
     *     possible object is
     *     {@link ClassRefs_RelStructure }
     *     
     */
    public ClassRefs_RelStructure getClasses() {
        return classes;
    }

    /**
     * Sets the value of the classes property.
     * 
     * @param value
     *     allowed object is
     *     {@link ClassRefs_RelStructure }
     *     
     */
    public void setClasses(ClassRefs_RelStructure value) {
        this.classes = value;
    }

    /**
     * Gets the value of the typeOfEntity property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link TypeOfLine }{@code >}
     *     {@link JAXBElement }{@code <}{@link TypeOfFrame }{@code >}
     *     {@link JAXBElement }{@code <}{@link TypeOfActivation }{@code >}
     *     {@link JAXBElement }{@code <}{@link TypeOfZone }{@code >}
     *     {@link JAXBElement }{@code <}{@link TypeOfEntity_VersionStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link TypeOfJourneyPattern }{@code >}
     *     {@link JAXBElement }{@code <}{@link TypeOfOrganisationPart }{@code >}
     *     {@link JAXBElement }{@code <}{@link TypeOfOrganisation }{@code >}
     *     {@link JAXBElement }{@code <}{@link TypeOfLinkSequence }{@code >}
     *     {@link JAXBElement }{@code <}{@link TypeOfPoint }{@code >}
     *     {@link JAXBElement }{@code <}{@link TypeOfPassengerInformationEquipment }{@code >}
     *     {@link JAXBElement }{@code <}{@link TypeOfTransfer }{@code >}
     *     {@link JAXBElement }{@code <}{@link TypeOfFacility }{@code >}
     *     {@link JAXBElement }{@code <}{@link TypeOfDeliveryVariant }{@code >}
     *     {@link JAXBElement }{@code <}{@link TypeOfOperation }{@code >}
     *     {@link JAXBElement }{@code <}{@link TypeOfFeature }{@code >}
     *     {@link JAXBElement }{@code <}{@link TypeOfTimeDemandType }{@code >}
     *     {@link JAXBElement }{@code <}{@link TypeOfEquipment }{@code >}
     *     {@link JAXBElement }{@code <}{@link TypeOfLink }{@code >}
     *     {@link JAXBElement }{@code <}{@link TypeOfProjection }{@code >}
     *     {@link JAXBElement }{@code <}{@link TypeOfNotice }{@code >}
     *     {@link JAXBElement }{@code <}{@link TypeOfPlace }{@code >}
     *     
     */
    public JAXBElement<? extends TypeOfEntity_VersionStructure> getTypeOfEntity() {
        return typeOfEntity;
    }

    /**
     * Sets the value of the typeOfEntity property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link TypeOfLine }{@code >}
     *     {@link JAXBElement }{@code <}{@link TypeOfFrame }{@code >}
     *     {@link JAXBElement }{@code <}{@link TypeOfActivation }{@code >}
     *     {@link JAXBElement }{@code <}{@link TypeOfZone }{@code >}
     *     {@link JAXBElement }{@code <}{@link TypeOfEntity_VersionStructure }{@code >}
     *     {@link JAXBElement }{@code <}{@link TypeOfJourneyPattern }{@code >}
     *     {@link JAXBElement }{@code <}{@link TypeOfOrganisationPart }{@code >}
     *     {@link JAXBElement }{@code <}{@link TypeOfOrganisation }{@code >}
     *     {@link JAXBElement }{@code <}{@link TypeOfLinkSequence }{@code >}
     *     {@link JAXBElement }{@code <}{@link TypeOfPoint }{@code >}
     *     {@link JAXBElement }{@code <}{@link TypeOfPassengerInformationEquipment }{@code >}
     *     {@link JAXBElement }{@code <}{@link TypeOfTransfer }{@code >}
     *     {@link JAXBElement }{@code <}{@link TypeOfFacility }{@code >}
     *     {@link JAXBElement }{@code <}{@link TypeOfDeliveryVariant }{@code >}
     *     {@link JAXBElement }{@code <}{@link TypeOfOperation }{@code >}
     *     {@link JAXBElement }{@code <}{@link TypeOfFeature }{@code >}
     *     {@link JAXBElement }{@code <}{@link TypeOfTimeDemandType }{@code >}
     *     {@link JAXBElement }{@code <}{@link TypeOfEquipment }{@code >}
     *     {@link JAXBElement }{@code <}{@link TypeOfLink }{@code >}
     *     {@link JAXBElement }{@code <}{@link TypeOfProjection }{@code >}
     *     {@link JAXBElement }{@code <}{@link TypeOfNotice }{@code >}
     *     {@link JAXBElement }{@code <}{@link TypeOfPlace }{@code >}
     *     
     */
    public void setTypeOfEntity(JAXBElement<? extends TypeOfEntity_VersionStructure> value) {
        this.typeOfEntity = value;
    }

}
