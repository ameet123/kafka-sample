//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7-b41 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.07.18 at 10:55:53 AM EDT 
//


package org.ameet.kafkasample.model.ihg.reservation;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FactItmType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FactItmType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FactLn" maxOccurs="unbounded" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="RB USD 10.  XP USD 10.  10 PCT TA CMSN."/>
 *               &lt;enumeration value="NO PETS ALLOWED.PENALTY OF USD 300 WILL"/>
 *               &lt;enumeration value="BE APPLIED AT CK OUT IF PETS ARE FOUND"/>
 *               &lt;enumeration value="ON PROPERTY. SERVICE ANIMALS ALLOWED."/>
 *               &lt;enumeration value="KENNELS LOCATED NEARBY."/>
 *               &lt;enumeration value="MINIMUM CHECK IN AGE IS 21."/>
 *               &lt;enumeration value="100 PCT NONSMOKING HOTEL."/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FactItmType", propOrder = {
    "factLn"
})
public class FactItmType {

    @XmlElement(name = "FactLn")
    protected List<String> factLn;

    /**
     * Gets the value of the factLn property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the factLn property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFactLn().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getFactLn() {
        if (factLn == null) {
            factLn = new ArrayList<String>();
        }
        return this.factLn;
    }

}
