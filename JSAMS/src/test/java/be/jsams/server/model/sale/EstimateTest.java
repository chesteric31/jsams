package be.jsams.server.model.sale;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import be.jsams.common.bean.model.MockBeanGenerator;
import be.jsams.common.bean.model.sale.EstimateBean;
import be.jsams.common.bean.model.sale.detail.EstimateDetailBean;
import be.jsams.server.model.ModelTestHelper;
import be.jsams.server.model.sale.detail.EstimateDetail;

/**
 * Test class for {@link Estimate} class.
 * 
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class EstimateTest {

    private Estimate estimate;

    /**
     * Test method for
     * {@link be.jsams.server.model.sale.Estimate#Estimate(be.jsams.common.bean.model.sale.EstimateBean)}
     * .
     */
    @Test
    public void testEstimateEstimateBean() {
        EstimateBean bean = MockBeanGenerator.generateMockEstimate();
        estimate = new Estimate(bean);
        ModelTestHelper.checkAgent(bean.getAgent(), estimate.getAgent());
        ModelTestHelper.checkAddress(bean.getBillingAddress(), estimate.getBillingAddress());
        assertEquals(bean.getCreationDate(), estimate.getCreationDate());
        ModelTestHelper.checkCustomer(bean.getCustomer(), estimate.getCustomer());
        assertEquals(bean.getDiscountRate(), estimate.getDiscountRate());
        assertEquals(bean.isTransferred(), estimate.isTransferred());
        assertEquals(bean.getRemark(), estimate.getRemark());
        List<EstimateDetail> details = estimate.getDetails();
        List<EstimateDetailBean> detailsBean = bean.getDetails();
        if (details != null && !details.isEmpty()) {
            int i = 0;
            for (EstimateDetail detail : details) {
                EstimateDetailBean detailBean = detailsBean.get(i);
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
