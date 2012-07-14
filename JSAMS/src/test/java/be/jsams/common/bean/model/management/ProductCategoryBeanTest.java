package be.jsams.common.bean.model.management;

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
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        bean = new ProductCategoryBean();
    }

    /**
     * Test method for {@link be.jsams.common.bean.model.AbstractTranslatableIdentityBean#refresh(be.jsams.common.bean.model.AbstractIdentityBean)}.
     */
    @Test
    public void testRefreshEquals() {
        ProductCategoryBean otherBean = MockBeanGenerator.generateMockProductCategory();
        bean.refresh(otherBean);
        assertTrue(bean.equals(otherBean));
    }

}
