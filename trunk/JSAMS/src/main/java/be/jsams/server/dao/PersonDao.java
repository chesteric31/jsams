package be.jsams.server.dao;

import java.util.List;

import be.jsams.server.model.Person;

public interface PersonDao extends GenericDao<Person> {

	public List<Person> findByName(String name);

}
