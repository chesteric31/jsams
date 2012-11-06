package be.jsams.server.model.sale;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import be.jsams.common.bean.model.MockBeanGenerator;
import be.jsams.common.bean.model.sale.DeliveryOrderBean;
import be.jsams.common.bean.model.sale.detail.DeliveryOrderDetailBean;
import be.jsams.server.model.ModelTestHelper;
import be.jsams.server.model.sale.detail.DeliveryOrderDetail;

/**
 * Test class for {@link DeliveryOrder} class.
 * 
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class DeliveryOrderTest {

    private DeliveryOrder deliveryOrder;

    /**
     * Test method for
     * {@link be.jsams.server.model.sale.DeliveryOrder#DeliveryOrder(be.jsams.common.bean.model.sale.DeliveryOrderBean)}
     * .
     */
    @Test
    public void testDeliveryOrderDeliveryOrderBean() {
        DeliveryOrderBean bean = MockBeanGenerator.generateMockDeliveryOrder();
        deliveryOrder = new DeliveryOrder(bean);
        assertEquals(bean.getCreationDate(), deliveryOrder.getCreationDate());
        ModelTestHelper.checkCustomer(bean.getCustomer(), deliveryOrder.getCustomer());
        ModelTestHelper.checkAddress(bean.getDeliveryAddress(), deliveryOrder.getDeliveryAddress());
        assertEquals(bean.getDiscountRate(), deliveryOrder.getDiscountRate());
        assertEquals(bean.isTransferred(), deliveryOrder.isTransferred());
        assertEquals(bean.getRemark(), deliveryOrder.getRemark());
        List<DeliveryOrderDetail> details = deliveryOrder.getDetails();
        List<DeliveryOrderDetailBean> detailsBean = bean.getDetails();
        if (details != null && !details.isEmpty()) {
            int i = 0;
            for (DeliveryOrderDetail detail : details) {
                DeliveryOrderDetailBean detailBean = detailsBean.get(i);
                assertEquals(detailBean.getDiscountRate(), detail.getDiscountRate());
                assertEquals(detailBean.getId(), detail.getId());
                assertEquals(detailBean.getPrice(), detail.getPrice());
                ModelTestHelper.checkProduct(detailBean.getProduct(), detail.getProduct());
                assertEquals(detailBean.getQuantity(), detail.getQuantity());
                assertEquals(detailBean.getVatApplicable(), detail.getVatApplicable());
                assertEquals(detailBean.isTransferred(), detail.isTransferred());
                i++;
            }
        }
    }

}
