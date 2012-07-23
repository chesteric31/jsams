package be.jsams.common.bean.model.sale;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.MockBeanGenerator;
import be.jsams.common.bean.model.PeriodBean;

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
    }

    /**
     * Test method for
     * {@link be.jsams.common.bean.model.sale.DeliveryOrderBean#refresh(
     * be.jsams.common.bean.model.AbstractIdentityBean)}
     * .
     */
    @Test
    public void testRefreshEquals() {
        bean = new DeliveryOrderBean(MockBeanGenerator.generateMockSociety(), MockBeanGenerator.generateMockCustomer());
        DeliveryOrderBean otherBean = MockBeanGenerator.generateMockDeliveryOrder();
        bean.refresh(otherBean);
        assertTrue(bean.equals(otherBean));
    }

    /**
     * Test method for {@link be.jsams.common.bean.model.sale.DeliveryOrderBean#clear()}.
     */
    @Test
    public void testClear() {
        bean = MockBeanGenerator.generateMockDeliveryOrder();
        bean.clear();
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
