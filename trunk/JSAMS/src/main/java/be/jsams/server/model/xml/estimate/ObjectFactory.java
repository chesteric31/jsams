//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.12.21 at 12:56:55 PM CET 
//

package be.jsams.server.model.xml.estimate;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;
import org.w3._2001.xmlschema.Adapter1;

import be.jsams.server.model.xml.AddressXml;
import be.jsams.server.model.xml.ContactInformationXml;
import be.jsams.server.model.xml.CustomerXml;
import be.jsams.server.model.xml.ProductXml;
import be.jsams.server.model.xml.SocietyXml;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the be.jsams.server.model.xml.estimate
 * package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DiscountRate_QNAME = new QName("", "discount_rate");
    private final static QName _Logo_QNAME = new QName("", "logo");
    private final static QName _Zip_QNAME = new QName("", "zip");
    private final static QName _Phone_QNAME = new QName("", "phone");
    private final static QName _Fax_QNAME = new QName("", "fax");
    private final static QName _Website_QNAME = new QName("", "website");
    private final static QName _Street_QNAME = new QName("", "street");
    private final static QName _TotalEt_QNAME = new QName("", "total_et");
    private final static QName _Vat_QNAME = new QName("", "vat");
    private final static QName _Number_QNAME = new QName("", "number");
    private final static QName _City_QNAME = new QName("", "city");
    private final static QName _CreationDate_QNAME = new QName("", "creation_date");
    private final static QName _FirstName_QNAME = new QName("", "first_name");
    private final static QName _Price_QNAME = new QName("", "price");
    private final static QName _VatNumber_QNAME = new QName("", "vat_number");
    private final static QName _Email_QNAME = new QName("", "email");
    private final static QName _Name_QNAME = new QName("", "name");
    private final static QName _TotalAti_QNAME = new QName("", "total_ati");
    private final static QName _Quantity_QNAME = new QName("", "quantity");
    private final static QName _Mobile_QNAME = new QName("", "mobile");

    /**
     * Create a new ObjectFactory that can be used to create new instances of
     * schema derived classes for package: be.jsams.server.model.xml.estimate
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DetailXml }
     * 
     */
    public DetailXml createDetail() {
        return new DetailXml();
    }

    /**
     * Create an instance of {@link ProductXml }
     * 
     */
    public ProductXml createProduct() {
        return new ProductXml();
    }

    /**
     * Create an instance of {@link CustomerXml }
     * 
     */
    public CustomerXml createCustomer() {
        return new CustomerXml();
    }

    /**
     * Create an instance of {@link EstimateXml }
     * 
     */
    public EstimateXml createEstimate() {
        return new EstimateXml();
    }

    /**
     * Create an instance of {@link SocietyXml }
     * 
     */
    public SocietyXml createSociety() {
        return new SocietyXml();
    }

    /**
     * Create an instance of {@link AddressXml }
     * 
     */
    public AddressXml createAddress() {
        return new AddressXml();
    }

    /**
     * Create an instance of {@link ContactInformationXml }
     * 
     */
    public ContactInformationXml createContactInformation() {
        return new ContactInformationXml();
    }

    /**
     * Create an instance of {@link DetailsXml }
     * 
     */
    public DetailsXml createDetails() {
        return new DetailsXml();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }
     * {@code >}
     * 
     */
    @XmlElementDecl(namespace = "", name = "discount_rate")
    public JAXBElement<BigDecimal> createDiscountRate(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_DiscountRate_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "", name = "logo")
    public JAXBElement<String> createLogo(String value) {
        return new JAXBElement<String>(_Logo_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }
     * {@code >}
     * 
     */
    @XmlElementDecl(namespace = "", name = "zip")
    public JAXBElement<BigInteger> createZip(BigInteger value) {
        return new JAXBElement<BigInteger>(_Zip_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }
     * {@code >}
     * 
     */
    @XmlElementDecl(namespace = "", name = "phone")
    public JAXBElement<BigInteger> createPhone(BigInteger value) {
        return new JAXBElement<BigInteger>(_Phone_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }
     * {@code >}
     * 
     */
    @XmlElementDecl(namespace = "", name = "fax")
    public JAXBElement<BigInteger> createFax(BigInteger value) {
        return new JAXBElement<BigInteger>(_Fax_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "", name = "website")
    public JAXBElement<String> createWebsite(String value) {
        return new JAXBElement<String>(_Website_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "", name = "street")
    public JAXBElement<String> createStreet(String value) {
        return new JAXBElement<String>(_Street_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }
     * {@code >}
     * 
     */
    @XmlElementDecl(namespace = "", name = "total_et")
    public JAXBElement<BigDecimal> createTotalEt(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_TotalEt_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }
     * {@code >}
     * 
     */
    @XmlElementDecl(namespace = "", name = "vat")
    public JAXBElement<BigDecimal> createVat(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_Vat_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }
     * {@code >}
     * 
     */
    @XmlElementDecl(namespace = "", name = "number")
    public JAXBElement<BigInteger> createNumber(BigInteger value) {
        return new JAXBElement<BigInteger>(_Number_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "", name = "city")
    public JAXBElement<String> createCity(String value) {
        return new JAXBElement<String>(_City_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Date }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "", name = "creation_date")
    @XmlJavaTypeAdapter(Adapter1.class)
    public JAXBElement<Date> createCreationDate(Date value) {
        return new JAXBElement<Date>(_CreationDate_QNAME, Date.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "", name = "first_name")
    public JAXBElement<String> createFirstName(String value) {
        return new JAXBElement<String>(_FirstName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }
     * {@code >}
     * 
     */
    @XmlElementDecl(namespace = "", name = "price")
    public JAXBElement<BigDecimal> createPrice(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_Price_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "", name = "vat_number")
    public JAXBElement<String> createVatNumber(String value) {
        return new JAXBElement<String>(_VatNumber_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "", name = "email")
    public JAXBElement<String> createEmail(String value) {
        return new JAXBElement<String>(_Email_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "", name = "name")
    public JAXBElement<String> createName(String value) {
        return new JAXBElement<String>(_Name_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }
     * {@code >}
     * 
     */
    @XmlElementDecl(namespace = "", name = "total_ati")
    public JAXBElement<BigDecimal> createTotalAti(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_TotalAti_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }
     * {@code >}
     * 
     */
    @XmlElementDecl(namespace = "", name = "quantity")
    public JAXBElement<BigInteger> createQuantity(BigInteger value) {
        return new JAXBElement<BigInteger>(_Quantity_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "", name = "mobile")
    public JAXBElement<String> createMobile(String value) {
        return new JAXBElement<String>(_Mobile_QNAME, String.class, null, value);
    }

}
