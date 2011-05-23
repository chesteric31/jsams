package be.jsams.server;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import be.jsams.server.dao.impl.AddressDaoImplTest;
import be.jsams.server.dao.impl.AgentDaoImplTest;
import be.jsams.server.dao.impl.CivilityDaoImplTest;
import be.jsams.server.dao.impl.ContactInformationDaoImplTest;
import be.jsams.server.dao.impl.ProductCategoryDaoImplTest;
import be.jsams.server.dao.impl.ProductDaoImplTest;
import be.jsams.server.dao.impl.SocietyDaoImplTest;

/**
 * Test suite for all JUNIT tests.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@RunWith(Suite.class)
@SuiteClasses(value = { AddressDaoImplTest.class, AgentDaoImplTest.class, CivilityDaoImplTest.class,
        ContactInformationDaoImplTest.class, ProductCategoryDaoImplTest.class, ProductDaoImplTest.class,
        SocietyDaoImplTest.class })
public class AllTests {

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

}
