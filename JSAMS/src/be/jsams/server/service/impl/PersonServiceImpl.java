package be.jsams.server.service.impl;

import java.util.List;

import be.jsams.server.dao.PersonDao;
import be.jsams.server.model.Person;
import be.jsams.server.service.PersonService;

public class PersonServiceImpl implements PersonService {

//	private final Log LOGGER = LogFactory.getLog(this.getClass());

	private PersonDao personDao;

	public PersonDao getPersonDao() {
		return personDao;
	}

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	@Override
	public List<Person> findAll() {
		return personDao.findAll();
	}

	@Override
	public Person findById(Long id) {
		return personDao.findById(id);
	}

	@Override
	public Person findByName(String name) {
		return personDao.findByName(name);
	}

	@Override
	public Person merge(Person person) {
		return personDao.merge(person);
	}

	@Override
	public void persist(Person person) {
		personDao.persist(person);
	}

	@Override
	public void remove(Person person) {
		personDao.remove(person);
	}

	@Override
	public void remove(Long id) {
		personDao.remove(id);
	}

}
