package be.jsams.server.model.management;

import org.junit.Test;

import be.jsams.common.bean.model.MockBeanGenerator;
import be.jsams.common.bean.model.management.ProductCategoryBean;
import be.jsams.server.model.ModelTestHelper;

/**
 * Test class for {@link ProductCategory} class.
 * 
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class ProductCategoryTest {

    private ProductCategory productCategory;

    /**
     * Test method for
     * {@link be.jsams.server.model.ProductCategory
     * #ProductCategory(be.jsams.common.bean.model.management.ProductCategoryBean)}
     * .
     */
    @Test
    public void testProductCategoryProductCategoryBean() {
        ProductCategoryBean bean = MockBeanGenerator.generateMockProductCategory();
        productCategory = new ProductCategory(bean);
        ModelTestHelper.checkProductCategory(bean, productCategory);
    }

}
