package be.jsams.common.bean.model.sale.detail;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import be.jsams.common.bean.model.MockBeanGenerator;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.common.bean.model.sale.EstimateBean;

/**
 * Test class for {@link EstimateDetailBean} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class EstimateDetailBeanTest {
    
    private EstimateDetailBean bean;

    /**
     * @throws java.lang.Exception a possible {@link Exception}
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * Test method for {@link be.jsams.common.bean.model.sale.detail.EstimateDetailBean#clear()}.
     */
    @Test
    public void testClear() {
        bean = MockBeanGenerator.generateMockEstimateDetail(MockBeanGenerator.generateMockEstimate());
        bean.clear();
        assertNull(bean.getDiscountRate());
        assertNull(bean.getEstimate());
        assertNull(bean.getPrice());
        assertNull(bean.getProduct());
        assertEquals(0, bean.getQuantity());
        assertNull(bean.getVatApplicable());
    }

    /**
     * Test method for {@link be.jsams.common.bean.model.sale.detail.EstimateDetailBean
     * #refresh(be.jsams.common.bean.model.AbstractIdentityBean)}.
     */
    @Test
    public void testRefreshEquals() {
        bean = new EstimateDetailBean();
        // we test only the detail here and not the estimate
        EstimateBean estimate = MockBeanGenerator.generateMockEstimate();
        bean.setEstimate(estimate);
        bean.setProduct(new ProductBean());
        EstimateDetailBean otherBean = MockBeanGenerator.generateMockEstimateDetail(estimate);
        bean.refresh(otherBean);
        assertTrue(bean.equals(otherBean));
    }

}
