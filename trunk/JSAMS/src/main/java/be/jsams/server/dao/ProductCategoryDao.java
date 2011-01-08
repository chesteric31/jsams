package be.jsams.server.dao;

import java.util.List;

import be.jsams.server.model.ProductCategory;

/**
 * Product DAO interface.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface ProductCategoryDao extends GenericDao<ProductCategory> {

    /**
     * Finds a list of {@link ProductCategory} following the criteria model
     * 
     * @param criteria
     *            the criteria model
     * @return a list of {@link ProductCategory}
     */
    List<ProductCategory> findByCriteria(ProductCategory criteria);

}
