package be.jsams.common.bean.model.sale;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import be.jsams.common.bean.model.MockBeanGenerator;

/**
 * Test class for {@link EstimateBean} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class EstimateBeanTest {

    private EstimateBean bean;

    /**
     * @throws java.lang.Exception a possible {@link Exception}
     */
    @Before
    public void setUp() throws Exception {
        bean = new EstimateBean(MockBeanGenerator.generateMockSociety(), MockBeanGenerator.generateMockCustomer(),
                MockBeanGenerator.generateMockAgent());
    }

    /**
     * Test method for
     * {@link be.jsams.common.bean.model.sale.EstimateBean#refresh(be.jsams.common.bean.model.AbstractIdentityBean)}
     * .
     */
    @Test
    public void testRefresh() {
        EstimateBean otherBean = MockBeanGenerator.generateMockEstimate();
        bean.refresh(otherBean);
        assertTrue(bean.equals(otherBean));
    }

}
