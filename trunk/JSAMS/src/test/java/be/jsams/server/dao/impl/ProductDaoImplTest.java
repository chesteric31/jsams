package be.jsams.server.dao.impl;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import be.jsams.common.bean.model.SocietyBean;
import be.jsams.server.dao.AbstractJUnitTestClass;
import be.jsams.server.dao.LegalFormDao;
import be.jsams.server.dao.MockDaoGenerator;
import be.jsams.server.dao.ProductCategoryDao;
import be.jsams.server.dao.ProductDao;
import be.jsams.server.dao.SocietyDao;
import be.jsams.server.model.Product;
import be.jsams.server.model.ProductCategory;
import be.jsams.server.model.Society;
import be.jsams.server.model.mock.MockModelGenerator;

/**
 * Test class for {@link ProductDaoImpl}.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class ProductDaoImplTest extends AbstractJUnitTestClass {

    @Autowired
    private ProductDao dao;
    private Product product;
    
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

            /**
             * {@inheritDoc}
             */
            @Override
            public LegalFormDao getLegalFormDao() {
                return MockDaoGenerator.generateMockLegalForm(persistedSociety);
            }
        };
        ((ProductCategoryDaoImpl) categoryDao).setCurrentSociety(societyBean);
        ProductCategory persistedCategory = categoryDao.add(category);
        product.setCategory(persistedCategory);
        Product persistedProduct = dao.add(product);
        ((ProductDaoImpl) dao).setCurrentSociety(societyBean);
        List<Product> founds = dao.findAll();
        assertTrue(founds.contains(persistedProduct));
    }

}
