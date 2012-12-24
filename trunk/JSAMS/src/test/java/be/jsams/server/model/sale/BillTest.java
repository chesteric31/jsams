package be.jsams.server.model.sale;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import be.jsams.common.bean.model.MockBeanGenerator;
import be.jsams.common.bean.model.sale.BillBean;
import be.jsams.common.bean.model.sale.detail.BillDetailBean;
import be.jsams.server.model.ModelTestHelper;
import be.jsams.server.model.sale.detail.BillDetail;

/**
 * Test class for {@link Bill} class.
 * 
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class BillTest {

    private Bill bill;

    /**
     * Test method for
     * {@link be.jsams.server.model.sale.Bill#Bill(be.jsams.common.bean.model.sale.BillBean)}
     * .
     */
    @Test
    public void testBillBillBean() {
        BillBean bean = MockBeanGenerator.generateMockBill();
        bill = new Bill(bean);
        ModelTestHelper.checkAddress(bean.getBillingAddress(), bill.getBillingAddress());
        assertEquals(bean.getCreationDate(), bill.getCreationDate());
        ModelTestHelper.checkCustomer(bean.getCustomer(), bill.getCustomer());
        assertEquals(bean.getFirstRememberDate(), bill.getFirstRememberDate());
        assertEquals(bean.getSecondRememberDate(), bill.getSecondRememberDate());
        assertEquals(bean.getFormalNoticeDate(), bill.getFormalNoticeDate());
        assertEquals(bean.getDueDate(), bill.getDueDate());
        assertEquals(bean.getDiscountRate(), bill.getDiscountRate());
        ModelTestHelper.checkPaymentMode(bean.getPaymentMode(), bill.getPaymentMode());
        assertEquals(bean.getRemark(), bill.getRemark());
        assertEquals(bean.isClosed(), bill.isClosed());
        assertEquals(bean.getPaymentDate(), bill.getPaymentDate());
        List<BillDetail> details = bill.getDetails();
        List<BillDetailBean> detailsBean = bean.getDetails();
        if (details != null && !details.isEmpty()) {
            int i = 0;
            for (BillDetail detail : details) {
                BillDetailBean detailBean = detailsBean.get(i);
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
