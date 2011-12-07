//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.12.07 at 09:57:15 PM CET 
//

package be.jsams.server.model.sale.xml;

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
 *         &lt;element ref="{}name"/>
 *         &lt;element ref="{}vat_number"/>
 *         &lt;element ref="{}address"/>
 *         &lt;element ref="{}contact_info"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "name", "vatNumber", "address", "contactInfo" })
@XmlRootElement(name = "society")
public class SocietyXml {

    @XmlElement(required = true)
    private String name;
    @XmlElement(name = "vat_number", required = true)
    private String vatNumber;
    @XmlElement(required = true)
    private AddressXml address;
    @XmlElement(name = "contact_info", required = true)
    private ContactInfoXml contactInfo;

    /**
     * Gets the value of the name property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the vatNumber property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getVatNumber() {
        return vatNumber;
    }

    /**
     * Sets the value of the vatNumber property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setVatNumber(String value) {
        this.vatNumber = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return possible object is {@link AddressXml }
     * 
     */
    public AddressXml getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value allowed object is {@link AddressXml }
     * 
     */
    public void setAddress(AddressXml value) {
        this.address = value;
    }

    /**
     * Gets the value of the contactInfo property.
     * 
     * @return possible object is {@link ContactInfoXml }
     * 
     */
    public ContactInfoXml getContactInfo() {
        return contactInfo;
    }

    /**
     * Sets the value of the contactInfo property.
     * 
     * @param value allowed object is {@link ContactInfoXml }
     * 
     */
    public void setContactInfo(ContactInfoXml value) {
        this.contactInfo = value;
    }

}
