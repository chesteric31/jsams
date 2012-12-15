package be.jsams.server.dao.sale.impl;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.sale.EstimateBean;
import be.jsams.server.BaseJUnitTestClass;
import be.jsams.server.dao.CivilityDao;
import be.jsams.server.dao.LegalFormDao;
import be.jsams.server.dao.PaymentModeDao;
import be.jsams.server.dao.SocietyDao;
import be.jsams.server.dao.management.AgentDao;
import be.jsams.server.dao.management.CustomerDao;
import be.jsams.server.dao.management.ProductCategoryDao;
import be.jsams.server.dao.management.ProductDao;
import be.jsams.server.dao.sale.EstimateDao;
import be.jsams.server.model.MockModelGenerator;
import be.jsams.server.model.PaymentMode;
import be.jsams.server.model.Society;
import be.jsams.server.model.management.Agent;
import be.jsams.server.model.management.Customer;
import be.jsams.server.model.management.Product;
import be.jsams.server.model.management.ProductCategory;
import be.jsams.server.model.sale.Estimate;
import be.jsams.server.model.sale.detail.EstimateDetail;

/**
 * Test class for {@link EstimateDao} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class EstimateDaoImplTest extends BaseJUnitTestClass {

    @Autowired
    private EstimateDao dao;
    private Estimate estimate;

    private Estimate persistedEstimate;

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private PaymentModeDao paymentModeDao;
    @Autowired
    private CivilityDao civilityDao;
    @Autowired
    private LegalFormDao legalFormDao;

    @Autowired
    private SocietyDao societyDao;

    @Autowired
    private AgentDao agentDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductCategoryDao productCategoryDao;
    
    private SocietyBean societyBean;
    private CustomerBean customerBean;
    private AgentBean agentBean;

    /**
     * Setup method.
     */
    @Before
    public void setUp() {
        estimate = MockModelGenerator.generateMockEstimate();
        Customer customer = estimate.getCustomer();
        
        Agent customerAgent = customer.getAgent();
        civilityDao.add(customerAgent.getCivility());
        societyDao.add(customerAgent.getSociety());
        agentDao.add(customerAgent);
        
        PaymentMode persistedPaymentMode = paymentModeDao.add(customer.getPaymentMode());
        customer.setPaymentMode(persistedPaymentMode);
        Society persistedSociety = societyDao.add(customer.getSociety());
        civilityDao.add(customer.getCivility());
        legalFormDao.add(customer.getLegalForm());
        Customer persistedCustomer = customerDao.add(customer);
        
        Agent agent = estimate.getAgent();
        agent.setSociety(persistedSociety);
        civilityDao.add(agent.getCivility());
        Agent persistedAgent = agentDao.add(agent);
        
        EstimateDetail estimateDetail = estimate.getDetails().get(0);
        Product product = estimateDetail.getProduct();
        ProductCategory category = product.getCategory();
        category.setSociety(persistedSociety);
        ProductCategory persistedCategory = productCategoryDao.add(category);
        product.setCategory(persistedCategory);
        Product persistedProduct = productDao.add(product);
        estimateDetail.setProduct(persistedProduct);

        societyBean = new SocietyBean(persistedSociety);
        customerBean = new CustomerBean(persistedCustomer, societyBean);
        agentBean = new AgentBean(persistedAgent, societyBean);
        
        persistedEstimate = dao.add(estimate);
        // necessary to avoid to have the details, not interesting here
        persistedEstimate.setDetails(new ArrayList<EstimateDetail>());
    }
    
    /**
     * Test method for
     * {@link be.jsams.server.dao.sale.impl.EstimateDaoImpl#findByCriteria(Long,
     * be.jsams.common.bean.model.sale.EstimateBean)}
     * .
     */
    @Test
    public void testFindByCriteria() {
        EstimateBean criteria = new EstimateBean(persistedEstimate, societyBean, customerBean, agentBean);
        List<Estimate> founds = dao.findByCriteria(criteria.getSociety().getId(), criteria);
        assertTrue(founds.contains(persistedEstimate));
    }

    /**
     * Test method for {@link be.jsams.server.dao.sale.impl.EstimateDaoImpl#findAll(Long)}.
     */
    @Test
    public void testFindAll() {
        List<Estimate> founds = dao.findAll(persistedEstimate.getCustomer().getSociety().getId());
        assertTrue(founds.contains(persistedEstimate));
    }

}
