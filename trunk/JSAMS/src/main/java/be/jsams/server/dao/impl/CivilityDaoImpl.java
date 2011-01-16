package be.jsams.server.dao.impl;

import be.jsams.server.dao.CivilityDao;
import be.jsams.server.model.Civility;

/**
 * Civility DAO implementation.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CivilityDaoImpl extends DaoImpl<Civility> implements CivilityDao {

    /**
     * Constructor
     * 
     * @param type
     *            the class type
     */
    public CivilityDaoImpl(final Class<Civility> type) {
        super(type);
    }

}
