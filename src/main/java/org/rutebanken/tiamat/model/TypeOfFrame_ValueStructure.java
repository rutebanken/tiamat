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
 * Type for a TYPE OF VERSION FRAME.
 * 
 * <p>Java class for TypeOfFrame_ValueStructure complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TypeOfFrame_ValueStructure">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.netex.org.uk/netex}TypeOfEntity_VersionStructure">
 *       &lt;group ref="{http://www.netex.org.uk/netex}TypeOfFrameGroup"/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TypeOfFrame_ValueStructure", propOrder = {
    "typeOfValidityRef",
    "frameClassRef",
    "classes",
    "includes",
    "locatingSystemRef",
    "modificationSet"
})
@XmlSeeAlso({
    TypeOfFrame.class
})
public class TypeOfFrame_ValueStructure
    extends TypeOfEntity_VersionStructure
{

    protected TypeOfValidityRefStructure typeOfValidityRef;
    protected ClassRefStructure frameClassRef;
    protected ClassesInRepository_RelStructure classes;
    protected TypesOfFrame_RelStructure includes;
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String locatingSystemRef;
    @XmlSchemaType(name = "NMTOKEN")
    protected ModificationSetEnumeration modificationSet;

    /**
     * Gets the value of the typeOfValidityRef property.
     * 
     * @return
     *     possible object is
     *     {@link TypeOfValidityRefStructure }
     *     
     */
    public TypeOfValidityRefStructure getTypeOfValidityRef() {
        return typeOfValidityRef;
    }

    /**
     * Sets the value of the typeOfValidityRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeOfValidityRefStructure }
     *     
     */
    public void setTypeOfValidityRef(TypeOfValidityRefStructure value) {
        this.typeOfValidityRef = value;
    }

    /**
     * Gets the value of the frameClassRef property.
     * 
     * @return
     *     possible object is
     *     {@link ClassRefStructure }
     *     
     */
    public ClassRefStructure getFrameClassRef() {
        return frameClassRef;
    }

    /**
     * Sets the value of the frameClassRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link ClassRefStructure }
     *     
     */
    public void setFrameClassRef(ClassRefStructure value) {
        this.frameClassRef = value;
    }

    /**
     * Gets the value of the classes property.
     * 
     * @return
     *     possible object is
     *     {@link ClassesInRepository_RelStructure }
     *     
     */
    public ClassesInRepository_RelStructure getClasses() {
        return classes;
    }

    /**
     * Sets the value of the classes property.
     * 
     * @param value
     *     allowed object is
     *     {@link ClassesInRepository_RelStructure }
     *     
     */
    public void setClasses(ClassesInRepository_RelStructure value) {
        this.classes = value;
    }

    /**
     * Gets the value of the includes property.
     * 
     * @return
     *     possible object is
     *     {@link TypesOfFrame_RelStructure }
     *     
     */
    public TypesOfFrame_RelStructure getIncludes() {
        return includes;
    }

    /**
     * Sets the value of the includes property.
     * 
     * @param value
     *     allowed object is
     *     {@link TypesOfFrame_RelStructure }
     *     
     */
    public void setIncludes(TypesOfFrame_RelStructure value) {
        this.includes = value;
    }

    /**
     * Gets the value of the locatingSystemRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocatingSystemRef() {
        return locatingSystemRef;
    }

    /**
     * Sets the value of the locatingSystemRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocatingSystemRef(String value) {
        this.locatingSystemRef = value;
    }

    /**
     * Gets the value of the modificationSet property.
     * 
     * @return
     *     possible object is
     *     {@link ModificationSetEnumeration }
     *     
     */
    public ModificationSetEnumeration getModificationSet() {
        return modificationSet;
    }

    /**
     * Sets the value of the modificationSet property.
     * 
     * @param value
     *     allowed object is
     *     {@link ModificationSetEnumeration }
     *     
     */
    public void setModificationSet(ModificationSetEnumeration value) {
        this.modificationSet = value;
    }

}
