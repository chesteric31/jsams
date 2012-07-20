package be.jsams.common.bean.model.sale;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import be.jsams.common.bean.model.MockBeanGenerator;

/**
 * Test class for {@link DeliveryOrderBean} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class DeliveryOrderBeanTest {

    private DeliveryOrderBean bean;

    /**
     * @throws java.lang.Exception a possible {@link Exception}
     */
    @Before
    public void setUp() throws Exception {
        bean = new DeliveryOrderBean(MockBeanGenerator.generateMockSociety(), MockBeanGenerator.generateMockCustomer());
    }

    /**
     * Test method for
     * {@link be.jsams.common.bean.model.sale.DeliveryOrderBean#refresh(
     * be.jsams.common.bean.model.AbstractIdentityBean)}
     * .
     */
    @Test
    public void testRefreshEquals() {
        DeliveryOrderBean otherBean = MockBeanGenerator.generateMockDeliveryOrder();
        bean.refresh(otherBean);
        assertTrue(bean.equals(otherBean));
    }

}
