package be.jsams.server.dao.impl;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.common.bean.model.management.ProductCategoryBean;
import be.jsams.server.dao.BaseJUnitTestClass;
import be.jsams.server.dao.MockDaoGenerator;
import be.jsams.server.dao.SocietyDao;
import be.jsams.server.dao.management.ProductCategoryDao;
import be.jsams.server.dao.management.ProductDao;
import be.jsams.server.dao.management.impl.ProductCategoryDaoImpl;
import be.jsams.server.dao.management.impl.ProductDaoImpl;
import be.jsams.server.model.Society;
import be.jsams.server.model.management.Product;
import be.jsams.server.model.management.ProductCategory;
import be.jsams.server.model.mock.MockModelGenerator;

/**
 * Test class for {@link ProductDaoImpl}.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class ProductDaoImplTest extends BaseJUnitTestClass {

    @Autowired
    private ProductDao dao;
    private Product product = MockModelGenerator.generateMockProduct();

    @Autowired
    private ProductCategoryDao categoryDao;
    private ProductCategory category = MockModelGenerator.generateMockProductCategory();

    @Autowired
    private SocietyDao societyDao;
    private Society society = MockModelGenerator.generateMockSociety();

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        product = new Product();
        product.setName("label");
        product.setPrice(10.01D);
        product.setQuantityStock(12);
        product.setReorderLevel(1);
        product.setVatApplicable(21.00D);
    }

    /**
     * Test method for {@link be.jsams.server.dao.impl.DaoImpl#findAll()}.
     */
    @Test
    public void testFindAll() {
        final Society persistedSociety = societyDao.add(society);
        category.setSociety(persistedSociety);
        SocietyBean societyBean = new SocietyBean(persistedSociety) {

            /**
             * Serial Version UID
             */
            private static final long serialVersionUID = -4652699508139431069L;

        };
        ((ProductCategoryDaoImpl) categoryDao).setCurrentSociety(societyBean);
        ProductCategory persistedCategory = categoryDao.add(category);
        product.setCategory(persistedCategory);
        Product persistedProduct = dao.add(product);
        ((ProductDaoImpl) dao).setCurrentSociety(societyBean);
        List<Product> founds = dao.findAll();
        assertTrue(founds.contains(persistedProduct));
    }

    /**
     * Test method for
     * {@link be.jsams.server.dao.management.impl.ProductDaoImpl#findByCriteria(be.jsams.common.bean.model.management.ProductBean)}
     * .
     */
    @Test
    public void testFindByCriteria() {
        final Society persistedSociety = societyDao.add(society);
        category.setSociety(persistedSociety);
        SocietyBean societyBean = new SocietyBean(persistedSociety) {

            /**
             * Serial Version UID
             */
            private static final long serialVersionUID = 8623360287537334295L;

        };
        categoryDao.setCurrentSociety(societyBean);
        ProductCategory persistedCategory = categoryDao.add(category);
        product.setCategory(persistedCategory);
        final Product persistedProduct = dao.add(product);
        final ProductCategoryBean categoryBean = new ProductCategoryBean(persistedCategory, societyBean);
        ProductBean criteria = new ProductBean(persistedProduct, societyBean) {

            /**
             * Serial Version UID
             */
            private static final long serialVersionUID = -4399264563287934694L;

            /**
             * {@inheritDoc}
             */
            @Override
            public ProductCategoryDao getProductCategoryDao() {
                return MockDaoGenerator.generateMockProductCategory(category);
            }

            /**
             * @return the category
             */
            @Override
            public ProductCategoryBean getCategory() {
                return categoryBean;
            }
        };
        dao.setCurrentSociety(societyBean);
        List<Product> founds = dao.findByCriteria(criteria);
        assertTrue(founds.contains(persistedProduct));
    }

}
