//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7-b41 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.07.18 at 10:55:53 AM EDT 
//


package org.ameet.kafkasample.model.ihg.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SuccessRSType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SuccessRSType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BookCNF" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="HtlRes" type="{http://ameet.org/reserv/envelope}HtlResType"/>
 *         &lt;element name="LastUpdate" type="{http://ameet.org/reserv/envelope}LastUpdateType"/>
 *         &lt;element name="OrigBkgData" type="{http://ameet.org/reserv/envelope}OrigBkgDataType"/>
 *         &lt;element name="MultAccom" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Customers" type="{http://ameet.org/reserv/envelope}CustomersType"/>
 *         &lt;element name="Addrs" type="{http://ameet.org/reserv/envelope}AddrsType"/>
 *         &lt;element name="Phones" type="{http://ameet.org/reserv/envelope}PhonesType"/>
 *         &lt;element name="MbrInfo" type="{http://ameet.org/reserv/envelope}MbrInfoType"/>
 *         &lt;element name="Email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EmailLang" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Htl" type="{http://ameet.org/reserv/envelope}HtlType"/>
 *         &lt;element name="Stay" type="{http://ameet.org/reserv/envelope}StayType"/>
 *         &lt;element name="RtItm" type="{http://ameet.org/reserv/envelope}RtItmType"/>
 *         &lt;element name="Gtee" type="{http://ameet.org/reserv/envelope}GteeType"/>
 *         &lt;element name="TaxItms" type="{http://ameet.org/reserv/envelope}TaxItmsType"/>
 *         &lt;element name="FactItms" type="{http://ameet.org/reserv/envelope}FactItmsType"/>
 *         &lt;element name="SrvCds" type="{http://ameet.org/reserv/envelope}SrvCdsType"/>
 *         &lt;element name="OrigSellDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TTT" type="{http://ameet.org/reserv/envelope}TTTType"/>
 *         &lt;element name="Daily" type="{http://ameet.org/reserv/envelope}DailyType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SuccessRSType", propOrder = {
    "bookCNF",
    "htlRes",
    "lastUpdate",
    "origBkgData",
    "multAccom",
    "customers",
    "addrs",
    "phones",
    "mbrInfo",
    "email",
    "emailLang",
    "htl",
    "stay",
    "rtItm",
    "gtee",
    "taxItms",
    "factItms",
    "srvCds",
    "origSellDate",
    "ttt",
    "daily"
})
public class SuccessRSType {

    @XmlElement(name = "BookCNF", required = true)
    protected String bookCNF;
    @XmlElement(name = "HtlRes", required = true)
    protected HtlResType htlRes;
    @XmlElement(name = "LastUpdate", required = true)
    protected LastUpdateType lastUpdate;
    @XmlElement(name = "OrigBkgData", required = true)
    protected OrigBkgDataType origBkgData;
    @XmlElement(name = "MultAccom", required = true)
    protected String multAccom;
    @XmlElement(name = "Customers", required = true)
    protected CustomersType customers;
    @XmlElement(name = "Addrs", required = true)
    protected AddrsType addrs;
    @XmlElement(name = "Phones", required = true)
    protected PhonesType phones;
    @XmlElement(name = "MbrInfo", required = true)
    protected MbrInfoType mbrInfo;
    @XmlElement(name = "Email", required = true)
    protected String email;
    @XmlElement(name = "EmailLang", required = true)
    protected String emailLang;
    @XmlElement(name = "Htl", required = true)
    protected HtlType htl;
    @XmlElement(name = "Stay", required = true)
    protected StayType stay;
    @XmlElement(name = "RtItm", required = true)
    protected RtItmType rtItm;
    @XmlElement(name = "Gtee", required = true)
    protected GteeType gtee;
    @XmlElement(name = "TaxItms", required = true)
    protected TaxItmsType taxItms;
    @XmlElement(name = "FactItms", required = true)
    protected FactItmsType factItms;
    @XmlElement(name = "SrvCds", required = true)
    protected SrvCdsType srvCds;
    @XmlElement(name = "OrigSellDate", required = true)
    protected String origSellDate;
    @XmlElement(name = "TTT", required = true)
    protected TTTType ttt;
    @XmlElement(name = "Daily", required = true)
    protected DailyType daily;

    /**
     * Gets the value of the bookCNF property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBookCNF() {
        return bookCNF;
    }

    /**
     * Sets the value of the bookCNF property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBookCNF(String value) {
        this.bookCNF = value;
    }

    /**
     * Gets the value of the htlRes property.
     * 
     * @return
     *     possible object is
     *     {@link HtlResType }
     *     
     */
    public HtlResType getHtlRes() {
        return htlRes;
    }

    /**
     * Sets the value of the htlRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link HtlResType }
     *     
     */
    public void setHtlRes(HtlResType value) {
        this.htlRes = value;
    }

    /**
     * Gets the value of the lastUpdate property.
     * 
     * @return
     *     possible object is
     *     {@link LastUpdateType }
     *     
     */
    public LastUpdateType getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Sets the value of the lastUpdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link LastUpdateType }
     *     
     */
    public void setLastUpdate(LastUpdateType value) {
        this.lastUpdate = value;
    }

    /**
     * Gets the value of the origBkgData property.
     * 
     * @return
     *     possible object is
     *     {@link OrigBkgDataType }
     *     
     */
    public OrigBkgDataType getOrigBkgData() {
        return origBkgData;
    }

    /**
     * Sets the value of the origBkgData property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrigBkgDataType }
     *     
     */
    public void setOrigBkgData(OrigBkgDataType value) {
        this.origBkgData = value;
    }

    /**
     * Gets the value of the multAccom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMultAccom() {
        return multAccom;
    }

    /**
     * Sets the value of the multAccom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMultAccom(String value) {
        this.multAccom = value;
    }

    /**
     * Gets the value of the customers property.
     * 
     * @return
     *     possible object is
     *     {@link CustomersType }
     *     
     */
    public CustomersType getCustomers() {
        return customers;
    }

    /**
     * Sets the value of the customers property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomersType }
     *     
     */
    public void setCustomers(CustomersType value) {
        this.customers = value;
    }

    /**
     * Gets the value of the addrs property.
     * 
     * @return
     *     possible object is
     *     {@link AddrsType }
     *     
     */
    public AddrsType getAddrs() {
        return addrs;
    }

    /**
     * Sets the value of the addrs property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddrsType }
     *     
     */
    public void setAddrs(AddrsType value) {
        this.addrs = value;
    }

    /**
     * Gets the value of the phones property.
     * 
     * @return
     *     possible object is
     *     {@link PhonesType }
     *     
     */
    public PhonesType getPhones() {
        return phones;
    }

    /**
     * Sets the value of the phones property.
     * 
     * @param value
     *     allowed object is
     *     {@link PhonesType }
     *     
     */
    public void setPhones(PhonesType value) {
        this.phones = value;
    }

    /**
     * Gets the value of the mbrInfo property.
     * 
     * @return
     *     possible object is
     *     {@link MbrInfoType }
     *     
     */
    public MbrInfoType getMbrInfo() {
        return mbrInfo;
    }

    /**
     * Sets the value of the mbrInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link MbrInfoType }
     *     
     */
    public void setMbrInfo(MbrInfoType value) {
        this.mbrInfo = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the emailLang property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailLang() {
        return emailLang;
    }

    /**
     * Sets the value of the emailLang property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailLang(String value) {
        this.emailLang = value;
    }

    /**
     * Gets the value of the htl property.
     * 
     * @return
     *     possible object is
     *     {@link HtlType }
     *     
     */
    public HtlType getHtl() {
        return htl;
    }

    /**
     * Sets the value of the htl property.
     * 
     * @param value
     *     allowed object is
     *     {@link HtlType }
     *     
     */
    public void setHtl(HtlType value) {
        this.htl = value;
    }

    /**
     * Gets the value of the stay property.
     * 
     * @return
     *     possible object is
     *     {@link StayType }
     *     
     */
    public StayType getStay() {
        return stay;
    }

    /**
     * Sets the value of the stay property.
     * 
     * @param value
     *     allowed object is
     *     {@link StayType }
     *     
     */
    public void setStay(StayType value) {
        this.stay = value;
    }

    /**
     * Gets the value of the rtItm property.
     * 
     * @return
     *     possible object is
     *     {@link RtItmType }
     *     
     */
    public RtItmType getRtItm() {
        return rtItm;
    }

    /**
     * Sets the value of the rtItm property.
     * 
     * @param value
     *     allowed object is
     *     {@link RtItmType }
     *     
     */
    public void setRtItm(RtItmType value) {
        this.rtItm = value;
    }

    /**
     * Gets the value of the gtee property.
     * 
     * @return
     *     possible object is
     *     {@link GteeType }
     *     
     */
    public GteeType getGtee() {
        return gtee;
    }

    /**
     * Sets the value of the gtee property.
     * 
     * @param value
     *     allowed object is
     *     {@link GteeType }
     *     
     */
    public void setGtee(GteeType value) {
        this.gtee = value;
    }

    /**
     * Gets the value of the taxItms property.
     * 
     * @return
     *     possible object is
     *     {@link TaxItmsType }
     *     
     */
    public TaxItmsType getTaxItms() {
        return taxItms;
    }

    /**
     * Sets the value of the taxItms property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaxItmsType }
     *     
     */
    public void setTaxItms(TaxItmsType value) {
        this.taxItms = value;
    }

    /**
     * Gets the value of the factItms property.
     * 
     * @return
     *     possible object is
     *     {@link FactItmsType }
     *     
     */
    public FactItmsType getFactItms() {
        return factItms;
    }

    /**
     * Sets the value of the factItms property.
     * 
     * @param value
     *     allowed object is
     *     {@link FactItmsType }
     *     
     */
    public void setFactItms(FactItmsType value) {
        this.factItms = value;
    }

    /**
     * Gets the value of the srvCds property.
     * 
     * @return
     *     possible object is
     *     {@link SrvCdsType }
     *     
     */
    public SrvCdsType getSrvCds() {
        return srvCds;
    }

    /**
     * Sets the value of the srvCds property.
     * 
     * @param value
     *     allowed object is
     *     {@link SrvCdsType }
     *     
     */
    public void setSrvCds(SrvCdsType value) {
        this.srvCds = value;
    }

    /**
     * Gets the value of the origSellDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrigSellDate() {
        return origSellDate;
    }

    /**
     * Sets the value of the origSellDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrigSellDate(String value) {
        this.origSellDate = value;
    }

    /**
     * Gets the value of the ttt property.
     * 
     * @return
     *     possible object is
     *     {@link TTTType }
     *     
     */
    public TTTType getTTT() {
        return ttt;
    }

    /**
     * Sets the value of the ttt property.
     * 
     * @param value
     *     allowed object is
     *     {@link TTTType }
     *     
     */
    public void setTTT(TTTType value) {
        this.ttt = value;
    }

    /**
     * Gets the value of the daily property.
     * 
     * @return
     *     possible object is
     *     {@link DailyType }
     *     
     */
    public DailyType getDaily() {
        return daily;
    }

    /**
     * Sets the value of the daily property.
     * 
     * @param value
     *     allowed object is
     *     {@link DailyType }
     *     
     */
    public void setDaily(DailyType value) {
        this.daily = value;
    }

}
