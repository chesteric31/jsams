package be.jsams.server.dao.sale.impl;

import be.jsams.server.dao.impl.DaoImpl;
import be.jsams.server.dao.sale.EstimateDetailDao;
import be.jsams.server.model.sale.detail.EstimateDetail;

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
