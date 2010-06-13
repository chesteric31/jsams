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

	@Override
	public List<Person> findAll() {
		return personDao.findAll();
	}

	@Override
	public Person findById(Long id) {
		return personDao.findById(id);
	}

	@Override
	public List<Person> findByName(String name) {
		return personDao.findByName(name);
	}

	@Override
	public void update(Person person) {
		personDao.update(person);
	}

	@Override
	public void add(Person person) {
		personDao.add(person);
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
