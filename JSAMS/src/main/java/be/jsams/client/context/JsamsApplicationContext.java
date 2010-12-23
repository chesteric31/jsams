package be.jsams.client.context;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import be.jsams.server.dao.CivilityDao;
import be.jsams.server.dao.LegalFormDao;
import be.jsams.server.dao.PaymentModeDao;
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

	public static void setContext(ClassPathXmlApplicationContext context) {
		JsamsApplicationContext.context = context;
	}

	public static SocietyService getSocietyService() {
		return (SocietyService) context.getBean("societyService");
	}

	public static LegalFormDao getLegalFormDao() {
		return (LegalFormDao) context.getBean("legalFormDao");
	}
	
	public static CivilityDao getCivilityDao() {
		return (CivilityDao) context.getBean("civilityDao");
	}
	
	public static PaymentModeDao getPaymentModeDao() {
		return (PaymentModeDao) context.getBean("paymentModeDao");
	}

	public static CustomerService getCustomerService() {
		return (CustomerService) context.getBean("customerService");
	}

}
