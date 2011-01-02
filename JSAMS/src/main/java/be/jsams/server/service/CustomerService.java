package be.jsams.server.service;

import java.util.List;

import be.jsams.server.model.Customer;

/**
 * Customer service interface.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface CustomerService {

    /**
     * Creates and persists a new {@link Customer} object.
     * 
     * @param customer the {@link Customer} to persist
     */
    public void create(Customer customer);

    /**
     * Deletes a {@link Customer} object.
     * 
     * @param customer the {@link Customer} to delete
     */
    public void delete(Customer customer);

    /**
     * Deletes a {@link Customer} object.
     * 
     * @param id the id of the {@link Customer} to delete
     */
    public void delete(Long id);

    /**
     * Updates a {@link Customer} object.
     * 
     * @param customer the {@link Customer} to update
     */
    public void update(Customer customer);

    /**
     * Finds a {@link Customer} object.
     * 
     * @param id the if of the {@link Customer} to find
     */
    public Customer findById(Long id);

    /**
     * Finds all the {@link Customer} of the database.
     * 
     * @return a list of {@link Customer}
     */
    public List<Customer> findAll();

}
