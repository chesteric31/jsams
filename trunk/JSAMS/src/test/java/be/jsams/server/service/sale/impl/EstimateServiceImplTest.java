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
import be.jsams.common.bean.model.sale.EstimateBean;
import be.jsams.common.bean.model.sale.EstimateMediator;
import be.jsams.common.bean.model.sale.detail.EstimateDetailBean;
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
import be.jsams.server.service.sale.EstimateService;

/**
 * Test class for {@link EstimateServiceImpl} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class EstimateServiceImplTest extends BaseJUnitTestClass {

    @Autowired
    @Qualifier(value = "estimateServiceTarget")
    private EstimateService service;
    @Autowired
    private LegalFormDao legalFormDao;
    @Autowired
    private SocietyDao societyDao;
    private EstimateBean estimate;
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
        estimate = MockBeanGenerator.generateMockEstimate();
        Society persistedSociety = persistSociety();
        SocietyBean society = new SocietyBean(persistedSociety);
        society.setLegalForm(new LegalFormBean(persistedSociety.getLegalForm()));
        estimate.setSociety(society);
        Agent persistedAgent = persistAgent(estimate.getAgent(), persistedSociety);
        AgentBean agent = new AgentBean(persistedAgent, society);
        agent.setCivility(new CivilityBean(persistedAgent.getCivility()));
//        agent.setSociety(new SocietyBean(persistedSociety));
        estimate.setAgent(agent);
        Customer persistedCustomer = persistCustomer(persistedSociety);
        CustomerBean customer = new CustomerBean(persistedCustomer, society);
        customer.setAgent(agent);
        customer.setCivility(new CivilityBean(persistedCustomer.getCivility()));
        customer.setLegalForm(new LegalFormBean(persistedCustomer.getLegalForm()));
        customer.setPaymentMode(new PaymentModeBean(persistedCustomer.getPaymentMode()));
        estimate.setCustomer(customer);
        List<EstimateDetailBean> details = estimate.getDetails();
        for (EstimateDetailBean detail : details) {
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
     * Persists the customer.
     * 
     * @param persistedSociety the society to use
     * @return the persisted customer
     */
    private Customer persistCustomer(Society persistedSociety) {
        CustomerBean customer = estimate.getCustomer();
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
        SocietyBean society = estimate.getSociety();
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
     * Test method for {@link be.jsams.server.service.management.impl.EstimateServiceImpl
     * #create(be.jsams.common.bean.model.management.EstimateBean)}.
     */
    @Test
    public void testCreate() {
        EstimateBean created = service.create(estimate);
        List<EstimateBean> founds = service.findAll(estimate.getSociety());
        assertTrue(founds.contains(created));
    }

    /**
     * Test method for {@link be.jsams.server.service.management.impl.EstimateServiceImpl
     * #delete(be.jsams.common.bean.model.management.EstimateBean)}.
     */
    @Test
    public void testDeleteEstimateBean() {
        EstimateBean created = service.create(estimate);
        service.delete(created);
        EstimateBean found = service.findById(created.getId());
        assertNull(found);
    }

    /**
     * Test method for {@link be.jsams.server.service.management.impl.EstimateServiceImpl
     * #delete(java.lang.Long)}.
     */
    @Test
    public void testDeleteLong() {
        EstimateBean created = service.create(estimate);
        Long id = created.getId();
        service.delete(id);
        EstimateBean found = service.findById(id);
        assertNull(found);
    }

    /**
     * Test method for {@link be.jsams.server.service.management.impl.EstimateServiceImpl#findAll(SocietyBean))}.
     */
    @Test
    public void testFindAll() {
        EstimateBean created = service.create(estimate);
        List<EstimateBean> founds = service.findAll(created.getSociety());
        assertTrue(founds.contains(created));
    }

    /**
     * Test method for {@link be.jsams.server.service.management.impl.EstimateCustomerServiceImpl
     * #findById(java.lang.Long)}.
     */
    @Test
    public void testFindById() {
        EstimateBean created = service.create(estimate);
        EstimateBean found = service.findById(created.getId());
        assertEquals(created, found);
    }

    /**
     * Test method for {@link be.jsams.server.service.management.impl.EstimateServiceImpl
     * #update(be.jsams.common.bean.model.sale.EstimateBean)}.
     */
    @Test
    public void testUpdate() {
        EstimateBean created = service.create(estimate);
        EstimateMediator mediator = new EstimateMediator();
        mediator.setEstimateBean(created);
        created.setMediator(mediator);
        for (EstimateDetailBean detail : created.getDetails()) {
            detail.setMediator(mediator);
        }
        Double discountRate = 12D;
        created.setDiscountRate(discountRate);
        service.update(created);
        EstimateBean found = service.findById(created.getId());
        assertEquals(discountRate, found.getDiscountRate());
    }

    /**
     * Test method for {@link be.jsams.server.service.management.impl.EstimateServiceImpl
     * #findByCriteria(be.jsams.common.bean.model.sale.EstimateBean)}.
     */
    @Test
    public void testFindByCriteria() {
        EstimateBean created = service.create(estimate);
        EstimateBean criteria = new EstimateBean(created.getSociety(), created.getCustomer(), created.getAgent());
        List<EstimateBean> founds = service.findByCriteria(criteria);
        assertTrue(founds.contains(created));
    }

}
