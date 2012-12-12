package be.jsams.client.desktop;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import be.jsams.client.swing.component.JsamsCloseableTabbedPane;
import be.jsams.client.swing.listener.TabbedPaneKeyListener;

/**
 * {@link JPanel} that contains the {@link JLayeredPane} with
 * {@link SplashPanel} and {@link JsamsCloseableTabbedPane}.
 * 
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class MainPanel extends JPanel {

    /**
     * Serial
     */
    private static final long serialVersionUID = 4422624883682080583L;

    private JLayeredPane layeredPane;
    private SplashPanel splashPanel;
    private JsamsCloseableTabbedPane tabbedPane;

    /**
     * Default constructor.
     */
    public MainPanel() {
        initComponents();
    }

    /**
     * Initializes all the components.
     */
    private void initComponents() {
        setLayout(new BorderLayout());
        layeredPane = new JLayeredPane();
        layeredPane.setLayout(new BorderLayout());
        splashPanel = SplashPanel.getInstance();
        tabbedPane = new JsamsCloseableTabbedPane();
        tabbedPane.addKeyListener(new TabbedPaneKeyListener());
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        Image image = splashPanel.getImage();
        int height = image.getHeight(null);
        int width = image.getWidth(null);
        int x = (screen.width / 2) - (width / 2); // Center horizontally.
        int y = (screen.height / 2) - (height / 2); // Center vertically.

        splashPanel.setBounds(x, y, width, height);
        layeredPane.add(splashPanel, JLayeredPane.FRAME_CONTENT_LAYER.intValue());
        layeredPane.add(tabbedPane, JLayeredPane.DEFAULT_LAYER.intValue());
        add(layeredPane);
    }

    /**
     * Enables/disables the tabbed pane.
     * 
     * @param value the boolean value
     */
    public void enableTabbedPane(boolean value) {
        if (value) {
            layeredPane.moveToFront(tabbedPane);
        } else {
            layeredPane.moveToFront(splashPanel);
        }
    }

    /**
     * @return the {@link JsamsCloseableTabbedPane}
     */
    public JsamsCloseableTabbedPane getTabbedPane() {
        return tabbedPane;
    }

}
