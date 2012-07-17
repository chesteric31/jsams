package be.jsams.common.bean.model;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import be.jsams.server.model.PaymentMode;

/**
 * Test class for {@link PaymentModeBean} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class PaymentModeBeanTest {

    private PaymentModeBean bean;

    /**
     * 
     * @throws Exception a possible {@link Exception}
     */
    @Before
    public void setUp() throws Exception {
        bean = new PaymentModeBean(new PaymentMode());
    }
    
    /**
     * Test method for
     * {@link be.jsams.common.bean.model.PaymentModeBean#refresh(be.jsams.common.bean.model.AbstractIdentityBean)}
     * .
     */
    @Test
    public void testRefreshEquals() {
        PaymentModeBean otherBean = MockBeanGenerator.generateMockPaymentMode();
        bean.refresh(otherBean);
        assertTrue(bean.equals(otherBean));
    }

}
