//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.12.07 at 09:57:15 PM CET 
//

package be.jsams.server.model.sale.xml;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

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
 *         &lt;element ref="{}number"/>
 *         &lt;element ref="{}street"/>
 *         &lt;element ref="{}zip"/>
 *         &lt;element ref="{}city"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "number", "street", "zip", "city" })
@XmlRootElement(name = "address")
public class AddressXml {

    @XmlElement(required = true)
    private BigInteger number;
    @XmlElement(required = true)
    private String street;
    @XmlElement(required = true)
    private BigInteger zip;
    @XmlElement(required = true)
    private String city;

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
     * Gets the value of the street property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the value of the street property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setStreet(String value) {
        this.street = value;
    }

    /**
     * Gets the value of the zip property.
     * 
     * @return possible object is {@link BigInteger }
     * 
     */
    public BigInteger getZip() {
        return zip;
    }

    /**
     * Sets the value of the zip property.
     * 
     * @param value allowed object is {@link BigInteger }
     * 
     */
    public void setZip(BigInteger value) {
        this.zip = value;
    }

    /**
     * Gets the value of the city property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setCity(String value) {
        this.city = value;
    }

}
