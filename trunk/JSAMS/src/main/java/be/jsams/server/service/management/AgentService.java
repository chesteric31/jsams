package be.jsams.server.service.management;

import java.util.List;

import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.server.service.Service;

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
