package be.jsams;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import be.jsams.client.formatter.DoubleFormatterTest;
import be.jsams.common.bean.model.AddressBeanTest;
import be.jsams.common.bean.model.ContactInformationBeanTest;
import be.jsams.common.bean.model.PeriodBeanTest;
import be.jsams.common.bean.model.SocietyBeanTest;
import be.jsams.common.bean.model.management.ProductBeanTest;
import be.jsams.common.bean.model.management.ProductCategoryBeanTest;
import be.jsams.common.bean.validator.StringValidatorTest;
import be.jsams.server.dao.impl.AddressDaoImplTest;
import be.jsams.server.dao.impl.AgentDaoImplTest;
import be.jsams.server.dao.impl.CivilityDaoImplTest;
import be.jsams.server.dao.impl.ContactInformationDaoImplTest;
import be.jsams.server.dao.impl.ProductCategoryDaoImplTest;
import be.jsams.server.dao.impl.ProductDaoImplTest;
import be.jsams.server.dao.impl.SocietyDaoImplTest;
import be.jsams.server.dao.sale.impl.CommandDaoImplTest;

/**
 * Test suite for all JUNIT tests.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@RunWith(Suite.class)
@SuiteClasses(value = { DoubleFormatterTest.class, AddressBeanTest.class, ContactInformationBeanTest.class,
        PeriodBeanTest.class, SocietyBeanTest.class, ProductBeanTest.class, ProductCategoryBeanTest.class,
        StringValidatorTest.class, AddressDaoImplTest.class, AgentDaoImplTest.class, CivilityDaoImplTest.class,
        ContactInformationDaoImplTest.class, ProductCategoryDaoImplTest.class, ProductDaoImplTest.class,
        SocietyDaoImplTest.class, CommandDaoImplTest.class })
public class AllTests {

    /**
     * @throws java.lang.Exception a possible {@link Exception}
     */
    @Before
    public void setUp() throws Exception {
    }

}
