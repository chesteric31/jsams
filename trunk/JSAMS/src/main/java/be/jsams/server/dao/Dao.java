package be.jsams.server.dao;

import java.util.List;

/**
 * Generic DAO interface.
 * 
 * @param <T> the object type
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface Dao<T> {

    /**
     * Add new object in the database
     * 
     * @param newInstance the object to persist
     * @return the newInstance with the id provided
     */
    T add(T newInstance);

    /**
     * Find an object following the id
     * 
     * @param id the object id
     * @return the object
     */
    T findById(Long id);

    /**
     * Find all the object of this type
     * 
     * @return a list of object of this type
     */
    List<T> findAll();

    /**
     * Flush
     */
    void flush();

    /**
     * Update the object in the database
     * 
     * @param transientObject the object to update
     */
    void update(T transientObject);

    /**
     * Delete the object from the database
     * 
     * @param persistentObject the object to remove
     */
    void delete(T persistentObject);

    /**
     * Delete the object from the database following the id
     * 
     * @param id the object id
     */
    void delete(Long id);

}
