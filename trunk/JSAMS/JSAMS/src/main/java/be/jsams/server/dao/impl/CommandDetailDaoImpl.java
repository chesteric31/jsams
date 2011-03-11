package be.jsams.server.dao.impl;

import be.jsams.server.dao.CommandDetailDao;
import be.jsams.server.model.CommandDetail;

/**
 * Command detail DAO implementation.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CommandDetailDaoImpl extends DaoImpl<CommandDetail> implements CommandDetailDao {

    /**
     * Constructor
     * 
     * @param type
     *            the class type
     */
    public CommandDetailDaoImpl(final Class<CommandDetail> type) {
        super(type);
    }
}
