package be.jsams.server.dao.impl;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.ProductCategoryBean;
import be.jsams.server.dao.AbstractJUnitTestClass;
import be.jsams.server.dao.LegalFormDao;
import be.jsams.server.dao.MockDaoGenerator;
import be.jsams.server.dao.SocietyDao;
import be.jsams.server.dao.management.ProductCategoryDao;
import be.jsams.server.dao.management.impl.ProductCategoryDaoImpl;
import be.jsams.server.model.Society;
import be.jsams.server.model.management.ProductCategory;
import be.jsams.server.model.mock.MockModelGenerator;

/**
 * Test class for {@link ProductCategoryDaoImpl}.
 * 
 * @author chesteric31
 * @version $$Rev$$ $$Date::                  $$ $$Author$$
 */
public class ProductCategoryDaoImplTest extends AbstractJUnitTestClass {

    @Autowired
    private ProductCategoryDao dao;
    private ProductCategory productCategory;

    @Autowired
    private SocietyDao societyDao;
    private Society society = MockModelGenerator.generateMockSociety();

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        productCategory = new ProductCategory();
        productCategory.setLabel("label");
        productCategory.setLabelFr("labelFr");
        productCategory.setLabelNl("labelNl");
        productCategory.setSociety(society);
    }

    /**
     * Test method for {@link be.jsams.server.dao.management.impl.ProductCategoryDaoImpl#findAll()}.
     */
    @Test
    public void testFindAll() {
        final Society persistedSociety = societyDao.add(society);
        productCategory.setSociety(persistedSociety);
        SocietyBean societyBean = new SocietyBean(persistedSociety) {

            /**
             * Serial Version UID
             */
            private static final long serialVersionUID = -4781433278494586047L;

            /**
             * {@inheritDoc}
             */
            @Override
            public LegalFormDao getLegalFormDao() {
                return MockDaoGenerator.generateMockLegalForm(persistedSociety);
            }
        };
        ((ProductCategoryDaoImpl) dao).setCurrentSociety(societyBean);
        ProductCategory category = dao.add(productCategory);
        List<ProductCategory> founds = dao.findAll();
        assertTrue(founds.contains(category));
    }

    /**
     * Test method for
     * {@link be.jsams.server.dao.management.impl.ProductCategoryDaoImpl#findByCriteria(be.jsams.common.bean.model.management.ProductCategoryBean)}
     * .
     */
    @Test
    public void testFindByCriteria() {
        final Society persistedSociety = societyDao.add(society);
        SocietyBean societyBean = new SocietyBean(persistedSociety) {
            /**
             * Serial Version UID
             */
            private static final long serialVersionUID = -4781433278494586047L;

            /**
             * {@inheritDoc}
             */
            @Override
            public LegalFormDao getLegalFormDao() {
                return MockDaoGenerator.generateMockLegalForm(persistedSociety);
            }
        };
        ProductCategory category = dao.add(productCategory);
        ProductCategoryBean criteria = new ProductCategoryBean(category, societyBean);
        List<ProductCategory> founds = dao.findByCriteria(criteria);
        assertTrue(founds.contains(category));
    }

}
