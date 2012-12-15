package be.jsams.server.service.sale.impl;

import java.util.ArrayList;
import java.util.List;

import be.jsams.client.desktop.Desktop;
import be.jsams.common.bean.builder.management.AgentBeanBuilder;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.sale.CommandBean;
import be.jsams.server.dao.sale.CommandDao;
import be.jsams.server.model.sale.Command;
import be.jsams.server.service.AbstractService;
import be.jsams.server.service.sale.CommandService;

/**
 * Command service implementation.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CommandServiceImpl extends AbstractService implements CommandService {

    private CommandDao commandDao;
    private AgentBeanBuilder agentBeanBuilder;

    /**
     * {@inheritDoc}
     */
    public CommandBean create(final CommandBean bean) {
        Command command = new Command(bean);
        Command persistedCommand = commandDao.add(command);
        SocietyBean currentSociety = Desktop.getInstance().getCurrentSociety();
        return new CommandBean(persistedCommand, currentSociety, bean.getCustomer(), bean.getAgent());
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
    public List<CommandBean> findAll(SocietyBean currentSociety) {
        List<Command> commands = commandDao.findAll(currentSociety.getId());
        List<CommandBean> beans = new ArrayList<CommandBean>();
        for (Command command : commands) {
            CustomerBean customer = getCustomerBeanBuilder().build(command.getCustomer(), currentSociety);
            AgentBean agent = agentBeanBuilder.build(command.getAgent(), currentSociety);
            beans.add(new CommandBean(command, currentSociety, customer, agent));
        }
        return beans;
    }

    /**
     * {@inheritDoc}
     */
    public CommandBean findById(final Long id) {
        SocietyBean currentSociety = Desktop.getInstance().getCurrentSociety();
        Command command = commandDao.findById(id);
        CustomerBean customer = getCustomerBeanBuilder().build(command.getCustomer(), currentSociety);
        AgentBean agent = agentBeanBuilder.build(command.getAgent(), currentSociety);
        return new CommandBean(command, currentSociety, customer, agent);
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
        SocietyBean society = criteria.getSociety();
        List<Command> commands = commandDao.findByCriteria(society.getId(), criteria);
        List<CommandBean> beans = new ArrayList<CommandBean>();
        for (Command command : commands) {
            CustomerBean customer = getCustomerBeanBuilder().build(command.getCustomer(), society);
            AgentBean agent = agentBeanBuilder.build(command.getAgent(), society);
            beans.add(new CommandBean(command, society, customer, agent));
        }
        return beans;
    }

    /**
     * @return the {@link CommandDao}
     */
    public CommandDao getCommandDao() {
        return commandDao;
    }

    /**
     * @param commandDao the {@link CommandDao} to set
     */
    public void setCommandDao(CommandDao commandDao) {
        this.commandDao = commandDao;
    }

    /**
     * @return the agentBeanBuilder
     */
    public AgentBeanBuilder getAgentBeanBuilder() {
        return agentBeanBuilder;
    }

    /**
     * @param agentBeanBuilder the agentBeanBuilder to set
     */
    public void setAgentBeanBuilder(AgentBeanBuilder agentBeanBuilder) {
        this.agentBeanBuilder = agentBeanBuilder;
    }

}
