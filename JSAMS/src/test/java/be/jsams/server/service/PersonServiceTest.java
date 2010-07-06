package be.jsams.server.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import be.jsams.server.model.Person;

@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class PersonServiceTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private PersonService personService;

	Person newPerson = null;

	@Before
	public void setUp() throws Exception {
		newPerson = new Person();
		newPerson.setName("NAME");
		Timestamp timestamp = new Timestamp(new Date().getTime());
		newPerson.setCreationDate(timestamp);
	}

	@Test
	public void testPersist() {
		personService.create(newPerson);
		assertNotNull(personService.findByName("NAME"));
	}

	@Test
	@Rollback(value = false)
	public void testRemove() {
		personService.create(newPerson);
		personService.delete(newPerson);
		List<Person> persons = personService.findByName("NAME");
		assertTrue(persons == null || persons.isEmpty());
	}

	@Test
	public void testUpdate() {
		personService.create(newPerson);
		newPerson.setName("NOM");
		personService.update(newPerson);
		List<Person> persons = personService.findByName("NOM");
		assertTrue(persons != null && !persons.isEmpty());
	}

	@Test
	public void testFindById() {
		personService.create(newPerson);
		Long id = newPerson.getId();
		Person person = personService.findById(id);
		assertNotNull(person);
	}

	@Test
	public void testFindByName() {
		personService.create(newPerson);
		List<Person> persons = personService.findByName("NAME");
		assertTrue(persons != null && !persons.isEmpty());
	}

	@Test
	public void testFindAll() {
		personService.create(newPerson);
		List<Person> persons = personService.findAll();
		assertTrue(persons != null && !persons.isEmpty());
	}

}
