package be.jsams.server.dao.sale;

import java.util.List;

import be.jsams.common.bean.model.sale.EstimateBean;
import be.jsams.server.dao.Dao;
import be.jsams.server.model.sale.Estimate;

/**
 * Estimate DAO interface.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface EstimateDao extends Dao<Estimate> {

    /**
     * Finds a list of {@link Estimate} following the criteria model
     * 
     * @param criteria
     *            the criteria model
     * @return a list of {@link Estimate}
     */
    List<Estimate> findByCriteria(final EstimateBean criteria);

}