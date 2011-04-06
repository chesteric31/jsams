package be.jsams.client.desktop;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.SplashScreen;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Enumeration;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.client.i18n.I18nString;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.dialog.OpenSocietyDialog;
import be.jsams.client.swing.action.EditSocietyAction;
import be.jsams.client.swing.component.JsamsMenuItem;
import be.jsams.common.bean.model.management.SocietyBean;

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

    private final boolean debug = LOGGER.isDebugEnabled();

    private SocietyBean currentSociety = null;

    private JsamsMainFrame frame = null;

    private static JsamsDesktop instance = null;

    private final int defaultFontSize = 13;

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
        setNativeLookAndFeel();
        setUIFont(new FontUIResource(Font.SANS_SERIF, Font.PLAIN, defaultFontSize));

        new OpenSocietyDialog(JsamsI18nResource.TITLE_OPEN_SOCIETY);
        Object[] parameters = new Object[1];
        parameters[0] = this.currentSociety.getName();
        I18nString newTitle = new I18nString("title.application", parameters);
        frame = new JsamsMainFrame(newTitle);
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

        // TODO review this if
        if (frame != null) {
            Object[] parameters = new Object[1];
            parameters[0] = this.currentSociety.getName();
            I18nString newTitle = new I18nString("title.application", parameters);
            frame.setTitle(newTitle);
            JsamsMenuItem societyParametersMI = frame.getSocietyParametersMI();
            societyParametersMI.setAction(new EditSocietyAction(societyParametersMI.getText(), societyParametersMI
                    .getIcon(), 1));
        }
        // frame.getManagementMenu().setEnabled(true);
        // frame.getSalesMenu().setEnabled(true);
    }

    /**
     * Sets the native look and feel.
     */
    protected void setNativeLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * Sets the default font for all swing components.
     * 
     * ex. setUIFont (new javax.swing.plaf.FontUIResource ("Serif",Font.ITALIC,12));
     * 
     * @param fontUIResource
     *            the {@link FontUIResource}
     */
    protected void setUIFont(FontUIResource fontUIResource) {
        Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, fontUIResource);
            }
        }
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
