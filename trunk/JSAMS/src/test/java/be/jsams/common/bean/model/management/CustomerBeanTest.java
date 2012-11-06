package be.jsams.common.bean.model.management;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.CivilityBean;
import be.jsams.common.bean.model.ContactInformationBean;
import be.jsams.common.bean.model.LegalFormBean;
import be.jsams.common.bean.model.MockBeanGenerator;
import be.jsams.common.bean.model.PaymentModeBean;
import be.jsams.common.bean.model.SocietyBean;

/**
 * Test class for {@link CustomerBean} class.
 * 
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class CustomerBeanTest {

    private CustomerBean bean;

    /**
     * @throws java.lang.Exception a possible {@link Exception}
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * Test method for
     * {@link be.jsams.common.bean.model.management.CustomerBean#refresh(
     * be.jsams.common.bean.model.AbstractIdentityBean)}
     * .
     */
    @Test
    public void testRefreshEquals() {
        SocietyBean mockSociety = MockBeanGenerator.generateMockSociety();
        bean = new CustomerBean(mockSociety);
        AgentBean mockAgent = new AgentBean(mockSociety);
        CivilityBean mockCivility = MockBeanGenerator.generateMockCivility();
        mockAgent.setCivility(mockCivility);
        bean.setAgent(mockAgent);
        bean.setCivility(mockCivility);
        bean.setLegalForm(MockBeanGenerator.generateMockLegalForm());
        bean.setPaymentMode(MockBeanGenerator.generateMockPaymentMode());
        CustomerBean otherBean = MockBeanGenerator.generateMockCustomer();
        bean.refresh(otherBean);
        assertTrue(bean.equals(otherBean));
    }

    /**
     * Test method for
     * {@link be.jsams.common.bean.model.management.CustomerBean#clear()}.
     */
    @Test
    public void testClear() {
        bean = MockBeanGenerator.generateMockCustomer();
        bean.clear();
        assertNull(bean.getBank1());
        assertNull(bean.getBank2());
        AddressBean billingAddress = bean.getBillingAddress();
        assertNull(billingAddress.getBox());
        assertNull(billingAddress.getCity());
        assertNull(billingAddress.getCountry());
        assertNull(billingAddress.getNumber());
        assertNull(billingAddress.getStreet());
        assertNull(billingAddress.getZipCode());
        CivilityBean civility = bean.getCivility();
        assertNull(civility.getLabel());
        assertNull(civility.getLabelFr());
        assertNull(civility.getLabelNl());
        assertNull(billingAddress.getCountry());
        ContactInformationBean contactInformation = bean.getContactInformation();
        assertNull(contactInformation.getEmail());
        assertNull(contactInformation.getFax());
        assertNull(contactInformation.getMobile());
        assertNull(contactInformation.getPhone());
        assertNull(contactInformation.getWebsite());
        assertNull(bean.getCreditLimit());
        assertNull(bean.getDefaultDiscountRate());
        AddressBean deliveryAddress = bean.getDeliveryAddress();
        assertNull(deliveryAddress.getBox());
        assertNull(deliveryAddress.getCity());
        assertNull(deliveryAddress.getCountry());
        assertNull(deliveryAddress.getNumber());
        assertNull(deliveryAddress.getStreet());
        assertNull(deliveryAddress.getZipCode());
        assertNull(bean.getDescription());
        assertNull(bean.getFirstName());
        LegalFormBean legalForm = bean.getLegalForm();
        assertNull(legalForm.getLabel());
        assertNull(legalForm.getLabelFr());
        assertNull(legalForm.getLabelNl());
        assertNull(bean.getName());
        PaymentModeBean paymentMode = bean.getPaymentMode();
        assertNull(paymentMode.getLabel());
        assertNull(paymentMode.getLabelFr());
        assertNull(paymentMode.getLabelNl());
        assertNull(bean.getVatApplicable());
        assertNull(bean.getVatNumber());
    }

}
