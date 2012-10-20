package be.jsams.server.model.management;

import org.junit.Test;

import be.jsams.common.bean.model.MockBeanGenerator;
import be.jsams.common.bean.model.management.ProductCategoryBean;
import be.jsams.server.model.AbstractModelTest;

/**
 * Test class for {@link ProductCategory} class.
 * 
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class ProductCategoryTest extends AbstractModelTest {

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
        checkProductCategory(bean, productCategory);
    }

}
