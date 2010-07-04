package be.jsams.server.service;

import java.util.List;

import be.jsams.server.model.Person;

public interface PersonService extends GenericService<Person> {

	public List<Person> findByName(String name);

}
