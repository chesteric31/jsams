package be.jsams.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import be.jsams.common.bean.model.CommandBean;
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
    public CommandBean create(final CommandBean bean) {
        return null;
        // TODO
//        Command command = new Command(bean);
//        commandDao.add(command);
//        return new CommandBean(command);
//        List<CommandDetailBean> details = bean.getDetails();
//        for (CommandDetail detail : details) {
//            commandDetailDao.add(new CommandDetailBean(detail));
//        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete(final CommandBean bean) {
        Command command = commandDao.findById(bean.getId());
        List<CommandDetail> details = command.getDetails();
        for (CommandDetail detail : details) {
            commandDetailDao.delete(detail);
        }
        commandDao.delete(command);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(final Long id) {
        Command command = commandDao.findById(id);
        List<CommandDetail> details = command.getDetails();
        for (CommandDetail detail : details) {
            commandDetailDao.delete(detail);
        }
        commandDao.delete(id);
    }

    /**
     * {@inheritDoc}
     */
    public List<CommandBean> findAll() {
        List<Command> commands = commandDao.findAll();
        List<CommandBean> beans = new ArrayList<CommandBean>();
        for (Command command : commands) {
            beans.add(new CommandBean(command));
        }
        return beans;
    }

    /**
     * {@inheritDoc}
     */
    public CommandBean findById(final Long id) {
        Command command = commandDao.findById(id);
        CommandBean bean = new CommandBean(command);
        return bean;
    }

    /**
     * {@inheritDoc}
     */
    public void update(final CommandBean bean) {
        Command command = new Command(bean);
        commandDao.update(command);
    }

}
