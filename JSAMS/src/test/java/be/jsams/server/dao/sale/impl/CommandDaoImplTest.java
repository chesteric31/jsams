package be.jsams.server.dao.sale.impl;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import be.jsams.common.bean.model.sale.CommandBean;
import be.jsams.server.dao.AbstractJUnitTestClass;
import be.jsams.server.dao.PaymentModeDao;
import be.jsams.server.dao.SocietyDao;
import be.jsams.server.dao.management.AgentDao;
import be.jsams.server.dao.management.CustomerDao;
import be.jsams.server.dao.management.ProductCategoryDao;
import be.jsams.server.dao.management.ProductDao;
import be.jsams.server.dao.sale.CommandDao;
import be.jsams.server.model.Society;
import be.jsams.server.model.management.Agent;
import be.jsams.server.model.management.Customer;
import be.jsams.server.model.management.Product;
import be.jsams.server.model.management.ProductCategory;
import be.jsams.server.model.mock.MockModelGenerator;
import be.jsams.server.model.sale.Command;
import be.jsams.server.model.sale.detail.CommandDetail;

/**
 * Test class for {@link CommandDaoImpl}.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CommandDaoImplTest extends AbstractJUnitTestClass {

    @Autowired
    private CommandDao dao;
    private Command command;
    
    @Autowired
    private CustomerDao customerDao;
    
    @Autowired
    private PaymentModeDao paymentModeDao;

    @Autowired
    private SocietyDao societyDao;

    @Autowired
    private AgentDao agentDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Before
    public void setUp() {
        command = MockModelGenerator.generateMockCommand();
    }
    
    /**
     * Test method for {@link be.jsams.server.dao.sale.impl.CommandDaoImpl#findByCriteria(be.jsams.common.bean.model.sale.CommandBean)}.
     */
    @Test
    public void testFindByCriteria() {
        Customer customer = command.getCustomer();
        customer.setPaymentMode(paymentModeDao.findById(1L));
        Society persistedSociety = societyDao.add(customer.getSociety());
        customerDao.add(customer);
        Agent agent = command.getAgent();
        agent.setSociety(persistedSociety);
        agentDao.add(agent);
        CommandDetail commandDetail = command.getDetails().get(0);
        Product product = commandDetail.getProduct();
        ProductCategory category = product.getCategory();
        category.setSociety(persistedSociety);
        ProductCategory persistedCategory = productCategoryDao.add(category);
        product.setCategory(persistedCategory);
        Product persistedProduct = productDao.add(product);
        commandDetail.setProduct(persistedProduct);
        Command persistedCommand = dao.add(command);
        CommandBean criteria = new CommandBean(persistedCommand);
        List<Command> founds = dao.findByCriteria(criteria);
        assertTrue(founds.contains(persistedCommand));
    }

}
