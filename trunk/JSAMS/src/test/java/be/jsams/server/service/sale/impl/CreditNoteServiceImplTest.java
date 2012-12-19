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
import be.jsams.common.bean.model.sale.CreditNoteBean;
import be.jsams.common.bean.model.sale.CreditNoteMediator;
import be.jsams.common.bean.model.sale.detail.CreditNoteDetailBean;
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
import be.jsams.server.service.sale.CreditNoteService;

/**
 * Test class for {@link CreditNoteServiceImpl} class.
 * 
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class CreditNoteServiceImplTest extends BaseJUnitTestClass {

    @Autowired
    @Qualifier(value = "creditNoteServiceTarget")
    private CreditNoteService service;
    @Autowired
    private LegalFormDao legalFormDao;
    @Autowired
    private SocietyDao societyDao;
    private CreditNoteBean creditNote;
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
        creditNote = MockBeanGenerator.generateMockCreditNote();
        Society persistedSociety = persistSociety();
        SocietyBean society = new SocietyBean(persistedSociety);
        society.setLegalForm(new LegalFormBean(persistedSociety.getLegalForm()));
        creditNote.setSociety(society);
        Customer persistedCustomer = persistCustomer(persistedSociety);
        creditNote.setCustomer(createCustomer(society, persistedCustomer));
        List<CreditNoteDetailBean> details = creditNote.getDetails();
        updateDetail(society, details);
    }

    /**
     * Updates the {@link CreditNoteDetailBean}s with the products.
     * 
     * @param society the {@link SocietyBean} to use
     * @param details the list of {@link CreditNoteDetailBean} to use
     */
    private void updateDetail(SocietyBean society, List<CreditNoteDetailBean> details) {
        for (CreditNoteDetailBean detail : details) {
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
     * Updates the customer with the civility and other reference objects.
     * 
     * @param society the {@link SocietyBean} to use
     * @param persistedCustomer the {@link Customer} to use
     * @return the created {@link CustomerBean}
     */
    private CustomerBean createCustomer(SocietyBean society, Customer persistedCustomer) {
        CustomerBean customer = new CustomerBean(persistedCustomer, society);
        customer.setCivility(new CivilityBean(persistedCustomer.getCivility()));
        customer.setLegalForm(new LegalFormBean(persistedCustomer.getLegalForm()));
        customer.setPaymentMode(new PaymentModeBean(persistedCustomer.getPaymentMode()));
        AgentBean agent = new AgentBean(persistedCustomer.getAgent(), society);
        agent.setCivility(new CivilityBean(persistedCustomer.getAgent().getCivility()));
        customer.setAgent(agent);
        return customer;
    }

    /**
     * Persists the customer.
     * 
     * @param persistedSociety the society to use
     * @return the persisted customer
     */
    private Customer persistCustomer(Society persistedSociety) {
        CustomerBean customer = creditNote.getCustomer();
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
        Agent persistedAgent = persistAgent(customer.getAgent(), persistedSociety);
        AgentBean agent = new AgentBean(persistedAgent, society);
        agent.setCivility(new CivilityBean(persistedAgent.getCivility()));
        customer.setAgent(agent);
        return customerDao.add(new Customer(customer));
    }

    /**
     * Persists the society.
     * 
     * @return the persisted society
     */
    private Society persistSociety() {
        SocietyBean society = creditNote.getSociety();
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
     * Test method for
     * {@link be.jsams.server.service.sale.impl.CreditNoteServiceImpl
     * #create(be.jsams.common.bean.model.management.CreditNoteBean)}
     * .
     */
    @Test
    public void testCreate() {
        CreditNoteBean created = service.create(creditNote);
        List<CreditNoteBean> founds = service.findAll(creditNote.getSociety());
        assertTrue(founds.contains(created));
    }

    /**
     * Test method for
     * {@link be.jsams.server.service.sale.impl.CreditNoteServiceImpl
     * #delete(be.jsams.common.bean.model.sale.CreditNoteBean)}
     * .
     */
    @Test
    public void testDeleteCreditNoteBean() {
        CreditNoteBean created = service.create(creditNote);
        service.delete(created);
        CreditNoteBean found = service.findById(created.getId());
        assertNull(found);
    }

    /**
     * Test method for
     * {@link be.jsams.server.service.sale.impl.CreditNoteServiceImpl#delete(java.lang.Long)}
     * .
     */
    @Test
    public void testDeleteLong() {
        CreditNoteBean created = service.create(creditNote);
        Long id = created.getId();
        service.delete(id);
        CreditNoteBean found = service.findById(id);
        assertNull(found);
    }

    /**
     * Test method for
     * {@link be.jsams.server.service.sale.impl.CreditNoteServiceImpl#findAll(SocietyBean)
     * )}.
     */
    @Test
    public void testFindAll() {
        CreditNoteBean created = service.create(creditNote);
        List<CreditNoteBean> founds = service.findAll(created.getSociety());
        assertTrue(founds.contains(created));
    }

    /**
     * Test method for
     * {@link be.jsams.server.service.sale.impl.CreditNoteServiceImpl#findById(java.lang.Long)}
     * .
     */
    @Test
    public void testFindById() {
        CreditNoteBean created = service.create(creditNote);
        CreditNoteBean found = service.findById(created.getId());
        assertEquals(created, found);
    }

    /**
     * Test method for
     * {@link be.jsams.server.service.sale.impl.CreditNoteServiceImpl
     * #update(be.jsams.common.bean.model.sale.CreditNoteBean)}
     * .
     */
    @Test
    public void testUpdate() {
        CreditNoteBean created = service.create(creditNote);
        CreditNoteMediator mediator = new CreditNoteMediator();
        mediator.setCreditNoteBean(created);
        created.setMediator(mediator);
        for (CreditNoteDetailBean detail : created.getDetails()) {
            detail.setMediator(mediator);
        }
        String remark = "newRemark";
        created.setRemark(remark);
        service.update(created);
        CreditNoteBean found = service.findById(created.getId());
        assertEquals(remark, found.getRemark());
    }

    /**
     * Test method for
     * {@link be.jsams.server.service.sale.impl.CreditNoteServiceImpl
     * #findByCriteria(be.jsams.common.bean.model.sale.CreditNoteBean)}
     * .
     */
    @Test
    public void testFindByCriteria() {
        CreditNoteBean created = service.create(creditNote);
        CreditNoteBean criteria = new CreditNoteBean(created.getSociety(), created.getCustomer());
        List<CreditNoteBean> founds = service.findByCriteria(criteria);
        assertTrue(founds.contains(created));
    }

}
