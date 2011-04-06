package be.jsams.server.service;

import java.util.List;

import be.jsams.server.model.Agent;

/**
 * Agent service interface.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface AgentService extends Service<Agent> {

    /**
     * Finds all {@link Agent} following the criteria agent.
     * 
     * @param criteria
     *            the criteria agent
     * @return a list of {@link Agent}
     */
    List<Agent> findByCriteria(final Agent criteria);

}
