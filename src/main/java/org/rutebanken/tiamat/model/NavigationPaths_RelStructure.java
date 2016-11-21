//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.06 at 10:37:32 AM CET 
//


package org.rutebanken.tiamat.model;

import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.MetaValue;
import org.hibernate.mapping.Property;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * Type for a list of NAVIGATION PATHs.
 * 
 * <p>Java class for navigationPaths_RelStructure complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="navigationPaths_RelStructure">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.netex.org.uk/netex}containmentAggregationStructure">
 *       &lt;choice maxOccurs="unbounded">
 *         &lt;element ref="{http://www.netex.org.uk/netex}NavigationPathRef"/>
 *         &lt;element ref="{http://www.netex.org.uk/netex}NavigationPath"/>
 *       &lt;/choice>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "navigationPaths_RelStructure", propOrder = {
    "navigationPathRefOrNavigationPath"
})
@Entity
public class NavigationPaths_RelStructure
    extends ContainmentAggregationStructure
{

    })
    @ManyToAny(metaColumn = @Column(name = "item_type"))
    @AnyMetaDef(
            idType = "integer", metaType = "string",
            metaValues = {
                    @MetaValue( targetEntity = NavigationPathRefStructure.class, value="navigation_path_ref_structure"),
                    @MetaValue( targetEntity = NavigationPath.class, value="navigation_path")
            }
    )
    @JoinTable(
            name = "navigationPath",
            joinColumns = @JoinColumn( name = "id" ),
            inverseJoinColumns = @JoinColumn( name = "path_id" )
    )
    protected List<Property> navigationPathRefOrNavigationPath;

    /**
     * Gets the value of the navigationPathRefOrNavigationPath property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the navigationPathRefOrNavigationPath property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNavigationPathRefOrNavigationPath().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NavigationPathRefStructure }
     * {@link NavigationPath }
     * 
     * 
     */
    public List<Property> getNavigationPathRefOrNavigationPath() {
        if (navigationPathRefOrNavigationPath == null) {
            navigationPathRefOrNavigationPath = new ArrayList<Property>();
        }
        return this.navigationPathRefOrNavigationPath;
    }

}
