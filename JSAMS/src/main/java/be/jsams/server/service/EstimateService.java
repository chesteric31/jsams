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
     * Finds all {@link EstimateBean} following the criteria
     * {@link EstimateBean}.
     * 
     * @param criteria the criteria {@link EstimateBean}
     * @return a list of {@link EstimateBean}
     */
    List<EstimateBean> findByCriteria(final EstimateBean criteria);

}
