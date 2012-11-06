package be.jsams.server.model.sale;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import be.jsams.common.bean.model.MockBeanGenerator;
import be.jsams.common.bean.model.sale.CreditNoteBean;
import be.jsams.common.bean.model.sale.detail.CreditNoteDetailBean;
import be.jsams.server.model.ModelTestHelper;
import be.jsams.server.model.sale.detail.CreditNoteDetail;

/**
 * Test class for {@link CreditNote} class.
 * 
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class CreditNoteTest {

    private CreditNote creditNote;

    /**
     * Test method for
     * {@link be.jsams.server.model.sale.CreditNote#CreditNote(be.jsams.common.bean.model.sale.CreditNoteBean)}
     * .
     */
    @Test
    public void testCreditNoteCreditNoteBean() {
        CreditNoteBean bean = MockBeanGenerator.generateMockCreditNote();
        creditNote = new CreditNote(bean);
        ModelTestHelper.checkAddress(bean.getBillingAddress(), creditNote.getBillingAddress());
        assertEquals(bean.getCreationDate(), creditNote.getCreationDate());
        ModelTestHelper.checkCustomer(bean.getCustomer(), creditNote.getCustomer());
        assertEquals(bean.getRemark(), creditNote.getRemark());
        List<CreditNoteDetail> details = creditNote.getDetails();
        List<CreditNoteDetailBean> detailsBean = bean.getDetails();
        if (details != null && !details.isEmpty()) {
            int i = 0;
            for (CreditNoteDetail detail : details) {
                CreditNoteDetailBean detailBean = detailsBean.get(i);
                assertEquals(detailBean.getDiscountRate(), detail.getDiscountRate());
                assertEquals(detailBean.getId(), detail.getId());
                assertEquals(detailBean.getPrice(), detail.getPrice());
                ModelTestHelper.checkProduct(detailBean.getProduct(), detail.getProduct());
                assertEquals(detailBean.getQuantity(), detail.getQuantity());
                assertEquals(detailBean.getVatApplicable(), detail.getVatApplicable());
                i++;
            }
        }
    }

}
