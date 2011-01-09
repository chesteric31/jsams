package be.jsams.server.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import be.jsams.server.dao.AbstractJUnitTestClass;
import be.jsams.server.dao.LegalFormDao;
import be.jsams.server.model.Address;
import be.jsams.server.model.ContactInformation;
import be.jsams.server.model.LegalForm;
import be.jsams.server.model.Society;

/**
 * Test class for {@link SocietyService}.
 * 
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class SocietyServiceTest extends AbstractJUnitTestClass {

	@Autowired
	@Qualifier(value = "societyService")
	private SocietyService societyService;
	
	@Autowired
	private LegalFormDao legalFormDao;

	private Society newSociety = null;

	@Before
	public void setUp() throws Exception {
		newSociety = new Society();
		newSociety.setName("LABEL");

		LegalForm form = new LegalForm();
		form.setLabel("Form");
        form.setLabelFr("Forme");
        form.setLabelNl("Form");
        legalFormDao.add(form);
		newSociety.setLegalForm(form);

		Address societyAddress = new Address();
		societyAddress.setCity("Brussels");
		societyAddress.setCountry("Belgium");
		societyAddress.setNumber("1B");
		societyAddress.setStreet("Rue Neuve");
		societyAddress.setZipCode(1000);
		newSociety.setAddress(societyAddress);

		ContactInformation societyContactInformation = new ContactInformation();
		societyContactInformation.setEmail("x.x@x.com");
		societyContactInformation.setMobile("+32499555444");
		societyContactInformation.setPhone("+322123456");
		newSociety.setContactInformation(societyContactInformation);

		String activity = "Activity";
		newSociety.setActivity(activity);
		newSociety.setCapital(new BigDecimal(100.00D));
		newSociety.setResponsible("Binard Eric");
	}

	@Test
	public void testCreate() {
		societyService.create(newSociety);
		Society society = societyService.findById(newSociety.getId());
		assertNotNull(society);
	}

	@Test
	public void testDelete() {
		societyService.create(newSociety);
		assertNotNull(societyService.findById(newSociety.getId()));
		societyService.delete(newSociety);
		Society findById = societyService.findById(newSociety.getId());
		assertNull(findById);
	}

	@Test
	public void testUpdate() {
		societyService.create(newSociety);
		newSociety.setName("Updated Label");
		societyService.update(newSociety);
		Society foundSociety = societyService.findById(newSociety.getId());
		assertTrue(foundSociety.getName().equalsIgnoreCase("Updated Label"));
	}

	@Test
	public void testFindById() {
		societyService.create(newSociety);
		Long id = newSociety.getId();
		Society society = societyService.findById(id);
		assertNotNull(society);
	}

	@Test
	public void testFindAll() {
		societyService.create(newSociety);
		List<Society> societies = societyService.findAll();
		assertTrue(societies != null && !societies.isEmpty());
	}

}
