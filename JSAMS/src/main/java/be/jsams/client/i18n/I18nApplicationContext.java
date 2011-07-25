package be.jsams.client.i18n;

import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Application context for I18n.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public final class I18nApplicationContext {

    /**
     * Classpath resource containing the configuration file.
     */
    public static final String CONFIG = "I18nApplicationContext.xml";

    /**
     * The application context.
     */
    private static ClassPathXmlApplicationContext context;

    /**
     * Constructor to avoid to instance this utility class.
     */
    private I18nApplicationContext() {
    }
    
    /**
     * @return the message source
     */
    public static MessageSource getMessageSource() {
        return context;
    }

    /**
     * @param context
     *            the context to set
     */
    public static void setContext(final ClassPathXmlApplicationContext context) {
        I18nApplicationContext.context = context;
    }

}
