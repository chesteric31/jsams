package be.jsams.server.service.xml.impl;

import java.math.BigInteger;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.common.bean.model.sale.DeliveryOrderBean;
import be.jsams.common.bean.model.sale.detail.DeliveryOrderDetailBean;
import be.jsams.server.model.xml.AddressXml;
import be.jsams.server.model.xml.ContactInformationXml;
import be.jsams.server.model.xml.CustomerXml;
import be.jsams.server.model.xml.ProductXml;
import be.jsams.server.model.xml.SocietyXml;
import be.jsams.server.model.xml.deliveryOrder.DeliveryOrderXml;
import be.jsams.server.model.xml.deliveryOrder.DetailXml;
import be.jsams.server.model.xml.deliveryOrder.DetailsXml;
import be.jsams.server.model.xml.deliveryOrder.ObjectFactory;
import be.jsams.server.service.xml.XmlGenerator;

/**
 * 
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class XmlDeliveryOrderGeneratorImpl implements XmlGenerator<DeliveryOrderBean, DeliveryOrderXml> {

    /**
     * {@inheritDoc}
     */
    @Override
    public DeliveryOrderXml generateXml(DeliveryOrderBean object) {
        ObjectFactory factory = new ObjectFactory();
        DeliveryOrderXml xml = factory.createDeliveryOrder();
        xml.setCreationDate(object.getCreationDate());
        CustomerXml customerXml = factory.createCustomer();
        CustomerBean customer = object.getCustomer();
        AddressXml addressXml = factory.createAddress();
        AddressBean deliveryAddress = object.getDeliveryAddress();
        addressXml.setCity(deliveryAddress.getCity());
        addressXml.setNumber(new BigInteger(deliveryAddress.getNumber()));
        addressXml.setStreet(deliveryAddress.getStreet());
        addressXml.setZip(new BigInteger(deliveryAddress.getZipCode()));
        xml.setAddress(addressXml);
        customerXml.setName(customer.getName());
        customerXml.setFirstName(customer.getFirstName());
        xml.setCustomer(customerXml);
        List<DeliveryOrderDetailBean> details = object.getDetails();
        DetailsXml detailsXml = factory.createDetails();
        xml.setDetails(detailsXml);
        for (DeliveryOrderDetailBean bean : details) {
            DetailXml detailXml = factory.createDetail();
            ProductXml productXml = factory.createProduct();
            ProductBean product = bean.getProduct();
            productXml.setName(product.getName());
            productXml.setNumber(new BigInteger(product.getId().toString()));
            detailXml.setProduct(productXml);
            detailXml.setQuantity(BigInteger.valueOf(bean.getQuantity()));
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
        ContactInformationXml contactInfoXml = factory.createContactInformation();
        contactInfoXml.setPhone(new BigInteger(society.getContactInformation().getPhone()));
        societyXml.setContactInformation(contactInfoXml);
        societyXml.setName(society.getName());
        societyXml.setVatNumber(society.getVatNumber());
        // obtain an encoded string from the byte array, which will be written
        // inside the XML
        String base64Image = Base64.encodeBase64String(society.getLogo());
        societyXml.setLogo(base64Image);
        xml.setSociety(societyXml);
        return xml;
    }

}
