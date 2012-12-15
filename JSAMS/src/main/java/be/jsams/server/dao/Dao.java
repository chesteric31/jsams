package be.jsams.server.dao;


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
     * Adds new object in the database
     * 
     * @param newInstance the object to persist
     * @return the newInstance with the id provided
     */
    T add(T newInstance);

    /**
     * Finds an object following the id
     * 
     * @param id the object id
     * @return the object
     */
    T findById(Long id);

    /**
     * Flush
     */
    void flush();

    /**
     * Updates the object in the database
     * 
     * @param transientObject the object to update
     */
    void update(T transientObject);

    /**
     * Deletes the object from the database
     * 
     * @param persistentObject the object to remove
     */
    void delete(T persistentObject);

    /**
     * Deletes the object from the database following the id
     * 
     * @param id the object id
     */
    void delete(Long id);

}
