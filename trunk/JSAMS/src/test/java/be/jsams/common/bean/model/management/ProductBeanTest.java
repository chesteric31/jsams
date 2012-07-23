package be.jsams.common.bean.model.management;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import be.jsams.common.bean.model.MockBeanGenerator;

/**
 * Test class for {@link ProductBean} class.
 * 
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class ProductBeanTest {

    private ProductBean bean;

    /**
     * @throws java.lang.Exception a possible {@link Exception}
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * Test method for
     * {@link be.jsams.common.bean.model.management.ProductBean#refresh(
     * be.jsams.common.bean.model.AbstractIdentityBean)}
     * .
     */
    @Test
    public void testRefreshEquals() {
        bean = new ProductBean();
        ProductBean otherBean = MockBeanGenerator.generateMockProduct();
        bean.refresh(otherBean);
        assertTrue(bean.equals(otherBean));
    }

    /**
     * Test method for
     * {@link be.jsams.common.bean.model.management.ProductBean#clear()}.
     */
    @Test
    public void testClear() {
        bean = MockBeanGenerator.generateMockProduct();
        bean.clear();
        ProductCategoryBean category = bean.getCategory();
        assertNull(category.getLabel());
        assertNull(category.getLabelFr());
        assertNull(category.getLabelNl());
        assertNull(bean.getName());
        assertNull(bean.getPrice());
        assertTrue(bean.getQuantityStock() == 0);
        assertTrue(bean.getReorderLevel() == 0);
        assertNull(bean.getVatApplicable());
    }

}
