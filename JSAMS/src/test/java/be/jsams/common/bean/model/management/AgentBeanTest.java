package be.jsams.common.bean.model.management;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import be.jsams.common.bean.model.MockBeanGenerator;

/**
 * Test class for {@link AgentBean} class. 
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class AgentBeanTest {

    private AgentBean bean;

    /**
     * @throws java.lang.Exception a possible {@link Exception}
     */
    @Before
    public void setUp() throws Exception {
        bean = new AgentBean(MockBeanGenerator.generateMockSociety());
        bean.setCivility(MockBeanGenerator.generateMockCivility());
    }
    
    /**
     * Test method for
     * {@link be.jsams.common.bean.model.management.AgentBean#refresh(be.jsams.common.bean.model.AbstractIdentityBean)}
     * .
     */
    @Test
    public void testRefreshEquals() {
        AgentBean otherBean = MockBeanGenerator.generateMockAgent();
        bean.refresh(otherBean);
        assertTrue(bean.equals(otherBean));
    }

}
