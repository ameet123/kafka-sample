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
 * <p>Java class for CustomerType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CustomerType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="Name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="SurName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="AdultSur" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ChildSur" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomerType", propOrder = {
    "value"
})
public class CustomerType {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "Name")
    protected String name;
    @XmlAttribute(name = "SurName")
    protected String surName;
    @XmlAttribute(name = "AdultSur")
    protected String adultSur;
    @XmlAttribute(name = "ChildSur")
    protected String childSur;

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
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the surName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSurName() {
        return surName;
    }

    /**
     * Sets the value of the surName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSurName(String value) {
        this.surName = value;
    }

    /**
     * Gets the value of the adultSur property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdultSur() {
        return adultSur;
    }

    /**
     * Sets the value of the adultSur property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdultSur(String value) {
        this.adultSur = value;
    }

    /**
     * Gets the value of the childSur property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChildSur() {
        return childSur;
    }

    /**
     * Sets the value of the childSur property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChildSur(String value) {
        this.childSur = value;
    }

}
