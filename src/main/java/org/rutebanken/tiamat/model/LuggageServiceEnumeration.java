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
 * <p>Java class for LuggageServiceEnumeration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="LuggageServiceEnumeration">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}normalizedString">
 *     &lt;enumeration value="leftLuggage"/>
 *     &lt;enumeration value="porterage"/>
 *     &lt;enumeration value="freeTrolleys"/>
 *     &lt;enumeration value="paidTrolleys"/>
 *     &lt;enumeration value="other"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "LuggageServiceEnumeration")
public enum LuggageServiceEnumeration {

    LEFT_LUGGAGE("leftLuggage"),
    PORTERAGE("porterage"),
    FREE_TROLLEYS("freeTrolleys"),
    PAID_TROLLEYS("paidTrolleys"),
    OTHER("other");
    private final String value;

    LuggageServiceEnumeration(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static LuggageServiceEnumeration fromValue(String v) {
        for (LuggageServiceEnumeration c: LuggageServiceEnumeration.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
