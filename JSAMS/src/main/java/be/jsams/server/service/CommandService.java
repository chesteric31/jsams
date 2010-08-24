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

	public void create(Command command);

	public void delete(Command command);

	public void delete(Long id);

	public void update(Command command);

	public Command findById(Long id);

	public List<Command> findAll();
	
}
