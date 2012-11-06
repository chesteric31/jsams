package be.jsams.server.model.management;

import org.junit.Test;

import be.jsams.common.bean.model.MockBeanGenerator;
import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.server.model.ModelTestHelper;

/**
 * Test class for {@link Agent} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class AgentTest {
    
    private Agent agent;

    /**
     * Test method for
     * {@link be.jsams.server.model.Agent#Agent(be.jsams.common.bean.model.management.AgentBean)}
     * .
     */
    @Test
    public void testAgentAgentBean() {
        AgentBean bean = MockBeanGenerator.generateMockAgent();
        agent = new Agent(bean);
        ModelTestHelper.checkAgent(bean, agent);
    }

}
