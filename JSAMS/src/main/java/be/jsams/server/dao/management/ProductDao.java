package be.jsams.server.dao.management;

import java.util.List;

import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.server.dao.Dao;
import be.jsams.server.model.management.Product;

/**
 * Product DAO interface.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface ProductDao extends Dao<Product> {

    /**
     * Finds a list of {@link Product} following the criteria model
     * 
     * @param criteria
     *            the criteria model
     * @return a list of {@link Product}
     */
    List<Product> findByCriteria(final ProductBean criteria);

}
