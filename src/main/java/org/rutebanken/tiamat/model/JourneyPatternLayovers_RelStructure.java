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
import javax.xml.bind.annotation.XmlType;


/**
 * Type for a list of JOURNEY PATTERN LAYOVERs.
 * 
 * <p>Java class for journeyPatternLayovers_RelStructure complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="journeyPatternLayovers_RelStructure">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.netex.org.uk/netex}strictContainmentAggregationStructure">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.netex.org.uk/netex}JourneyPatternLayover" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "journeyPatternLayovers_RelStructure", propOrder = {
    "journeyPatternLayover"
})
public class JourneyPatternLayovers_RelStructure
    extends StrictContainmentAggregationStructure
{

    protected List<JourneyPatternLayover> journeyPatternLayover;

    /**
     * JOURNEY PATTERN LAYOVER for a specified TIME DEMAND TYPE.Gets the value of the journeyPatternLayover property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the journeyPatternLayover property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getJourneyPatternLayover().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JourneyPatternLayover }
     * 
     * 
     */
    public List<JourneyPatternLayover> getJourneyPatternLayover() {
        if (journeyPatternLayover == null) {
            journeyPatternLayover = new ArrayList<JourneyPatternLayover>();
        }
        return this.journeyPatternLayover;
    }

}
