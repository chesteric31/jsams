package be.jsams.server.dao.sale;

import java.util.List;

import be.jsams.common.bean.model.sale.CreditNoteBean;
import be.jsams.server.dao.Dao;
import be.jsams.server.model.sale.CreditNote;

/**
 * Credit note DAO interface.
 *
 * @author ebinard
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface CreditNoteDao extends Dao<CreditNote> {

    /**
     * Finds a list of {@link CreditNote} following the criteria model.
     * 
     * @param criteria the criteria model
     * @return a list of {@link CreditNote}
     */
    List<CreditNote> findByCriteria(final CreditNoteBean criteria);
    
}
