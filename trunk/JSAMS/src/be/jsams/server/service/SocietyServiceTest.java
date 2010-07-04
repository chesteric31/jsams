package be.jsams.server.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import be.jsams.server.model.LegalForm;
import be.jsams.server.model.Society;

@ContextConfiguration("classpath:ApplicationContext.xml")
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class SocietyServiceTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private SocietyService societyService;

	Society newSociety = null;

	@Before
	public void setUp() throws Exception {
		newSociety = new Society();
		newSociety.setLabel("LABEL");
	}

	@Test
	public void testPersist() {
		LegalForm form = new LegalForm();
		form.setLabel("Form");
		
		societyService.add(newSociety);
		assertNotNull(societyService.findAll());
	}

	@Test
	@Rollback(value = false)
	public void testRemove() {
		societyService.add(newSociety);
		societyService.remove(newSociety);
		List<Society> societies = societyService.findAll();
		assertTrue(societies == null || societies.isEmpty());
	}

	@Test
	public void testUpdate() {
//		societyService.add(newSociety);
//		newSociety.setLabel("Labellll");
//		societyService.update(newSociety);
//		List<Person> persons = societyService.findByName("NOM");
//		assertTrue(persons != null && !persons.isEmpty());
	}

	@Test
	public void testFindById() {
		societyService.add(newSociety);
		Long id = newSociety.getId();
		Society society = societyService.findById(id);
		assertNotNull(society);
	}

	@Test
	public void testFindAll() {
		societyService.add(newSociety);
		List<Society> societies = societyService.findAll();
		assertTrue(societies != null && !societies.isEmpty());
	}

}
