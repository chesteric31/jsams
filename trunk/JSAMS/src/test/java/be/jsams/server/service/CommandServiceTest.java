package be.jsams.server.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import be.jsams.common.bean.model.sale.CommandBean;
import be.jsams.server.dao.AbstractJUnitTestClass;
import be.jsams.server.dao.AddressDao;
import be.jsams.server.dao.PaymentModeDao;
import be.jsams.server.dao.management.AgentDao;
import be.jsams.server.dao.management.CustomerDao;
import be.jsams.server.dao.management.ProductCategoryDao;
import be.jsams.server.dao.management.ProductDao;
import be.jsams.server.model.Address;
import be.jsams.server.model.ContactInformation;
import be.jsams.server.model.PaymentMode;
import be.jsams.server.model.management.Agent;
import be.jsams.server.model.management.Customer;
import be.jsams.server.model.management.Product;
import be.jsams.server.model.management.ProductCategory;
import be.jsams.server.model.sale.Command;
import be.jsams.server.model.sale.CommandDetail;
import be.jsams.server.service.sale.CommandService;

/**
 * Test class for {@link CommandService}.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class CommandServiceTest extends AbstractJUnitTestClass {

	@Autowired
	@Qualifier(value = "commandService")
	private CommandService commandService;
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

	@Before
	public void setUp() throws Exception {
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
		Agent contact = new Agent();
		contact.setFunction("Saler");
		contact.setName("John Doe");
		contact.setAddress(billingAddress);
		contact.setContactInformation(contactInformation);
		agentDao.add(contact);
		newCommand.setAgent(contact);
		
		newCommand.setCreationDate(new Date());
		
		Customer customer = new Customer();
		customer.setBank1("Fortis");
		
		customer.setBillingAddress(billingAddress);
		
		customer.setContactInformation(contactInformation);
		
		customer.setCreditLimit(Double.valueOf(1000.00));
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
	}
	
	@Test
	public void testCreate() {
        ProductCategory booksCategory = new ProductCategory();
        booksCategory.setLabel("Books");
        booksCategory.setLabelFr("Livres");
        booksCategory.setLabelNl("Boeken");
        productCategoryDao.add(booksCategory);
		
		Product book = new Product();
		book.setCategory(booksCategory);
		book.setName("Fight Club");
		book.setPrice(Double.valueOf(35.95D));
		book.setQuantityStock(15);
		book.setReorderLevel(10);
		book.setVatApplicable(Double.valueOf(6.00D));
		productDao.add(book);

        CommandDetail detail = new CommandDetail();
        detail.setCommand(newCommand);
        detail.setPrice(32.95D);
        detail.setProduct(book);
        detail.setQuantity(1);
//        detailDao.add(detail);
        List<CommandDetail> details = new ArrayList<CommandDetail>();
        details.add(detail);
        
        newCommand.setDetails(details);
        
        commandService.create(new CommandBean(newCommand));
		
//		CommandBean command = commandService.findById(newCommand.getId());
//		assertNotNull(command);
	}

	@Test
	public void testDelete() {
		
	}

	@Test
	public void testUpdate() {
		
	}

	@Test
	public void findById() {
		
	}

	@Test
	public void findAll() {
		
	}
	
}
