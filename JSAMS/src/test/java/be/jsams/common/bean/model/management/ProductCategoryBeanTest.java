package be.jsams.common.bean.model.management;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import be.jsams.common.bean.model.MockBeanGenerator;

/**
 * Test class for {@link ProductCategoryBean} class.
 * 
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class ProductCategoryBeanTest {

    private ProductCategoryBean bean;

    /**
     * @throws java.lang.Exception a possible {@link Exception}
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * Test method for
     * {@link be.jsams.common.bean.model.AbstractTranslatableIdentityBean#refresh(
     * be.jsams.common.bean.model.AbstractIdentityBean)}
     * .
     */
    @Test
    public void testRefreshEquals() {
        bean = new ProductCategoryBean();
        ProductCategoryBean otherBean = MockBeanGenerator.generateMockProductCategory();
        bean.refresh(otherBean);
        assertTrue(bean.equals(otherBean));
    }

    /**
     * Test method for
     * {@link be.jsams.common.bean.model.management.ProductCategoryBean#clear()}.
     */
    @Test
    public void testClear() {
        bean = MockBeanGenerator.generateMockProductCategory();
        bean.clear();
        assertNull(bean.getLabel());
        assertNull(bean.getLabelFr());
        assertNull(bean.getLabelNl());
    }
    
}
