package be.jsams.server.service;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
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

import be.jsams.server.dao.AbstractJUnitTestClass;
import be.jsams.server.model.Address;
import be.jsams.server.model.Agent;
import be.jsams.server.model.Civility;
import be.jsams.server.model.Command;
import be.jsams.server.model.CommandDetail;
import be.jsams.server.model.ContactInformation;
import be.jsams.server.model.Customer;
import be.jsams.server.model.PaymentMode;
import be.jsams.server.model.Product;
import be.jsams.server.model.ProductCategory;

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
	
	private Command newCommand = null;

	@Before
	public void setUp() throws Exception {
		newCommand = new Command();
		
		Address billingAddress = new Address();
		billingAddress.setCity("Brussels");
		billingAddress.setCountry("Belgium");
		billingAddress.setNumber("1");
		billingAddress.setStreet("Rue Neuve");
		billingAddress.setZipCode(1000);
		
		newCommand.setBillingAddress(billingAddress);
		
		Agent contact = new Agent();
		contact.setFunction("Saler");
		contact.setName("John Doe");
		
		newCommand.setAgent(contact);
		
		newCommand.setCreationDate(new Date());
		
		Customer customer = new Customer();
		customer.setBank1("Fortis");
		
		customer.setBillingAddress(billingAddress);
		
		Civility civility = new Civility();
		civility.setLabel("Mr");
		
		customer.setCivility(civility);
		
		ContactInformation contactInformation = new ContactInformation();
		contactInformation.setPhone("+3271887755");
		customer.setContactInformation(contactInformation);
		
		customer.setCreditLimit(new BigDecimal(1000.00));
		customer.setDeliveryAddress(billingAddress);
		customer.setName("Wyatt Earp");
		
		PaymentMode paymentMode = new PaymentMode();
		paymentMode.setLabel("CASH");
		
		customer.setPaymentMode(paymentMode);
		
		newCommand.setCustomer(customer);
		
		newCommand.setDeliveryAddress(billingAddress);
	}
	
	@Test
	public void testCreate() {
		ProductCategory booksCategory = new ProductCategory();
		booksCategory.setLabel("Books");
		
		Product book = new Product();
		book.setCategory(booksCategory);
		book.setName("Fight Club");
		book.setPrice(new BigDecimal(35.95));
		book.setQuantityStock(15);
		book.setReorderLevel(10);
		book.setVatApplicable(new BigDecimal(6.00));
		
		CommandDetail detail = new CommandDetail();
		detail.setCommand(newCommand);
		detail.setPrice(new BigDecimal(32.95));
		detail.setProduct(book);
		detail.setQuantity(1);
		
		List<CommandDetail> details = new ArrayList<CommandDetail>();
		details.add(detail);
		
		newCommand.setDetails(details);
		
		commandService.create(newCommand);
		
		Command command = commandService.findById(newCommand.getId());
		assertNotNull(command);
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
