package be.jsams.server.service.sale;

import java.math.BigDecimal;
import java.util.List;

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
     * Calculates the global turnover for a {@link SocietyBean}.
     * 
     * @param society the {@link SocietyBean} to use
     * @return the calculated global turnover
     */
    BigDecimal findGlobalTurnover(SocietyBean society);
    
    /**
     * Finds all opened bills.
     * 
     * @param society the {@link SocietyBean} to use
     * @return a list of opened bills
     */
    List<BillBean> findOpenedBills(SocietyBean society);

    /**
     * Finds all closed bills.
     * 
     * @param society the {@link SocietyBean} to use
     * @return a list of closed bills
     */
    List<BillBean> findClosedBills(SocietyBean society);

}
