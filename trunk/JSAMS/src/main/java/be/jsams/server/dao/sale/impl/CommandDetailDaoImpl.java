package be.jsams.server.dao.sale.impl;

import be.jsams.server.dao.impl.DaoImpl;
import be.jsams.server.dao.sale.CommandDetailDao;
import be.jsams.server.model.sale.CommandDetail;

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
     * @param type the class type
     */
    public CommandDetailDaoImpl(final Class<CommandDetail> type) {
        super(type);
    }

}
