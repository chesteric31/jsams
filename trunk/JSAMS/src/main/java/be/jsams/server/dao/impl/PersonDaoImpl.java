package be.jsams.server.dao.impl;

import java.util.List;

import javax.persistence.Query;

import be.jsams.server.dao.PersonDao;
import be.jsams.server.model.Person;

public class PersonDaoImpl extends GenericDaoImpl<Person> implements PersonDao {

	public PersonDaoImpl(Class<Person> type) {
		super(type);
	}

	public List<Person> findByName(String name) {
		List<Person> persons = null;
		try {
			Query query = getEntityManager().createQuery(
					"SELECT p FROM Person p WHERE p.name = :name");
			query.setParameter("name", name);
			persons = query.getResultList();
		} catch (RuntimeException re) {
			super.LOGGER.error(re);
		}
		return persons;
	}

}
