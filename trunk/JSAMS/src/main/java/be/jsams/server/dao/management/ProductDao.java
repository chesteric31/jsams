package be.jsams.server.dao.management;

import java.util.List;

import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.server.dao.CriteriableDao;
import be.jsams.server.model.management.Product;

/**
 * Product DAO interface.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface ProductDao extends CriteriableDao<Product, ProductBean> {

    /**
     * Finds a top 5 of products with the most turnover from the start of the
     * program with bills.
     * 
     * @param societyId the society id to use
     * @return a list of a top 5 of products with the most turnover
     */
    List<Object[]> findTop5ProductsWithBills(Long societyId);

    /**
     * Finds a top 5 of products with the most turnover from the start of the
     * program with credit notes.
     * 
     * @param societyId the society id to use
     * @return a list of a top 5 of products with the most turnover
     */
    List<Object[]> findTop5ProductsWithCreditNotes(Long societyId);

}
