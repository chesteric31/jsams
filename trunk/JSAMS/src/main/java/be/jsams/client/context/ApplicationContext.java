package be.jsams.client.context;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import be.jsams.common.bean.builder.LegalFormBeanBuilder;
import be.jsams.common.bean.builder.PaymentModeBeanBuilder;
import be.jsams.common.bean.builder.ProductBeanBuilder;
import be.jsams.common.bean.builder.ProductCategoryBeanBuilder;
import be.jsams.common.bean.builder.SocietyBeanBuilder;
import be.jsams.common.bean.builder.management.AgentBeanBuilder;
import be.jsams.common.bean.builder.management.CustomerBeanBuilder;
import be.jsams.server.dao.CivilityDao;
import be.jsams.server.dao.LegalFormDao;
import be.jsams.server.dao.PaymentModeDao;
import be.jsams.server.dao.management.ProductCategoryDao;
import be.jsams.server.service.SocietyService;
import be.jsams.server.service.management.AgentService;
import be.jsams.server.service.management.CustomerService;
import be.jsams.server.service.management.ProductCategoryService;
import be.jsams.server.service.management.ProductService;
import be.jsams.server.service.pdf.impl.PdfBillServiceImpl;
import be.jsams.server.service.pdf.impl.PdfCommandServiceImpl;
import be.jsams.server.service.pdf.impl.PdfCreditNoteServiceImpl;
import be.jsams.server.service.pdf.impl.PdfDeliveryOrderServiceImpl;
import be.jsams.server.service.pdf.impl.PdfEstimateServiceImpl;
import be.jsams.server.service.property.PropertyHolder;
import be.jsams.server.service.rss.RSSFeedParser;
import be.jsams.server.service.sale.BillService;
import be.jsams.server.service.sale.CommandService;
import be.jsams.server.service.sale.CreditNoteService;
import be.jsams.server.service.sale.DeliveryOrderService;
import be.jsams.server.service.sale.EstimateService;
import be.jsams.server.service.transfer.TransferService;
import be.jsams.server.service.update.DownloaderService;

/**
 * This class provides static methods to get a reference to a specific service,
 * anywhere in this project.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public final class ApplicationContext {

    public static final String CONFIG = "ApplicationContext.xml";

    private static ClassPathXmlApplicationContext context;

    /**
     * Constructor to avoid to instance this utility class.
     */
    private ApplicationContext() {
    }

    /**
     * @param context the {@link ClassPathXmlApplicationContext} to set
     */
    public static void setContext(final ClassPathXmlApplicationContext context) {
        ApplicationContext.context = context;
    }

    /**
     * @return the {@link SocietyService}
     */
    public static SocietyService getSocietyService() {
        return (SocietyService) context.getBean("societyService");
    }

    /**
     * @return the {@link LegalFormDao}
     */
    public static LegalFormDao getLegalFormDao() {
        return (LegalFormDao) context.getBean("legalFormDao");
    }

    /**
     * @return the {@link CivilityDao}
     */
    public static CivilityDao getCivilityDao() {
        return (CivilityDao) context.getBean("civilityDao");
    }

    /**
     * @return the {@link PaymentModeDao}
     */
    public static PaymentModeDao getPaymentModeDao() {
        return (PaymentModeDao) context.getBean("paymentModeDao");
    }

    /**
     * @return the {@link CustomerService}
     */
    public static CustomerService getCustomerService() {
        return (CustomerService) context.getBean("customerService");
    }

    /**
     * @return the {@link ProductCategoryDao}
     */
    public static ProductCategoryDao getProductCategoryDao() {
        return (ProductCategoryDao) context.getBean("productCategoryDao");
    }

    /**
     * @return the {@link ProductService}
     */
    public static ProductService getProductService() {
        return (ProductService) context.getBean("productService");
    }

    /**
     * @return the {@link ProductCategoryService}
     */
    public static ProductCategoryService getProductCategoryService() {
        return (ProductCategoryService) context.getBean("productCategoryService");
    }

    /**
     * @return the {@link EstimateService}
     */
    public static EstimateService getEstimateService() {
        return (EstimateService) context.getBean("estimateService");
    }

    /**
     * @return the {@link AgentService}
     */
    public static AgentService getAgentService() {
        return (AgentService) context.getBean("agentService");
    }

    /**
     * @return the {@link SocietyBeanBuilder}
     */
    public static SocietyBeanBuilder getSocietyBeanBuilder() {
        return (SocietyBeanBuilder) context.getBean("societyBeanBuilder");
    }

    /**
     * @return the {@link ProductBeanBuilder}
     */
    public static ProductBeanBuilder getProductBeanBuilder() {
        return (ProductBeanBuilder) context.getBean("productBeanBuilder");
    }

    /**
     * @return the {@link ProductCategoryBeanBuilder}
     */
    public static ProductCategoryBeanBuilder getProductCategoryBeanBuilder() {
        return (ProductCategoryBeanBuilder) context.getBean("productCategoryBeanBuilder");
    }

    /**
     * @return the {@link CommandService}
     */
    public static CommandService getCommandService() {
        return (CommandService) context.getBean("commandService");
    }

    /**
     * @return the {@link DeliveryOrderService}
     */
    public static DeliveryOrderService getDeliveryOrderService() {
        return (DeliveryOrderService) context.getBean("deliveryOrderService");
    }

    /**
     * @return the {@link BillService}
     */
    public static BillService getBillService() {
        return (BillService) context.getBean("billService");
    }

    /**
     * @return the {@link CreditNoteService}
     */
    public static CreditNoteService getCreditNoteService() {
        return (CreditNoteService) context.getBean("creditNoteService");
    }

    /**
     * @return the {@link LegalFormBeanBuilder}
     */
    public static LegalFormBeanBuilder getLegalFormBeanBuilder() {
        return (LegalFormBeanBuilder) context.getBean("legalFormBeanBuilder");
    }

    /**
     * @return the {@link CustomerBeanBuilder}
     */
    public static CustomerBeanBuilder getCustomerBeanBuilder() {
        return (CustomerBeanBuilder) context.getBean("customerBeanBuilder");
    }

    /**
     * @return the {@link AgentBeanBuilder}
     */
    public static AgentBeanBuilder getAgentBeanBuilder() {
        return (AgentBeanBuilder) context.getBean("agentBeanBuilder");
    }

    /**
     * @return the {@link PaymentModeBeanBuilder}
     */
    public static PaymentModeBeanBuilder getPaymentModeBeanBuilder() {
        return (PaymentModeBeanBuilder) context.getBean("paymentModeBeanBuilder");
    }

    /**
     * @return the {@link TransferService}
     */
    public static TransferService getTransferService() {
        return (TransferService) context.getBean("transferService");
    }

    /**
     * @return the {@link PdfEstimateServiceImpl}
     */
    public static PdfEstimateServiceImpl getPdfEstimateService() {
        return (PdfEstimateServiceImpl) context.getBean("pdfEstimateServiceImpl");
    }

    /**
     * @return the {@link PdfCommandServiceImpl}
     */
    public static PdfCommandServiceImpl getPdfCommandService() {
        return (PdfCommandServiceImpl) context.getBean("pdfCommandServiceImpl");
    }

    /**
     * @return the {@link PdfDeliveryOrderServiceImpl}
     */
    public static PdfDeliveryOrderServiceImpl getPdfDeliveryOrderService() {
        return (PdfDeliveryOrderServiceImpl) context.getBean("pdfDeliveryOrderServiceImpl");
    }

    /**
     * @return the {@link PdfBillServiceImpl}
     */
    public static PdfBillServiceImpl getPdfBillService() {
        return (PdfBillServiceImpl) context.getBean("pdfBillServiceImpl");
    }

    /**
     * @return the {@link PdfCreditNoteServiceImpl}
     */
    public static PdfCreditNoteServiceImpl getPdfCreditNoteService() {
        return (PdfCreditNoteServiceImpl) context.getBean("pdfCreditNoteServiceImpl");
    }

    /**
     * @return the {@link PropertyHolder}
     */
    public static PropertyHolder getPropertyHolder() {
        return (PropertyHolder) context.getBean("propertyHolder");
    }

    /**
     * @return the {@link RSSFeedParser}
     */
    public static RSSFeedParser getRSSFeedParser() {
        return (RSSFeedParser) context.getBean("rssFeedParser");
    }
    
    /**
     * @return the {@link DownloaderService}
     */
    public static DownloaderService getDownloaderService() {
        return (DownloaderService) context.getBean("downloaderService");
    }

}
