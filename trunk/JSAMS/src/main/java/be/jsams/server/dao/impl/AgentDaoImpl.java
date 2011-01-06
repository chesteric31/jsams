package be.jsams.server.dao.impl;

import be.jsams.server.dao.AgentDao;
import be.jsams.server.model.Agent;

/**
 * Agent DAO implementation.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class AgentDaoImpl extends GenericDaoImpl<Agent> implements AgentDao {

    /**
     * Constructor
     * 
     * @param type
     *            the class type
     */
    public AgentDaoImpl(Class<Agent> type) {
        super(type);
    }

}
