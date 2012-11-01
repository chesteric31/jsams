package be.jsams.server.service.xml.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;

import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.ContactInformationBean;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.server.model.xml.AddressXml;
import be.jsams.server.model.xml.ContactInformationXml;
import be.jsams.server.model.xml.CustomerXml;
import be.jsams.server.model.xml.ProductXml;
import be.jsams.server.model.xml.SocietyXml;

/**
 * 
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public abstract class AbstractXmlTest {

    /**
     * Checks if the data are equals.
     * 
     * @param bean the {@link ProductBean} to use
     * @param xml the {@link ProductXml} to check
     */
    protected void checkProduct(ProductBean bean, ProductXml xml) {
        String name = xml.getName();
        assertEquals(name, bean.getName());
    }
    
    /**
     * Checks if the data are equals.
     * 
     * @param bean the {@link SocietyBean} to use
     * @param xml the {@link SocietyXml} to check
     */
    protected void checkSociety(SocietyBean bean, SocietyXml xml) {
        String name = xml.getName();
        assertEquals(name, bean.getName());
        AddressXml address = xml.getAddress();
        AddressBean addressBean = bean.getAddress();
        checkAddress(addressBean, address);
        ContactInformationXml contactInformation = xml.getContactInformation();
        ContactInformationBean contactInformationBean = bean.getContactInformation();
        checkContactInformation(contactInformationBean, contactInformation);
        String logo = xml.getLogo();
        assertEquals(logo, bean.getLogo());
        String vatNumber = xml.getVatNumber();
        assertEquals(vatNumber, bean.getVatNumber());
    }
    
    /**
     * Checks if the data are equals.
     * 
     * @param bean the {@link ContactInformationBean} to use
     * @param xml the {@link ContactInformationXml} to check
     */
    protected void checkContactInformation(ContactInformationBean bean, ContactInformationXml xml) {
        String phone = xml.getPhone();
        assertEquals(bean.getPhone(), String.valueOf(phone));
    }

    /**
     * Checks if the data are equals.
     * 
     * @param bean the {@link AddressBean} to use
     * @param xml the {@link AddressXml} to check
     */
    protected void checkAddress(AddressBean bean, AddressXml xml) {
        String city = xml.getCity();
        assertEquals(city, bean.getCity());
        BigInteger number = xml.getNumber();
        assertEquals(String.valueOf(number), bean.getNumber());
        String street = xml.getStreet();
        assertEquals(street, bean.getStreet());
        String zipCode = xml.getZip();
        assertEquals(zipCode, bean.getZipCode());
    }

    /**
     * Checks if the data are equals.
     * 
     * @param bean the {@link CustomerBean} to use
     * @param xml the {@link CustomerXml} to check
     */
    protected void checkCustomer(CustomerBean bean, CustomerXml xml) {
        String firstName = xml.getFirstName();
        assertTrue(firstName.equals(bean.getFirstName()));
        String name = xml.getName();
        assertEquals(name, bean.getName());
    }


}
