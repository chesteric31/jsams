package be.jsams;

import javax.swing.SwingUtilities;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import be.jsams.client.context.ApplicationContext;
import be.jsams.client.desktop.Desktop;
import be.jsams.client.i18n.I18nApplicationContext;

/**
 * Startup class for the JSAMS Application.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public final class JsamsStart {

    /**
     * The JSAMS application context string
     */
    private static final String JSAMS_APPLICATION_CONTEXT = "jsams";

    /**
     * Constructor to avoid to instance this utility class.
     */
    private JsamsStart() {
    }

    /**
     * Main starting method for the JSAMS Application
     * 
     * @param args for now, no argument are needed
     */
    public static void main(final String[] args) {
        System.setProperty("application.context", JSAMS_APPLICATION_CONTEXT);
        ApplicationContext.setContext(new ClassPathXmlApplicationContext(ApplicationContext.CONFIG));
        I18nApplicationContext.setContext(new ClassPathXmlApplicationContext(I18nApplicationContext.CONFIG));
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                final Desktop application = new Desktop();
                application.start();
            }
        });
    }

}
