package be.jsams.server.service;

import java.util.List;

import be.jsams.server.model.Person;

public interface PersonService {

	public void create(Person person);

	public void delete(Person person);

	public void delete(Long id);

	public void update(Person person);

	public Person findById(Long id);

	public List<Person> findAll();
	
	public List<Person> findByName(String name);

}
