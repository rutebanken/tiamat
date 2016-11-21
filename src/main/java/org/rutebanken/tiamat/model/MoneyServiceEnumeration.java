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
 * <p>Java class for MoneyServiceEnumeration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="MoneyServiceEnumeration">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="cashMachine"/>
 *     &lt;enumeration value="bank"/>
 *     &lt;enumeration value="insurance"/>
 *     &lt;enumeration value="bureauDeChange"/>
 *     &lt;enumeration value="customsOffice"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MoneyServiceEnumeration")
public enum MoneyServiceEnumeration {

    CASH_MACHINE("cashMachine"),
    BANK("bank"),
    INSURANCE("insurance"),
    BUREAU_DE_CHANGE("bureauDeChange"),
    CUSTOMS_OFFICE("customsOffice");
    private final String value;

    MoneyServiceEnumeration(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MoneyServiceEnumeration fromValue(String v) {
        for (MoneyServiceEnumeration c: MoneyServiceEnumeration.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
