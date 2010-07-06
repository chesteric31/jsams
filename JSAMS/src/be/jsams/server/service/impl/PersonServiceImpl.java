package be.jsams.server.service.impl;

import java.util.List;

import be.jsams.server.dao.PersonDao;
import be.jsams.server.model.Person;
import be.jsams.server.service.PersonService;

public class PersonServiceImpl implements PersonService {

	private PersonDao personDao;

	public PersonDao getPersonDao() {
		return personDao;
	}

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	public List<Person> findAll() {
		return personDao.findAll();
	}

	public Person findById(Long id) {
		return personDao.findById(id);
	}

	public List<Person> findByName(String name) {
		return personDao.findByName(name);
	}

	public void update(Person person) {
		personDao.update(person);
	}

	public void create(Person person) {
		personDao.add(person);
	}

	public void delete(Person person) {
		personDao.remove(person);
	}

	public void delete(Long id) {
		personDao.remove(id);
	}

}
