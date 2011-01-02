package be.jsams.server.service;

import java.util.List;

import be.jsams.server.model.Product;

/**
 * Product service interface.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface ProductService {

    /**
     * Creates and persists a new {@link Product} object.
     * 
     * @param customer the {@link Product} to persist
     */
    public void create(Product product);

    /**
     * Deletes a {@link Product} object.
     * 
     * @param product the {@link Product} to delete
     */
    public void delete(Product product);

    /**
     * Deletes a {@link Product} object.
     * 
     * @param id the id of the {@link Product} to delete
     */
    public void delete(Long id);

    /**
     * Updates a {@link Product} object.
     * 
     * @param product the {@link Product} to update
     */
    public void update(Product product);

    /**
     * Finds a {@link Product} object.
     * 
     * @param id the if of the {@link Product} to find
     */
    public Product findById(Long id);

    /**
     * Finds all the {@link Product} of the database.
     * 
     * @return a list of {@link Product}
     */
    public List<Product> findAll();

    /**
     * Finds all {@link Product} following the criteria product.
     * 
     * @param criteria the criteria product
     * @return a list of {@link Product}
     */
    public List<Product> findByCriteria(final Product criteria);

}
