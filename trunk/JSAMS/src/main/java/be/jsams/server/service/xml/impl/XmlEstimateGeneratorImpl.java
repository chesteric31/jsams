package be.jsams.server.service.xml.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.common.bean.model.sale.EstimateBean;
import be.jsams.common.bean.model.sale.detail.EstimateDetailBean;
import be.jsams.server.model.sale.xml.AddressXml;
import be.jsams.server.model.sale.xml.ContactInfoXml;
import be.jsams.server.model.sale.xml.CustomerXml;
import be.jsams.server.model.sale.xml.DetailXml;
import be.jsams.server.model.sale.xml.DetailsXml;
import be.jsams.server.model.sale.xml.EstimateXml;
import be.jsams.server.model.sale.xml.ObjectFactory;
import be.jsams.server.model.sale.xml.ProductXml;
import be.jsams.server.model.sale.xml.SocietyXml;
import be.jsams.server.service.xml.XmlGenerator;

/**
 * Service to generate a XML from a {@link EstimateBean} object.
 * 
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class XmlEstimateGeneratorImpl implements XmlGenerator<EstimateBean, EstimateXml> {

    /**
     * {@inheritDoc}
     */
    @Override
    public EstimateXml generateXml(EstimateBean object) {
        ObjectFactory factory = new ObjectFactory();
        EstimateXml xml = factory.createEstimate();
        xml.setCreationDate(object.getCreationDate());
        CustomerXml customerXml = factory.createCustomer();
        CustomerBean customer = object.getCustomer();
        AddressXml addressXml = factory.createAddress();
        AddressBean billingAddress = customer.getBillingAddress();
        addressXml.setCity(billingAddress.getCity());
        addressXml.setNumber(new BigInteger(billingAddress.getNumber()));
        addressXml.setStreet(billingAddress.getStreet());
        addressXml.setZip(new BigInteger(billingAddress.getZipCode()));
        customerXml.setAddress(addressXml);
        customerXml.setName(customer.getName());
        xml.setCustomer(customerXml);
        List<EstimateDetailBean> details = object.getDetails();
        BigDecimal fullTotalEt = new BigDecimal(0D);
        BigDecimal fullVat = new BigDecimal(0D);
        DetailsXml detailsXml = factory.createDetails();
        xml.setDetails(detailsXml);
        for (EstimateDetailBean bean : details) {
            DetailXml detailXml = factory.createDetail();
            detailXml.setPrice(BigDecimal.valueOf(bean.getPrice()));
            ProductXml productXml = factory.createProduct();
            ProductBean product = bean.getProduct();
            productXml.setName(product.getName());
            productXml.setNumber(new BigInteger(product.getId().toString()));
            detailXml.setProduct(productXml);
            detailXml.setQuantity(BigInteger.valueOf(bean.getQuantity()));
            BigDecimal totalEt = BigDecimal.valueOf(bean.getPrice() * bean.getQuantity());
            Double discountRate = bean.getDiscountRate();
            if (discountRate != null) {
                totalEt = totalEt.multiply(BigDecimal.valueOf(1 - (discountRate / 100)));
            }
            BigDecimal vat = totalEt.multiply(BigDecimal.valueOf(bean.getVatApplicable() / 100));
            fullVat = fullVat.add(vat);
            fullTotalEt = fullTotalEt.add(totalEt);
            detailXml.setTotalEt(totalEt.setScale(2, BigDecimal.ROUND_UP));
            List<DetailXml> detail = xml.getDetails().getDetail();
            detail.add(detailXml);
        }
        xml.setNumber(new BigInteger(object.getId().toString()));
        SocietyXml societyXml = factory.createSociety();
        SocietyBean society = object.getSociety();
        AddressXml value = factory.createAddress();
        AddressBean address = society.getAddress();
        value.setCity(address.getCity());
        value.setNumber(new BigInteger(address.getNumber()));
        value.setStreet(address.getStreet());
        value.setZip(new BigInteger(address.getZipCode()));
        societyXml.setAddress(value);
        ContactInfoXml contactInfoXml = factory.createContactInfo();
        contactInfoXml.setPhone(new BigInteger(society.getContactInformation().getPhone()));
        societyXml.setContactInfo(contactInfoXml);
        societyXml.setName(society.getName());
        societyXml.setVatNumber(society.getVatNumber());
        xml.setSociety(societyXml);
        BigDecimal fullTotalAti = fullTotalEt.add(fullVat);
        xml.setTotalAti(fullTotalAti.setScale(2, BigDecimal.ROUND_UP));
        xml.setTotalEt(fullTotalEt.setScale(2, BigDecimal.ROUND_UP));
        xml.setVat(fullVat.setScale(2, BigDecimal.ROUND_UP));
        return xml;
    }

    // public static void main(String[] args) {
    // ObjectFactory factory = new ObjectFactory();
    // EstimateXml xml = factory.createEstimate();
    // xml.setCreationDate(new Date());
    // new XmlEstimateGeneratorImpl().generateXml(EstimateXml.class, xml);
    // }

}
