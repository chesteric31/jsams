package be.jsams.server.service;

import java.util.List;

import be.jsams.common.bean.model.sale.EstimateBean;

/**
 * Estimate service interface.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface EstimateService extends Service<EstimateBean> {

    /**
     * Finds all {@link EstimateBean} following the criteria agent.
     * 
     * @param criteria
     *            the criteria agent
     * @return a list of {@link EstimateBean}
     */
    List<EstimateBean> findByCriteria(EstimateBean criteria);

}
