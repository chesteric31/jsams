package be.jsams.server.model.management;

import org.junit.Test;

import be.jsams.common.bean.model.MockBeanGenerator;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.server.model.AbstractModelTest;

/**
 * Test class for {@link Product} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class ProductTest extends AbstractModelTest {
    
    private Product product;

    /**
     * Test method for
     * {@link be.jsams.server.model.Product#Product(be.jsams.common.bean.model.management.ProductBean)}
     * .
     */
    @Test
    public void testProductProductBean() {
        ProductBean bean = MockBeanGenerator.generateMockProduct();
        product = new Product(bean);
        checkProduct(bean, product);
    }

}
