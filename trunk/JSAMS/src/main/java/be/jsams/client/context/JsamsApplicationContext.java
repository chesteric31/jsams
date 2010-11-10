package be.jsams.client.context;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import be.jsams.server.service.LegalFormService;
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

	public static LegalFormService getLegalFormService() {
		return (LegalFormService) context.getBean("legalFormService");
	}

}
