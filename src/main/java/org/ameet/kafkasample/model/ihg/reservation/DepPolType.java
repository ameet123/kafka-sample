//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7-b41 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.07.18 at 10:55:53 AM EDT 
//


package org.ameet.kafkasample.model.ihg.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>Java class for DepPolType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DepPolType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="Start" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="AtBkng" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="BasisTy" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DepPolType", propOrder = {
    "value"
})
public class DepPolType {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "Start")
    protected String start;
    @XmlAttribute(name = "AtBkng")
    protected String atBkng;
    @XmlAttribute(name = "BasisTy")
    protected String basisTy;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the start property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStart() {
        return start;
    }

    /**
     * Sets the value of the start property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStart(String value) {
        this.start = value;
    }

    /**
     * Gets the value of the atBkng property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAtBkng() {
        return atBkng;
    }

    /**
     * Sets the value of the atBkng property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAtBkng(String value) {
        this.atBkng = value;
    }

    /**
     * Gets the value of the basisTy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBasisTy() {
        return basisTy;
    }

    /**
     * Sets the value of the basisTy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBasisTy(String value) {
        this.basisTy = value;
    }

}
