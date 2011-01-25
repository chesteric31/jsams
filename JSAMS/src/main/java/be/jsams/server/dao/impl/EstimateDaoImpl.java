package be.jsams.server.dao.impl;

import be.jsams.server.dao.EstimateDao;
import be.jsams.server.model.Estimate;

/**
 * Estimate DAO implementation.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EstimateDaoImpl extends DaoImpl<Estimate> implements EstimateDao {

    /**
     * Constructor
     * 
     * @param type
     *            the class type
     */
    public EstimateDaoImpl(final Class<Estimate> type) {
        super(type);
    }

}
