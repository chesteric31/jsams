package be.jsams.common.bean.model.sale.detail;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import be.jsams.common.bean.model.MockBeanGenerator;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.common.bean.model.sale.CommandBean;

/**
 * Test class for {@link CommandDetailBean} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class CommandDetailBeanTest {
    
    private CommandDetailBean bean;

    /**
     * @throws java.lang.Exception a possible {@link Exception}
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * Test method for
     * {@link be.jsams.common.bean.model.sale.detail.CommandDetailBean#clear()}.
     */
    @Test
    public void testClear() {
        bean = MockBeanGenerator.generateMockCommandDetail(null);
        bean.clear();
        assertNull(bean.getDiscountRate());
        assertNull(bean.getCommand());
        assertNull(bean.getPrice());
        assertNull(bean.getProduct());
        assertEquals(0, bean.getQuantity());
        assertNull(bean.getVatApplicable());
    }

    /**
     * Test method for {@link be.jsams.common.bean.model.sale.detail.CommandDetailBean
     * #refresh(be.jsams.common.bean.model.AbstractIdentityBean)}.
     */
    @Test
    public void testRefreshEquals() {
        bean = new CommandDetailBean();
        // we test only the detail here and not the command
        CommandBean command = MockBeanGenerator.generateMockCommand();
        bean.setCommand(command);
        bean.setProduct(new ProductBean());
        CommandDetailBean otherBean = MockBeanGenerator.generateMockCommandDetail(command);
        bean.refresh(otherBean);
        assertTrue(bean.equals(otherBean));
    }

}
