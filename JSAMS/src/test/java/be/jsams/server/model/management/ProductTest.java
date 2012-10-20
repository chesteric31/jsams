package be.jsams.server.model.management;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import be.jsams.common.bean.model.MockBeanGenerator;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.common.bean.model.management.ProductCategoryBean;
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
        Double price = product.getPrice();
        assertTrue(price.equals(bean.getPrice()));
        String name = product.getName();
        assertEquals(name, bean.getName());
        ProductCategory category = product.getCategory();
        ProductCategoryBean categoryBean = bean.getCategory();
        checkProductCategory(categoryBean, category);
        int quantityStock = product.getQuantityStock();
        assertEquals(quantityStock, bean.getQuantityStock());
        int reorderLevel = product.getReorderLevel();
        assertEquals(reorderLevel, bean.getReorderLevel());
        Double vatApplicable = product.getVatApplicable();
        assertEquals(vatApplicable, bean.getVatApplicable());
        Long id = product.getId();
        assertEquals(id, bean.getId());
    }

}
