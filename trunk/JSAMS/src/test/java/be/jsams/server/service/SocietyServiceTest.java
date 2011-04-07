package be.jsams.server.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import be.jsams.common.bean.model.management.SocietyBean;
import be.jsams.server.dao.AbstractJUnitTestClass;
import be.jsams.server.dao.LegalFormDao;

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

	private SocietyBean newSociety = null;

	@Before
	public void setUp() throws Exception {
//		newSociety = new SocietyBean();
//		newSociety.setName("LABEL");
//
//		LegalForm form = new LegalForm();
//		form.setLabel("Form");
//        form.setLabelFr("Forme");
//        form.setLabelNl("Form");
//        legalFormDao.add(form);
//		newSociety.setLegalForm(new LegalFormBean(form));
//
//		AddressBean societyAddress = new AddressBean();
//		societyAddress.setCity("Brussels");
//		societyAddress.setCountry("Belgium");
//		societyAddress.setNumber("1B");
//		societyAddress.setStreet("Rue Neuve");
//		societyAddress.setZipCode("1000");
//		newSociety.setAddress(societyAddress);
//
//		ContactInformationBean societyContactInformation = new ContactInformationBean();
//		societyContactInformation.setEmail("x.x@x.com");
//		societyContactInformation.setMobile("+32499555444");
//		societyContactInformation.setPhone("+322123456");
//		newSociety.setContactInformation(societyContactInformation);
//
//		String activity = "Activity";
//		newSociety.setActivity(activity);
//		newSociety.setCapital(new Double(100.00D));
//		newSociety.setResponsible("Binard Eric");
	}

	@Test
	public void testCreate() {
//		societyService.create(newSociety);
//		SocietyBean society = societyService.findById(newSociety.getId());
//		assertNotNull(society);
	}

	@Test
	public void testDelete() {
//		societyService.create(newSociety);
//		assertNotNull(societyService.findById(newSociety.getId()));
//		societyService.delete(newSociety);
//		SocietyBean findById = societyService.findById(newSociety.getId());
//		assertNull(findById);
	}

	@Test
	public void testUpdate() {
//		societyService.create(newSociety);
//		newSociety.setName("Updated Label");
//		societyService.update(newSociety);
//		SocietyBean foundSociety = societyService.findById(newSociety.getId());
//		assertTrue(foundSociety.getName().equalsIgnoreCase("Updated Label"));
	}

	@Test
	public void testFindById() {
//		societyService.create(newSociety);
//		Long id = newSociety.getId();
//		SocietyBean society = societyService.findById(id);
//		assertNotNull(society);
	}

	@Test
	public void testFindAll() {
//		societyService.create(newSociety);
//		List<SocietyBean> societies = societyService.findAll();
//		assertTrue(societies != null && !societies.isEmpty());
	}

}
