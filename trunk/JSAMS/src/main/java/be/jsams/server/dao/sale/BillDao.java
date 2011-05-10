package be.jsams.server.dao.sale;

import java.util.List;

import be.jsams.common.bean.model.sale.BillBean;
import be.jsams.server.dao.Dao;
import be.jsams.server.model.sale.Bill;

/**
 * Bill DAO interface.
 *
 * @author ebinard
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface BillDao extends Dao<Bill> {

    /**
     * Finds a list of {@link Bill} following the criteria model.
     * 
     * @param criteria the criteria model
     * @return a list of {@link Bill}
     */
    List<Bill> findByCriteria(final BillBean criteria);
    
}
