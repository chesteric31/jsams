package be.jsams.server.dao.sale.impl;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import be.jsams.common.bean.builder.ProductBeanBuilder;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.sale.CommandBean;
import be.jsams.server.BaseJUnitTestClass;
import be.jsams.server.dao.CivilityDao;
import be.jsams.server.dao.LegalFormDao;
import be.jsams.server.dao.PaymentModeDao;
import be.jsams.server.dao.SocietyDao;
import be.jsams.server.dao.management.AgentDao;
import be.jsams.server.dao.management.CustomerDao;
import be.jsams.server.dao.management.ProductCategoryDao;
import be.jsams.server.dao.management.ProductDao;
import be.jsams.server.dao.sale.CommandDao;
import be.jsams.server.model.MockModelGenerator;
import be.jsams.server.model.PaymentMode;
import be.jsams.server.model.Society;
import be.jsams.server.model.management.Agent;
import be.jsams.server.model.management.Customer;
import be.jsams.server.model.management.Product;
import be.jsams.server.model.management.ProductCategory;
import be.jsams.server.model.sale.Command;
import be.jsams.server.model.sale.detail.CommandDetail;

/**
 * Test class for {@link CommandDao} class.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CommandDaoImplTest extends BaseJUnitTestClass {

    @Autowired
    private CommandDao dao;
    private Command command;

    private Command persistedCommand;

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
    
    @Autowired
    private ProductBeanBuilder productBeanBuilder;

    /**
     * Setup method.
     */
    @Before
    public void setUp() {
        command = MockModelGenerator.generateMockCommand();
        Customer customer = command.getCustomer();
        
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
        
        Agent agent = command.getAgent();
        agent.setSociety(persistedSociety);
        civilityDao.add(agent.getCivility());
        Agent persistedAgent = agentDao.add(agent);
        
        CommandDetail commandDetail = command.getDetails().get(0);
        Product product = commandDetail.getProduct();
        ProductCategory category = product.getCategory();
        category.setSociety(persistedSociety);
        ProductCategory persistedCategory = productCategoryDao.add(category);
        product.setCategory(persistedCategory);
        Product persistedProduct = productDao.add(product);
        commandDetail.setProduct(persistedProduct);

        societyBean = new SocietyBean(persistedSociety);
        customerBean = new CustomerBean(persistedCustomer, societyBean);
        agentBean = new AgentBean(persistedAgent, societyBean);
        
        persistedCommand = dao.add(command);
        // necessary to avoid to have the details, not interesting here
        persistedCommand.setDetails(new ArrayList<CommandDetail>());
    }

    /**
     * Test method for
     * {@link be.jsams.server.dao.sale.impl.CommandDaoImpl#findByCriteria(Long,
     * be.jsams.common.bean.model.sale.CommandBean)}
     * .
     */
    @Test
    public void testFindByCriteria() {
        CommandBean criteria = new CommandBean(persistedCommand, societyBean, customerBean, agentBean,
                productBeanBuilder);
        List<Command> founds = dao.findByCriteria(criteria.getCustomer().getSociety().getId(), criteria);
        assertTrue(founds.contains(persistedCommand));
    }

    /**
     * Test method for
     * {@link be.jsams.server.dao.sale.impl.CommandDaoImpl#findAll(Long)}.
     */
    @Test
    public void testFindAll() {
        List<Command> founds = dao.findAll(persistedCommand.getCustomer().getSociety().getId());
        assertTrue(founds.contains(persistedCommand));
    }

}
