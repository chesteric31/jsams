package be.jsams.server.service.management.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import be.jsams.common.bean.model.LegalFormBean;
import be.jsams.common.bean.model.MockBeanGenerator;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.common.bean.model.management.ProductCategoryBean;
import be.jsams.server.BaseJUnitTestClass;
import be.jsams.server.dao.LegalFormDao;
import be.jsams.server.dao.SocietyDao;
import be.jsams.server.dao.management.ProductCategoryDao;
import be.jsams.server.model.LegalForm;
import be.jsams.server.model.Society;
import be.jsams.server.model.management.ProductCategory;
import be.jsams.server.service.management.ProductService;

/**
 * Test class for {@link ProductServiceImpl} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class ProductServiceImplTest extends BaseJUnitTestClass {

    @Autowired
    @Qualifier(value = "productServiceTarget")
    private ProductService service;
    @Autowired
    private LegalFormDao legalFormDao;
    @Autowired
    private SocietyDao societyDao;
    private ProductBean product;
    @Autowired
    private ProductCategoryDao productCategoryDao;

    /**
     * @throws java.lang.Exception a possible {@link Exception}
     */
    @Before
    public void setUp() throws Exception {
        product = MockBeanGenerator.generateMockProduct();
        ProductCategoryBean productCategory = product.getCategory();
        SocietyBean society = productCategory.getSociety();
        LegalForm legalForm = legalFormDao.add(new LegalForm(society.getLegalForm()));
        society.setLegalForm(new LegalFormBean(legalForm));
        Society persistedSociety = societyDao.add(new Society(society));
        productCategory.setSociety(new SocietyBean(persistedSociety));
        ProductCategory persistedProductCategory = productCategoryDao.add(new ProductCategory(productCategory));
        product.setCategory(new ProductCategoryBean(persistedProductCategory, new SocietyBean(persistedSociety)));
    }

    /**
     * Test method for {@link be.jsams.server.service.management.impl.ProductServiceImpl
     * #create(be.jsams.common.bean.model.management.ProductBean)}.
     */
    @Test
    public void testCreate() {
        ProductBean created = service.create(product);
        List<ProductBean> founds = service.findAll(created.getCategory().getSociety());
        assertTrue(founds.contains(created));
    }

    /**
     * Test method for {@link be.jsams.server.service.management.impl.ProductServiceImpl
     * #delete(be.jsams.common.bean.model.management.ProductBean)}.
     */
    @Test
    public void testDeleteProductBean() {
        ProductBean created = service.create(product);
        service.delete(created);
        ProductBean found = service.findById(created.getId());
        assertNull(found);
    }

    /**
     * Test method for {@link be.jsams.server.service.management.impl.ProductServiceImpl
     * #delete(java.lang.Long)}.
     */
    @Test
    public void testDeleteLong() {
        ProductBean created = service.create(product);
        Long id = created.getId();
        service.delete(id);
        ProductBean found = service.findById(id);
        assertNull(found);
    }

    /**
     * Test method for {@link be.jsams.server.service.management.impl.ProductServiceImpl#findAll(SocietyBean))}.
     */
    @Test
    public void testFindAll() {
        ProductBean created = service.create(product);
        List<ProductBean> founds = service.findAll(created.getCategory().getSociety());
        assertTrue(founds.contains(created));
    }

    /**
     * Test method for {@link be.jsams.server.service.management.impl.ProductServiceImpl
     * #findById(java.lang.Long)}.
     */
    @Test
    public void testFindById() {
        ProductBean created = service.create(product);
        ProductBean found = service.findById(created.getId());
        assertEquals(created, found);
    }

    /**
     * Test method for {@link be.jsams.server.service.management.impl.ProductServiceImpl
     * #update(be.jsams.common.bean.model.management.ProductBean)}.
     */
    @Test
    public void testUpdate() {
        ProductBean created = service.create(product);
        String newName = "newName";
        created.setName(newName);
        service.update(created);
        ProductBean found = service.findById(created.getId());
        assertEquals(newName, found.getName());
    }

    /**
     * Test method for {@link be.jsams.server.service.management.impl.ProductServiceImpl
     * #findByCriteria(be.jsams.common.bean.model.management.ProductBean)}.
     */
    @Test
    public void testFindByCriteria() {
        ProductBean created = service.create(product);
        ProductBean criteria = new ProductBean();
        criteria.setName(created.getName());
        criteria.setCategory(created.getCategory());
        List<ProductBean> founds = service.findByCriteria(criteria);
        assertTrue(founds.contains(created));
    }

}
