package be.jsams.client.context;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import be.jsams.server.dao.CivilityDao;
import be.jsams.server.dao.LegalFormDao;
import be.jsams.server.dao.PaymentModeDao;
import be.jsams.server.dao.ProductCategoryDao;
import be.jsams.server.service.CustomerService;
import be.jsams.server.service.SocietyService;

/**
 * This class provides static methods to get a reference to a specific service,
 * anywhere in this project. As a side-job, it provides caching for business
 * objects that are frequently used.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class JsamsApplicationContext {

	public static final String CONFIG = "ApplicationContext.xml";

	private static ClassPathXmlApplicationContext context;

	/**
	 * 
	 * @param context the {@link ClassPathXmlApplicationContext} to set
	 */
	public static void setContext(final ClassPathXmlApplicationContext context) {
		JsamsApplicationContext.context = context;
	}

	/**
	 * 
	 * @return the {@link SocietyService}
	 */
	public static SocietyService getSocietyService() {
		return (SocietyService) context.getBean("societyService");
	}

	/**
	 * 
	 * @return the {@link LegalFormDao}
	 */
	public static LegalFormDao getLegalFormDao() {
		return (LegalFormDao) context.getBean("legalFormDao");
	}
	
	/**
	 * 
	 * @return the {@link CivilityDao}
	 */
	public static CivilityDao getCivilityDao() {
		return (CivilityDao) context.getBean("civilityDao");
	}
	
	/**
	 * 
	 * @return the {@link PaymentModeDao}
	 */
	public static PaymentModeDao getPaymentModeDao() {
		return (PaymentModeDao) context.getBean("paymentModeDao");
	}

	/**
	 * 
	 * @return the {@link CustomerService}
	 */
	public static CustomerService getCustomerService() {
		return (CustomerService) context.getBean("customerService");
	}
	
	/**
	 * 
	 * @return the {@link ProductCategoryDao}
	 */
	public static ProductCategoryDao getProductCategoryDao() {
		return (ProductCategoryDao) context.getBean("productCategoryDao");
	}

}
