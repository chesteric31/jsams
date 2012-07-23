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
 * Test class for {@link EstimateBean} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class EstimateBeanTest {

    private EstimateBean bean;

    /**
     * @throws java.lang.Exception a possible {@link Exception}
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * Test method for
     * {@link be.jsams.common.bean.model.sale.EstimateBean#refresh(be.jsams.common.bean.model.AbstractIdentityBean)}
     * .
     */
    @Test
    public void testRefreshEquals() {
        bean = new EstimateBean(MockBeanGenerator.generateMockSociety(), MockBeanGenerator.generateMockCustomer(),
                MockBeanGenerator.generateMockAgent());
        EstimateBean otherBean = MockBeanGenerator.generateMockEstimate();
        bean.refresh(otherBean);
        assertTrue(bean.equals(otherBean));
    }

    /**
     * Test method for
     * {@link be.jsams.common.bean.model.sale.EstimateBean#clear()}.
     */
    @Test
    public void testClear() {
        bean = MockBeanGenerator.generateMockEstimate();
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
        assertNull(bean.getCreationDate());
        AddressBean billingAddress = bean.getBillingAddress();
        assertNull(billingAddress.getBox());
        assertNull(billingAddress.getCity());
        assertNull(billingAddress.getCountry());
        assertNull(billingAddress.getNumber());
        assertNull(billingAddress.getStreet());
        assertNull(billingAddress.getZipCode());
        assertNull(bean.getDiscountRate());
        PeriodBean period = bean.getPeriod();
        assertNull(period.getStartDate());
        assertNull(period.getEndDate());
        assertNull(bean.getRemark());
    }

}
