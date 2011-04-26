package be.jsams.server.service.management;

import java.util.List;

import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.server.service.Service;

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
