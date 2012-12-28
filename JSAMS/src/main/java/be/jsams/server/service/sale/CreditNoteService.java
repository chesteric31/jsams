package be.jsams.server.service.sale;

import java.math.BigDecimal;

import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.sale.CreditNoteBean;
import be.jsams.server.service.CriteriableService;

/**
 * Credit note service interface.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface CreditNoteService extends CriteriableService<CreditNoteBean> {

    /**
     * Calculates the global turnover for a {@link SocietyBean}.
     * 
     * @param society the {@link SocietyBean} to use
     * @return the calculated global turnover
     */
    BigDecimal findGlobalTurnover(SocietyBean society);

    /**
     * Finds the turnover by month.
     * 
     * @param societyId the society id
     * @param startMonth the start month number
     * @param startYear the start year
     * @return the built array of turnover
     */
    Double[] findTurnoverByMonth(Long societyId, int startMonth, int startYear);

}
