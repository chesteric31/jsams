package be.jsams.server.service.management;

import java.util.List;
import java.util.Map;

import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.server.service.CriteriableService;

/**
 * Customer service interface.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface CustomerService extends CriteriableService<CustomerBean> {

    /**
     * Finds a top 5 of customers with the most turnover from the start of the
     * program.
     * 
     * @param society the {@link SocietyBean} to use
     * @return a list of a top 5 of customers with the most turnover
     */
    Map<Double, CustomerBean> findTop5Customers(SocietyBean society);

    /**
     * Finds all customers with at least one estimate.
     * 
     * @param society the {@link SocietyBean} to use
     * @return a list of customers
     */
    List<CustomerBean> findWithEstimates(SocietyBean society);

    /**
     * Finds all customers with at least one bill.
     * 
     * @param society the {@link SocietyBean} to use
     * @return a list of customers
     */
    List<CustomerBean> findWithBills(SocietyBean society);

}
