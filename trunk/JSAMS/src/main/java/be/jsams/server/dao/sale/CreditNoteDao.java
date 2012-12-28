package be.jsams.server.dao.sale;

import be.jsams.common.bean.model.sale.CreditNoteBean;
import be.jsams.server.dao.CriteriableDao;
import be.jsams.server.model.sale.CreditNote;

/**
 * Credit note DAO interface.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface CreditNoteDao extends CriteriableDao<CreditNote, CreditNoteBean> {

    /**
     * Finds the turnover by month.
     * 
     * @param societyId the society id
     * @param month the month
     * @param year the year
     * @return the built turnover
     */
    Double findTurnoverByMonth(Long societyId, int month, int year);

}
