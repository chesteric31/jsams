package be.jsams.server.dao;

import java.util.List;

import be.jsams.server.model.Product;

/**
 * Product DAO interface.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface ProductDao extends GenericDao<Product> {

	List<Product> findByCriteria(Product criteria);

}
