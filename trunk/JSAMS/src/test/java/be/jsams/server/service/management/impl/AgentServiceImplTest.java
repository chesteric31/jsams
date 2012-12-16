package be.jsams.server.service.management.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import be.jsams.common.bean.model.CivilityBean;
import be.jsams.common.bean.model.LegalFormBean;
import be.jsams.common.bean.model.MockBeanGenerator;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.server.BaseJUnitTestClass;
import be.jsams.server.dao.CivilityDao;
import be.jsams.server.dao.LegalFormDao;
import be.jsams.server.dao.SocietyDao;
import be.jsams.server.model.Civility;
import be.jsams.server.model.LegalForm;
import be.jsams.server.model.Society;
import be.jsams.server.service.management.AgentService;

/**
 * Test class for {@link AgentServiceImpl} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class AgentServiceImplTest extends BaseJUnitTestClass {

    @Autowired
    @Qualifier(value = "agentServiceTarget")
    private AgentService service;
    @Autowired
    private LegalFormDao legalFormDao;
    @Autowired
    private SocietyDao societyDao;
    private AgentBean agent;
    @Autowired
    private CivilityDao civilityDao;

    /**
     * @throws java.lang.Exception a possible {@link Exception}
     */
    @Before
    public void setUp() throws Exception {
        agent = MockBeanGenerator.generateMockAgent();
        SocietyBean society = agent.getSociety();
        LegalForm legalForm = legalFormDao.add(new LegalForm(society.getLegalForm()));
        society.setLegalForm(new LegalFormBean(legalForm));
        Society persistedSociety = societyDao.add(new Society(society));
        agent.setSociety(new SocietyBean(persistedSociety));
        CivilityBean civility = agent.getCivility();
        Civility persistedCivility = civilityDao.add(new Civility(civility));
        agent.setCivility(new CivilityBean(persistedCivility));
    }

    /**
     * Test method for {@link be.jsams.server.service.management.impl.AgentServiceImpl
     * #create(be.jsams.common.bean.model.management.AgentBean)}.
     */
    @Test
    public void testCreate() {
        AgentBean created = service.create(agent);
        List<AgentBean> founds = service.findAll(agent.getSociety());
        assertTrue(founds.contains(created));
    }

    /**
     * Test method for {@link be.jsams.server.service.management.impl.AgentServiceImpl
     * #delete(be.jsams.common.bean.model.management.AgentBean)}.
     */
    @Test
    public void testDeleteProductCategoryBean() {
        AgentBean created = service.create(agent);
        service.delete(created);
        AgentBean found = service.findById(created.getId());
        assertNull(found);
    }

    /**
     * Test method for {@link be.jsams.server.service.management.impl.AgentServiceImpl
     * #delete(java.lang.Long)}.
     */
    @Test
    public void testDeleteLong() {
        AgentBean created = service.create(agent);
        Long id = created.getId();
        service.delete(id);
        AgentBean found = service.findById(id);
        assertNull(found);
    }

    /**
     * Test method for {@link be.jsams.server.service.management.impl.AgentServiceImpl#findAll(AgentBean))}.
     */
    @Test
    public void testFindAll() {
        AgentBean created = service.create(agent);
        List<AgentBean> founds = service.findAll(created.getSociety());
        assertTrue(founds.contains(created));
    }

    /**
     * Test method for {@link be.jsams.server.service.management.impl.AgentServiceImpl
     * #findById(java.lang.Long)}.
     */
    @Test
    public void testFindById() {
        AgentBean created = service.create(agent);
        AgentBean found = service.findById(created.getId());
        assertEquals(created, found);
    }

    /**
     * Test method for {@link be.jsams.server.service.management.impl.AgentServiceImpl
     * #update(be.jsams.common.bean.model.management.AgentBean)}.
     */
    @Test
    public void testUpdate() {
        AgentBean created = service.create(agent);
        String newName = "newName";
        created.setName(newName);
        service.update(created);
        AgentBean found = service.findById(created.getId());
        assertEquals(newName, found.getName());
    }

    /**
     * Test method for {@link be.jsams.server.service.management.impl.AgentServiceImpl
     * #findByCriteria(be.jsams.common.bean.model.management.AgentBean)}.
     */
    @Test
    public void testFindByCriteria() {
        AgentBean created = service.create(agent);
        AgentBean criteria = new AgentBean(created.getSociety());
        criteria.setName(created.getName());
        criteria.setCivility(created.getCivility());
        List<AgentBean> founds = service.findByCriteria(criteria);
        assertTrue(founds.contains(created));
    }

}
