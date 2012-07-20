package be.jsams.common.bean.model.management;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import be.jsams.common.bean.model.CivilityBean;
import be.jsams.common.bean.model.MockBeanGenerator;
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
        SocietyBean mockSociety = MockBeanGenerator.generateMockSociety();
        bean = new CustomerBean(mockSociety);
        AgentBean mockAgent = new AgentBean(mockSociety);
        CivilityBean mockCivility = MockBeanGenerator.generateMockCivility();
        mockAgent.setCivility(mockCivility);
        bean.setAgent(mockAgent);
        bean.setCivility(mockCivility);
        bean.setLegalForm(MockBeanGenerator.generateMockLegalForm());
        bean.setPaymentMode(MockBeanGenerator.generateMockPaymentMode());
    }

    /**
     * Test method for
     * {@link be.jsams.common.bean.model.management.CustomerBean#refresh(
     * be.jsams.common.bean.model.AbstractIdentityBean)}
     * .
     */
    @Test
    public void testRefreshEquals() {
        CustomerBean otherBean = MockBeanGenerator.generateMockCustomer();
        bean.refresh(otherBean);
        assertTrue(bean.equals(otherBean));
    }

}
