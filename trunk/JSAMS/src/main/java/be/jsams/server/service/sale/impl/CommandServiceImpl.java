package be.jsams.server.service.sale.impl;

import java.util.ArrayList;
import java.util.List;

import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.sale.CommandBean;
import be.jsams.server.dao.sale.CommandDao;
import be.jsams.server.model.sale.Command;
import be.jsams.server.service.sale.CommandService;

/**
 * Command service implementation.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CommandServiceImpl implements CommandService {

    private CommandDao commandDao;

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
     * {@inheritDoc}
     */
    public CommandBean create(final CommandBean bean) {
        Command command = new Command(bean);
        Command addingCommand = commandDao.add(command);
        return new CommandBean(addingCommand, JsamsDesktop.getInstance().getCurrentSociety());
    }

    /**
     * {@inheritDoc}
     */
    public void delete(final CommandBean bean) {
        Command command = commandDao.findById(bean.getId());
        commandDao.delete(command);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(final Long id) {
        Command command = commandDao.findById(id);
        commandDao.delete(command);
    }

    /**
     * {@inheritDoc}
     */
    public List<CommandBean> findAll() {
        List<Command> commands = commandDao.findAll();
        List<CommandBean> beans = new ArrayList<CommandBean>();
        SocietyBean currentSociety = JsamsDesktop.getInstance().getCurrentSociety();
        for (Command command : commands) {
            beans.add(new CommandBean(command, currentSociety));
        }
        return beans;
    }

    /**
     * {@inheritDoc}
     */
    public CommandBean findById(final Long id) {
        Command command = commandDao.findById(id);
        CommandBean bean = new CommandBean(command, JsamsDesktop.getInstance().getCurrentSociety());
        return bean;
    }

    /**
     * {@inheritDoc}
     */
    public void update(final CommandBean bean) {
        Command command = new Command(bean);
        commandDao.update(command);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CommandBean> findByCriteria(final CommandBean criteria) {
        SocietyBean currentSociety = JsamsDesktop.getInstance().getCurrentSociety();
        criteria.setSociety(currentSociety);
        List<Command> commands = commandDao.findByCriteria(criteria);
        List<CommandBean> beans = new ArrayList<CommandBean>();
        for (Command command : commands) {
            beans.add(new CommandBean(command, currentSociety));
        }
        return beans;
    }

}
