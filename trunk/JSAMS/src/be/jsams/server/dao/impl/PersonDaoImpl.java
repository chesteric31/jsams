package be.jsams.server.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.server.dao.PersonDao;
import be.jsams.server.model.Person;

public class PersonDaoImpl implements PersonDao {

	private final Log LOGGER = LogFactory.getLog(this.getClass());

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Person> findAll() {
		List<Person> persons = null;
		try {
			Query query = entityManager.createQuery("SELECT p FROM Person p");
			persons = query.getResultList();
		} catch (RuntimeException re) {
			LOGGER.error(re);
		}
		return persons;
	}

	@Override
	public Person findById(Long id) {
		Person person = null;
		try {
			person = entityManager.find(Person.class, id);
		} catch (RuntimeException re) {
			LOGGER.error(re);
		}
		return person;
	}

	@Override
	public Person findByName(String name) {
		Person person = null;
		try {
			Query query = entityManager
					.createQuery("SELECT p FROM Person p WHERE p.name = :name");
			query.setParameter("name", name);
			person = (Person) query.getSingleResult();
		} catch (RuntimeException re) {
			LOGGER.error(re);
		}
		return person;
	}

	@Override
	public Person merge(Person person) {
		Person result = null;
		try {
			result = entityManager.merge(person);
		} catch (RuntimeException re) {
			LOGGER.error(re);
		}
		return result;
	}

	@Override
	public void persist(Person person) {
		LOGGER.debug("Persisting Person instance");
		try {
			entityManager.persist(person);
			LOGGER.debug("Persist successful");
		} catch (RuntimeException re) {
			LOGGER.error("Persist failed", re);
		}
	}

	@Override
	public void remove(Person person) {
		try {
			entityManager.remove(person);
		} catch (RuntimeException re) {
			LOGGER.error(re);
		}
	}

	@Override
	public void remove(Long id) {
		this.remove(this.findById(id));
	}
}
