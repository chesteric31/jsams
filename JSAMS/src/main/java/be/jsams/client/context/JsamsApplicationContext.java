package be.jsams.client.context;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import be.jsams.common.bean.builder.LegalFormBeanBuilder;
import be.jsams.common.bean.builder.PaymentModeBeanBuilder;
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
import be.jsams.server.service.sale.BillService;
import be.jsams.server.service.sale.CommandService;
import be.jsams.server.service.sale.CreditNoteService;
import be.jsams.server.service.sale.DeliveryOrderService;
import be.jsams.server.service.sale.EstimateService;
import be.jsams.server.service.transfer.TransferService;

/**
 * This class provides static methods to get a reference to a specific service, anywhere in this project.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public final class JsamsApplicationContext {

    public static final String CONFIG = "ApplicationContext.xml";

    private static ClassPathXmlApplicationContext context;

    /**
     * Constructor to avoid to instance this utility class.
     */
    private JsamsApplicationContext() {
    }

    /**
     * @param context the {@link ClassPathXmlApplicationContext} to set
     */
    public static void setContext(final ClassPathXmlApplicationContext context) {
        JsamsApplicationContext.context = context;
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

}
