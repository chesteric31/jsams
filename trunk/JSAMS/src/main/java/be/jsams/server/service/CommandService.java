package be.jsams.server.service;

import java.util.List;

import be.jsams.server.model.Command;

/**
 * Command service interface.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface CommandService {

    /**
     * Creates and persists a new {@link Command} object.
     * 
     * @param command the {@link Command} to persist
     */
    public void create(Command command);

    /**
     * Deletes a {@link Command} object.
     * 
     * @param command the {@link Command} to delete
     */
    public void delete(Command command);

    /**
     * Deletes a {@link Command} object.
     * 
     * @param id the id of the {@link Command} to delete
     */
    public void delete(Long id);

    /**
     * Updates a {@link Command} object.
     * 
     * @param command the {@link Command} to update
     */
    public void update(Command command);

    /**
     * Finds a {@link Command} object.
     * 
     * @param id the if of the {@link Command} to find
     */
    public Command findById(Long id);

    /**
     * Finds all the {@link Command} of the database.
     * 
     * @return a list of {@link Command}
     */
    public List<Command> findAll();

}
