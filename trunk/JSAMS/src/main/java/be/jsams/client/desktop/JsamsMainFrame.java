package be.jsams.client.desktop;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.client.i18n.I18nString;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.dialog.EditSocietyDialog;
import be.jsams.client.model.dialog.OpenSocietyDialog;
import be.jsams.client.model.panel.SearchCustomerPanel;
import be.jsams.client.model.panel.SearchProductCategoryPanel;
import be.jsams.client.model.panel.SearchProductPanel;
import be.jsams.client.swing.component.JsamsCloseableTabbedPane;
import be.jsams.client.swing.component.JsamsFrame;
import be.jsams.client.swing.component.JsamsMenu;
import be.jsams.client.swing.component.JsamsMenuItem;
import be.jsams.client.swing.component.JsamsShortcutToolBar;
import be.jsams.client.swing.component.JsamsStatusBar;
import be.jsams.client.swing.listener.TabbedPaneKeyListener;
import be.jsams.client.swing.utils.IconUtil;

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

    protected static final Log LOGGER = LogFactory.getLog(JsamsMainFrame.class);

    private JMenuBar mainMenuBar;
    private JSeparator[] separators = new JSeparator[] { new JSeparator(), new JSeparator(), new JSeparator(),
            new JSeparator(), new JSeparator(), new JSeparator(), new JSeparator(), new JSeparator(), new JSeparator(),
            new JSeparator() };

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
    private JsamsStatusBar statusBar;

    private JsamsCloseableTabbedPane tabbedPane;

    /**
     * Constructor
     * 
     * @param title
     *            the {@link I18nString} title
     */
    public JsamsMainFrame(final I18nString title) {
        super(title, IconUtil.TITLE_ICON_PREFIX + "categories/applications-office.png");
        setSize(JsamsDesktop.CENTER);
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

            getContentPane().add(tabbedPane);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * Builds the helping menu.
     */
    private void buildHelpMenu() {
        helpMenu = new JsamsMenu(JsamsI18nResource.MENU_HELP);
        helpMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_HELP, IconUtil.MENU_ICON_PREFIX
                + "apps/help-browser.png");
        helpMenu.add(helpMI);
        helpMenu.add(separators[9]);
        aboutMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_ABOUT, IconUtil.MENU_ICON_PREFIX
                + "categories/applications-office.png");
        helpMenu.add(aboutMI);
    }

    /**
     * Builds the windows menu.
     */
    private void buildWindowsMenu() {
        windowsMenu = new JsamsMenu(JsamsI18nResource.MENU_WINDOWS);
        closeWindowMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_CLOSE_WINDOW);
        windowsMenu.add(closeWindowMI);
        closeAllWindowsMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_CLOSE_ALL_WINDOWS);
        windowsMenu.add(closeAllWindowsMI);
        windowsMenu.add(separators[8]);
        nextMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_NEXT, IconUtil.MENU_ICON_PREFIX + "actions/go-next.png");
        windowsMenu.add(nextMI);
        previousMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_PREVIOUS, IconUtil.MENU_ICON_PREFIX
                + "actions/go-previous.png");
        windowsMenu.add(previousMI);
    }

    /**
     * Builds the sales menu.
     */
    private void buildSalesMenu() {
        salesMenu = new JsamsMenu(JsamsI18nResource.MENU_SALES);
        createDocumentsMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_CREATE_DOCUMENTS, IconUtil.MENU_ICON_PREFIX
                + "actions/document-new.png");
        salesMenu.add(createDocumentsMI);
        transferDocumentsMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_TRANSFER_DOCUMENTS,
                IconUtil.MENU_ICON_PREFIX + "actions/media-seek-forward.png");
        salesMenu.add(transferDocumentsMI);
        salesMenu.add(separators[6]);
        listDocumentsMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_LIST_DOCUMENTS);
        salesMenu.add(listDocumentsMI);
        salesMenu.add(separators[7]);
        estimateMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_ESTIMATE);
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
        customersMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_CUSTOMERS, IconUtil.MENU_ICON_PREFIX
                + "apps/system-users.png");
        customersMI.addActionListener(customersListener());
        managementMenu.add(customersMI);
        managementMenu.add(separators[5]);
        productsCategoryMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_PRODUCTS_CATEGORY);
        productsCategoryMI.addActionListener(productsCategoryListener());
        managementMenu.add(productsCategoryMI);
        productsMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_PRODUCTS);
        productsMI.addActionListener(productsListener());
        managementMenu.add(productsMI);
    }

    /**
     * Builds the file menu.
     */
    private void buildFileMenu() {
        fileMenu = new JsamsMenu(JsamsI18nResource.MENU_FILE);
        newMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_NEW, IconUtil.MENU_ICON_PREFIX + "actions/folder-new.png");
        newMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
        newMI.addActionListener(newSocietyListener());
        fileMenu.add(newMI);
        openMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_OPEN, IconUtil.MENU_ICON_PREFIX
                + "actions/document-open.png");
        openMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK));
        openMI.addActionListener(chooseSocietyListener());
        fileMenu.add(openMI);
        closeMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_CLOSE, IconUtil.MENU_ICON_PREFIX
                + "status/folder-visiting.png");
        fileMenu.add(closeMI);
        fileMenu.add(separators[0]);
        societyParametersMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_SOCIETY_PARAMETERS,
                IconUtil.MENU_ICON_PREFIX + "actions/document-properties.png");
        societyParametersMI.addActionListener(editSocietyListener());
        fileMenu.add(societyParametersMI);
        printerParametersMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_PRINTER_PARAMETERS,
                IconUtil.MENU_ICON_PREFIX + "devices/printer.png");
        printerParametersMI.addActionListener(printerParametersListener());
        fileMenu.add(printerParametersMI);
        fileMenu.add(separators[1]);
        exitMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_EXIT_APPLICATION, IconUtil.MENU_ICON_PREFIX
                + "actions/system-log-out.png");
        exitMI.addActionListener(exitListener());
        fileMenu.add(exitMI);
    }

    /**
     * Builds the edit menu.
     */
    private void buildEditMenu() {
        editMenu = new JsamsMenu(JsamsI18nResource.MENU_EDIT);
        cancelMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_CANCEL, IconUtil.MENU_ICON_PREFIX
                + "actions/edit-undo.png");
        cancelMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_MASK));
        editMenu.add(cancelMI);
        editMenu.add(separators[2]);
        cutMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_CUT, IconUtil.MENU_ICON_PREFIX + "actions/edit-cut.png");
        cutMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_MASK));
        editMenu.add(cutMI);
        copyMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_COPY, IconUtil.MENU_ICON_PREFIX
                + "actions/edit-copy.png");
        copyMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_MASK));
        editMenu.add(copyMI);
        pasteMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_PASTE, IconUtil.MENU_ICON_PREFIX
                + "actions/edit-paste.png");
        pasteMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_MASK));
        editMenu.add(pasteMI);
        editMenu.add(separators[3]);
        selectAllMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_SELECT_ALL, IconUtil.MENU_ICON_PREFIX
                + "actions/edit-select-all.png");
        selectAllMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_MASK));
        editMenu.add(selectAllMI);
        editMenu.add(separators[4]);
        refreshMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_REFRESH, IconUtil.MENU_ICON_PREFIX
                + "actions/view-refresh.png");
        refreshMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_MASK));
        editMenu.add(refreshMI);
    }

    /**
     * Builds the 'south' panel that contains the shortcut tool-bar and the status bar.
     * 
     * @return the south panel
     */
    private JPanel buildSouthPanel() {
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.PAGE_AXIS));
        southPanel.setBorder(BorderFactory.createEtchedBorder());
        shortcutToolBar = new JsamsShortcutToolBar();
        statusBar = new JsamsStatusBar();
        southPanel.add(shortcutToolBar);
        southPanel.add(statusBar);
        return southPanel;
    }

    /**
     * Opens the {@link OpenSocietyDialog} at the starting of the application
     */
    public void openChooseSocietyDialog() {
        new OpenSocietyDialog(JsamsI18nResource.TITLE_OPEN_SOCIETY);
    }

    /**
     * 
     * @return a {@link ActionListener} for the exiting of the application
     */
    private ActionListener exitListener() {
        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JsamsDesktop.getInstance().stopNow();
            }
        };
        return listener;
    }

    /**
     * 
     * @return a {@link ActionListener} for the editing of a Society object
     */
    private ActionListener editSocietyListener() {
        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                new EditSocietyDialog(JsamsI18nResource.TITLE_EDIT_SOCIETY, JsamsDesktop.getInstance()
                        .getCurrentSociety());
            }
        };
        return listener;
    }

    /**
     * 
     * @return a {@link ActionListener} for the creation of the Society object
     */
    private ActionListener newSocietyListener() {
        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                new EditSocietyDialog(JsamsI18nResource.TITLE_EDIT_SOCIETY, null);
            }
        };
        return listener;
    }

    /**
     * 
     * @return a {@link ActionListener} for the choosing of the Society object
     */
    private ActionListener chooseSocietyListener() {
        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                new OpenSocietyDialog(JsamsI18nResource.TITLE_OPEN_SOCIETY);
            }
        };
        return listener;
    }

    /**
     * 
     * @return a {@link ActionListener} for the editing of printer settings
     */
    private ActionListener printerParametersListener() {
        ActionListener listener = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                PrinterJob pjob = PrinterJob.getPrinterJob();
                PageFormat pf = pjob.defaultPage();
                pjob.setPrintable(null, pf);

                if (pjob.printDialog()) {
                    // pjob.print();
                }
            }
        };
        return listener;
    }

    /**
     * 
     * @return a {@link ActionListener} for the searching of customers
     */
    private ActionListener customersListener() {
        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                SearchCustomerPanel searchPanel = new SearchCustomerPanel();
                tabbedPane.addTab(JsamsI18nResource.TITLE_SEARCH_CUSTOMER, "apps/system-users.png", searchPanel);
            }
        };
        return listener;
    }

    /**
     * 
     * @return a {@link ActionListener} for the searching of products
     */
    private ActionListener productsListener() {
        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                SearchProductPanel searchPanel = new SearchProductPanel();
                tabbedPane.addTab(JsamsI18nResource.TITLE_SEARCH_PRODUCT, null, searchPanel);
            }
        };
        return listener;
    }

    /**
     * 
     * @return a {@link ActionListener} for the searching of product categories
     */
    private ActionListener productsCategoryListener() {
        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                SearchProductCategoryPanel searchPanel = new SearchProductCategoryPanel();
                tabbedPane.addTab(JsamsI18nResource.TITLE_SEARCH_PRODUCT_CATEGORY, null, searchPanel);
            }
        };
        return listener;
    }

    /**
     * 
     * @return the {@link JsamsStatusBar}
     */
    public JsamsStatusBar getStatusBar() {
        return statusBar;
    }

}
