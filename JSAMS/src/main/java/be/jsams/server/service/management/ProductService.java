package be.jsams.server.service.management;

import java.util.Map;

import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.server.service.CriteriableService;

/**
 * Product service interface.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface ProductService extends CriteriableService<ProductBean> {

    /**
     * Finds a top 5 of products with the most turnover from the start of the
     * program with bills.
     * 
     * @param society the {@link SocietyBean} to use
     * @return a list of a top 5 of products with the most turnover
     */
    Map<Long, Double> findTop5ProductsWithBills(SocietyBean society);

    /**
     * Finds a top 5 of products with the most turnover from the start of the
     * program with credit notes.
     * 
     * @param society the {@link SocietyBean} to use
     * @return a list of a top 5 of products with the most turnover
     */
    Map<Long, Double> findTop5ProductsWithCreditNotes(SocietyBean society);

}
