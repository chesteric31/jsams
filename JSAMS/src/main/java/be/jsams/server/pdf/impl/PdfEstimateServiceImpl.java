package be.jsams.server.pdf.impl;

import java.math.BigDecimal;
import java.util.List;

import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.common.bean.model.sale.EstimateBean;
import be.jsams.common.bean.model.sale.detail.EstimateDetailBean;
import be.jsams.server.pdf.PdfService;
import be.jsams.server.xml.model.estimate.AddressXml;
import be.jsams.server.xml.model.estimate.ContactInfoXml;
import be.jsams.server.xml.model.estimate.CustomerXml;
import be.jsams.server.xml.model.estimate.DetailXml;
import be.jsams.server.xml.model.estimate.EstimateXml;
import be.jsams.server.xml.model.estimate.ObjectFactory;
import be.jsams.server.xml.model.estimate.ProductXml;
import be.jsams.server.xml.model.estimate.SocietyXml;

/**
 * PDF service implementation for an {@link EstimateBean}.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class PdfEstimateServiceImpl implements PdfService<EstimateBean> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void generatePdf(EstimateBean object) {
        ObjectFactory factory = new ObjectFactory();
        EstimateXml xml = factory.createEstimate();
        xml.setCreationDate(object.getCreationDate());
        CustomerXml customerXml = factory.createCustomer();
        CustomerBean customer = object.getCustomer();
        AddressXml addressXml = factory.createAddress();
        AddressBean billingAddress = customer.getBillingAddress();
        addressXml.setCity(billingAddress.getCity());
        addressXml.setNumber(Integer.valueOf(billingAddress.getNumber()));
        addressXml.setStreet(billingAddress.getStreet());
        addressXml.setZip(billingAddress.getZipCode());
        customerXml.setAddress(addressXml);
        customerXml.setName(customer.getName());
        xml.setCustomer(customerXml);
        List<EstimateDetailBean> details = object.getDetails();
        Double fullTotalEt = 0D;
        Double fullVat = 0D;
        for (EstimateDetailBean bean : details) {
            DetailXml detailXml = factory.createDetail();
            detailXml.setPrice(bean.getPrice());
            ProductXml productXml = factory.createProduct();
            ProductBean product = bean.getProduct();
            productXml.setName(product.getName());
            productXml.setNumber(Integer.valueOf(product.getId().toString()));
            detailXml.setProduct(productXml);
            detailXml.setQuantity((short) bean.getQuantity());
            bean.getDiscountRate();
            BigDecimal totalEt = BigDecimal.valueOf(bean.getPrice() * bean.getQuantity()
                    * (1 - (bean.getDiscountRate() / 100)));
            BigDecimal vat = BigDecimal.valueOf(totalEt.doubleValue() * (bean.getVatApplicable() / 100));
            fullVat += vat.doubleValue();
            fullTotalEt += totalEt.doubleValue();
            detailXml.setTotalEt(totalEt.doubleValue());
            xml.getDetails().getDetail().add(detailXml);
        }
        xml.setNumber(Integer.valueOf(object.getId().toString()));
        SocietyXml societyXml = factory.createSociety();
        SocietyBean society = object.getSociety();
        AddressXml value = factory.createAddress();
        AddressBean address = society.getAddress();
        value.setCity(address.getCity());
        value.setNumber(Integer.valueOf(address.getNumber()));
        value.setStreet(address.getStreet());
        value.setZip(address.getZipCode());
        societyXml.setAddress(value);
        ContactInfoXml contactInfoXml = factory.createContactInfo();
        contactInfoXml.setPhone(society.getContactInformation().getPhone());
        societyXml.setContactInfo(contactInfoXml);
        societyXml.setName(society.getName());
        societyXml.setVatNumber(society.getVatNumber());
        xml.setSociety(societyXml);
        xml.setTotalAti(fullTotalEt + fullVat);
        xml.setTotalEt(fullTotalEt);
        xml.setVat(fullVat);
    }

}
