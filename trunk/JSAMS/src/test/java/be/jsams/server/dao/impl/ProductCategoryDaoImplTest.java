package be.jsams.server.dao.impl;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import be.jsams.common.bean.builder.SocietyBeanBuilder;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.ProductCategoryBean;
import be.jsams.server.dao.BaseJUnitTestClass;
import be.jsams.server.dao.SocietyDao;
import be.jsams.server.dao.management.ProductCategoryDao;
import be.jsams.server.model.MockModelGenerator;
import be.jsams.server.model.Society;
import be.jsams.server.model.management.ProductCategory;

/**
 * Test class for {@link ProductCategoryDao}.
 * 
 * @author chesteric31
 * @version $$Rev$$ $$Date::                  $$ $$Author$$
 */
public class ProductCategoryDaoImplTest extends BaseJUnitTestClass {

    @Autowired
    private ProductCategoryDao dao;
    private ProductCategory productCategory;

    @Autowired
    private SocietyDao societyDao;
    private Society society = MockModelGenerator.generateMockSociety();
    
    @Autowired
    private SocietyBeanBuilder societyBeanBuilder;

    /**
     * @throws java.lang.Exception a possible {@link Exception}
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
        societyBeanBuilder.setModel(persistedSociety);
        SocietyBean societyBean = societyBeanBuilder.build(false);
        dao.setCurrentSociety(societyBean);
        ProductCategory category = dao.add(productCategory);
        List<ProductCategory> founds = dao.findAll();
        assertTrue(founds.contains(category));
    }

    /**
     * Test method for
     * {@link be.jsams.server.dao.management.impl.ProductCategoryDaoImpl#findByCriteria(
     * be.jsams.common.bean.model.management.ProductCategoryBean)}
     * .
     */
    @Test
    public void testFindByCriteria() {
        final Society persistedSociety = societyDao.add(society);
        societyBeanBuilder.setModel(persistedSociety);
        SocietyBean societyBean = societyBeanBuilder.build(false);
        ProductCategory category = dao.add(productCategory);
        dao.setCurrentSociety(societyBean);
        ProductCategoryBean criteria = new ProductCategoryBean(category, societyBean);
        List<ProductCategory> founds = dao.findByCriteria(criteria);
        assertTrue(founds.contains(category));
    }

}
