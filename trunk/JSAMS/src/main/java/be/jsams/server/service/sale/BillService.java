package be.jsams.server.service.sale;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.sale.BillBean;
import be.jsams.server.service.CriteriableService;

/**
 * Bill service interface.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface BillService extends CriteriableService<BillBean> {

    /**
     * Finds the global turnover for a {@link SocietyBean}.
     * 
     * @param society the {@link SocietyBean} to use
     * @return the calculated global turnover
     */
    BigDecimal findGlobalTurnover(SocietyBean society);

    /**
     * Finds all opened bills.
     * 
     * @param society the {@link SocietyBean} to use
     * @return a list of opened bills with the sum as key
     */
    Map<Double, List<BillBean>> findOpenedBills(SocietyBean society);

    /**
     * Finds all not paid bills.
     * 
     * @param society the {@link SocietyBean} to use
     * @return a list of not paid bills with the sum as key
     */
    Map<Double, List<BillBean>> findNotPaidBills(SocietyBean society);

    /**
     * Finds all the bills to throw back.
     * 
     * @param society the {@link SocietyBean} to use
     * @return a list of bills to throw back with the sum as key
     */
    Map<Double, List<BillBean>> findToThrowBackBills(SocietyBean society);

    /**
     * Finds all the expired bills.
     * 
     * @param society the {@link SocietyBean} to use
     * @return a list of expired bills with the sum as key
     */
    Map<Double, List<BillBean>> findExpiredBills(SocietyBean society);

}
