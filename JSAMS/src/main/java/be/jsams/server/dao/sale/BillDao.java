package be.jsams.server.dao.sale;

import java.util.List;

import be.jsams.common.bean.model.sale.BillBean;
import be.jsams.server.dao.CriteriableDao;
import be.jsams.server.model.sale.Bill;

/**
 * Bill DAO interface.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface BillDao extends CriteriableDao<Bill, BillBean> {

    /**
     * Finds all bills to throw back.
     * 
     * @param societyId the society id
     * @return the bills list to throw back
     */
    List<Bill> findToThrowBack(Long societyId);

    /**
     * Finds all expired bills. (due date >= today)
     * 
     * @param societyId the society id
     * @return the expired bills list
     */
    List<Bill> findExpired(Long societyId);

}
