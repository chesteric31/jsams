//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.12.07 at 09:57:15 PM CET 
//

package be.jsams.server.model.sale.xml;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.w3._2001.xmlschema.Adapter1;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}society"/>
 *         &lt;element ref="{}customer"/>
 *         &lt;element ref="{}creation_date"/>
 *         &lt;element ref="{}number"/>
 *         &lt;element ref="{}details"/>
 *         &lt;element ref="{}total_et"/>
 *         &lt;element ref="{}vat"/>
 *         &lt;element ref="{}total_ati"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "society", "customer", "creationDate", "number", "details", "totalEt", "vat",
        "totalAti" })
@XmlRootElement(name = "estimate")
public class EstimateXml {

    @XmlElement(required = true)
    private SocietyXml society;
    @XmlElement(required = true)
    private CustomerXml customer;
    @XmlElement(name = "creation_date", required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1.class)
    @XmlSchemaType(name = "date")
    private Date creationDate;
    @XmlElement(required = true)
    private BigInteger number;
    @XmlElement(required = true)
    private DetailsXml details;
    @XmlElement(name = "total_et", required = true)
    private BigDecimal totalEt;
    @XmlElement(required = true)
    private BigDecimal vat;
    @XmlElement(name = "total_ati", required = true)
    private BigDecimal totalAti;

    /**
     * Gets the value of the society property.
     * 
     * @return possible object is {@link SocietyXml }
     * 
     */
    public SocietyXml getSociety() {
        return society;
    }

    /**
     * Sets the value of the society property.
     * 
     * @param value allowed object is {@link SocietyXml }
     * 
     */
    public void setSociety(SocietyXml value) {
        this.society = value;
    }

    /**
     * Gets the value of the customer property.
     * 
     * @return possible object is {@link CustomerXml }
     * 
     */
    public CustomerXml getCustomer() {
        return customer;
    }

    /**
     * Sets the value of the customer property.
     * 
     * @param value allowed object is {@link CustomerXml }
     * 
     */
    public void setCustomer(CustomerXml value) {
        this.customer = value;
    }

    /**
     * Gets the value of the creationDate property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the value of the creationDate property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setCreationDate(Date value) {
        this.creationDate = value;
    }

    /**
     * Gets the value of the number property.
     * 
     * @return possible object is {@link BigInteger }
     * 
     */
    public BigInteger getNumber() {
        return number;
    }

    /**
     * Sets the value of the number property.
     * 
     * @param value allowed object is {@link BigInteger }
     * 
     */
    public void setNumber(BigInteger value) {
        this.number = value;
    }

    /**
     * Gets the value of the details property.
     * 
     * @return possible object is {@link DetailsXml }
     * 
     */
    public DetailsXml getDetails() {
        return details;
    }

    /**
     * Sets the value of the details property.
     * 
     * @param value allowed object is {@link DetailsXml }
     * 
     */
    public void setDetails(DetailsXml value) {
        this.details = value;
    }

    /**
     * Gets the value of the totalEt property.
     * 
     * @return possible object is {@link BigDecimal }
     * 
     */
    public BigDecimal getTotalEt() {
        return totalEt;
    }

    /**
     * Sets the value of the totalEt property.
     * 
     * @param value allowed object is {@link BigDecimal }
     * 
     */
    public void setTotalEt(BigDecimal value) {
        this.totalEt = value;
    }

    /**
     * Gets the value of the vat property.
     * 
     * @return possible object is {@link BigDecimal }
     * 
     */
    public BigDecimal getVat() {
        return vat;
    }

    /**
     * Sets the value of the vat property.
     * 
     * @param value allowed object is {@link BigDecimal }
     * 
     */
    public void setVat(BigDecimal value) {
        this.vat = value;
    }

    /**
     * Gets the value of the totalAti property.
     * 
     * @return possible object is {@link BigDecimal }
     * 
     */
    public BigDecimal getTotalAti() {
        return totalAti;
    }

    /**
     * Sets the value of the totalAti property.
     * 
     * @param value allowed object is {@link BigDecimal }
     * 
     */
    public void setTotalAti(BigDecimal value) {
        this.totalAti = value;
    }

}
