package be.jsams.server.service;

import java.util.List;

import be.jsams.common.bean.model.AbstractIdentityBean;

/**
 * Generic service for B extends of {@link AbstractIdentityBean}.
 * 
 * @param <B>
 *            an extension of {@link AbstractIdentityBean}
 * 
 * @author chesteric31
 * @version $$Rev$$ $$Date::                  $$ $$Author$$
 */
public interface Service<B extends AbstractIdentityBean<?, ?>> {

    /**
     * Creates and persists a new B object.
     * 
     * @param bean
     *            the B object to persist
     * @return the B object persisted with id
     */
    B create(B bean);

    /**
     * Deletes a B object.
     * 
     * @param bean
     *            the B object to delete
     */
    void delete(B bean);

    /**
     * Deletes a B object.
     * 
     * @param id
     *            the id of the B object to delete
     */
    void delete(Long id);

    /**
     * Updates a B object.
     * 
     * @param bean
     *            the B object to update
     */
    void update(B bean);

    /**
     * Finds a B object.
     * 
     * @param id
     *            the if of the B object to find
     * @return the found object
     */
    B findById(Long id);

    /**
     * Finds all the B object of the database.
     * 
     * @return a list of B objects
     */
    List<B> findAll();

}
