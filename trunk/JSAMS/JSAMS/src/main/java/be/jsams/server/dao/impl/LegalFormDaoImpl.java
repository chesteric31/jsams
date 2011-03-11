package be.jsams.server.dao.impl;

import be.jsams.server.dao.LegalFormDao;
import be.jsams.server.model.LegalForm;

/**
 * Legal Form DAO implementation.
 * 
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class LegalFormDaoImpl extends DaoImpl<LegalForm> implements LegalFormDao {

    /**
     * Constructor
     * 
     * @param type
     *            the class type
     */
    public LegalFormDaoImpl(final Class<LegalForm> type) {
        super(type);
    }

}
