package be.jsams.common.bean.model.sale;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.MockBeanGenerator;
import be.jsams.common.bean.model.PaymentModeBean;
import be.jsams.common.bean.model.PeriodBean;

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
    }

    /**
     * Test method for
     * {@link be.jsams.common.bean.model.sale.BillBean#refresh(be.jsams.common.bean.model.AbstractIdentityBean)}
     * .
     */
    @Test
    public void testRefreshEquals() {
        bean = new BillBean(MockBeanGenerator.generateMockSociety(), MockBeanGenerator.generateMockCustomer(),
                MockBeanGenerator.generateMockPaymentMode());
        BillMediator mediator = new BillMediator();
        bean.setMediator(mediator);
        mediator.setBillBean(bean);
        BillBean otherBean = MockBeanGenerator.generateMockBill();
        BillMediator otherMediator = new BillMediator();
        otherBean.setMediator(otherMediator);
        otherMediator.setBillBean(otherBean);
        bean.refresh(otherBean);
        assertTrue(bean.equals(otherBean));
    }

    /**
     * Test method for {@link be.jsams.common.bean.model.sale.BillBean#clear()}.
     */
    @Test
    public void testClear() {
        bean = MockBeanGenerator.generateMockBill();
        bean.clear();
        AddressBean billingAddress = bean.getBillingAddress();
        assertNull(billingAddress.getBox());
        assertNull(billingAddress.getCity());
        assertNull(billingAddress.getCountry());
        assertNull(billingAddress.getNumber());
        assertNull(billingAddress.getStreet());
        assertNull(billingAddress.getZipCode());
        assertNull(bean.getCreationDate());
        assertNull(bean.getDateFirstRemember());
        assertNull(bean.getDateFormalNotice());
        assertNull(bean.getDateSecondRemember());
        assertNull(bean.getDiscountRate());
        assertNull(bean.getDueDate());
        PaymentModeBean paymentMode = bean.getPaymentMode();
        assertNull(paymentMode.getLabel());
        assertNull(paymentMode.getLabelFr());
        assertNull(paymentMode.getLabelNl());
        PeriodBean period = bean.getPeriod();
        assertNull(period.getStartDate());
        assertNull(period.getEndDate());
        assertNull(bean.getRemark());
    }

}
