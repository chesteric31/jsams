package be.jsams.server.service.management.impl;

import java.util.ArrayList;
import java.util.List;

import be.jsams.client.desktop.JsamsDesktop;
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
        return new AgentBean(agent, bean.getSociety());
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
        SocietyBean currentSociety = JsamsDesktop.getInstance().getCurrentSociety();
        agentDao.setCurrentSociety(currentSociety);
        List<Agent> agents = agentDao.findAll();
        List<AgentBean> beans = new ArrayList<AgentBean>();
        for (Agent agent : agents) {
            beans.add(new AgentBean(agent, currentSociety));
        }
        return beans;
    }

    /**
     * {@inheritDoc}
     */
    public AgentBean findById(Long id) {
        Agent agent = agentDao.findById(id);
        AgentBean bean = new AgentBean(agent, JsamsDesktop.getInstance().getCurrentSociety());
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
        agentDao.setCurrentSociety(JsamsDesktop.getInstance().getCurrentSociety());
        List<Agent> agents = agentDao.findByCriteria(criteria);
        List<AgentBean> beans = new ArrayList<AgentBean>();
        for (Agent agent : agents) {
            beans.add(new AgentBean(agent, criteria.getSociety()));
        }
        return beans;
    }

}