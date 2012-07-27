package be.jsams;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import be.jsams.client.formatter.DoubleFormatterTest;
import be.jsams.client.validator.edit.EditAddressValidatorTest;
import be.jsams.client.validator.edit.EditSocietyValidatorTest;
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
import be.jsams.common.bean.model.sale.BillBeanTest;
import be.jsams.common.bean.model.sale.CommandBeanTest;
import be.jsams.common.bean.model.sale.CreditNoteBeanTest;
import be.jsams.common.bean.model.sale.DeliveryOrderBeanTest;
import be.jsams.common.bean.model.sale.EstimateBeanTest;
import be.jsams.common.bean.validator.EmailValidatorTest;
import be.jsams.common.bean.validator.StringValidatorTest;
import be.jsams.server.dao.impl.AddressDaoImplTest;
import be.jsams.server.dao.impl.CivilityDaoImplTest;
import be.jsams.server.dao.impl.ContactInformationDaoImplTest;
import be.jsams.server.dao.impl.SocietyDaoImplTest;
import be.jsams.server.dao.management.impl.AgentDaoImplTest;
import be.jsams.server.dao.management.impl.CustomerDaoImplTest;
import be.jsams.server.dao.management.impl.ProductCategoryDaoImplTest;
import be.jsams.server.dao.management.impl.ProductDaoImplTest;
import be.jsams.server.dao.sale.impl.BillDaoImplTest;
import be.jsams.server.dao.sale.impl.CommandDaoImplTest;
import be.jsams.server.dao.sale.impl.CreditNoteDaoImplTest;
import be.jsams.server.dao.sale.impl.DeliveryOrderDaoImplTest;
import be.jsams.server.dao.sale.impl.EstimateDaoImplTest;
import be.jsams.server.model.utils.xml.JsamsDateAdapterTest;
import be.jsams.server.service.rss.impl.RSSFeedParserImplTest;

/**
 * Test suite for all JUNIT tests. <br />
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@RunWith(Suite.class)
@SuiteClasses(value = { DoubleFormatterTest.class, EditAddressValidatorTest.class, EditSocietyValidatorTest.class,
        AddressBeanTest.class, CivilityBeanTest.class, LegalFormBeanTest.class, ContactInformationBeanTest.class,
        PaymentModeBeanTest.class, PeriodBeanTest.class, SocietyBeanTest.class, AgentBeanTest.class,
        CustomerBeanTest.class, ProductBeanTest.class, ProductCategoryBeanTest.class, BillBeanTest.class,
        CommandBeanTest.class, CreditNoteBeanTest.class, DeliveryOrderBeanTest.class, EstimateBeanTest.class,
        EmailValidatorTest.class, StringValidatorTest.class, AddressDaoImplTest.class, CivilityDaoImplTest.class,
        ContactInformationDaoImplTest.class, SocietyDaoImplTest.class, AgentDaoImplTest.class,
        CustomerDaoImplTest.class, ProductCategoryDaoImplTest.class, ProductDaoImplTest.class, BillDaoImplTest.class,
        CommandDaoImplTest.class, CreditNoteDaoImplTest.class, DeliveryOrderDaoImplTest.class,
        EstimateDaoImplTest.class, JsamsDateAdapterTest.class, RSSFeedParserImplTest.class })
public class AllTests {

    /**
     * @throws java.lang.Exception a possible {@link Exception}
     */
    @Before
    public void setUp() throws Exception {
    }

}
