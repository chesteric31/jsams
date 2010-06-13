package be.jsams.server.service;

import java.util.List;

import be.jsams.server.model.Person;

public interface PersonService {

	public void add(Person person);

	public void remove(Person person);

	public void remove(Long id);

	public void update(Person person);

	public Person findById(Long id);

	public List<Person> findByName(String name);

	public List<Person> findAll();

}
