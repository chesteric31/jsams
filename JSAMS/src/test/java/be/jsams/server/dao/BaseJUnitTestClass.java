package be.jsams.server.dao;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * Abstract class for all test classes.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
public class BaseJUnitTestClass extends AbstractTransactionalJUnit4SpringContextTests {

    /**
     * Constructor
     */
    protected BaseJUnitTestClass() {
    }

}
