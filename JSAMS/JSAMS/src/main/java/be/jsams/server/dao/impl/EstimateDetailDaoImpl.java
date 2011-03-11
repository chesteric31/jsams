package be.jsams.server.dao.impl;

import be.jsams.server.dao.EstimateDetailDao;
import be.jsams.server.model.EstimateDetail;

/**
 * Estimate detail DAO implementation.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EstimateDetailDaoImpl extends DaoImpl<EstimateDetail> implements EstimateDetailDao {

    /**
     * Constructor
     * 
     * @param type
     *            the class type
     */
    public EstimateDetailDaoImpl(final Class<EstimateDetail> type) {
        super(type);
    }
    
}
