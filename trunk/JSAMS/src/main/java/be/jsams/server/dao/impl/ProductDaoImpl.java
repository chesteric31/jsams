package be.jsams.server.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Query;

import com.mysql.jdbc.StringUtils;

import be.jsams.server.dao.ProductDao;
import be.jsams.server.model.Product;
import be.jsams.server.model.ProductCategory;

/**
 * Product DAO implementation.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class ProductDaoImpl extends GenericDaoImpl<Product> implements
		ProductDao {

	public ProductDaoImpl(Class<Product> type) {
		super(type);
	}

	@SuppressWarnings("unchecked")
	public List<Product> findByCriteria(Product criteria) {
		StringBuilder queryBuilder = new StringBuilder("FROM Product p");
		
		boolean isFirst = true;
		
		ProductCategory category = criteria.getCategory();
		String name = criteria.getName();
		BigDecimal price = criteria.getPrice();
		int quantityStock = criteria.getQuantityStock();
		int reorderLevel = criteria.getReorderLevel();
		BigDecimal vatApplicable = criteria.getVatApplicable();
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
			queryBuilder.append(" p.price = " + price.toPlainString());
		}
		if (reorderLevel != -1) {
			if (isFirst) {
				queryBuilder.append(" WHERE");
				isFirst = false;
			} else {
				queryBuilder.append(" AND");
			}
			queryBuilder.append(" p.reorderLevel = " + reorderLevel);
		}
		if (quantityStock != -1) {
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
			queryBuilder.append(" p.vatApplicable = " + vatApplicable.toPlainString());
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
