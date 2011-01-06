package be.jsams.server.service.impl;

import java.util.List;

import be.jsams.server.dao.CommandDao;
import be.jsams.server.dao.CommandDetailDao;
import be.jsams.server.model.Command;
import be.jsams.server.model.CommandDetail;
import be.jsams.server.service.CommandService;

/**
 * Command service implementation
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CommandServiceImpl implements CommandService {

    private CommandDao commandDao;
    private CommandDetailDao commandDetailDao;

    public CommandDao getCommandDao() {
        return commandDao;
    }

    public void setCommandDao(CommandDao commandDao) {
        this.commandDao = commandDao;
    }

    public CommandDetailDao getCommandDetailDao() {
        return commandDetailDao;
    }

    public void setCommandDetailDao(CommandDetailDao commandDetailDao) {
        this.commandDetailDao = commandDetailDao;
    }

    /**
     * {@inheritDoc}
     */
    public void create(Command command) {
        commandDao.add(command);
        List<CommandDetail> details = command.getDetails();
        for (CommandDetail detail : details) {
            commandDetailDao.add(detail);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Command command) {
        List<CommandDetail> details = command.getDetails();
        for (CommandDetail detail : details) {
            commandDetailDao.remove(detail);
        }
        commandDao.remove(command);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Long id) {
        Command command = findById(id);
        List<CommandDetail> details = command.getDetails();
        for (CommandDetail detail : details) {
            commandDetailDao.remove(detail);
        }
        commandDao.remove(id);
    }

    /**
     * {@inheritDoc}
     */
    public List<Command> findAll() {
        return commandDao.findAll();
    }

    /**
     * {@inheritDoc}
     */
    public Command findById(Long id) {
        return commandDao.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    public void update(Command command) {
        commandDao.update(command);
    }

}
