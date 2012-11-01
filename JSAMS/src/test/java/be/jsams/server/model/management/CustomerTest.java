package be.jsams.server.model.management;

import org.junit.Test;

import be.jsams.common.bean.model.MockBeanGenerator;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.server.model.AbstractModelTest;

/**
 * Test class for {@link Customer} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class CustomerTest extends AbstractModelTest {
    
    private Customer customer;

    /**
     * Test method for
     * {@link be.jsams.server.model.Customer#Customer(be.jsams.common.bean.model.management.CustomerBean)}
     * .
     */
    @Test
    public void testCustomerCustomerBean() {
        CustomerBean bean = MockBeanGenerator.generateMockCustomer();
        customer = new Customer(bean);
        checkCustomer(bean, customer);
    }

}
