//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.05 at 07:41:01 PM CET 
//


package org.rutebanken.tiamat.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;


/**
 * Type for containment in frame of TYPE OF VALUEs.
 * 
 * <p>Java class for typesOfValueInFrame_RelStructure complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="typesOfValueInFrame_RelStructure">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.netex.org.uk/netex}containmentAggregationStructure">
 *       &lt;choice maxOccurs="unbounded">
 *         &lt;element ref="{http://www.netex.org.uk/netex}ValueSet"/>
 *         &lt;element ref="{http://www.netex.org.uk/netex}TypeOfValue"/>
 *       &lt;/choice>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "typesOfValueInFrame_RelStructure", propOrder = {
    "valueSetOrTypeOfValue"
})
public class TypesOfValueInFrame_RelStructure
    extends ContainmentAggregationStructure
{

    })
    protected List<JAXBElement<? extends DataManagedObjectStructure>> valueSetOrTypeOfValue;

    /**
     * Gets the value of the valueSetOrTypeOfValue property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the valueSetOrTypeOfValue property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValueSetOrTypeOfValue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link PointOfInterestClassification }{@code >}
     * {@link JAXBElement }{@code <}{@link TypeOfLine }{@code >}
     * {@link JAXBElement }{@code <}{@link TypeOfFrame }{@code >}
     * {@link JAXBElement }{@code <}{@link TypeOfZone }{@code >}
     * {@link JAXBElement }{@code <}{@link TypeOfEntity_VersionStructure }{@code >}
     * {@link JAXBElement }{@code <}{@link TypeOfJourneyPattern }{@code >}
     * {@link JAXBElement }{@code <}{@link TypeOfOrganisation }{@code >}
     * {@link JAXBElement }{@code <}{@link TypeOfLinkSequence }{@code >}
     * {@link JAXBElement }{@code <}{@link TypeOfPassengerInformationEquipment }{@code >}
     * {@link JAXBElement }{@code <}{@link TypeOfFacility }{@code >}
     * {@link JAXBElement }{@code <}{@link TypeOfValue_VersionStructure }{@code >}
     * {@link JAXBElement }{@code <}{@link TypeOfEquipment }{@code >}
     * {@link JAXBElement }{@code <}{@link DataSource }{@code >}
     * {@link JAXBElement }{@code <}{@link TypeOfNotice }{@code >}
     * {@link JAXBElement }{@code <}{@link TypeOfPlace }{@code >}
     * {@link JAXBElement }{@code <}{@link PurposeOfEquipmentProfile }{@code >}
     * {@link JAXBElement }{@code <}{@link TypeOfActivation }{@code >}
     * {@link JAXBElement }{@code <}{@link PurposeOfGrouping }{@code >}
     * {@link JAXBElement }{@code <}{@link TypeOfOrganisationPart }{@code >}
     * {@link JAXBElement }{@code <}{@link TypeOfPoint }{@code >}
     * {@link JAXBElement }{@code <}{@link TypeOfTransfer }{@code >}
     * {@link JAXBElement }{@code <}{@link ClassOfUse }{@code >}
     * {@link JAXBElement }{@code <}{@link TypeOfDeliveryVariant }{@code >}
     * {@link JAXBElement }{@code <}{@link TypeOfOperation }{@code >}
     * {@link JAXBElement }{@code <}{@link TypeOfServiceFeature }{@code >}
     * {@link JAXBElement }{@code <}{@link TypeOfFeature }{@code >}
     * {@link JAXBElement }{@code <}{@link TypeOfTimeDemandType }{@code >}
     * {@link JAXBElement }{@code <}{@link Branding }{@code >}
     * {@link JAXBElement }{@code <}{@link TypeOfLink }{@code >}
     * {@link JAXBElement }{@code <}{@link Submode }{@code >}
     * {@link JAXBElement }{@code <}{@link ValueSet }{@code >}
     * {@link JAXBElement }{@code <}{@link TypeOfProjection }{@code >}
     * {@link JAXBElement }{@code <}{@link TypeOfValidity }{@code >}
     * {@link JAXBElement }{@code <}{@link OpenTransportMode }{@code >}
     * 
     * 
     */
    public List<JAXBElement<? extends DataManagedObjectStructure>> getValueSetOrTypeOfValue() {
        if (valueSetOrTypeOfValue == null) {
            valueSetOrTypeOfValue = new ArrayList<JAXBElement<? extends DataManagedObjectStructure>>();
        }
        return this.valueSetOrTypeOfValue;
    }

}
