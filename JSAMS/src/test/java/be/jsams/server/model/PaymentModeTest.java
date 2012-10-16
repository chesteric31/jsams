package be.jsams.server.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import be.jsams.common.bean.model.MockBeanGenerator;
import be.jsams.common.bean.model.PaymentModeBean;

/**
 * Test class for {@link PaymentMode} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class PaymentModeTest {
    
    private PaymentMode paymentMode;

    /**
     * Test method for
     * {@link be.jsams.server.model.PaymentMode#PaymentMode(be.jsams.common.bean.model.PaymentModeBean)}
     * .
     */
    @Test
    public void testAddressAddressBean() {
        PaymentModeBean bean = MockBeanGenerator.generateMockPaymentMode();
        paymentMode = new PaymentMode(bean);
        String label = paymentMode.getLabel();
        assertTrue(label.equals(bean.getLabel()));
        String labelFr = paymentMode.getLabelFr();
        assertEquals(labelFr, bean.getLabelFr());
        String labelNl = paymentMode.getLabelNl();
        assertEquals(labelNl, bean.getLabelNl());
        Integer additionalDays = paymentMode.getAdditionalDays();
        assertEquals(additionalDays, bean.getAdditionalDays());
        Integer daysNumber = paymentMode.getDaysNumber();
        assertEquals(daysNumber, bean.getDaysNumber());
        Long id = paymentMode.getId();
        assertEquals(id, bean.getId());
    }

}
