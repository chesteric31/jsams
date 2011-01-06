package be.jsams.server.dao.impl;

import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import be.jsams.server.dao.AbstractJUnitTestClass;
import be.jsams.server.dao.CommandDetailDao;
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
 * Test class for {@link CommandDetailDaoImpl}.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CommandDetailDaoImplTest extends AbstractJUnitTestClass {

	@Autowired
	private CommandDetailDao dao;
	
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
		billingAddress.setZipCode(1000);
		
		newCommand.setBillingAddress(billingAddress);
		
		Agent agent = new Agent();
		agent.setFunction("Saler");
		agent.setName("John Doe");
		
		newCommand.setAgent(agent);
		
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
		
		ProductCategory booksCategory = new ProductCategory();
		booksCategory.setLabel("Books");
		
		Product book = new Product();
		book.setCategory(booksCategory);
		book.setName("Fight Club");
		book.setPrice(new BigDecimal(35.95));
		book.setQuantityStock(15);
		book.setReorderLevel(10);
		
		detail = new CommandDetail();
		detail.setCommand(newCommand);
		detail.setPrice(new BigDecimal(32.95));
		detail.setProduct(book);
		detail.setQuantity(1);
	}

	@Test
	public void testAdd() {
		dao.add(detail);
	}

	@Test
	public void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindById() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveT() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveLong() {
		fail("Not yet implemented");
	}

}
