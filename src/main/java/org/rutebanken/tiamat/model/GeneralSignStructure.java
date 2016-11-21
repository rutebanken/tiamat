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
 * Type for an GENERAL SIGN.
 * 
 * <p>Java class for GeneralSignStructure complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GeneralSignStructure">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.netex.org.uk/netex}SignEquipment_VersionStructure">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.netex.org.uk/netex}GeneralSignGroup"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GeneralSignStructure", propOrder = {
    "content",
    "signContentType"
})
@XmlSeeAlso({
    GeneralSign.class
})
public class GeneralSignStructure
    extends SignEquipment_VersionStructure
{

    protected MultilingualStringEntity content;
    @XmlSchemaType(name = "string")
    protected SignContentEnumeration signContentType;

    /**
     * Gets the value of the content property.
     * 
     * @return
     *     possible object is
     *     {@link MultilingualStringEntity }
     *     
     */
    public MultilingualStringEntity getContent() {
        return content;
    }

    /**
     * Sets the value of the content property.
     * 
     * @param value
     *     allowed object is
     *     {@link MultilingualStringEntity }
     *     
     */
    public void setContent(MultilingualStringEntity value) {
        this.content = value;
    }

    /**
     * Gets the value of the signContentType property.
     * 
     * @return
     *     possible object is
     *     {@link SignContentEnumeration }
     *     
     */
    public SignContentEnumeration getSignContentType() {
        return signContentType;
    }

    /**
     * Sets the value of the signContentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link SignContentEnumeration }
     *     
     */
    public void setSignContentType(SignContentEnumeration value) {
        this.signContentType = value;
    }

}
