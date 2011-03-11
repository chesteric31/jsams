package be.jsams.server.dao.impl;

import java.util.List;

import javax.persistence.Query;

import be.jsams.common.bean.model.ProductBean;
import be.jsams.common.bean.model.ProductCategoryBean;
import be.jsams.server.dao.ProductDao;
import be.jsams.server.model.Product;

import com.mysql.jdbc.StringUtils;

/**
 * Product DAO implementation.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class ProductDaoImpl extends DaoImpl<Product> implements ProductDao {

    /**
     * Constructor
     * 
     * @param type
     *            the class type
     */
    public ProductDaoImpl(final Class<Product> type) {
        super(type);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Product> findByCriteria(final ProductBean criteria) {
        StringBuilder queryBuilder = new StringBuilder("FROM Product p");

        boolean isFirst = true;

        ProductCategoryBean category = (ProductCategoryBean) criteria.getCategory().getSelection();
        String name = criteria.getName();
        Double price = criteria.getPrice();
        int quantityStock = criteria.getQuantityStock();
        int reorderLevel = criteria.getReorderLevel();
        Double vatApplicable = criteria.getVatApplicable();
        if (!StringUtils.isNullOrEmpty(name)) {
            if (isFirst) {
                queryBuilder.append(" WHERE");
                isFirst = false;
            }
            queryBuilder.append(" p.name LIKE '%" + name + "%'");
        }
        if (price != null) {
            if (isFirst) {
                queryBuilder.append(" WHERE");
                isFirst = false;
            } else {
                queryBuilder.append(" AND");
            }
            queryBuilder.append(" p.price = " + price);
        }
        if (reorderLevel != 0) {
            if (isFirst) {
                queryBuilder.append(" WHERE");
                isFirst = false;
            } else {
                queryBuilder.append(" AND");
            }
            queryBuilder.append(" p.reorderLevel = " + reorderLevel);
        }
        if (quantityStock != 0) {
            if (isFirst) {
                queryBuilder.append(" WHERE");
                isFirst = false;
            } else {
                queryBuilder.append(" AND");
            }
            queryBuilder.append(" p.quantityStock = " + quantityStock);
        }
        if (vatApplicable != null) {
            if (isFirst) {
                queryBuilder.append(" WHERE");
                isFirst = false;
            } else {
                queryBuilder.append(" AND");
            }
            queryBuilder.append(" p.vatApplicable = " + vatApplicable);
        }
        if (category != null) {
            if (isFirst) {
                queryBuilder.append(" WHERE");
                isFirst = false;
            } else {
                queryBuilder.append(" AND");
            }
            queryBuilder.append(" p.category.id = " + category.getId());
        }

        Query query = getEntityManager().createQuery(queryBuilder.toString());
        List<Product> result = query.getResultList();
        return result;
    }

}