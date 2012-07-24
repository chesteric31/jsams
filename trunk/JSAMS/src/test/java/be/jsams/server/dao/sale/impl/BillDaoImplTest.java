package be.jsams.server.dao.sale.impl;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import be.jsams.common.bean.model.PaymentModeBean;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.sale.BillBean;
import be.jsams.server.dao.BaseJUnitTestClass;
import be.jsams.server.dao.CivilityDao;
import be.jsams.server.dao.LegalFormDao;
import be.jsams.server.dao.PaymentModeDao;
import be.jsams.server.dao.SocietyDao;
import be.jsams.server.dao.management.AgentDao;
import be.jsams.server.dao.management.CustomerDao;
import be.jsams.server.dao.management.ProductCategoryDao;
import be.jsams.server.dao.management.ProductDao;
import be.jsams.server.dao.sale.BillDao;
import be.jsams.server.model.MockModelGenerator;
import be.jsams.server.model.PaymentMode;
import be.jsams.server.model.Society;
import be.jsams.server.model.management.Agent;
import be.jsams.server.model.management.Customer;
import be.jsams.server.model.management.Product;
import be.jsams.server.model.management.ProductCategory;
import be.jsams.server.model.sale.Bill;
import be.jsams.server.model.sale.detail.BillDetail;

/**
 * Test class for {@link BillDao} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class BillDaoImplTest extends BaseJUnitTestClass {

    @Autowired
    private BillDao dao;
    private Bill bill;

    private Bill persistedBill;

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
    private PaymentModeBean paymentModeBean;
    
    /**
     * Setup method.
     */
    @Before
    public void setUp() {
        bill = MockModelGenerator.generateMockBill();
        Customer customer = bill.getCustomer();
        
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
        
        BillDetail billDetail = bill.getDetails().get(0);
        Product product = billDetail.getProduct();
        ProductCategory category = product.getCategory();
        category.setSociety(persistedSociety);
        ProductCategory persistedCategory = productCategoryDao.add(category);
        product.setCategory(persistedCategory);
        Product persistedProduct = productDao.add(product);
        billDetail.setProduct(persistedProduct);

        societyBean = new SocietyBean(persistedSociety);
        customerBean = new CustomerBean(persistedCustomer, societyBean);
        PaymentMode persistedBillPaymentMode = paymentModeDao.add(bill.getPaymentMode());
        paymentModeBean = new PaymentModeBean(persistedBillPaymentMode);
        
        persistedBill = dao.add(bill);
        dao.setCurrentSociety(societyBean);
        // necessary to avoid to have the details, not interesting here
        persistedBill.setDetails(new ArrayList<BillDetail>());
    }

    /**
     * Test method for
     * {@link be.jsams.server.dao.sale.impl.BillDaoImpl#findByCriteria(
     * be.jsams.common.bean.model.sale.BillBean)}
     * .
     */
    @Test
    public void testFindByCriteria() {
        BillBean criteria = new BillBean(persistedBill, societyBean, customerBean, paymentModeBean);
        List<Bill> founds = dao.findByCriteria(criteria);
        assertTrue(founds.contains(persistedBill));
    }

    /**
     * Test method for {@link be.jsams.server.dao.sale.impl.BillDaoImpl#findAll()}.
     */
    @Test
    public void testFindAll() {
        List<Bill> founds = dao.findAll();
        assertTrue(founds.contains(persistedBill));
    }

}
