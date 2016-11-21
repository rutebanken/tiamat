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
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Type for BOOKING ARRANGEMENTs.
 * 
 * <p>Java class for BookingArrangementsStructure complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BookingArrangementsStructure">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;group ref="{http://www.netex.org.uk/netex}BookingArrangementsGroup"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BookingArrangementsStructure", propOrder = {
    "bookingMethods",
    "bookingAccess",
    "bookWhen",
    "buyWhen",
    "latestBookingTime",
    "minimumBookingPeriod",
    "bookingUrl",
    "bookingNote"
})
public class BookingArrangementsStructure {

    @XmlSchemaType(name = "anySimpleType")
    protected List<BookingMethodEnumeration> bookingMethods;
    @XmlSchemaType(name = "string")
    protected BookingAccessEnumeration bookingAccess;
    @XmlSchemaType(name = "normalizedString")
    protected PurchaseWhenEnumeration bookWhen;
    @XmlSchemaType(name = "anySimpleType")
    protected List<PurchaseMomentEnumeration> buyWhen;
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar latestBookingTime;
    protected Duration minimumBookingPeriod;
    @XmlSchemaType(name = "anyURI")
    protected String bookingUrl;
    protected MultilingualStringEntity bookingNote;

    /**
     * Gets the value of the bookingMethods property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bookingMethods property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBookingMethods().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BookingMethodEnumeration }
     * 
     * 
     */
    public List<BookingMethodEnumeration> getBookingMethods() {
        if (bookingMethods == null) {
            bookingMethods = new ArrayList<BookingMethodEnumeration>();
        }
        return this.bookingMethods;
    }

    /**
     * Gets the value of the bookingAccess property.
     * 
     * @return
     *     possible object is
     *     {@link BookingAccessEnumeration }
     *     
     */
    public BookingAccessEnumeration getBookingAccess() {
        return bookingAccess;
    }

    /**
     * Sets the value of the bookingAccess property.
     * 
     * @param value
     *     allowed object is
     *     {@link BookingAccessEnumeration }
     *     
     */
    public void setBookingAccess(BookingAccessEnumeration value) {
        this.bookingAccess = value;
    }

    /**
     * Gets the value of the bookWhen property.
     * 
     * @return
     *     possible object is
     *     {@link PurchaseWhenEnumeration }
     *     
     */
    public PurchaseWhenEnumeration getBookWhen() {
        return bookWhen;
    }

    /**
     * Sets the value of the bookWhen property.
     * 
     * @param value
     *     allowed object is
     *     {@link PurchaseWhenEnumeration }
     *     
     */
    public void setBookWhen(PurchaseWhenEnumeration value) {
        this.bookWhen = value;
    }

    /**
     * Gets the value of the buyWhen property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the buyWhen property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBuyWhen().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PurchaseMomentEnumeration }
     * 
     * 
     */
    public List<PurchaseMomentEnumeration> getBuyWhen() {
        if (buyWhen == null) {
            buyWhen = new ArrayList<PurchaseMomentEnumeration>();
        }
        return this.buyWhen;
    }

    /**
     * Gets the value of the latestBookingTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLatestBookingTime() {
        return latestBookingTime;
    }

    /**
     * Sets the value of the latestBookingTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLatestBookingTime(XMLGregorianCalendar value) {
        this.latestBookingTime = value;
    }

    /**
     * Gets the value of the minimumBookingPeriod property.
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getMinimumBookingPeriod() {
        return minimumBookingPeriod;
    }

    /**
     * Sets the value of the minimumBookingPeriod property.
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setMinimumBookingPeriod(Duration value) {
        this.minimumBookingPeriod = value;
    }

    /**
     * Gets the value of the bookingUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBookingUrl() {
        return bookingUrl;
    }

    /**
     * Sets the value of the bookingUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBookingUrl(String value) {
        this.bookingUrl = value;
    }

    /**
     * Gets the value of the bookingNote property.
     * 
     * @return
     *     possible object is
     *     {@link MultilingualStringEntity }
     *     
     */
    public MultilingualStringEntity getBookingNote() {
        return bookingNote;
    }

    /**
     * Sets the value of the bookingNote property.
     * 
     * @param value
     *     allowed object is
     *     {@link MultilingualStringEntity }
     *     
     */
    public void setBookingNote(MultilingualStringEntity value) {
        this.bookingNote = value;
    }

}
