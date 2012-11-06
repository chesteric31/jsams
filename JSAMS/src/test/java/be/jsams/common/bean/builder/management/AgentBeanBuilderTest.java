package be.jsams.common.bean.builder.management;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import be.jsams.common.bean.model.MockBeanGenerator;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.server.model.MockModelGenerator;
import be.jsams.server.model.ModelTestHelper;
import be.jsams.server.model.management.Agent;

/**
 * 
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
public class AgentBeanBuilderTest extends AbstractTransactionalJUnit4SpringContextTests  {

    @Autowired
    private AgentBeanBuilder builder;
    
    /**
     * Test method for
     * {@link be.jsams.common.bean.builder.management.AgentBeanBuilder
     * #build(be.jsams.server.model.management.Agent, be.jsams.common.bean.model.SocietyBean)}
     * .
     */
    @Test
    public void testBuild() {
        Agent agent = MockModelGenerator.generateMockAgent();
        SocietyBean societyBean = MockBeanGenerator.generateMockSociety();
        AgentBean agentBean = builder.build(agent, societyBean);
        ModelTestHelper.checkAgent(agentBean, agent);
    }

}
