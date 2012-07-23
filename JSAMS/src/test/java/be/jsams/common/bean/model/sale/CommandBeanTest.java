package be.jsams.common.bean.model.sale;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.CivilityBean;
import be.jsams.common.bean.model.ContactInformationBean;
import be.jsams.common.bean.model.MockBeanGenerator;
import be.jsams.common.bean.model.PeriodBean;
import be.jsams.common.bean.model.management.AgentBean;

/**
 * Test class for {@link CommandBean} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class CommandBeanTest {

    private CommandBean bean;

    /**
     * @throws java.lang.Exception a possible {@link Exception}
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * Test method for
     * {@link be.jsams.common.bean.model.sale.CommandBean#refresh(be.jsams.common.bean.model.AbstractIdentityBean)}
     * .
     */
    @Test
    public void testRefreshEquals() {
        bean = new CommandBean(MockBeanGenerator.generateMockSociety(), MockBeanGenerator.generateMockCustomer(),
                MockBeanGenerator.generateMockAgent());
        CommandBean otherBean = MockBeanGenerator.generateMockCommand();
        bean.refresh(otherBean);
        assertTrue(bean.equals(otherBean));
    }

    /**
     * Test method for {@link be.jsams.common.bean.model.sale.CommandBean#clear()}.
     */
    @Test
    public void testClear() {
        bean = MockBeanGenerator.generateMockCommand();
        bean.clear();
        AgentBean agent = bean.getAgent();
        AddressBean address = agent.getAddress();
        assertNull(address.getBox());
        assertNull(address.getCity());
        assertNull(address.getCountry());
        assertNull(address.getNumber());
        assertNull(address.getStreet());
        assertNull(address.getZipCode());
        CivilityBean civility = agent.getCivility();
        assertNull(civility.getLabel());
        assertNull(civility.getLabelFr());
        assertNull(civility.getLabelNl());
        assertNull(address.getCountry());
        ContactInformationBean contactInformation = agent.getContactInformation();
        assertNull(contactInformation.getEmail());
        assertNull(contactInformation.getFax());
        assertNull(contactInformation.getMobile());
        assertNull(contactInformation.getPhone());
        assertNull(contactInformation.getWebsite());
        assertNull(agent.getFunction());
        assertNull(agent.getName());
        AddressBean billingAddress = bean.getBillingAddress();
        assertNull(billingAddress.getBox());
        assertNull(billingAddress.getCity());
        assertNull(billingAddress.getCountry());
        assertNull(billingAddress.getNumber());
        assertNull(billingAddress.getStreet());
        assertNull(billingAddress.getZipCode());
        assertNull(bean.getCreationDate());
        AddressBean deliveryAddress = bean.getDeliveryAddress();
        assertNull(deliveryAddress.getBox());
        assertNull(deliveryAddress.getCity());
        assertNull(deliveryAddress.getCountry());
        assertNull(deliveryAddress.getNumber());
        assertNull(deliveryAddress.getStreet());
        assertNull(deliveryAddress.getZipCode());
        assertNull(bean.getDiscountRate());
        PeriodBean period = bean.getPeriod();
        assertNull(period.getStartDate());
        assertNull(period.getEndDate());
        assertNull(bean.getRemark());
    }

}
