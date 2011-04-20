package be.jsams.server.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import be.jsams.server.dao.AbstractJUnitTestClass;
import be.jsams.server.dao.AddressDao;
import be.jsams.server.dao.AgentDao;
import be.jsams.server.dao.CommandDao;
import be.jsams.server.dao.CommandDetailDao;
import be.jsams.server.dao.CustomerDao;
import be.jsams.server.dao.PaymentModeDao;
import be.jsams.server.dao.ProductCategoryDao;
import be.jsams.server.dao.ProductDao;
import be.jsams.server.model.Address;
import be.jsams.server.model.Agent;
import be.jsams.server.model.Command;
import be.jsams.server.model.CommandDetail;
import be.jsams.server.model.ContactInformation;
import be.jsams.server.model.Customer;
import be.jsams.server.model.PaymentMode;
import be.jsams.server.model.Product;
import be.jsams.server.model.ProductCategory;

/**
 * Test class for {@link CommandDetailDaoImpl}.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CommandDetailDaoImplTest extends AbstractJUnitTestClass {

	@Autowired
	private CommandDetailDao detailDao;
    @Autowired
    private CommandDao dao;
    @Autowired
    private AgentDao agentDao;
    @Autowired
    private AddressDao addressDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private PaymentModeDao paymentModeDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductCategoryDao productCategoryDao;
	
	private Command newCommand = null;
	private CommandDetail detail = null;
	
	@Before
	public void setUp() {
		newCommand = new Command();
		
		Address billingAddress = new Address();
		billingAddress.setCity("Brussels");
		billingAddress.setCountry("Belgium");
		billingAddress.setNumber("1");
		billingAddress.setStreet("Rue Neuve");
		billingAddress.setZipCode("1000");
		addressDao.add(billingAddress);
		newCommand.setBillingAddress(billingAddress);

        ContactInformation contactInformation = new ContactInformation();
        contactInformation.setPhone("+3271887755");
        
		Agent agent = new Agent();
		agent.setFunction("Saler");
		agent.setName("John Doe");
		agent.setAddress(billingAddress);
		agent.setContactInformation(contactInformation);
		agentDao.add(agent);
		newCommand.setAgent(agent);
		
		newCommand.setCreationDate(new Date());
		
		Customer customer = new Customer();
		customer.setBank1("Fortis");
		
		customer.setBillingAddress(billingAddress);
		
		customer.setContactInformation(contactInformation);
		
		customer.setCreditLimit(1000.00D);
		customer.setDeliveryAddress(billingAddress);
		customer.setName("Wyatt Earp");
		
		PaymentMode paymentMode = new PaymentMode();
		paymentMode.setLabel("CASH");
        paymentMode.setLabelFr("COMPTANT");
        paymentMode.setLabelNl("CONTENT");
		paymentModeDao.add(paymentMode);
		
		customer.setPaymentMode(paymentMode);
		
		customerDao.add(customer);
		newCommand.setCustomer(customer);
		
		newCommand.setDeliveryAddress(billingAddress);
		
		dao.add(newCommand);
		
		ProductCategory booksCategory = new ProductCategory();
		booksCategory.setLabel("Books");
        booksCategory.setLabelFr("Livres");
        booksCategory.setLabelNl("Boeken");
        productCategoryDao.add(booksCategory);
		
		Product book = new Product();
		book.setCategory(booksCategory);
		book.setName("Fight Club");
		book.setPrice(35.95D);
		book.setQuantityStock(15);
		book.setReorderLevel(10);
		book.setVatApplicable(6D);
		productDao.add(book);
		
		detail = new CommandDetail();
		detail.setCommand(newCommand);
		detail.setPrice(32.95D);
		detail.setProduct(book);
		detail.setQuantity(1);
	}

	@Test
	public void testAdd() {
		detailDao.add(detail);
	}

	@Test
	public void testFindAll() {
        detailDao.add(detail);
        assertTrue(detailDao.findAll().size() > 0);
	}

	@Test
	public void testFindById() {
        detailDao.add(detail);
        assertNotNull(detail.getId());
	}

	@Test
	public void testRemove() {
        detailDao.add(detail);
        Long id = detail.getId();
        assertNotNull(id);
        detailDao.delete(detail);
        assertNull(detailDao.findById(id));
	}

	@Test
	public void testUpdate() {
	    detailDao.add(detail);
	    detail.setPrice(30.95D);
	    detailDao.update(detail);
	    CommandDetail findById = detailDao.findById(detail.getId());
        assertEquals(0, findById.getPrice().compareTo(30.95D));
	}

	@Test
	public void testRemoveWithId() {
        detailDao.add(detail);
        Long id = detail.getId();
        assertNotNull(id);
        detailDao.delete(id);
        assertNull(detailDao.findById(id));
	}

}
