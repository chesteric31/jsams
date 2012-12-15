package be.jsams.server.dao.sale.impl;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.sale.CreditNoteBean;
import be.jsams.server.BaseJUnitTestClass;
import be.jsams.server.dao.CivilityDao;
import be.jsams.server.dao.LegalFormDao;
import be.jsams.server.dao.PaymentModeDao;
import be.jsams.server.dao.SocietyDao;
import be.jsams.server.dao.management.AgentDao;
import be.jsams.server.dao.management.CustomerDao;
import be.jsams.server.dao.management.ProductCategoryDao;
import be.jsams.server.dao.management.ProductDao;
import be.jsams.server.dao.sale.CreditNoteDao;
import be.jsams.server.model.MockModelGenerator;
import be.jsams.server.model.PaymentMode;
import be.jsams.server.model.Society;
import be.jsams.server.model.management.Agent;
import be.jsams.server.model.management.Customer;
import be.jsams.server.model.management.Product;
import be.jsams.server.model.management.ProductCategory;
import be.jsams.server.model.sale.CreditNote;
import be.jsams.server.model.sale.detail.CreditNoteDetail;

/**
 * Test class for {@link CreditNoteDao} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class CreditNoteDaoImplTest extends BaseJUnitTestClass {

    @Autowired
    private CreditNoteDao dao;
    private CreditNote creditNote;

    private CreditNote persistedCreditNote;

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
        creditNote = MockModelGenerator.generateMockCreditNote();
        Customer customer = creditNote.getCustomer();
        
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
        
        CreditNoteDetail creditNoteDetail = creditNote.getDetails().get(0);
        Product product = creditNoteDetail.getProduct();
        ProductCategory category = product.getCategory();
        category.setSociety(persistedSociety);
        ProductCategory persistedCategory = productCategoryDao.add(category);
        product.setCategory(persistedCategory);
        Product persistedProduct = productDao.add(product);
        creditNoteDetail.setProduct(persistedProduct);

        societyBean = new SocietyBean(persistedSociety);
        customerBean = new CustomerBean(persistedCustomer, societyBean);
        
        persistedCreditNote = dao.add(creditNote);
        // necessary to avoid to have the details, not interesting here
        persistedCreditNote.setDetails(new ArrayList<CreditNoteDetail>());
    }

    /**
     * Test method for
     * {@link be.jsams.server.dao.sale.impl.CreditNoteDaoImpl#findByCriteria(Long,
     * be.jsams.common.bean.model.sale.CreditNoteBean)}
     * .
     */
    @Test
    public void testFindByCriteria() {
        CreditNoteBean criteria = new CreditNoteBean(persistedCreditNote, societyBean, customerBean);
        List<CreditNote> founds = dao.findByCriteria(criteria.getCustomer().getSociety().getId(), criteria);
        assertTrue(founds.contains(persistedCreditNote));
    }

    /**
     * Test method for {@link be.jsams.server.dao.sale.impl.CreditNoteDaoImpl#findAll(Long)}.
     */
    @Test
    public void testFindAll() {
        List<CreditNote> founds = dao.findAll(persistedCreditNote.getCustomer().getSociety().getId());
        assertTrue(founds.contains(persistedCreditNote));
    }

}
