package be.jsams.server.service;

import java.util.List;

import be.jsams.server.model.Society;

/**
 * Society service interface.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface SocietyService {

    /**
     * Creates and persists a new {@link Society} object.
     * 
     * @param society the {@link Society} to persist
     */
    public void create(Society society);

    /**
     * Deletes a {@link Society} object.
     * 
     * @param society the {@link Society} to delete
     */
    public void delete(Society society);

    /**
     * Deletes a {@link Society} object.
     * 
     * @param id the id of the {@link Society} to delete
     */
    public void delete(Long id);

    /**
     * Updates a {@link Society} object.
     * 
     * @param society the {@link Society} to update
     */
    public void update(Society society);

    /**
     * Finds a {@link Society} object.
     * 
     * @param id the if of the {@link Society} to find
     */
    public Society findById(Long id);

    /**
     * Finds all the {@link Society} of the database.
     * 
     * @return a list of {@link Society}
     */
    public List<Society> findAll();

}
