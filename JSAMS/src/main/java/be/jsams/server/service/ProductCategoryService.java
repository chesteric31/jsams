package be.jsams.server.service;

import java.util.List;

import be.jsams.server.model.ProductCategory;

/**
 * Product category service interface.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface ProductCategoryService extends Service<ProductCategory> {

    /**
     * Finds all {@link ProductCategory} following the criteria product category.
     * 
     * @param criteria
     *            the criteria product category
     * @return a list of {@link ProductCategory}
     */
    List<ProductCategory> findByCriteria(final ProductCategory criteria);
    
}
