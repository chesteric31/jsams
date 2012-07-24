package be.jsams.server.dao.sale.impl;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.sale.DeliveryOrderBean;
import be.jsams.server.dao.BaseJUnitTestClass;
import be.jsams.server.dao.CivilityDao;
import be.jsams.server.dao.LegalFormDao;
import be.jsams.server.dao.PaymentModeDao;
import be.jsams.server.dao.SocietyDao;
import be.jsams.server.dao.management.AgentDao;
import be.jsams.server.dao.management.CustomerDao;
import be.jsams.server.dao.management.ProductCategoryDao;
import be.jsams.server.dao.management.ProductDao;
import be.jsams.server.dao.sale.DeliveryOrderDao;
import be.jsams.server.model.MockModelGenerator;
import be.jsams.server.model.PaymentMode;
import be.jsams.server.model.Society;
import be.jsams.server.model.management.Agent;
import be.jsams.server.model.management.Customer;
import be.jsams.server.model.management.Product;
import be.jsams.server.model.management.ProductCategory;
import be.jsams.server.model.sale.DeliveryOrder;
import be.jsams.server.model.sale.detail.DeliveryOrderDetail;

/**
 * Test class for {@link DeliveryOrderDao} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class DeliveryOrderDaoImplTest extends BaseJUnitTestClass {

    @Autowired
    private DeliveryOrderDao dao;
    private DeliveryOrder deliveryOrder;

    private DeliveryOrder persistedDeliveryOrder;

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

    /**
     * Setup method.
     */
    @Before
    public void setUp() {
        deliveryOrder = MockModelGenerator.generateMockDeliveryOrder();
        Customer customer = deliveryOrder.getCustomer();
        
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
        
        DeliveryOrderDetail deliveryOrderDetail = deliveryOrder.getDetails().get(0);
        Product product = deliveryOrderDetail.getProduct();
        ProductCategory category = product.getCategory();
        category.setSociety(persistedSociety);
        ProductCategory persistedCategory = productCategoryDao.add(category);
        product.setCategory(persistedCategory);
        Product persistedProduct = productDao.add(product);
        deliveryOrderDetail.setProduct(persistedProduct);

        societyBean = new SocietyBean(persistedSociety);
        customerBean = new CustomerBean(persistedCustomer, societyBean);
        
        persistedDeliveryOrder = dao.add(deliveryOrder);
        dao.setCurrentSociety(societyBean);
        // necessary to avoid to have the details, not interesting here
        persistedDeliveryOrder.setDetails(new ArrayList<DeliveryOrderDetail>());
    }

    /**
     * Test method for
     * {@link be.jsams.server.dao.sale.impl.DeliveryOrderDaoImpl#findByCriteria(
     * be.jsams.common.bean.model.sale.DeliveryOrderBean)}
     * .
     */
    @Test
    public void testFindByCriteria() {
        DeliveryOrderBean criteria = new DeliveryOrderBean(persistedDeliveryOrder, societyBean, customerBean);
        List<DeliveryOrder> founds = dao.findByCriteria(criteria);
        assertTrue(founds.contains(persistedDeliveryOrder));
    }

    /**
     * Test method for {@link be.jsams.server.dao.sale.impl.DeliveryOrderDaoImpl#findAll()}.
     */
    @Test
    public void testFindAll() {
        List<DeliveryOrder> founds = dao.findAll();
        assertTrue(founds.contains(persistedDeliveryOrder));
    }

}
