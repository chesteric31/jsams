package be.jsams.server.dao.impl;

import be.jsams.server.dao.SocietyDao;
import be.jsams.server.model.Society;

/**
 * Society DAO implementation.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class SocietyDaoImpl extends ReferenceDaoImpl<Society> implements SocietyDao {

    /**
     * Constructor.
     * 
     * @param type the class type
     */
    public SocietyDaoImpl(final Class<Society> type) {
        super(type);
    }

}
