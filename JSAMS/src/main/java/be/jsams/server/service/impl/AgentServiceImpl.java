package be.jsams.server.service.impl;

import java.util.List;

import be.jsams.server.dao.AgentDao;
import be.jsams.server.model.Agent;
import be.jsams.server.service.AgentService;

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
    public void create(Agent agent) {
        agentDao.add(agent);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Agent agent) {
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
    public List<Agent> findAll() {
        return agentDao.findAll();
    }

    /**
     * {@inheritDoc}
     */
    public Agent findById(Long id) {
        return agentDao.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    public void update(Agent agent) {
        agentDao.update(agent);
    }

    /**
     * {@inheritDoc}
     */
    public List<Agent> findByCriteria(Agent criteria) {
        return agentDao.findByCriteria(criteria);
    }

}
