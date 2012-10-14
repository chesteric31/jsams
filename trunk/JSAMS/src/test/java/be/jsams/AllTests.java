package be.jsams;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import be.jsams.client.formatter.DoubleFormatterTest;
import be.jsams.client.i18n.UserContextTest;
import be.jsams.client.validator.edit.EditAddressValidatorTest;
import be.jsams.client.validator.edit.EditSocietyValidatorTest;
import be.jsams.client.validator.edit.management.EditAgentValidatorTest;
import be.jsams.client.validator.edit.management.EditCustomerValidatorTest;
import be.jsams.client.validator.edit.management.EditProductCategoryValidatorTest;
import be.jsams.client.validator.edit.management.EditProductValidatorTest;
import be.jsams.client.validator.edit.sale.EditBillValidatorTest;
import be.jsams.client.validator.edit.sale.EditCommandValidatorTest;
import be.jsams.client.validator.edit.sale.EditCreditNoteValidatorTest;
import be.jsams.client.validator.edit.sale.EditDeliveryOrderValidatorTest;
import be.jsams.client.validator.edit.sale.EditEstimateValidatorTest;
import be.jsams.client.validator.search.management.SearchAgentValidatorTest;
import be.jsams.client.validator.search.management.SearchCustomerValidatorTest;
import be.jsams.client.validator.search.management.SearchProductValidatorTest;
import be.jsams.client.validator.wizard.DocumentValidatorTest;
import be.jsams.client.validator.wizard.DocumentsValidatorTest;
import be.jsams.client.validator.wizard.SourceTypeValidatorTest;
import be.jsams.client.validator.wizard.TransferModeValidatorTest;
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
import be.jsams.common.bean.model.sale.detail.BillDetailBeanTest;
import be.jsams.common.bean.model.sale.detail.CommandDetailBeanTest;
import be.jsams.common.bean.model.sale.detail.CreditNoteDetailBeanTest;
import be.jsams.common.bean.model.sale.detail.DeliveryOrderDetailBeanTest;
import be.jsams.common.bean.model.sale.detail.EstimateDetailBeanTest;
import be.jsams.common.bean.model.transfer.TransferBeanTest;
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
import be.jsams.server.service.update.impl.DownloaderServiceImplTest;

/**
 * Test suite for all JUNIT tests. <br />
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@RunWith(Suite.class)
@SuiteClasses(value = { DoubleFormatterTest.class, UserContextTest.class, EditAddressValidatorTest.class,
        EditSocietyValidatorTest.class, EditAgentValidatorTest.class, EditCustomerValidatorTest.class,
        EditProductValidatorTest.class, EditProductCategoryValidatorTest.class, EditEstimateValidatorTest.class,
        EditCommandValidatorTest.class, EditDeliveryOrderValidatorTest.class, EditBillValidatorTest.class,
        EditCreditNoteValidatorTest.class, SearchAgentValidatorTest.class, SearchCustomerValidatorTest.class,
        SearchProductValidatorTest.class, DocumentValidatorTest.class, DocumentsValidatorTest.class,
        SourceTypeValidatorTest.class, TransferModeValidatorTest.class, AddressBeanTest.class, CivilityBeanTest.class,
        LegalFormBeanTest.class, ContactInformationBeanTest.class, PaymentModeBeanTest.class, PeriodBeanTest.class,
        SocietyBeanTest.class, AgentBeanTest.class, CustomerBeanTest.class, ProductBeanTest.class,
        ProductCategoryBeanTest.class, BillBeanTest.class, CommandBeanTest.class, CreditNoteBeanTest.class,
        DeliveryOrderBeanTest.class, EstimateBeanTest.class, EstimateDetailBeanTest.class, CommandDetailBeanTest.class,
        DeliveryOrderDetailBeanTest.class, BillDetailBeanTest.class, CreditNoteDetailBeanTest.class,
        TransferBeanTest.class, EmailValidatorTest.class, StringValidatorTest.class, AddressDaoImplTest.class,
        CivilityDaoImplTest.class, ContactInformationDaoImplTest.class, SocietyDaoImplTest.class,
        AgentDaoImplTest.class, CustomerDaoImplTest.class, ProductCategoryDaoImplTest.class, ProductDaoImplTest.class,
        BillDaoImplTest.class, CommandDaoImplTest.class, CreditNoteDaoImplTest.class, DeliveryOrderDaoImplTest.class,
        EstimateDaoImplTest.class, JsamsDateAdapterTest.class, RSSFeedParserImplTest.class,
        DownloaderServiceImplTest.class })
public class AllTests {

}
