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
 * <p>Java class for OrigBkgDataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrigBkgDataType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="RqstrID" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="AgtSine" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="AgtInit" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="AgtDtCd" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrigBkgDataType", propOrder = {
    "value"
})
public class OrigBkgDataType {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "RqstrID")
    protected String rqstrID;
    @XmlAttribute(name = "AgtSine")
    protected String agtSine;
    @XmlAttribute(name = "AgtInit")
    protected String agtInit;
    @XmlAttribute(name = "AgtDtCd")
    protected String agtDtCd;

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
     * Gets the value of the rqstrID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRqstrID() {
        return rqstrID;
    }

    /**
     * Sets the value of the rqstrID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRqstrID(String value) {
        this.rqstrID = value;
    }

    /**
     * Gets the value of the agtSine property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgtSine() {
        return agtSine;
    }

    /**
     * Sets the value of the agtSine property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgtSine(String value) {
        this.agtSine = value;
    }

    /**
     * Gets the value of the agtInit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgtInit() {
        return agtInit;
    }

    /**
     * Sets the value of the agtInit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgtInit(String value) {
        this.agtInit = value;
    }

    /**
     * Gets the value of the agtDtCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgtDtCd() {
        return agtDtCd;
    }

    /**
     * Sets the value of the agtDtCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgtDtCd(String value) {
        this.agtDtCd = value;
    }

}
