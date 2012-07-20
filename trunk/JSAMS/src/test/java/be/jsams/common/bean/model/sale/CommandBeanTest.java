package be.jsams.common.bean.model.sale;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import be.jsams.common.bean.model.MockBeanGenerator;

/**
 * Test class for {@link CommandBean} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class CommandBeanTest {

    private CommandBean bean;

    /**
     * @throws java.lang.Exception a possible {@link Exception}
     */
    @Before
    public void setUp() throws Exception {
        bean = new CommandBean(MockBeanGenerator.generateMockSociety(), MockBeanGenerator.generateMockCustomer(),
                MockBeanGenerator.generateMockAgent());
    }

    /**
     * Test method for
     * {@link be.jsams.common.bean.model.sale.CommandBean#refresh(be.jsams.common.bean.model.AbstractIdentityBean)}
     * .
     */
    @Test
    public void testRefreshEquals() {
        CommandBean otherBean = MockBeanGenerator.generateMockCommand();
        bean.refresh(otherBean);
        assertTrue(bean.equals(otherBean));
    }

}
