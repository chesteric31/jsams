package be.jsams.common.bean.model.transfer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import be.jsams.common.bean.model.MockBeanGenerator;

/**
 * Test class for {@link TransferBean} class.
 * 
 * @author chesteric31
 * @version $Revision$ $Date:: $ $Author$
 */
public class TransferBeanTest {

    private TransferBean bean;

    /**
     * @throws java.lang.Exception a possible {@link Exception}
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * Test method for
     * {@link be.jsams.common.bean.model.transfer.TransferBean#refresh(be.jsams.common.bean.model.AbstractIdentityBean)}
     * .
     */
    @Test
    public void testRefreshEquals() {
        bean = new TransferBean();
        TransferBean otherBean = MockBeanGenerator.generateMockTransfer();
        bean.refresh(otherBean);
        assertTrue(bean.equals(otherBean));
    }
    /**
     * Test method for
     * {@link be.jsams.common.bean.model.transfer.TransferBean#clear()}.
     */
    @Test
    public void testClear() {
        bean = MockBeanGenerator.generateMockTransfer();
        bean.clear();
        assertNull(bean.getBillDetails());
        assertNull(bean.getCommandDetails());
        assertNull(bean.getDeliveryOrderDetails());
        assertEquals(0, bean.getDestinationType());
        assertNull(bean.getDocuments());
        assertNull(bean.getEstimateDetails());
        assertNull(bean.getSelectableDetails());
        assertEquals(0, bean.getSourceType());
        assertEquals(0, bean.getTransferMode());
    }

}
