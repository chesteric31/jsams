package be.jsams.server.service.management;

import java.util.List;

import be.jsams.common.bean.model.management.ProductCategoryBean;
import be.jsams.server.service.Service;

/**
 * Product category service interface.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface ProductCategoryService extends Service<ProductCategoryBean> {

    /**
     * Find all {@link ProductCategoryBean} following the criteria product category.
     * 
     * @param criteria the criteria product category
     * @return a list of {@link ProductCategoryBean}
     */
    List<ProductCategoryBean> findByCriteria(final ProductCategoryBean criteria);

}
