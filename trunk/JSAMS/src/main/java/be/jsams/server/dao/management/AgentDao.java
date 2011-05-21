package be.jsams.server.dao.management;

import java.util.List;

import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.server.dao.Dao;
import be.jsams.server.model.management.Agent;

/**
 * Contact DAO interface.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface AgentDao extends Dao<Agent> {

    /**
     * Find a list of {@link Agent} following the criteria model
     * 
     * @param criteria the criteria model
     * @return a list of {@link Agent}
     */
    List<Agent> findByCriteria(final AgentBean criteria);

}
