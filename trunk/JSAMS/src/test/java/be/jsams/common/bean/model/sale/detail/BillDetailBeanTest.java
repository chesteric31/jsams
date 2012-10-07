package be.jsams.common.bean.model.sale.detail;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import be.jsams.common.bean.model.MockBeanGenerator;
import be.jsams.common.bean.model.management.ProductBean;

/**
 * Test class for {@link BillDetailBean} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class BillDetailBeanTest {
    
    private BillDetailBean bean;

    /**
     * @throws java.lang.Exception a possible {@link Exception}
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * Test method for
     * {@link be.jsams.common.bean.model.sale.detail.BillDetailBean#clear()}
     * .
     */
    @Test
    public void testClear() {
        bean = MockBeanGenerator.generateMockBillDetail();
        bean.clear();
        assertNull(bean.getBill());
        assertNull(bean.getDiscountRate());
        assertNull(bean.getPrice());
        assertNull(bean.getProduct());
        assertEquals(0, bean.getQuantity());
        assertNull(bean.getVatApplicable());
    }

    /**
     * Test method for {@link be.jsams.common.bean.model.sale.detail.BillDetailBean
     * #refresh(be.jsams.common.bean.model.AbstractIdentityBean)}.
     */
    @Test
    public void testRefreshEquals() {
        bean = new BillDetailBean();
        // we test only the detail here and not the bill
        bean.setBill(MockBeanGenerator.generateMockBill());
        bean.setProduct(new ProductBean());
        BillDetailBean otherBean = MockBeanGenerator.generateMockBillDetail();
        bean.refresh(otherBean);
        assertTrue(bean.equals(otherBean));
    }

}
