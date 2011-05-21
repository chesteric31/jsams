package be.jsams.server.service.sale;

import java.util.List;

import be.jsams.common.bean.model.sale.CreditNoteBean;
import be.jsams.server.service.Service;

/**
 * Credit note service interface.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface CreditNoteService extends Service<CreditNoteBean> {

    /**
     * Find all {@link CreditNoteBean} following the criteria {@link CreditNoteBean}.
     * 
     * @param criteria the criteria {@link CreditNoteBean}
     * @return a list of {@link CreditNoteBean}
     */
    List<CreditNoteBean> findByCriteria(final CreditNoteBean criteria);

}
