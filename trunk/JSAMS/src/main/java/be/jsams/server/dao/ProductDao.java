package be.jsams.server.dao;

import java.util.List;

import be.jsams.server.model.Product;

/**
 * Product DAO interface.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface ProductDao extends GenericDao<Product> {

    /**
     * Finds a list of {@link Product} following the criteria model
     * 
     * @param criteria
     *            the criteria model
     * @return a list of {@link Product}
     */
    List<Product> findByCriteria(Product criteria);

}
