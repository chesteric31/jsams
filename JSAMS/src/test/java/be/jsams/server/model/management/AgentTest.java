package be.jsams.server.model.management;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.CivilityBean;
import be.jsams.common.bean.model.ContactInformationBean;
import be.jsams.common.bean.model.MockBeanGenerator;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.server.model.AbstractModelTest;
import be.jsams.server.model.Address;
import be.jsams.server.model.Civility;
import be.jsams.server.model.ContactInformation;
import be.jsams.server.model.Society;

/**
 * Test class for {@link Agent} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class AgentTest extends AbstractModelTest {
    
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
        String function = agent.getFunction();
        assertTrue(function.equals(bean.getFunction()));
        String name = agent.getName();
        assertEquals(name, bean.getName());
        Address address = agent.getAddress();
        AddressBean addressBean = bean.getAddress();
        checkAddress(addressBean, address);
        ContactInformation contactInformation = agent.getContactInformation();
        ContactInformationBean contactInformationBean = bean.getContactInformation();
        checkContactInformation(contactInformationBean, contactInformation);
        Civility civility = agent.getCivility();
        CivilityBean civilityBean = bean.getCivility();
        checkCivility(civilityBean, civility);
        Society society = agent.getSociety();
        SocietyBean societyBean = bean.getSociety();
        checkSociety(societyBean, society);
        Long id = agent.getId();
        assertEquals(id, bean.getId());
    }

}
