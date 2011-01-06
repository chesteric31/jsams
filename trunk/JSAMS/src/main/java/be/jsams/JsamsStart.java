package be.jsams;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.client.i18n.I18nApplicationContext;

/**
 * Startup class for the Jsams Application.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class JsamsStart {

    /**
     * The JSAMS application context string
     */
    private static final String JSAMS_APPLICATION_CONTEXT = "jsams";

    /**
     * Main starting method for the Jsams Application
     * 
     * @param args
     *            for now, no argument are needed
     */
    public static void main(final String[] args) {
        System.setProperty("application.context", JSAMS_APPLICATION_CONTEXT);
        JsamsApplicationContext.setContext(new ClassPathXmlApplicationContext(JsamsApplicationContext.CONFIG));
        I18nApplicationContext.setContext(new ClassPathXmlApplicationContext(I18nApplicationContext.CONFIG));

        JsamsDesktop application = new JsamsDesktop();
        application.start();
    }

}
