package be.jsams.server.service.sale;

import java.util.List;
import java.util.Map;

import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.sale.EstimateBean;
import be.jsams.server.service.CriteriableService;

/**
 * Estimate service interface.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface EstimateService extends CriteriableService<EstimateBean> {

    /**
     * Finds all the not transferred estimates.
     * 
     * @param society the {@link SocietyBean} to use
     * @return a list of not transferred estimates with the sum as key
     */
    Map<Double, List<EstimateBean>> findNotTransferredEstimates(SocietyBean society);

}
