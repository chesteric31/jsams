package be.jsams.common.bean.model.sale.detail;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import be.jsams.common.bean.model.MockBeanGenerator;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.common.bean.model.sale.DeliveryOrderBean;

/**
 * Test class for {@link DeliveryOrderDetailBean} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class DeliveryOrderDetailBeanTest {
    
    private DeliveryOrderDetailBean bean;

    /**
     * @throws java.lang.Exception a possible {@link Exception}
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * Test method for
     * {@link be.jsams.common.bean.model.sale.detail.DeliveryOrderDetailBean#clear()}
     * .
     */
    @Test
    public void testClear() {
        bean = MockBeanGenerator.generateMockDeliveryOrderDetail(null);
        bean.clear();
        assertNull(bean.getBillDetail());
        assertNull(bean.getCommandDetail());
        assertNull(bean.getDeliveryOrder());
        assertNull(bean.getDiscountRate());
        assertNull(bean.getPrice());
        assertNull(bean.getProduct());
        assertEquals(0, bean.getQuantity());
        assertNull(bean.getVatApplicable());
    }

    /**
     * Test method for {@link be.jsams.common.bean.model.sale.detail.DeliveryOrderDetailBean
     * #refresh(be.jsams.common.bean.model.AbstractIdentityBean)}.
     */
    @Test
    public void testRefreshEquals() {
        bean = new DeliveryOrderDetailBean();
        // we test only the detail here and not the delivery order
        DeliveryOrderBean deliveryOrder = MockBeanGenerator.generateMockDeliveryOrder();
        bean.setDeliveryOrder(deliveryOrder);
        bean.setProduct(new ProductBean());
        DeliveryOrderDetailBean otherBean = MockBeanGenerator.generateMockDeliveryOrderDetail(deliveryOrder);
        bean.refresh(otherBean);
        assertTrue(bean.equals(otherBean));
    }

}
