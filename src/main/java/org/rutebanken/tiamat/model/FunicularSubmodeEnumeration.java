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
 * <p>Java class for FunicularSubmodeEnumeration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="FunicularSubmodeEnumeration">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *     &lt;enumeration value="unknown"/>
 *     &lt;enumeration value="funicular"/>
 *     &lt;enumeration value="streetCableCar"/>
 *     &lt;enumeration value="allFunicularServices"/>
 *     &lt;enumeration value="undefinedFunicular"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "FunicularSubmodeEnumeration")
public enum FunicularSubmodeEnumeration {

    UNKNOWN("unknown"),
    FUNICULAR("funicular"),
    STREET_CABLE_CAR("streetCableCar"),
    ALL_FUNICULAR_SERVICES("allFunicularServices"),
    UNDEFINED_FUNICULAR("undefinedFunicular");
    private final String value;

    FunicularSubmodeEnumeration(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static FunicularSubmodeEnumeration fromValue(String v) {
        for (FunicularSubmodeEnumeration c: FunicularSubmodeEnumeration.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
