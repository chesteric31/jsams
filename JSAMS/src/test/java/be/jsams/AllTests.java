package be.jsams;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import be.jsams.client.formatter.DoubleFormatterTest;
import be.jsams.common.bean.model.AddressBeanTest;
import be.jsams.common.bean.model.CivilityBeanTest;
import be.jsams.common.bean.model.ContactInformationBeanTest;
import be.jsams.common.bean.model.LegalFormBeanTest;
import be.jsams.common.bean.model.PaymentModeBeanTest;
import be.jsams.common.bean.model.PeriodBeanTest;
import be.jsams.common.bean.model.SocietyBeanTest;
import be.jsams.common.bean.model.management.AgentBeanTest;
import be.jsams.common.bean.model.management.CustomerBeanTest;
import be.jsams.common.bean.model.management.ProductBeanTest;
import be.jsams.common.bean.model.management.ProductCategoryBeanTest;
import be.jsams.common.bean.model.sale.CommandBeanTest;
import be.jsams.common.bean.model.sale.DeliveryOrderBeanTest;
import be.jsams.common.bean.model.sale.EstimateBeanTest;
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
@SuiteClasses(value = { DoubleFormatterTest.class, AddressBeanTest.class, CivilityBeanTest.class,
        LegalFormBeanTest.class, ContactInformationBeanTest.class, PaymentModeBeanTest.class, PeriodBeanTest.class,
        SocietyBeanTest.class, AgentBeanTest.class, CustomerBeanTest.class, ProductBeanTest.class,
        ProductCategoryBeanTest.class, CommandBeanTest.class, DeliveryOrderBeanTest.class, EstimateBeanTest.class,
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
