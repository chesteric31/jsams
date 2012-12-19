package be.jsams.server.service.sale.impl;

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
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.common.bean.model.management.ProductCategoryBean;
import be.jsams.common.bean.model.sale.CommandBean;
import be.jsams.common.bean.model.sale.CommandMediator;
import be.jsams.common.bean.model.sale.detail.CommandDetailBean;
import be.jsams.server.BaseJUnitTestClass;
import be.jsams.server.dao.CivilityDao;
import be.jsams.server.dao.LegalFormDao;
import be.jsams.server.dao.PaymentModeDao;
import be.jsams.server.dao.SocietyDao;
import be.jsams.server.dao.management.AgentDao;
import be.jsams.server.dao.management.CustomerDao;
import be.jsams.server.dao.management.ProductCategoryDao;
import be.jsams.server.dao.management.ProductDao;
import be.jsams.server.model.Civility;
import be.jsams.server.model.LegalForm;
import be.jsams.server.model.PaymentMode;
import be.jsams.server.model.Society;
import be.jsams.server.model.management.Agent;
import be.jsams.server.model.management.Customer;
import be.jsams.server.model.management.Product;
import be.jsams.server.model.management.ProductCategory;
import be.jsams.server.service.sale.CommandService;

/**
 * Test class for {@link CommandServiceImpl} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class CommandServiceImplTest extends BaseJUnitTestClass {

    @Autowired
    @Qualifier(value = "commandServiceTarget")
    private CommandService service;
    @Autowired
    private LegalFormDao legalFormDao;
    @Autowired
    private SocietyDao societyDao;
    private CommandBean command;
    @Autowired
    private CivilityDao civilityDao;
    @Autowired
    private PaymentModeDao paymentModeDao;
    @Autowired
    private AgentDao agentDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductCategoryDao productCategoryDao;

    /**
     * @throws java.lang.Exception a possible {@link Exception}
     */
    @Before
    public void setUp() throws Exception {
        command = MockBeanGenerator.generateMockCommand();
        Society persistedSociety = persistSociety();
        SocietyBean society = new SocietyBean(persistedSociety);
        society.setLegalForm(new LegalFormBean(persistedSociety.getLegalForm()));
        command.setSociety(society);
        Agent persistedAgent = persistAgent(command.getAgent(), persistedSociety);
        AgentBean agent = new AgentBean(persistedAgent, society);
        agent.setCivility(new CivilityBean(persistedAgent.getCivility()));
        command.setAgent(agent);
        Customer persistedCustomer = persistCustomer(persistedSociety);
        command.setCustomer(createCustomerWithAgent(society, agent, persistedCustomer));
        List<CommandDetailBean> details = command.getDetails();
        updateDetail(society, details);
    }

    /**
     * Updates the {@link CommandDetailBean}s with the products.
     * 
     * @param society the {@link SocietyBean} to use
     * @param details the list of {@link CommandDetailBean} to use
     */
    private void updateDetail(SocietyBean society, List<CommandDetailBean> details) {
        for (CommandDetailBean detail : details) {
            ProductBean product = detail.getProduct();
            ProductCategoryBean category = product.getCategory();
            category.setSociety(society);
            ProductCategory productCategory = productCategoryDao.add(new ProductCategory(category));
            product.setCategory(new ProductCategoryBean(productCategory, society));
            Product persistedProduct = productDao.add(new Product(product));
            detail.setProduct(new ProductBean(persistedProduct, society));
        }
    }

    /**
     * Updates the customer with the agent.
     * 
     * @param society the {@link SocietyBean} to use
     * @param agent the {@link AgentBean} to use
     * @param persistedCustomer the {@link Customer} to use
     * @return the created {@link CustomerBean}
     */
    private CustomerBean createCustomerWithAgent(SocietyBean society, AgentBean agent, Customer persistedCustomer) {
        CustomerBean customer = new CustomerBean(persistedCustomer, society);
        customer.setAgent(agent);
        customer.setCivility(new CivilityBean(persistedCustomer.getCivility()));
        customer.setLegalForm(new LegalFormBean(persistedCustomer.getLegalForm()));
        customer.setPaymentMode(new PaymentModeBean(persistedCustomer.getPaymentMode()));
        return customer;
    }

    /**
     * Persists the customer.
     * 
     * @param persistedSociety the society to use
     * @return the persisted customer
     */
    private Customer persistCustomer(Society persistedSociety) {
        CustomerBean customer = command.getCustomer();
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
        customer.setAgent(new AgentBean(persistAgent(customer.getAgent(), persistedSociety), society));
        return customerDao.add(new Customer(customer));
    }

    /**
     * Persists the society.
     * 
     * @return the persisted society
     */
    private Society persistSociety() {
        SocietyBean society = command.getSociety();
        LegalForm legalForm = legalFormDao.add(new LegalForm(society.getLegalForm()));
        society.setLegalForm(new LegalFormBean(legalForm));
        Society persistedSociety = societyDao.add(new Society(society));
        return persistedSociety;
    }

    /**
     * Persists the agent.
     * 
     * @param agent the agent to use
     * @param persistedSociety the society to use
     * @return the persisted agent
     */
    private Agent persistAgent(AgentBean agent, Society persistedSociety) {
        agent.setSociety(new SocietyBean(persistedSociety));
        CivilityBean civility = agent.getCivility();
        Civility persistedCivility = new Civility(civility);
        Agent persistedAgent = new Agent(agent);
        if (persistedCivility.getId() == null) {
            persistedCivility = civilityDao.add(persistedCivility);
            agent.setCivility(new CivilityBean(persistedCivility));
            persistedAgent = agentDao.add(new Agent(agent));
        }
        return persistedAgent;
    }

    /**
     * Test method for {@link be.jsams.server.service.sale.impl.CommandServiceImpl
     * #create(be.jsams.common.bean.model.management.CommandBean)}.
     */
    @Test
    public void testCreate() {
        CommandBean created = service.create(command);
        List<CommandBean> founds = service.findAll(command.getSociety());
        assertTrue(founds.contains(created));
    }

    /**
     * Test method for {@link be.jsams.server.service.sale.impl.CommandServiceImpl
     * #delete(be.jsams.common.bean.model.sale.CommandBean)}.
     */
    @Test
    public void testDeleteCommandBean() {
        CommandBean created = service.create(command);
        service.delete(created);
        CommandBean found = service.findById(created.getId());
        assertNull(found);
    }

    /**
     * Test method for {@link be.jsams.server.service.sale.impl.CommandServiceImpl
     * #delete(java.lang.Long)}.
     */
    @Test
    public void testDeleteLong() {
        CommandBean created = service.create(command);
        Long id = created.getId();
        service.delete(id);
        CommandBean found = service.findById(id);
        assertNull(found);
    }

    /**
     * Test method for {@link be.jsams.server.service.sale.impl.CommandServiceImpl#findAll(SocietyBean))}.
     */
    @Test
    public void testFindAll() {
        CommandBean created = service.create(command);
        List<CommandBean> founds = service.findAll(created.getSociety());
        assertTrue(founds.contains(created));
    }

    /**
     * Test method for {@link be.jsams.server.service.sale.impl.CommandServiceImpl
     * #findById(java.lang.Long)}.
     */
    @Test
    public void testFindById() {
        CommandBean created = service.create(command);
        CommandBean found = service.findById(created.getId());
        assertEquals(created, found);
    }

    /**
     * Test method for {@link be.jsams.server.service.sale.impl.CommandServiceImpl
     * #update(be.jsams.common.bean.model.sale.CommandBean)}.
     */
    @Test
    public void testUpdate() {
        CommandBean created = service.create(command);
        CommandMediator mediator = new CommandMediator();
        mediator.setCommandBean(created);
        created.setMediator(mediator);
        for (CommandDetailBean detail : created.getDetails()) {
            detail.setMediator(mediator);
        }
        Double discountRate = 12D;
        created.setDiscountRate(discountRate);
        service.update(created);
        CommandBean found = service.findById(created.getId());
        assertEquals(discountRate, found.getDiscountRate());
    }

    /**
     * Test method for {@link be.jsams.server.service.sale.impl.CommandServiceImpl
     * #findByCriteria(be.jsams.common.bean.model.sale.CommandBean)}.
     */
    @Test
    public void testFindByCriteria() {
        CommandBean created = service.create(command);
        CommandBean criteria = new CommandBean(created.getSociety(), created.getCustomer(), created.getAgent());
        List<CommandBean> founds = service.findByCriteria(criteria);
        assertTrue(founds.contains(created));
    }

}
