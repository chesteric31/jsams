package be.jsams.client.context;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * This class provides static methods to get a reference to a specific service,
 * anywhere in this project. As a side-job, it provides caching for business
 * objects that are frequently used.
 * 
 * 
 * @author chesteric31
 * @version $Revision:$ $Date:$ $Author:$
 */
public class JsamsApplicationContext {

	public static final String CONFIG = "ApplicationContext.xml";

	private static ClassPathXmlApplicationContext context;

	public static void setContext(ClassPathXmlApplicationContext context) {
		JsamsApplicationContext.context = context;
	}

	// /** example of getBean
	// *
	// * @return
	// */
	// public static DossierService getDossierService() {
	// return (DossierService) context.getBean("dossierDelegate");
	// }

}
