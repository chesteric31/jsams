package be.jsams.common.bean.builder.management;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import be.jsams.common.bean.model.MockBeanGenerator;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.server.model.MockModelGenerator;
import be.jsams.server.model.ModelTestHelper;
import be.jsams.server.model.management.Customer;

/**
 * Test class for {@link CustomerBeanBuilder} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
public class CustomerBeanBuilderTest extends AbstractTransactionalJUnit4SpringContextTests  {

    @Autowired
    private CustomerBeanBuilder builder;
    
    /**
     * Test method for
     * {@link be.jsams.common.bean.builder.management.CustomerBeanBuilder
     * #build(be.jsams.server.model.management.Customer, be.jsams.common.bean.model.SocietyBean)}
     * .
     */
    @Test
    public void testBuild() {
        Customer customer = MockModelGenerator.generateMockCustomer();
        SocietyBean societyBean = MockBeanGenerator.generateMockSociety();
        CustomerBean customerBean = builder.build(customer, societyBean);
        ModelTestHelper.checkCustomer(customerBean, customer);
    }

}
