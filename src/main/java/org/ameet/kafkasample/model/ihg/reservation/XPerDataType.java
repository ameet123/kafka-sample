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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for XPerDataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="XPerDataType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Amount" type="{http://ameet.org/reserv/envelope}AmountType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="XPerChgStart" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "XPerDataType", propOrder = {
    "amount"
})
public class XPerDataType {

    @XmlElement(name = "Amount", required = true)
    protected AmountType amount;
    @XmlAttribute(name = "XPerChgStart")
    protected String xPerChgStart;

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link AmountType }
     *     
     */
    public AmountType getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link AmountType }
     *     
     */
    public void setAmount(AmountType value) {
        this.amount = value;
    }

    /**
     * Gets the value of the xPerChgStart property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXPerChgStart() {
        return xPerChgStart;
    }

    /**
     * Sets the value of the xPerChgStart property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXPerChgStart(String value) {
        this.xPerChgStart = value;
    }

}
