package be.jsams.server.service.management.impl;

import java.util.ArrayList;
import java.util.List;

import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.server.dao.management.AgentDao;
import be.jsams.server.model.management.Agent;
import be.jsams.server.service.management.AgentService;

/**
 * Agent service implementation.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class AgentServiceImpl implements AgentService {

    private AgentDao agentDao;
    
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
     * {@inheritDoc}
     */
    public AgentBean create(AgentBean bean) {
        Agent agent = new Agent(bean);
        agentDao.add(agent);
        return new AgentBean(agent);
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
    public List<AgentBean> findAll() {
        List<Agent> agents = agentDao.findAll();
        List<AgentBean> beans = new ArrayList<AgentBean>();
        for (Agent agent : agents) {
            beans.add(new AgentBean(agent));
        }
        return beans;
    }

    /**
     * {@inheritDoc}
     */
    public AgentBean findById(Long id) {
        Agent agent = agentDao.findById(id);
        AgentBean bean = new AgentBean(agent);
        return bean;
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
        List<Agent> agents = agentDao.findByCriteria(criteria);
        List<AgentBean> beans = new ArrayList<AgentBean>();
        for (Agent agent : agents) {
            beans.add(new AgentBean(agent));
        }
        return beans;
    }

}
