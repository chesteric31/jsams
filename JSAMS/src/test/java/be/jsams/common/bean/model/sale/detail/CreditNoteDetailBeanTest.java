package be.jsams.common.bean.model.sale.detail;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import be.jsams.common.bean.model.MockBeanGenerator;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.common.bean.model.sale.CreditNoteBean;
import be.jsams.common.bean.model.sale.CreditNoteMediator;

/**
 * Test class for {@link CreditNoteDetailBean} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class CreditNoteDetailBeanTest {
    
    private CreditNoteDetailBean bean;

    /**
     * @throws java.lang.Exception a possible {@link Exception}
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * Test method for {@link be.jsams.common.bean.model.sale.detail.CreditNoteDetailBean#clear()}.
     */
    @Test
    public void testClear() {
        CreditNoteBean creditNote = MockBeanGenerator.generateMockCreditNote();
        CreditNoteMediator mediator = new CreditNoteMediator();
        bean = MockBeanGenerator.generateMockCreditNoteDetail(creditNote);
        bean.setMediator(mediator);
        mediator.setCreditNoteBean(creditNote);
        bean.clear();
        assertNull(bean.getDiscountRate());
        assertNull(bean.getCreditNote());
        assertNull(bean.getPrice());
        assertNull(bean.getProduct());
        assertEquals(0, bean.getQuantity());
        assertNull(bean.getVatApplicable());
    }

    /**
     * Test method for {@link be.jsams.common.bean.model.sale.detail.CreditNoteDetailBean
     * #refresh(be.jsams.common.bean.model.AbstractIdentityBean)}.
     */
    @Test
    public void testRefreshEquals() {
        bean = new CreditNoteDetailBean();
        // we test only the detail here and not the credit note
        CreditNoteBean creditNote = MockBeanGenerator.generateMockCreditNote();
        CreditNoteMediator mediator = new CreditNoteMediator();
        bean.setMediator(mediator);
        mediator.setCreditNoteBean(creditNote);
        bean.setCreditNote(creditNote);
        bean.setProduct(new ProductBean());
        CreditNoteDetailBean otherBean = MockBeanGenerator.generateMockCreditNoteDetail(creditNote);
        otherBean.setMediator(mediator);
        bean.refresh(otherBean);
        assertTrue(bean.equals(otherBean));
    }

}
