package be.jsams.server.dao.management.impl;

import java.util.List;

import javax.persistence.Query;

import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.common.bean.model.management.ProductCategoryBean;
import be.jsams.server.dao.impl.DaoImpl;
import be.jsams.server.dao.management.ProductDao;
import be.jsams.server.model.management.Product;

import com.mysql.jdbc.StringUtils;

/**
 * Product DAO implementation.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class ProductDaoImpl extends DaoImpl<Product> implements ProductDao {

    /**
     * Constructor.
     * 
     * @param type the class type
     */
    public ProductDaoImpl(final Class<Product> type) {
        super(type);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Product> findAll(Long currentSocietyId) {
        StringBuilder queryBuilder = new StringBuilder("FROM Product p");

        queryBuilder.append(WHERE);
        queryBuilder.append("p.category.society.id = " + currentSocietyId);

        Query query = getEntityManager().createQuery(queryBuilder.toString());
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Product> findByCriteria(Long currentSocietyId, final ProductBean criteria) {
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
                queryBuilder.append(WHERE);
                isFirst = false;
            }
            queryBuilder.append(" p.name LIKE '%" + name + "%'");
        }
        if (price != null) {
            if (isFirst) {
                queryBuilder.append(WHERE);
                isFirst = false;
            } else {
                queryBuilder.append(AND);
            }
            queryBuilder.append(" p.price = " + price);
        }
        if (reorderLevel != 0) {
            if (isFirst) {
                queryBuilder.append(WHERE);
                isFirst = false;
            } else {
                queryBuilder.append(AND);
            }
            queryBuilder.append(" p.reorderLevel = " + reorderLevel);
        }
        if (quantityStock != 0) {
            if (isFirst) {
                queryBuilder.append(WHERE);
                isFirst = false;
            } else {
                queryBuilder.append(AND);
            }
            queryBuilder.append(" p.quantityStock = " + quantityStock);
        }
        if (vatApplicable != null) {
            if (isFirst) {
                queryBuilder.append(WHERE);
                isFirst = false;
            } else {
                queryBuilder.append(AND);
            }
            queryBuilder.append(" p.vatApplicable = " + vatApplicable);
        }
        if (category != null) {
            if (isFirst) {
                queryBuilder.append(WHERE);
                isFirst = false;
            } else {
                queryBuilder.append(AND);
            }
            queryBuilder.append(" p.category.id = " + category.getId());
        } else {
            if (isFirst) {
                queryBuilder.append(WHERE);
                isFirst = false;
            } else {
                queryBuilder.append(AND);
            }
            queryBuilder.append(" p.category.society.id = " + currentSocietyId);
        }

        Query query = getEntityManager().createQuery(queryBuilder.toString());
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> findTop5Products(Long societyId) {
        StringBuilder queryBuilder = new StringBuilder(
                "select p, sum(bd.quantity* bd.price*(1-(coalesce(bd.discountRate, 0)/100))) "
                        + "from Bill b, BillDetail bd, Customer c, Product p "
                        + "WHERE b.id = bd.bill.id and bd.product.id = p.id and c.society.id = " + societyId
                        + " group by p.id "
                        + "ORDER BY sum(bd.quantity* bd.price*(1-(coalesce(bd.discountRate, 0)/100))) DESC");

        Query query = getEntityManager().createQuery(queryBuilder.toString());
        return query.getResultList();
    }

}
