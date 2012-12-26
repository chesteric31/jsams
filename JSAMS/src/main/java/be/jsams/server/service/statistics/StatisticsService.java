package be.jsams.server.service.statistics;

import java.util.List;
import java.util.Map;

import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.common.bean.model.sale.BillBean;
import be.jsams.common.bean.model.sale.EstimateBean;

/**
 * Statistics service for all data to show to the appropriate panel.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public interface StatisticsService {

    /**
     * Finds the global turnover from the start of the program.
     * 
     * @param society the {@link SocietyBean} to use
     * 
     * @return the global turnover
     */
    Double findGlobalTurnover(SocietyBean society);

    /**
     * Finds the turnover evolution from the start of the current year.
     * 
     * @param society the {@link SocietyBean} to use
     * 
     * @return a list of turnover for each month
     */
    List<Double> findTurnoverEvolution(SocietyBean society);

    /**
     * Finds all not paid bills from the start of the program.
     * 
     * @param society the {@link SocietyBean} to use
     * 
     * @return a list of not paid bills with sum
     */
    Map<Double, List<BillBean>> findNotPaidBills(SocietyBean society);

    /**
     * Finds all the bills to throw back from the start of the program.
     * 
     * @param society the {@link SocietyBean} to use
     * 
     * @return a list of bills to throw back with sum
     */
    Map<Double, List<BillBean>> findToThrowBackBills(SocietyBean society);

    /**
     * Finds all the expired bills from the start of the program.
     * 
     * @param society the {@link SocietyBean} to use
     * 
     * @return a list of expired bills with sum
     */
    Map<Double, List<BillBean>> findExpiredBills(SocietyBean society);

    /**
     * Finds all the opened bills from the start of the program.
     * 
     * @param society the {@link SocietyBean} to use
     * 
     * @return a list of opened bills with the sum
     */
    Map<Double, List<BillBean>> findOpenedBills(SocietyBean society);

    /**
     * Finds all the not transferred estimates from the start of the program.
     * 
     * @param society the {@link SocietyBean} to use
     * 
     * @return a list of not transferred estimates with sum
     */
    Map<Double, List<EstimateBean>> findNotTransferredEstimates(SocietyBean society);

    /**
     * Finds a top 5 of customers with the most turnover from the start of the program.
     * 
     * @param society the {@link SocietyBean} to use
     * 
     * @return a list of a top 5 of customers with the most turnover
     */    
    List<CustomerBean> findTop5Customers(SocietyBean society);

    /**
     * Finds all the customers with an estimate document.
     * 
     * @param society the {@link SocietyBean} to use
     * 
     * @return a list of estimates
     */
    List<CustomerBean> findCustomersWithEstimates(SocietyBean society);

    /**
     * Finds all the customers with a bill document.
     * 
     * @param society the {@link SocietyBean} to use
     * 
     * @return a list of bills
     */
    List<CustomerBean> findCustomersWithBills(SocietyBean society);

    /**
     * Finds a top 5 of products with the most turnover from the start of the program.
     * 
     * @param society the {@link SocietyBean} to use
     * 
     * @return a list of a top 5 of products with the most turnover
     */    
    List<ProductBean> findTop5Products(SocietyBean society);
    
}
