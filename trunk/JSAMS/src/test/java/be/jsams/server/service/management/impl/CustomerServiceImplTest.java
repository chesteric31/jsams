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
import be.jsams.common.bean.model.PaymentModeBean;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.server.BaseJUnitTestClass;
import be.jsams.server.dao.CivilityDao;
import be.jsams.server.dao.LegalFormDao;
import be.jsams.server.dao.PaymentModeDao;
import be.jsams.server.dao.SocietyDao;
import be.jsams.server.dao.management.AgentDao;
import be.jsams.server.model.Civility;
import be.jsams.server.model.LegalForm;
import be.jsams.server.model.PaymentMode;
import be.jsams.server.model.Society;
import be.jsams.server.model.management.Agent;
import be.jsams.server.service.management.CustomerService;

/**
 * Test class for {@link CustomerServiceImpl} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class CustomerServiceImplTest extends BaseJUnitTestClass {

    @Autowired
    @Qualifier(value = "customerServiceTarget")
    private CustomerService service;
    @Autowired
    private LegalFormDao legalFormDao;
    @Autowired
    private SocietyDao societyDao;
    private CustomerBean customer;
    @Autowired
    private CivilityDao civilityDao;
    @Autowired
    private PaymentModeDao paymentModeDao;
    @Autowired
    private AgentDao agentDao;

    /**
     * @throws java.lang.Exception a possible {@link Exception}
     */
    @Before
    public void setUp() throws Exception {
        customer = MockBeanGenerator.generateMockCustomer();
        Society persistedSociety = persistSociety();
        SocietyBean society = new SocietyBean(persistedSociety);
        society.setLegalForm(new LegalFormBean(persistedSociety.getLegalForm()));
        customer.setSociety(society);
        CivilityBean civility = customer.getCivility();
        Civility persistedCivility = civilityDao.add(new Civility(civility));
        customer.setCivility(new CivilityBean(persistedCivility));
        LegalFormBean legalForm = customer.getLegalForm();
        LegalForm persistedLegalForm = legalFormDao.add(new LegalForm(legalForm));
        customer.setLegalForm(new LegalFormBean(persistedLegalForm));
        PaymentModeBean paymentMode = customer.getPaymentMode();
        PaymentMode persistedPaymentMode = paymentModeDao.add(new PaymentMode(paymentMode));
        customer.setPaymentMode(new PaymentModeBean(persistedPaymentMode));
        Agent persistedAgent = persistAgent(persistedSociety);
        customer.setAgent(new AgentBean(persistedAgent, society));
    }

    /**
     * Persists the society.
     * 
     * @return the persisted society
     */
    private Society persistSociety() {
        SocietyBean society = customer.getSociety();
        LegalForm legalForm = legalFormDao.add(new LegalForm(society.getLegalForm()));
        society.setLegalForm(new LegalFormBean(legalForm));
        Society persistedSociety = societyDao.add(new Society(society));
        return persistedSociety;
    }

    /**
     * Persists the agent.
     * 
     * @param persistedSociety the society to use
     * @return the persisted agent
     */
    private Agent persistAgent(Society persistedSociety) {
        AgentBean agent = customer.getAgent();
        agent.setSociety(new SocietyBean(persistedSociety));
        CivilityBean civility = agent.getCivility();
        Civility persistedCivility = civilityDao.add(new Civility(civility));
        agent.setCivility(new CivilityBean(persistedCivility));
        Agent persistedAgent = agentDao.add(new Agent(agent));
        return persistedAgent;
    }

    /**
     * Test method for {@link be.jsams.server.service.management.impl.CustomerServiceImpl
     * #create(be.jsams.common.bean.model.management.CustomerBean)}.
     */
    @Test
    public void testCreate() {
        CustomerBean created = service.create(customer);
        List<CustomerBean> founds = service.findAll(customer.getSociety());
        assertTrue(founds.contains(created));
    }

    /**
     * Test method for {@link be.jsams.server.service.management.impl.CustomerServiceImpl
     * #delete(be.jsams.common.bean.model.management.CustomerBean)}.
     */
    @Test
    public void testDeleteCustomerBean() {
        CustomerBean created = service.create(customer);
        service.delete(created);
        CustomerBean found = service.findById(created.getId());
        assertNull(found);
    }

    /**
     * Test method for {@link be.jsams.server.service.management.impl.CustomerServiceImpl
     * #delete(java.lang.Long)}.
     */
    @Test
    public void testDeleteLong() {
        CustomerBean created = service.create(customer);
        Long id = created.getId();
        service.delete(id);
        CustomerBean found = service.findById(id);
        assertNull(found);
    }

    /**
     * Test method for {@link be.jsams.server.service.management.impl.CustomerServiceImpl#findAll(SocietyBean))}.
     */
    @Test
    public void testFindAll() {
        CustomerBean created = service.create(customer);
        List<CustomerBean> founds = service.findAll(created.getSociety());
        assertTrue(founds.contains(created));
    }

    /**
     * Test method for {@link be.jsams.server.service.management.impl.CustomerServiceImpl
     * #findById(java.lang.Long)}.
     */
    @Test
    public void testFindById() {
        CustomerBean created = service.create(customer);
        CustomerBean found = service.findById(created.getId());
        assertEquals(created, found);
    }

    /**
     * Test method for {@link be.jsams.server.service.management.impl.CustomerServiceImpl
     * #update(be.jsams.common.bean.model.management.CustomerBean)}.
     */
    @Test
    public void testUpdate() {
        CustomerBean created = service.create(customer);
        String newName = "newName";
        created.setName(newName);
        service.update(created);
        CustomerBean found = service.findById(created.getId());
        assertEquals(newName, found.getName());
    }

    /**
     * Test method for {@link be.jsams.server.service.management.impl.CustomerServiceImpl
     * #findByCriteria(be.jsams.common.bean.model.management.CustomerBean)}.
     */
    @Test
    public void testFindByCriteria() {
        CustomerBean created = service.create(customer);
        CustomerBean criteria = new CustomerBean(created.getSociety());
        criteria.setName(created.getName());
        criteria.setCivility(created.getCivility());
        criteria.setPaymentMode(created.getPaymentMode());
        criteria.setLegalForm(created.getLegalForm());
        List<CustomerBean> founds = service.findByCriteria(criteria);
        assertTrue(founds.contains(created));
    }

}
