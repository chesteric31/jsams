package be.jsams.server.dao;

import java.util.List;

/**
 * Generic DAO interface.
 * 
 * @param <T>
 *            the objec type
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface GenericDao<T> {

	/**
	 * Adds new object in the database
	 * 
	 * @param newInstance
	 *            the object to persist
	 */
	void add(T newInstance);

	/**
	 * Finds an object following the id
	 * 
	 * @param id
	 *            the object id
	 * @return the object
	 */
	T findById(Long id);

	/**
	 * Finds all the object of this type
	 * 
	 * @return a list of object of this type
	 */
	List<T> findAll();

	/**
	 * Flushs
	 */
	void flush();

	/**
	 * Updates the object in the database
	 * 
	 * @param transientObject
	 *            the object to update
	 */
	void update(T transientObject);

	/**
	 * Removes the object from the database
	 * 
	 * @param persistentObject
	 */
	void remove(T persistentObject);

	/**
	 * Removes the object from the database following the id
	 * 
	 * @param id
	 *            the object id
	 */
	void remove(Long id);

}
