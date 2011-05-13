package be.jsams.server.dao.management;

import java.util.List;

import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.ProductCategoryBean;
import be.jsams.server.dao.Dao;
import be.jsams.server.model.management.ProductCategory;

/**
 * Product DAO interface.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface ProductCategoryDao extends Dao<ProductCategory> {
    
    /**
     * Finds a list of {@link ProductCategory} following the criteria model
     * 
     * @param criteria
     *            the criteria model
     * @return a list of {@link ProductCategory}
     */
    List<ProductCategory> findByCriteria(final ProductCategoryBean criteria);

    /**
     * @return the currentSociety
     */
    SocietyBean getCurrentSociety();
    /**
     * @param currentSociety the currentSociety to set
     */
    void setCurrentSociety(SocietyBean currentSociety);

}
