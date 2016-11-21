//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.05 at 07:41:01 PM CET 
//


package org.rutebanken.tiamat.model;

import java.math.BigInteger;
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
 * Type for allowed combinations of ACCOMMODATION.
 * 
 * <p>Java class for Accommodation_VersionedChildStructure complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Accommodation_VersionedChildStructure">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.netex.org.uk/netex}VersionedChildStructure">
 *       &lt;sequence>
 *         &lt;element name="Name" type="{http://www.netex.org.uk/netex}MultilingualStringEntity" minOccurs="0"/>
 *         &lt;element ref="{http://www.netex.org.uk/netex}FareClass" minOccurs="0"/>
 *         &lt;element ref="{http://www.netex.org.uk/netex}ClassOfUseRef" minOccurs="0"/>
 *         &lt;element ref="{http://www.netex.org.uk/netex}AccommodationFacility" minOccurs="0"/>
 *         &lt;element ref="{http://www.netex.org.uk/netex}CouchetteFacility" minOccurs="0"/>
 *         &lt;element name="MaximumNumberOfBerths" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element ref="{http://www.netex.org.uk/netex}BerthFacility" minOccurs="0"/>
 *         &lt;element name="ShowerFacility" type="{http://www.netex.org.uk/netex}SanitaryFacilityEnumeration" minOccurs="0"/>
 *         &lt;element name="ToiletFacility" type="{http://www.netex.org.uk/netex}SanitaryFacilityEnumeration" minOccurs="0"/>
 *         &lt;element ref="{http://www.netex.org.uk/netex}GenderLimitation" minOccurs="0"/>
 *         &lt;element ref="{http://www.netex.org.uk/netex}NuisanceFacilityList" minOccurs="0"/>
 *         &lt;element ref="{http://www.netex.org.uk/netex}PassengerCommsFacilityList" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Accommodation_VersionedChildStructure", propOrder = {
    "name",
    "fareClass",
    "classOfUseRef",
    "accommodationFacility",
    "couchetteFacility",
    "maximumNumberOfBerths",
    "berthFacility",
    "showerFacility",
    "toiletFacility",
    "genderLimitation",
    "nuisanceFacilityList",
    "passengerCommsFacilityList"
})
@XmlSeeAlso({
    Accommodation.class
})
public class Accommodation_VersionedChildStructure
    extends VersionedChildStructure
{

    protected MultilingualStringEntity name;

    protected ClassOfUseRef classOfUseRef;
    @XmlSchemaType(name = "NMTOKEN")
    protected AccommodationFacilityEnumeration accommodationFacility;
    @XmlSchemaType(name = "NMTOKEN")
    protected CouchetteFacilityEnumeration couchetteFacility;
    protected BigInteger maximumNumberOfBerths;
    @XmlSchemaType(name = "NMTOKEN")
    protected BerthFacilityEnumeration berthFacility;
    @XmlSchemaType(name = "string")
    protected SanitaryFacilityEnumeration showerFacility;
    @XmlSchemaType(name = "string")
    protected SanitaryFacilityEnumeration toiletFacility;
    @XmlSchemaType(name = "normalizedString")
    protected GenderLimitationEnumeration genderLimitation;
    @XmlSchemaType(name = "anySimpleType")
    protected List<NuisanceFacilityEnumeration> nuisanceFacilityList;
    @XmlSchemaType(name = "anySimpleType")
    protected List<PassengerCommsFacilityEnumeration> passengerCommsFacilityList;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link MultilingualStringEntity }
     *     
     */
    public MultilingualStringEntity getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link MultilingualStringEntity }
     *     
     */
    public void setName(MultilingualStringEntity value) {
        this.name = value;
    }

    /**
     * Gets the value of the classOfUseRef property.
     * 
     * @return
     *     possible object is
     *     {@link ClassOfUseRef }
     *     
     */
    public ClassOfUseRef getClassOfUseRef() {
        return classOfUseRef;
    }

    /**
     * Sets the value of the classOfUseRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link ClassOfUseRef }
     *     
     */
    public void setClassOfUseRef(ClassOfUseRef value) {
        this.classOfUseRef = value;
    }

    /**
     * Type of ACCOMMODATION. . Default is seating.
     * 
     * @return
     *     possible object is
     *     {@link AccommodationFacilityEnumeration }
     *     
     */
    public AccommodationFacilityEnumeration getAccommodationFacility() {
        return accommodationFacility;
    }

    /**
     * Sets the value of the accommodationFacility property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccommodationFacilityEnumeration }
     *     
     */
    public void setAccommodationFacility(AccommodationFacilityEnumeration value) {
        this.accommodationFacility = value;
    }

    /**
     * Type of Couchette.
     * 
     * @return
     *     possible object is
     *     {@link CouchetteFacilityEnumeration }
     *     
     */
    public CouchetteFacilityEnumeration getCouchetteFacility() {
        return couchetteFacility;
    }

    /**
     * Sets the value of the couchetteFacility property.
     * 
     * @param value
     *     allowed object is
     *     {@link CouchetteFacilityEnumeration }
     *     
     */
    public void setCouchetteFacility(CouchetteFacilityEnumeration value) {
        this.couchetteFacility = value;
    }

    /**
     * Gets the value of the maximumNumberOfBerths property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaximumNumberOfBerths() {
        return maximumNumberOfBerths;
    }

    /**
     * Sets the value of the maximumNumberOfBerths property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaximumNumberOfBerths(BigInteger value) {
        this.maximumNumberOfBerths = value;
    }

    /**
     * Classification of BERTH FACILITY.
     * 
     * @return
     *     possible object is
     *     {@link BerthFacilityEnumeration }
     *     
     */
    public BerthFacilityEnumeration getBerthFacility() {
        return berthFacility;
    }

    /**
     * Sets the value of the berthFacility property.
     * 
     * @param value
     *     allowed object is
     *     {@link BerthFacilityEnumeration }
     *     
     */
    public void setBerthFacility(BerthFacilityEnumeration value) {
        this.berthFacility = value;
    }

    /**
     * Gets the value of the showerFacility property.
     * 
     * @return
     *     possible object is
     *     {@link SanitaryFacilityEnumeration }
     *     
     */
    public SanitaryFacilityEnumeration getShowerFacility() {
        return showerFacility;
    }

    /**
     * Sets the value of the showerFacility property.
     * 
     * @param value
     *     allowed object is
     *     {@link SanitaryFacilityEnumeration }
     *     
     */
    public void setShowerFacility(SanitaryFacilityEnumeration value) {
        this.showerFacility = value;
    }

    /**
     * Gets the value of the toiletFacility property.
     * 
     * @return
     *     possible object is
     *     {@link SanitaryFacilityEnumeration }
     *     
     */
    public SanitaryFacilityEnumeration getToiletFacility() {
        return toiletFacility;
    }

    /**
     * Sets the value of the toiletFacility property.
     * 
     * @param value
     *     allowed object is
     *     {@link SanitaryFacilityEnumeration }
     *     
     */
    public void setToiletFacility(SanitaryFacilityEnumeration value) {
        this.toiletFacility = value;
    }

    /**
     * Gets the value of the genderLimitation property.
     * 
     * @return
     *     possible object is
     *     {@link GenderLimitationEnumeration }
     *     
     */
    public GenderLimitationEnumeration getGenderLimitation() {
        return genderLimitation;
    }

    /**
     * Sets the value of the genderLimitation property.
     * 
     * @param value
     *     allowed object is
     *     {@link GenderLimitationEnumeration }
     *     
     */
    public void setGenderLimitation(GenderLimitationEnumeration value) {
        this.genderLimitation = value;
    }

    /**
     * Gets the value of the nuisanceFacilityList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nuisanceFacilityList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNuisanceFacilityList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NuisanceFacilityEnumeration }
     * 
     * 
     */
    public List<NuisanceFacilityEnumeration> getNuisanceFacilityList() {
        if (nuisanceFacilityList == null) {
            nuisanceFacilityList = new ArrayList<NuisanceFacilityEnumeration>();
        }
        return this.nuisanceFacilityList;
    }

    /**
     * Gets the value of the passengerCommsFacilityList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the passengerCommsFacilityList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPassengerCommsFacilityList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PassengerCommsFacilityEnumeration }
     * 
     * 
     */
    public List<PassengerCommsFacilityEnumeration> getPassengerCommsFacilityList() {
        if (passengerCommsFacilityList == null) {
            passengerCommsFacilityList = new ArrayList<PassengerCommsFacilityEnumeration>();
        }
        return this.passengerCommsFacilityList;
    }

}
