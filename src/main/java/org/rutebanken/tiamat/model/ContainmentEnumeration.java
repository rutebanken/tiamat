//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.05 at 07:41:01 PM CET 
//


package org.rutebanken.tiamat.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ContainmentEnumeration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ContainmentEnumeration">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *     &lt;enumeration value="inline"/>
 *     &lt;enumeration value="byReference"/>
 *     &lt;enumeration value="byVersionedReference"/>
 *     &lt;enumeration value="both"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ContainmentEnumeration")
public enum ContainmentEnumeration {


    /**
     * This is a definition of a new entity.
     * 
     */
    INLINE("inline"),

    /**
     * This is a deletion of an existing entity.
     * 
     */
    BY_REFERENCE("byReference"),
    BY_VERSIONED_REFERENCE("byVersionedReference"),
    BOTH("both");
    private final String value;

    ContainmentEnumeration(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ContainmentEnumeration fromValue(String v) {
        for (ContainmentEnumeration c: ContainmentEnumeration.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
