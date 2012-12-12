package be.jsams.client.desktop;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.SplashScreen;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Enumeration;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.plaf.FontUIResource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.client.i18n.I18nResource;
import be.jsams.client.swing.action.EditSocietyAction;
import be.jsams.client.swing.component.JsamsMenuItem;
import be.jsams.common.bean.model.SocietyBean;

/**
 * JSAMS desktop that creates the {@link MainFrame} and contains the
 * current Society.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class Desktop {

    private static final Log LOGGER = LogFactory.getLog(Desktop.class);
    private final boolean debug = LOGGER.isDebugEnabled();

    private SocietyBean currentSociety = null;

    private MainFrame frame = null;

    private static Desktop instance = null;

    private static final int DEFAULT_FONT_SIZE = 13;

    /**
     * Default constructor
     */
    public Desktop() {
        setInstance(this);
        try {
            initComponents();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getCause(), I18nResource.ERROR_TITLE.getTranslation(),
                    JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    /**
     * Initializes the {@link MainFrame}
     */
    private void initComponents() {
        setNativeLookAndFeel();
        setUIFont(new FontUIResource(Font.SANS_SERIF, Font.PLAIN, DEFAULT_FONT_SIZE));
        frame = new MainFrame(I18nResource.TITLE_APPLICATION_NO_ARGUMENTS);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                stopNow();
            }
        });
    }

    /**
     * Sets visible the {@link MainFrame}.
     */
    public void start() {
        showSplash();
        frame.toFront();
        frame.setVisible(true);
    }

    /**
     * Shows the splash screen.
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
     * Gets the {@link MainFrame}.
     * 
     * @return the frame in which the application appears (in MDI-mode), or the
     *         central container (in SDI-mode).
     */
    public MainFrame getMainWindow() {
        return frame;
    }

    /**
     * Exits the application after having shown a confirmation dialog.
     */
    public void stopNow() {
        int confirm = JOptionPane.showConfirmDialog(getMainWindow(), I18nResource.CONFIRMATION_EXIT);
        if (confirm == 0) {
            frame.dispose();
            System.exit(0);
        }
    }
    
    /**
     * Closes the society file and restarts the process.
     */
    public void restart() {
        frame.getTabbedPane().removeAll();
        frame.enableTabbedPane(false);
        frame.setTitle(I18nResource.TITLE_APPLICATION_NO_ARGUMENTS);
        frame.enableAllMenuItems(false);
        frame.getShortcutToolBar().enableButtons(false);
    }

    /**
     * @return the current {@link SocietyBean}
     */
    public SocietyBean getCurrentSociety() {
        return currentSociety;
    }

    /**
     * @param currentSociety the current {@link SocietyBean} to set
     */
    public void setCurrentSociety(final SocietyBean currentSociety) {
        this.currentSociety = currentSociety;
        if (frame != null) {
            JsamsMenuItem societyParametersMI = frame.getSocietyParametersMI();
            societyParametersMI.setAction(new EditSocietyAction(societyParametersMI.getText(), societyParametersMI
                    .getIcon(), EditSocietyAction.CURRENT_SOCIETY_MODE));
        }
    }

    /**
     * Sets the native look and feel i.e. Nimbus.
     */
    private void setNativeLookAndFeel() {
        try {
            LookAndFeelInfo[] installedLookAndFeels = UIManager.getInstalledLookAndFeels();
            for (LookAndFeelInfo lookAndFeelInfo : installedLookAndFeels) {
                if ("Nimbus".equals(lookAndFeelInfo.getName())) {
                    UIManager.setLookAndFeel(lookAndFeelInfo.getClassName());
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * Sets the default font for all swing components.
     * 
     * ex. setUIFont (new javax.swing.plaf.FontUIResource
     * ("Serif",Font.ITALIC,12));
     * 
     * @param fontUIResource the {@link FontUIResource}
     */
    private void setUIFont(FontUIResource fontUIResource) {
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
     * @param instance the {@link Desktop} instance to set
     */
    public static void setInstance(Desktop instance) {
        Desktop.instance = instance;
    }

    /**
     * @return the {@link Desktop} instance
     */
    public static Desktop getInstance() {
        return instance;
    }

}
