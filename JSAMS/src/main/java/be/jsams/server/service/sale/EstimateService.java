package be.jsams.server.service.sale;

import java.util.List;

import be.jsams.common.bean.model.sale.EstimateBean;
import be.jsams.server.service.Service;

/**
 * Estimate service interface.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface EstimateService extends Service<EstimateBean> {

    /**
     * Find all {@link EstimateBean} following the criteria {@link EstimateBean}.
     * 
     * @param criteria the criteria {@link EstimateBean}
     * @return a list of {@link EstimateBean}
     */
    List<EstimateBean> findByCriteria(final EstimateBean criteria);

}
