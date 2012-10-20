package be.jsams.server.model.management;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.CivilityBean;
import be.jsams.common.bean.model.ContactInformationBean;
import be.jsams.common.bean.model.MockBeanGenerator;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.server.model.AbstractModelTest;
import be.jsams.server.model.Address;
import be.jsams.server.model.Civility;
import be.jsams.server.model.ContactInformation;
import be.jsams.server.model.LegalForm;
import be.jsams.server.model.PaymentMode;
import be.jsams.server.model.Society;

/**
 * Test class for {@link Customer} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class CustomerTest extends AbstractModelTest {
    
    private Customer customer;

    /**
     * Test method for
     * {@link be.jsams.server.model.Customer#Customer(be.jsams.common.bean.model.management.CustomerBean)}
     * .
     */
    @Test
    public void testCustomerCustomerBean() {
        CustomerBean bean = MockBeanGenerator.generateMockCustomer();
        customer = new Customer(bean);
        String bank1 = customer.getBank1();
        assertTrue(bank1.equals(bean.getBank1()));
        String bank2 = customer.getBank2();
        assertTrue(bank2.equals(bean.getBank2()));
        String description = customer.getDescription();
        assertTrue(description.equals(bean.getDescription()));
        String firstName = customer.getFirstName();
        assertTrue(firstName.equals(bean.getFirstName()));
        String name = customer.getName();
        assertEquals(name, bean.getName());
        Double creditLimit = customer.getCreditLimit();
        assertEquals(creditLimit, bean.getCreditLimit());
        Double defaultDiscountRate = customer.getDefaultDiscountRate();
        assertEquals(defaultDiscountRate, bean.getDefaultDiscountRate());
        Double vatApplicable = customer.getVatApplicable();
        assertEquals(vatApplicable, bean.getVatApplicable());
        String vatNumber = customer.getVatNumber();
        assertEquals(vatNumber, bean.getVatNumber());
        Address billingAddress = customer.getBillingAddress();
        AddressBean billingAddressBean = bean.getBillingAddress();
        checkAddress(billingAddressBean, billingAddress);
        Address deliveryAddress = customer.getDeliveryAddress();
        AddressBean deliveryAddressBean = bean.getDeliveryAddress();
        checkAddress(deliveryAddressBean, deliveryAddress);
        ContactInformation contactInformation = customer.getContactInformation();
        ContactInformationBean contactInformationBean = bean.getContactInformation();
        checkContactInformation(contactInformationBean, contactInformation);
        LegalForm legalForm = customer.getLegalForm();
        checkLegalForm(bean.getLegalForm(), legalForm);
        PaymentMode paymentMode = customer.getPaymentMode();
        checkPaymentMode(bean.getPaymentMode(), paymentMode);
        Civility civility = customer.getCivility();
        CivilityBean civilityBean = bean.getCivility();
        checkCivility(civilityBean, civility);
        Society society = customer.getSociety();
        SocietyBean societyBean = bean.getSociety();
        checkSociety(societyBean, society);
        Long id = customer.getId();
        assertEquals(id, bean.getId());
    }

}
