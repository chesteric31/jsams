package be.jsams.common.bean.model.sale;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import be.jsams.common.bean.model.MockBeanGenerator;

/**
 * Test class for {@link BillBean} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class BillBeanTest {

    private BillBean bean;

    /**
     * @throws java.lang.Exception a possible {@link Exception}
     */
    @Before
    public void setUp() throws Exception {
        bean = new BillBean(MockBeanGenerator.generateMockSociety(), MockBeanGenerator.generateMockCustomer(),
                MockBeanGenerator.generateMockPaymentMode());
    }

    /**
     * Test method for
     * {@link be.jsams.common.bean.model.sale.BillBean#refresh(be.jsams.common.bean.model.AbstractIdentityBean)}
     * .
     */
    @Test
    public void testRefreshEquals() {
        BillBean otherBean = MockBeanGenerator.generateMockBill();
        bean.refresh(otherBean);
        assertTrue(bean.equals(otherBean));
    }

}
