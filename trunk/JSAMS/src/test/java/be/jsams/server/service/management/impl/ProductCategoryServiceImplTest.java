package be.jsams.server.service.management.impl;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import be.jsams.common.bean.model.LegalFormBean;
import be.jsams.common.bean.model.MockBeanGenerator;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.ProductCategoryBean;
import be.jsams.server.BaseJUnitTestClass;
import be.jsams.server.dao.LegalFormDao;
import be.jsams.server.dao.SocietyDao;
import be.jsams.server.model.LegalForm;
import be.jsams.server.model.Society;
import be.jsams.server.service.management.ProductCategoryService;

/**
 * Test class for {@link ProductCategoryServiceImpl} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class ProductCategoryServiceImplTest extends BaseJUnitTestClass {

    @Autowired
    @Qualifier(value = "productCategoryServiceTarget")
    private ProductCategoryService service;
    @Autowired
    private LegalFormDao legalFormDao;
    @Autowired
    private SocietyDao societyDao;
    private ProductCategoryBean productCategory;

    /**
     * @throws java.lang.Exception a possible {@link Exception}
     */
    @Before
    public void setUp() throws Exception {
        productCategory = MockBeanGenerator.generateMockProductCategory();
        SocietyBean society = productCategory.getSociety();
        LegalForm legalForm = legalFormDao.add(new LegalForm(society.getLegalForm()));
        society.setLegalForm(new LegalFormBean(legalForm));
        Society persistedSociety = societyDao.add(new Society(society));
        productCategory.setSociety(new SocietyBean(persistedSociety));
    }

    /**
     * Test method for {@link be.jsams.server.service.management.impl.ProductCategoryServiceImpl
     * #create(be.jsams.common.bean.model.management.ProductCategoryBean)}.
     */
    @Test
    public void testCreate() {
        ProductCategoryBean created = service.create(productCategory);
        List<ProductCategoryBean> founds = service.findAll(productCategory.getSociety());
        assertTrue(founds.contains(created));
    }

    /**
     * Test method for {@link be.jsams.server.service.management.impl.ProductCategoryServiceImpl
     * #delete(be.jsams.common.bean.model.management.ProductCategoryBean)}.
     */
    @Test
    public void testDeleteProductCategoryBean() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link be.jsams.server.service.management.impl.ProductCategoryServiceImpl
     * #delete(java.lang.Long)}.
     */
    @Test
    public void testDeleteLong() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link be.jsams.server.service.management.impl.ProductCategoryServiceImpl#findAll()}.
     */
    @Test
    public void testFindAll() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link be.jsams.server.service.management.impl.ProductCategoryServiceImpl
     * #findById(java.lang.Long)}.
     */
    @Test
    public void testFindById() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link be.jsams.server.service.management.impl.ProductCategoryServiceImpl
     * #update(be.jsams.common.bean.model.management.ProductCategoryBean)}.
     */
    @Test
    public void testUpdate() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link be.jsams.server.service.management.impl.ProductCategoryServiceImpl
     * #findByCriteria(be.jsams.common.bean.model.management.ProductCategoryBean)}.
     */
    @Test
    public void testFindByCriteria() {
        fail("Not yet implemented");
    }

}
