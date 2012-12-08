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
import be.jsams.client.swing.utils.DialogUtil;
import be.jsams.client.swing.utils.IconResource;

/**
 * {@link MainFrame} that contains all the components.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class MainFrame extends AbstractJsamsFrame {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 8570689474653666931L;

    private static final Log LOGGER = LogFactory.getLog(MainFrame.class);

    private JMenuBar mainMenuBar;
    
    private FileMenuBuilder fileMenuBuilder;
    private ManagementMenuBuilder managementMenuBuilder;
    private SalesMenuBuilder salesMenuBuilder;
    private WindowsMenuBuilder windowsMenuBuilder;
    private HelpMenuBuilder helpMenuBuilder;

    private JsamsShortcutToolBar shortcutToolBar;

    private MainPanel mainPanel = new MainPanel();

    /**
     * Constructor.
     * 
     * @param title the {@link I18nString} title
     */
    public MainFrame(final I18nString title) {
        super(title, IconResource.JSAMS);
        initComponents();
    }

    /**
     * Initializes all the components.
     */
    private void initComponents() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        try {
            mainMenuBar = new JMenuBar();

            fileMenuBuilder = new FileMenuBuilder();
            mainMenuBar.add(fileMenuBuilder.build());

            managementMenuBuilder = new ManagementMenuBuilder(this);
            mainMenuBar.add(managementMenuBuilder.build());

            salesMenuBuilder = new SalesMenuBuilder(this);
            mainMenuBar.add(salesMenuBuilder.build());

            windowsMenuBuilder = new WindowsMenuBuilder();
            mainMenuBar.add(windowsMenuBuilder.build());

            helpMenuBuilder = new HelpMenuBuilder();
            mainMenuBar.add(helpMenuBuilder.build());

            add(buildSouthPanel(), BorderLayout.SOUTH);

            setJMenuBar(mainMenuBar);

            mainPanel.setOpaque(true);
            add(mainPanel, BorderLayout.CENTER);

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
        return mainPanel.getTabbedPane();
    }

    /**
     * @return the societyParametersMI
     */
    public JsamsMenuItem getSocietyParametersMI() {
        return fileMenuBuilder.getSocietyParametersMI();
    }

    /**
     * Enables/disables all menu items.
     * 
     * @param value the boolean value
     */
    public void enableAllMenuItems(boolean value) {
        fileMenuBuilder.enableMenuItems(value);
        managementMenuBuilder.enableMenuItems(value);
        salesMenuBuilder.enableMenuItems(value);
    }
    
    /**
     * Enables/disables the tabbed pane.
     * 
     * @param value the boolean value
     */
    public void enableTabbedPane(boolean value) {
        mainPanel.enableTabbedPane(value);
    }

    /**
     * @return the shortcutToolBar
     */
    public JsamsShortcutToolBar getShortcutToolBar() {
        return shortcutToolBar;
    }

    /**
     * @param shortcutToolBar the shortcutToolBar to set
     */
    public void setShortcutToolBar(JsamsShortcutToolBar shortcutToolBar) {
        this.shortcutToolBar = shortcutToolBar;
    }
    
}
