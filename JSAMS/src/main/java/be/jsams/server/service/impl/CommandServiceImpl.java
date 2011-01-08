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

    /**
     * 
     * @return the {@link CommandDao}
     */
    public CommandDao getCommandDao() {
        return commandDao;
    }

    /**
     * 
     * @param commandDao the {@link CommandDao} to set
     */
    public void setCommandDao(CommandDao commandDao) {
        this.commandDao = commandDao;
    }

    /**
     * 
     * @return the {@link CommandDetailDao}
     */
    public CommandDetailDao getCommandDetailDao() {
        return commandDetailDao;
    }

    /**
     * 
     * @param commandDetailDao the {@link CommandDetailDao} to set
     */
    public void setCommandDetailDao(CommandDetailDao commandDetailDao) {
        this.commandDetailDao = commandDetailDao;
    }

    /**
     * {@inheritDoc}
     */
    public void create(final Command command) {
        commandDao.add(command);
        List<CommandDetail> details = command.getDetails();
        for (CommandDetail detail : details) {
            commandDetailDao.add(detail);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete(final Command command) {
        List<CommandDetail> details = command.getDetails();
        for (CommandDetail detail : details) {
            commandDetailDao.remove(detail);
        }
        commandDao.remove(command);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(final Long id) {
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
    public Command findById(final Long id) {
        return commandDao.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    public void update(final Command command) {
        commandDao.update(command);
    }

}
