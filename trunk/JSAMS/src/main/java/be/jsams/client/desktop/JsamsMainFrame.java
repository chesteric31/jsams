package be.jsams.client.desktop;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.panel.SearchAgentPanel;
import be.jsams.client.model.panel.SearchCustomerPanel;
import be.jsams.client.model.panel.SearchEstimatePanel;
import be.jsams.client.model.panel.SearchProductCategoryPanel;
import be.jsams.client.model.panel.SearchProductPanel;
import be.jsams.client.swing.action.ChooseSocietyAction;
import be.jsams.client.swing.action.EditSocietyAction;
import be.jsams.client.swing.action.ExitAction;
import be.jsams.client.swing.action.PrinterParametersAction;
import be.jsams.client.swing.component.JsamsCloseableTabbedPane;
import be.jsams.client.swing.component.JsamsFrame;
import be.jsams.client.swing.component.JsamsMenu;
import be.jsams.client.swing.component.JsamsMenuItem;
import be.jsams.client.swing.component.JsamsShortcutToolBar;
import be.jsams.client.swing.listener.AgentTableMouseListener;
import be.jsams.client.swing.listener.CustomerTableMouseListener;
import be.jsams.client.swing.listener.EstimateTableMouseListener;
import be.jsams.client.swing.listener.ProductCategoryTableMouseListener;
import be.jsams.client.swing.listener.ProductTableMouseListener;
import be.jsams.client.swing.listener.TabbedPaneKeyListener;
import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.common.bean.model.management.ProductCategoryBean;
import be.jsams.common.bean.model.management.SocietyBean;
import be.jsams.common.bean.model.sale.EstimateBean;

/**
 * {@link JsamsMainFrame} that contains all the components.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class JsamsMainFrame extends JsamsFrame {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 8570689474653666931L;

    private static final Log LOGGER = LogFactory.getLog(JsamsMainFrame.class);

    /** Screen dimension */
    public static final Dimension SCREEN = Toolkit.getDefaultToolkit().getScreenSize();

    /** Center dimension onto the screen */
    public static final Dimension CENTER = new Dimension((int) SCREEN.getWidth(), (int) SCREEN.getHeight());

    private JMenuBar mainMenuBar;

    private JsamsMenu fileMenu;
    private JsamsMenuItem newMI;
    private JsamsMenuItem openMI;
    private JsamsMenuItem closeMI;
    private JsamsMenuItem societyParametersMI;
    private JsamsMenuItem printerParametersMI;
    private JsamsMenuItem exitMI;

    private JsamsMenu editMenu;
    private JsamsMenuItem cancelMI;
    private JsamsMenuItem cutMI;
    private JsamsMenuItem copyMI;
    private JsamsMenuItem pasteMI;
    private JsamsMenuItem selectAllMI;
    private JsamsMenuItem refreshMI;

    private JsamsMenu managementMenu;
    private JsamsMenuItem customersMI;
    private JsamsMenuItem agentsMI;
    private JsamsMenuItem productsCategoryMI;
    private JsamsMenuItem productsMI;

    private JsamsMenu salesMenu;
    private JsamsMenuItem createDocumentsMI;
    private JsamsMenuItem transferDocumentsMI;
    private JsamsMenuItem listDocumentsMI;
    private JsamsMenuItem estimateMI;
    private JsamsMenuItem commandMI;
    private JsamsMenuItem deliveryOrderMI;
    private JsamsMenuItem billMI;
    private JsamsMenuItem creditNoteMI;

    private JsamsMenu windowsMenu;
    private JsamsMenuItem closeWindowMI;
    private JsamsMenuItem closeAllWindowsMI;
    private JsamsMenuItem nextMI;
    private JsamsMenuItem previousMI;

    private JsamsMenu helpMenu;
    private JsamsMenuItem helpMI;
    private JsamsMenuItem aboutMI;

    private JsamsShortcutToolBar shortcutToolBar;
    // private JsamsStatusBar statusBar;

    private JsamsCloseableTabbedPane tabbedPane;

    private SocietyBean currentSociety = JsamsDesktop.getInstance().getCurrentSociety();

    /**
     * Constructor
     * 
     * @param title the {@link I18nString} title
     */
    public JsamsMainFrame(final I18nString title) {
        super(title, "categories/applications-office.png");
        setSize(CENTER);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        initComponents();
    }

    /**
     * Initializes all the components
     */
    private void initComponents() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        try {
            mainMenuBar = new JMenuBar();

            buildFileMenu();
            mainMenuBar.add(fileMenu);

            buildEditMenu();
            mainMenuBar.add(editMenu);

            buildManagementMenu();
            mainMenuBar.add(managementMenu);

            buildSalesMenu();
            mainMenuBar.add(salesMenu);

            buildWindowsMenu();
            mainMenuBar.add(windowsMenu);

            buildHelpMenu();
            mainMenuBar.add(helpMenu);

            add(buildSouthPanel(), BorderLayout.SOUTH);

            tabbedPane = new JsamsCloseableTabbedPane();
            tabbedPane.addKeyListener(new TabbedPaneKeyListener());

            setJMenuBar(mainMenuBar);

            // TODO correct the components with splash panel, with the closing
            // and opening menu item...
            add(tabbedPane);
            // add(JsamsSpashPanel.getInstance());
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * Builds the helping menu.
     */
    private void buildHelpMenu() {
        helpMenu = new JsamsMenu(JsamsI18nResource.MENU_HELP);
        helpMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_HELP, "apps/help-browser.png");
        helpMenu.add(helpMI);
        helpMenu.add(new JSeparator());
        aboutMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_ABOUT, "categories/applications-office.png");
        helpMenu.add(aboutMI);
    }

    /**
     * Builds the windows menu.
     */
    private void buildWindowsMenu() {
        windowsMenu = new JsamsMenu(JsamsI18nResource.MENU_WINDOWS);
        closeWindowMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_CLOSE_WINDOW, "emblems/emblem-unreadable.png");
        windowsMenu.add(closeWindowMI);
        closeAllWindowsMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_CLOSE_ALL_WINDOWS);
        windowsMenu.add(closeAllWindowsMI);
        windowsMenu.add(new JSeparator());
        nextMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_NEXT, "actions/go-next.png");
        windowsMenu.add(nextMI);
        previousMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_PREVIOUS, "actions/go-previous.png");
        windowsMenu.add(previousMI);
    }

    /**
     * Builds the sales menu.
     */
    private void buildSalesMenu() {
        salesMenu = new JsamsMenu(JsamsI18nResource.MENU_SALES);
        // per default: false, true if a current society is set
        // salesMenu.setEnabled(false);
        createDocumentsMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_CREATE_DOCUMENTS, "actions/document-new.png");
        salesMenu.add(createDocumentsMI);
        transferDocumentsMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_TRANSFER_DOCUMENTS,
                "actions/media-seek-forward.png");
        salesMenu.add(transferDocumentsMI);
        salesMenu.add(new JSeparator());
        listDocumentsMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_LIST_DOCUMENTS,
                "apps/internet-news-reader.png");
        salesMenu.add(listDocumentsMI);
        salesMenu.add(new JSeparator());
        estimateMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_ESTIMATE);
        estimateMI.setAction(estimatesAction(estimateMI.getText(), estimateMI.getIcon()));
        salesMenu.add(estimateMI);
        commandMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_COMMAND);
        salesMenu.add(commandMI);
        deliveryOrderMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_DELIVERY_ORDER);
        salesMenu.add(deliveryOrderMI);
        billMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_BILL);
        salesMenu.add(billMI);
        creditNoteMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_CREDIT_NOTE);
        salesMenu.add(creditNoteMI);
    }

    /**
     * Builds the management menu.
     */
    private void buildManagementMenu() {
        managementMenu = new JsamsMenu(JsamsI18nResource.MENU_MANAGEMENT);
        // per default: false, true if a current society is set
        // managementMenu.setEnabled(false);
        customersMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_CUSTOMERS, "apps/system-users.png");
        customersMI.setAction(customersAction(customersMI.getText(), customersMI.getIcon()));
        managementMenu.add(customersMI);
        agentsMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_AGENTS, "categories/applications-development.png");
        agentsMI.setAction(agentsAction(agentsMI.getText(), agentsMI.getIcon()));
        managementMenu.add(agentsMI);
        managementMenu.add(new JSeparator());
        productsCategoryMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_PRODUCTS_CATEGORY);
        productsCategoryMI
                .setAction(productsCategoryAction(productsCategoryMI.getText(), productsCategoryMI.getIcon()));
        managementMenu.add(productsCategoryMI);
        productsMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_PRODUCTS, "apps/preferences-desktop-theme.png");
        productsMI.setAction(productsAction(productsMI.getText(), productsMI.getIcon()));
        managementMenu.add(productsMI);
    }

    /**
     * Builds the file menu.
     */
    private void buildFileMenu() {
        fileMenu = new JsamsMenu(JsamsI18nResource.MENU_FILE);
        newMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_NEW, "actions/folder-new.png");
        newMI.setAction(new EditSocietyAction(newMI.getText(), newMI.getIcon(), EditSocietyAction.NEW_ONE_MODE));
        newMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
        fileMenu.add(newMI);
        openMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_OPEN, "actions/document-open.png");
        openMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK));
        openMI.setAction(new ChooseSocietyAction(openMI.getText(), openMI.getIcon()));
        fileMenu.add(openMI);
        closeMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_CLOSE, "status/folder-visiting.png");
        fileMenu.add(closeMI);
        fileMenu.add(new JSeparator());
        societyParametersMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_SOCIETY_PARAMETERS,
                "actions/document-properties.png");
        societyParametersMI.setAction(new EditSocietyAction(societyParametersMI.getText(), societyParametersMI
                .getIcon(), EditSocietyAction.CURRENT_SOCIETY_MODE));
        fileMenu.add(societyParametersMI);
        printerParametersMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_PRINTER_PARAMETERS, "devices/printer.png");
        printerParametersMI.setAction(new PrinterParametersAction(printerParametersMI.getText(), printerParametersMI
                .getIcon()));
        fileMenu.add(printerParametersMI);
        fileMenu.add(new JSeparator());
        exitMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_EXIT_APPLICATION, "actions/system-log-out.png");
        exitMI.setAction(new ExitAction(exitMI.getText(), exitMI.getIcon()));
        fileMenu.add(exitMI);
    }

    /**
     * Builds the edit menu.
     */
    private void buildEditMenu() {
        editMenu = new JsamsMenu(JsamsI18nResource.MENU_EDIT);
        cancelMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_CANCEL, "actions/edit-undo.png");
        cancelMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_MASK));
        editMenu.add(cancelMI);
        editMenu.add(new JSeparator());
        cutMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_CUT, "actions/edit-cut.png");
        cutMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_MASK));
        editMenu.add(cutMI);
        copyMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_COPY, "actions/edit-copy.png");
        copyMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_MASK));
        editMenu.add(copyMI);
        pasteMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_PASTE, "actions/edit-paste.png");
        pasteMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_MASK));
        editMenu.add(pasteMI);
        editMenu.add(new JSeparator());
        selectAllMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_SELECT_ALL, "actions/edit-select-all.png");
        selectAllMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_MASK));
        editMenu.add(selectAllMI);
        editMenu.add(new JSeparator());
        refreshMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_REFRESH, "actions/view-refresh.png");
        refreshMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_MASK));
        editMenu.add(refreshMI);
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
     * 
     * @param text the text to display
     * @param icon the {@link Icon} to display
     * @return an {@link Action} for the searching of customers
     */
    private Action customersAction(String text, Icon icon) {
        AbstractAction action = new AbstractAction() {
            /**
             * Serial
             */
            private static final long serialVersionUID = -8367998985097440307L;

            public void actionPerformed(ActionEvent event) {
                SearchCustomerPanel searchPanel = new SearchCustomerPanel(new CustomerBean(currentSociety),
                        new CustomerTableMouseListener(), JsamsApplicationContext.getCustomerService(), true);
                tabbedPane.addTab(JsamsI18nResource.TITLE_SEARCH_CUSTOMER, "apps/system-users.png", searchPanel);
            }
        };
        action.putValue(Action.NAME, text);
        action.putValue(Action.SMALL_ICON, icon);
        return action;
    }

    /**
     * 
     * @param text the text to display
     * @param icon the {@link Icon} to display
     * @return an {@link Action} for the searching of agents
     */
    private Action agentsAction(String text, Icon icon) {
        AbstractAction action = new AbstractAction() {
            /**
             * Serial Version UID
             */
            private static final long serialVersionUID = 3233472575375812337L;

            public void actionPerformed(ActionEvent event) {
                SearchAgentPanel searchPanel = new SearchAgentPanel(new AgentBean(currentSociety),
                        new AgentTableMouseListener(), JsamsApplicationContext.getAgentService(), true);
                tabbedPane.addTab(JsamsI18nResource.TITLE_SEARCH_AGENT, "categories/applications-development.png",
                        searchPanel);
            }
        };
        action.putValue(Action.NAME, text);
        action.putValue(Action.SMALL_ICON, icon);
        return action;
    }

    /**
     * 
     * @param text the text to display
     * @param icon the {@link Icon} to display
     * @return an {@link Action} for the searching of estimates
     */
    private Action estimatesAction(String text, Icon icon) {
        AbstractAction action = new AbstractAction() {
            /**
             * Serial Version UID
             */
            private static final long serialVersionUID = 3569088526731341971L;

            public void actionPerformed(ActionEvent event) {
                SearchEstimatePanel searchPanel = new SearchEstimatePanel(new EstimateBean(),
                        new EstimateTableMouseListener(), JsamsApplicationContext.getEstimateService(), true);
                tabbedPane.addTab(JsamsI18nResource.TITLE_SEARCH_ESTIMATE, null, searchPanel);
            }
        };
        action.putValue(Action.NAME, text);
        action.putValue(Action.SMALL_ICON, icon);
        return action;
    }

    /**
     * 
     * @param text the text to display
     * @param icon the {@link Icon} to display
     * @return a {@link Action} for the searching of products
     */
    private Action productsAction(String text, Icon icon) {
        AbstractAction action = new AbstractAction() {
            /**
             * Serial
             */
            private static final long serialVersionUID = 3233472575375812337L;

            public void actionPerformed(ActionEvent event) {
                SearchProductPanel searchPanel = new SearchProductPanel(new ProductBean(),
                        new ProductTableMouseListener(), JsamsApplicationContext.getProductService(), true);
                tabbedPane.addTab(JsamsI18nResource.TITLE_SEARCH_PRODUCT, "apps/preferences-desktop-theme.png",
                        searchPanel);
            }
        };
        action.putValue(Action.NAME, text);
        action.putValue(Action.SMALL_ICON, icon);
        return action;
    }

    /**
     * 
     * @param text the text to display
     * @param icon the {@link Icon} to display
     * @return a {@link Action} for the searching of product categories
     */
    private Action productsCategoryAction(String text, Icon icon) {
        AbstractAction action = new AbstractAction() {
            /**
             * Serial Version UID
             */
            private static final long serialVersionUID = -1558165346800183997L;

            public void actionPerformed(ActionEvent event) {
                ProductCategoryBean categoryBean = new ProductCategoryBean();
                categoryBean.setSociety(currentSociety);
                SearchProductCategoryPanel searchPanel = new SearchProductCategoryPanel(categoryBean,
                        new ProductCategoryTableMouseListener(), JsamsApplicationContext.getProductCategoryService(),
                        true);
                tabbedPane.addTab(JsamsI18nResource.TITLE_SEARCH_PRODUCT_CATEGORY, null, searchPanel);
            }
        };
        action.putValue(Action.NAME, text);
        action.putValue(Action.SMALL_ICON, icon);
        return action;
    }

    /**
     * @return the managementMenu
     */
    public JsamsMenu getManagementMenu() {
        return managementMenu;
    }

    /**
     * @return the salesMenu
     */
    public JsamsMenu getSalesMenu() {
        return salesMenu;
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
        return societyParametersMI;
    }

}
