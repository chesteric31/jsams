package be.jsams.client.desktop;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.client.i18n.I18nString;
import be.jsams.client.swing.component.AbstractJsamsFrame;
import be.jsams.client.swing.component.JsamsCloseableTabbedPane;
import be.jsams.client.swing.component.JsamsMenu;
import be.jsams.client.swing.component.JsamsMenuItem;
import be.jsams.client.swing.component.JsamsShortcutToolBar;
import be.jsams.client.swing.listener.TabbedPaneKeyListener;
import be.jsams.client.swing.utils.DialogUtil;

/**
 * {@link JsamsMainFrame} that contains all the components.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class JsamsMainFrame extends AbstractJsamsFrame {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 8570689474653666931L;

    private static final Log LOGGER = LogFactory.getLog(JsamsMainFrame.class);

    private JMenuBar mainMenuBar;
    
    private JsamsFileMenuBuilder fileMenuBuilder;
    
    private JsamsEditMenuBuilder editMenuBuilder;
    
    private JsamsManagementMenuBuilder managementMenuBuilder;
    
    private JsamsSalesMenuBuilder salesMenuBuilder;
    
    private JsamsWindowsMenuBuilder windowsMenuBuilder;
    
    private JsamsHelpMenuBuilder helpMenuBuilder;

    private JsamsShortcutToolBar shortcutToolBar;
    // private JsamsStatusBar statusBar;

    private JsamsCloseableTabbedPane tabbedPane;

    /**
     * Constructor
     * 
     * @param title the {@link I18nString} title
     */
    public JsamsMainFrame(final I18nString title) {
        super(title, "categories/applications-office.png");
        initComponents();
    }

    /**
     * Initializes all the components
     */
    private void initComponents() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        try {
            mainMenuBar = new JMenuBar();

            fileMenuBuilder = new JsamsFileMenuBuilder();
            mainMenuBar.add(fileMenuBuilder.build());

            editMenuBuilder = new JsamsEditMenuBuilder();
            mainMenuBar.add(editMenuBuilder.build());

            managementMenuBuilder = new JsamsManagementMenuBuilder(this);
            mainMenuBar.add(managementMenuBuilder.build());

            salesMenuBuilder = new JsamsSalesMenuBuilder(this);
            mainMenuBar.add(salesMenuBuilder.build());

            windowsMenuBuilder = new JsamsWindowsMenuBuilder();
            mainMenuBar.add(windowsMenuBuilder.build());

            helpMenuBuilder = new JsamsHelpMenuBuilder();
            mainMenuBar.add(helpMenuBuilder.build());

            add(buildSouthPanel(), BorderLayout.SOUTH);

            tabbedPane = new JsamsCloseableTabbedPane();
            tabbedPane.addKeyListener(new TabbedPaneKeyListener());

            setJMenuBar(mainMenuBar);

            // TODO correct the components with splash panel, with the closing
            // and opening menu item...
            add(tabbedPane);
            // add(JsamsSpashPanel.getInstance());
            pack();
            DialogUtil.centerComponentOnScreen(this);
            setExtendedState(JFrame.MAXIMIZED_BOTH);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * Builds the 'south' panel that contains the shortcut tool-bar and the
     * status bar.
     * 
     * @return the south panel
     */
    private JPanel buildSouthPanel() {
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.PAGE_AXIS));
        southPanel.setBorder(BorderFactory.createEtchedBorder());
        shortcutToolBar = new JsamsShortcutToolBar();
        southPanel.add(shortcutToolBar);
        return southPanel;
    }

    /**
     * @return the managementMenu
     */
    public JsamsMenu getManagementMenu() {
        return managementMenuBuilder.getMenu();
    }

    /**
     * @return the salesMenu
     */
    public JsamsMenu getSalesMenu() {
        return salesMenuBuilder.getMenu();
    }

    /**
     * @return the tabbedPane
     */
    public JsamsCloseableTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    /**
     * @return the societyParametersMI
     */
    public JsamsMenuItem getSocietyParametersMI() {
        return fileMenuBuilder.getSocietyParametersMI();
    }

}
