package be.jsams.server.dao.impl;

import be.jsams.server.dao.CommandDao;
import be.jsams.server.model.Command;

/**
 * Command DAO implementation.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CommandDaoImpl extends GenericDaoImpl<Command> implements CommandDao {

	public CommandDaoImpl(Class<Command> type) {
		super(type);
	}

}
