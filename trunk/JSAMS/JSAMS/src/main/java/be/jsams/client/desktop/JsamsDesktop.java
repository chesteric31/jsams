package be.jsams.client.desktop;

import java.awt.Graphics2D;
import java.awt.SplashScreen;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.client.i18n.I18nString;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.common.bean.model.SocietyBean;

/**
 * JSAMS desktop that creates the {@link JsamsMainFrame} and contains the current Society.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class JsamsDesktop {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 4428593979427620070L;

    private static final Log LOGGER = LogFactory.getLog(JsamsDesktop.class);

    private boolean debug = LOGGER.isDebugEnabled();

    private SocietyBean currentSociety = null;

    private I18nString title = new I18nString("title.application_no_arguments");

    private JsamsMainFrame frame = null;

    private static JsamsDesktop instance = null;

    /**
     * Default constructor
     */
    public JsamsDesktop() {
        setInstance(this);
        try {
            initComponents();
        } catch (Throwable e) {
            JOptionPane.showMessageDialog(null, e.getCause(), JsamsI18nResource.ERROR_TITLE.getTranslation(),
                    JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    /**
     * Initializes the {@link JsamsMainFrame}
     */
    private void initComponents() {
        frame = new JsamsMainFrame(title);
        frame.openChooseSocietyDialog();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                stopNow();
            }
        });
    }

    /**
     * Sets visible the {@link JsamsMainFrame}
     */
    public void start() {
        showSplash();
        frame.toFront();
        frame.setVisible(true);
    }

    /**
     * Shows the splash screen
     */
    private void showSplash() {
        final SplashScreen splash = SplashScreen.getSplashScreen();
        if (splash == null) {
            if (debug) {
                LOGGER.debug("An error occurred with the splash screen");
            }
        } else {
            Graphics2D graphics = splash.createGraphics();
            if (graphics == null) {
                if (debug) {
                    LOGGER.debug("Graphics is null");
                }
            }
            splash.close();
        }
    }

    /**
     * Gets the {@link JsamsMainFrame}
     * 
     * @return the frame in which the application appears (in MDI-mode), or the central container (in SDI-mode).
     */
    public JsamsMainFrame getMainWindow() {
        return frame;
    }

    /**
     * Exits the application after having shown a confirmation dialog
     */
    public void stopNow() {
        int confirm = JOptionPane.showConfirmDialog(getMainWindow(), JsamsI18nResource.EXIT_CONFIRMATION);
        if (confirm == 0) {
            frame.dispose();
            System.exit(0);
        }
    }

    /**
     * 
     * @return the current {@link SocietyBean}
     */
    public SocietyBean getCurrentSociety() {
        return currentSociety;
    }

    /**
     * @param currentSociety
     *            the current {@link SocietyBean} to set
     */
    public void setCurrentSociety(final SocietyBean currentSociety) {
        this.currentSociety = currentSociety;
        I18nString newTitle = new I18nString("title.application", new Object[] { this.currentSociety.getName() });
        frame.setTitle(newTitle);
        frame.getManagementMenu().setEnabled(true);
        frame.getSalesMenu().setEnabled(true);
    }

    /**
     * 
     * @param instance
     *            the {@link JsamsDesktop} instance to set
     */
    public static void setInstance(JsamsDesktop instance) {
        JsamsDesktop.instance = instance;
    }

    /**
     * 
     * @return the {@link JsamsDesktop} instance
     */
    public static JsamsDesktop getInstance() {
        return instance;
    }

}
