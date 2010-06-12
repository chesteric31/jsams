package be.jsams.server.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import be.jsams.server.model.Person;

@ContextConfiguration("classpath:ApplicationContext.xml")
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class PersonServiceTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private PersonService personService;

	@Before
	public void setUp() throws Exception {
		// ClassPathXmlApplicationContext context = new
		// ClassPathXmlApplicationContext(
		// "ApplicationContext.xml");
		// personService = (PersonService) context.getBean("personService");
	}

	@Test
	@Rollback
	public void testPersist() {
		Person newPerson = new Person();
		newPerson.setName("Y");
		Timestamp timestamp = new Timestamp(new Date().getTime());
		newPerson.setCreationDate(timestamp);
		personService.persist(newPerson);
		assertNotNull(personService.findByName("Y"));
	}

	@Test
	public void testRemovePerson() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveLong() {
		fail("Not yet implemented");
	}

	@Test
	public void testMerge() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindById() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByName() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAll() {
		fail("Not yet implemented");
	}

}
