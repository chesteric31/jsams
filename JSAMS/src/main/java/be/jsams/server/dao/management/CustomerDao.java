package be.jsams.server.dao.management;

import java.util.List;

import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.server.dao.CriteriableDao;
import be.jsams.server.model.management.Customer;

/**
 * Customer DAO interface.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface CustomerDao extends CriteriableDao<Customer, CustomerBean> {

    /**
     * Finds a top 5 of customers with the most turnover from the start of the
     * program for bills.
     * 
     * @param societyId the society id to use
     * @return a list of a top 5 of customers with the most turnover
     */
    List<Object[]> findTop5CustomersWithBills(Long societyId);

    /**
     * Finds all customers with at least one estimate.
     * 
     * @param societyId the society id to use
     * @return a list of customers
     */
    List<Customer> findWithEstimates(Long societyId);

    /**
     * Finds all customers with at least one bill.
     * 
     * @param societyId the society id to use
     * @return a list of customers
     */
    List<Customer> findWithBills(Long societyId);

    /**
     * Finds a top 5 of customers with the most turnover from the start of the
     * program for credit notes.
     * 
     * @param societyId the society id to use
     * @return a list of a top 5 of customers with the most turnover
     */
    List<Object[]> findTop5CustomersWithCreditNotes(Long societyId);

}
