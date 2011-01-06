package be.jsams.server.service;

import java.util.List;

import be.jsams.server.model.AbstractIdentity;

/**
 * Generic service for M extends of {@link AbstractIdentity}.
 * 
 * @param <M>
 *            an extension of {@link AbstractIdentity}
 * 
 * @author chesteric31
 * @version $$Rev$$ $$Date::                  $$ $$Author$$
 */
public interface Service<M extends AbstractIdentity> {

	/**
	 * Creates and persists a new M object.
	 * 
	 * @param model
	 *            the M object to persist
	 */
	void create(M model);

	/**
	 * Deletes a M object.
	 * 
	 * @param model
	 *            the M object to delete
	 */
	void delete(M model);

	/**
	 * Deletes a M object.
	 * 
	 * @param id
	 *            the id of the M object to delete
	 */
	void delete(Long id);

	/**
	 * Updates a M object.
	 * 
	 * @param model
	 *            the M object to update
	 */
	void update(M model);

	/**
	 * Finds a M object.
	 * 
	 * @param id
	 *            the if of the M object to find
	 * @return the found object
	 */
	M findById(Long id);

	/**
	 * Finds all the M object of the database.
	 * 
	 * @return a list of M object
	 */
	List<M> findAll();

}
