package be.jsams.server.service;

import java.util.List;

import be.jsams.common.bean.model.ProductBean;

/**
 * Product service interface.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface ProductService extends Service<ProductBean> {

    /**
     * Finds all {@link ProductBean} following the criteria product.
     * 
     * @param criteria
     *            the criteria product
     * @return a list of {@link ProductBean}
     */
    List<ProductBean> findByCriteria(final ProductBean criteria);

}
