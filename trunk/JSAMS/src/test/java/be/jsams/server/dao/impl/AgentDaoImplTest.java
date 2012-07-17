package be.jsams.server.dao.impl;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import be.jsams.common.bean.model.CivilityBean;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.server.dao.AddressDao;
import be.jsams.server.dao.BaseJUnitTestClass;
import be.jsams.server.dao.CivilityDao;
import be.jsams.server.dao.ContactInformationDao;
import be.jsams.server.dao.SocietyDao;
import be.jsams.server.dao.management.AgentDao;
import be.jsams.server.model.Address;
import be.jsams.server.model.Civility;
import be.jsams.server.model.ContactInformation;
import be.jsams.server.model.MockModelGenerator;
import be.jsams.server.model.Society;
import be.jsams.server.model.management.Agent;

/**
 * Test class for {@link AgentDao}.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class AgentDaoImplTest extends BaseJUnitTestClass {

    @Autowired
    private AgentDao dao;
    private Agent agent;

    @Autowired
    private SocietyDao societyDao;

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private CivilityDao civilityDao;

    @Autowired
    private ContactInformationDao contactInformationDao;

    /**
     * @throws java.lang.Exception a possible {@link Exception}
     */
    @Before
    public void setUp() throws Exception {
        agent = MockModelGenerator.generateMockAgent();
    }

    /**
     * Test method for
     * {@link be.jsams.server.dao.management.impl.AgentDaoImpl#findByCriteria(
     * be.jsams.common.bean.model.management.AgentBean)}
     * .
     */
    @Test
    public void testFindByCriteria() {
        final Society persistedSociety = societyDao.add(agent.getSociety());
        SocietyBean societyBean = new SocietyBean(persistedSociety) {

            /**
             * Serial Version UID
             */
            private static final long serialVersionUID = -5835207230986956543L;

        };

        final Civility persistedCivility = civilityDao.add(agent.getCivility());
        agent.setCivility(persistedCivility);
        final ContactInformation persistedContactInformation = contactInformationDao.add(agent.getContactInformation());
        agent.setContactInformation(persistedContactInformation);
        final Address persistedAddress = addressDao.add(agent.getAddress());
        agent.setAddress(persistedAddress);
        final Agent persistedAgent = dao.add(agent);
        AgentBean criteria = new AgentBean(societyBean) {

            /**
             * Serial Version UID
             */
            private static final long serialVersionUID = -3429748354389589805L;

        };

        criteria.setSociety(societyBean);
        CivilityBean civilityBean = new CivilityBean(persistedCivility) {

            /**
             * Serial Version UID
             */
            private static final long serialVersionUID = -4476727993398758872L;

        };
        criteria.setCivility(civilityBean);
        dao.setCurrentSociety(societyBean);
        List<Agent> founds = dao.findByCriteria(criteria);
        assertTrue(founds.contains(persistedAgent));
    }

}
