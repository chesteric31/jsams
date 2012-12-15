package be.jsams.server.service.management.impl;

import java.util.ArrayList;
import java.util.List;

import be.jsams.client.desktop.Desktop;
import be.jsams.common.bean.builder.management.AgentBeanBuilder;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.server.dao.management.AgentDao;
import be.jsams.server.model.management.Agent;
import be.jsams.server.service.AbstractService;
import be.jsams.server.service.management.AgentService;

/**
 * Agent service implementation.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class AgentServiceImpl extends AbstractService implements AgentService {

    private AgentDao agentDao;
    private AgentBeanBuilder agentBeanBuilder;

    /**
     * {@inheritDoc}
     */
    public AgentBean create(AgentBean bean) {
        Agent agent = new Agent(bean);
        Agent persistedAgent = agentDao.add(agent);
        return getAgentBeanBuilder().build(persistedAgent, bean.getSociety());
    }

    /**
     * {@inheritDoc}
     */
    public void delete(AgentBean bean) {
        Agent agent = agentDao.findById(bean.getId());
        agentDao.delete(agent);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Long id) {
        agentDao.delete(id);
    }

    /**
     * {@inheritDoc}
     */
    public List<AgentBean> findAll(SocietyBean currentSociety) {
        List<Agent> agents = agentDao.findAll(currentSociety.getId());
        List<AgentBean> beans = new ArrayList<AgentBean>();
        for (Agent agent : agents) {
            beans.add(getAgentBeanBuilder().build(agent, currentSociety));
        }
        return beans;
    }

    /**
     * {@inheritDoc}
     */
    public AgentBean findById(Long id) {
        Agent agent = agentDao.findById(id);
        return getAgentBeanBuilder().build(agent, Desktop.getInstance().getCurrentSociety());
    }

    /**
     * {@inheritDoc}
     */
    public void update(AgentBean bean) {
        Agent agent = new Agent(bean);
        agentDao.update(agent);
    }

    /**
     * {@inheritDoc}
     */
    public List<AgentBean> findByCriteria(AgentBean criteria) {
        SocietyBean society = criteria.getSociety();
        List<Agent> agents = agentDao.findByCriteria(society.getId(), criteria);
        List<AgentBean> beans = new ArrayList<AgentBean>();
        for (Agent agent : agents) {
            beans.add(getAgentBeanBuilder().build(agent, society));
        }
        return beans;
    }

    /**
     * 
     * @return the {@link AgentDao}
     */
    public AgentDao getAgentDao() {
        return agentDao;
    }

    /**
     * 
     * @param agentDao the {@link AgentDao} to set
     */
    public void setAgentDao(AgentDao agentDao) {
        this.agentDao = agentDao;
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
