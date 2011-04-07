package be.jsams.server.service;

import java.util.List;

import be.jsams.common.bean.model.management.AgentBean;

/**
 * Agent service interface.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface AgentService extends Service<AgentBean> {

    /**
     * Finds all {@link AgentBean} following the criteria agent.
     * 
     * @param criteria
     *            the criteria agent
     * @return a list of {@link AgentBean}
     */
    List<AgentBean> findByCriteria(final AgentBean criteria);

}
