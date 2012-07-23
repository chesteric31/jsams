package be.jsams.common.bean.model.sale;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.MockBeanGenerator;
import be.jsams.common.bean.model.PeriodBean;

/**
 * Test class for {@link CreditNoteBean} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class CreditNoteBeanTest {

    private CreditNoteBean bean;

    /**
     * @throws java.lang.Exception a possible {@link Exception}
     */
    @Before
    public void setUp() throws Exception {
    }
    
    /**
     * Test method for
     * {@link be.jsams.common.bean.model.sale.CreditNoteBean#refresh(be.jsams.common.bean.model.AbstractIdentityBean)}
     * .
     */
    @Test
    public void testRefreshEquals() {
        bean = new CreditNoteBean(MockBeanGenerator.generateMockSociety(), MockBeanGenerator.generateMockCustomer());
        CreditNoteBean otherBean = MockBeanGenerator.generateMockCreditNote();
        bean.refresh(otherBean);
        assertTrue(bean.equals(otherBean));
    }

    /**
     * Test method for {@link be.jsams.common.bean.model.sale.CreditNoteBean#clear()}.
     */
    @Test
    public void testClear() {
        bean = MockBeanGenerator.generateMockCreditNote();
        bean.clear();
        AddressBean billingAddress = bean.getBillingAddress();
        assertNull(billingAddress.getBox());
        assertNull(billingAddress.getCity());
        assertNull(billingAddress.getCountry());
        assertNull(billingAddress.getNumber());
        assertNull(billingAddress.getStreet());
        assertNull(billingAddress.getZipCode());
        assertNull(bean.getCreationDate());
        PeriodBean period = bean.getPeriod();
        assertNull(period.getStartDate());
        assertNull(period.getEndDate());
        assertNull(bean.getRemark());
    }

}
