package be.jsams.server.dao.impl;

import java.util.List;

import javax.persistence.Query;

import be.jsams.server.dao.ProductCategoryDao;
import be.jsams.server.model.ProductCategory;

import com.mysql.jdbc.StringUtils;

/**
 * Product Category DAO implementation.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class ProductCategoryDaoImpl extends DaoImpl<ProductCategory> implements ProductCategoryDao {

    /**
     * Constructor
     * 
     * @param type
     *            the class type
     */
    public ProductCategoryDaoImpl(final Class<ProductCategory> type) {
        super(type);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<ProductCategory> findByCriteria(final ProductCategory criteria) {
        StringBuilder queryBuilder = new StringBuilder("FROM ProductCategory c");

        boolean isFirst = true;

        String name = criteria.getLabel();
        String nameFr = criteria.getLabelFr();
        String nameNl = criteria.getLabelNl();
        if (!StringUtils.isNullOrEmpty(name)) {
            if (isFirst) {
                queryBuilder.append(" WHERE");
                isFirst = false;
            }
            queryBuilder.append(" c.label LIKE '%" + name + "%'");
        }
        if (nameFr != null) {
            if (isFirst) {
                queryBuilder.append(" WHERE");
                isFirst = false;
            } else {
                queryBuilder.append(" AND");
            }
            queryBuilder.append(" c.labelFr LIKE '%" + nameFr + "%'");
        }
        if (nameNl != null) {
            if (isFirst) {
                queryBuilder.append(" WHERE");
                isFirst = false;
            } else {
                queryBuilder.append(" AND");
            }
            queryBuilder.append(" c.labelNl LIKE '%" + nameNl + "%'");
        }

        Query query = getEntityManager().createQuery(queryBuilder.toString());
        List<ProductCategory> result = query.getResultList();
        return result;
    }

}
