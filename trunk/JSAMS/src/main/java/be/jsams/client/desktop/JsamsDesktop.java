package be.jsams.client.desktop;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import be.jsams.client.i18n.I18nString;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.server.model.Society;

/**
 * Jsams desktop that creates the {@link JsamsMainFrame} and contains the current {@link Society}.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class JsamsDesktop {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 4428593979427620070L;

    private Society currentSociety = null;

    private I18nString title = new I18nString("title.application_no_arguments");

    private JsamsMainFrame frame = null;

    private static JsamsDesktop instance = null;

    /** Screen dimension */
    public static final Dimension SCREEN = Toolkit.getDefaultToolkit().getScreenSize();

    /** Center dimension onto the screen */
    public static final Dimension CENTER = new Dimension((int) SCREEN.getWidth(), (int) SCREEN.getHeight());

    /**
     * Constructor
     */
    public JsamsDesktop() {
        setInstance(this);
        initComponents();
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
        // TODO add splash-screen...
        frame.setVisible(true);
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
        int confirm = JOptionPane.showConfirmDialog(getMainWindow(), JsamsI18nResource.EXIT_APPLICATION_CONFIRMATION);
        if (confirm == 0) {
            frame.dispose();
            System.exit(0);
        }
    }

    /**
     * 
     * @return the current {@link Society}
     */
    public Society getCurrentSociety() {
        return currentSociety;
    }

    /**
     * @param currentSociety
     *            the current {@link Society} to set
     */
    public void setCurrentSociety(final Society currentSociety) {
        this.currentSociety = currentSociety;
        I18nString newTitle = new I18nString("title.application", new Object[] { this.currentSociety.getName() });
        frame.setTitle(newTitle);
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
