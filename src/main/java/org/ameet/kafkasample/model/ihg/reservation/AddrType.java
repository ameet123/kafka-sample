//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.07.14 at 02:21:50 PM EDT 
//


package org.ameet.kafkasample.model.ihg.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>Java class for AddrType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AddrType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="AddTy" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="AddNm" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="AddLn1" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="AddLn2" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="AddLn3" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddrType", propOrder = {
    "value"
})
public class AddrType {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "AddTy")
    protected String addTy;
    @XmlAttribute(name = "AddNm")
    protected String addNm;
    @XmlAttribute(name = "AddLn1")
    protected String addLn1;
    @XmlAttribute(name = "AddLn2")
    protected String addLn2;
    @XmlAttribute(name = "AddLn3")
    protected String addLn3;

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
     * Gets the value of the addTy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddTy() {
        return addTy;
    }

    /**
     * Sets the value of the addTy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddTy(String value) {
        this.addTy = value;
    }

    /**
     * Gets the value of the addNm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddNm() {
        return addNm;
    }

    /**
     * Sets the value of the addNm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddNm(String value) {
        this.addNm = value;
    }

    /**
     * Gets the value of the addLn1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddLn1() {
        return addLn1;
    }

    /**
     * Sets the value of the addLn1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddLn1(String value) {
        this.addLn1 = value;
    }

    /**
     * Gets the value of the addLn2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddLn2() {
        return addLn2;
    }

    /**
     * Sets the value of the addLn2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddLn2(String value) {
        this.addLn2 = value;
    }

    /**
     * Gets the value of the addLn3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddLn3() {
        return addLn3;
    }

    /**
     * Sets the value of the addLn3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddLn3(String value) {
        this.addLn3 = value;
    }

}
