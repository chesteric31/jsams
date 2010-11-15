package be.jsams.client.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JSeparator;

import be.jsams.client.i18n.I18nString;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.swing.component.JsamsFrame;
import be.jsams.client.swing.component.JsamsMenu;
import be.jsams.client.swing.component.JsamsMenuItem;

/**
 * {@link JsamsMainFrame} that contains all the components.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class JsamsMainFrame extends JsamsFrame {

	private static final String MENU_ICON_PREFIX = "org/freedesktop/tango/16x16/";

	private static final String TITLE_ICON_PREFIX = "org/freedesktop/tango/32x32/";

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 8570689474653666931L;

	private JMenuBar mainMenuBar;
	private JSeparator[] separators = new JSeparator[] { new JSeparator(),
			new JSeparator(), new JSeparator(), new JSeparator(),
			new JSeparator(), new JSeparator(), new JSeparator(),
			new JSeparator(), new JSeparator(), new JSeparator() };

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

	public JsamsMainFrame(final I18nString title) {
		super(title, TITLE_ICON_PREFIX + "categories/applications-office.png");
		setSize(JsamsDesktop.center);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		initComponents();
	}

	private void initComponents() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		try {
			mainMenuBar = new JMenuBar();
			fileMenu = new JsamsMenu(JsamsI18nResource.MENU_FILE);
			newMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_NEW,
					MENU_ICON_PREFIX + "actions/folder-new.png");
			fileMenu.add(newMI);
			openMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_OPEN,
					MENU_ICON_PREFIX + "actions/document-open.png");
			fileMenu.add(openMI);
			closeMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_CLOSE,
					MENU_ICON_PREFIX + "status/folder-visiting.png");
			fileMenu.add(closeMI);
			fileMenu.add(separators[0]);
			societyParametersMI = new JsamsMenuItem(
					JsamsI18nResource.MENU_ITEM_SOCIETY_PARAMETERS,
					MENU_ICON_PREFIX + "actions/document-properties.png");
			societyParametersMI.addActionListener(societyEditListener(this));
			fileMenu.add(societyParametersMI);
			printerParametersMI = new JsamsMenuItem(
					JsamsI18nResource.MENU_ITEM_PRINTER_PARAMETERS,
					MENU_ICON_PREFIX + "devices/printer.png");
			fileMenu.add(printerParametersMI);
			fileMenu.add(separators[1]);
			exitMI = new JsamsMenuItem(
					JsamsI18nResource.MENU_ITEM_EXIT_APPLICATION,
					MENU_ICON_PREFIX + "actions/system-log-out.png");
			exitMI.addActionListener(exitListener());
			fileMenu.add(exitMI);
			mainMenuBar.add(fileMenu);

			editMenu = new JsamsMenu(JsamsI18nResource.MENU_EDIT);
			cancelMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_CANCEL,
					MENU_ICON_PREFIX + "actions/edit-undo.png");
			editMenu.add(cancelMI);
			editMenu.add(separators[2]);
			cutMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_CUT,
					MENU_ICON_PREFIX + "actions/edit-cut.png");
			editMenu.add(cutMI);
			copyMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_COPY,
					MENU_ICON_PREFIX + "actions/edit-copy.png");
			editMenu.add(copyMI);
			pasteMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_PASTE,
					MENU_ICON_PREFIX + "actions/edit-paste.png");
			editMenu.add(pasteMI);
			editMenu.add(separators[3]);
			selectAllMI = new JsamsMenuItem(
					JsamsI18nResource.MENU_ITEM_SELECT_ALL, MENU_ICON_PREFIX
							+ "actions/edit-select-all.png");
			editMenu.add(selectAllMI);
			editMenu.add(separators[4]);
			refreshMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_REFRESH,
					MENU_ICON_PREFIX + "actions/view-refresh.png");
			editMenu.add(refreshMI);
			mainMenuBar.add(editMenu);

			managementMenu = new JsamsMenu(JsamsI18nResource.MENU_MANAGEMENT);
			customersMI = new JsamsMenuItem(
					JsamsI18nResource.MENU_ITEM_CUSTOMERS, MENU_ICON_PREFIX
							+ "apps/system-users.png");
			managementMenu.add(customersMI);
			managementMenu.add(separators[5]);
			productsCategoryMI = new JsamsMenuItem(
					JsamsI18nResource.MENU_ITEM_PRODUCTS_CATEGORY);
			managementMenu.add(productsCategoryMI);
			productsMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_PRODUCTS);
			managementMenu.add(productsMI);
			mainMenuBar.add(managementMenu);

			salesMenu = new JsamsMenu(JsamsI18nResource.MENU_SALES);
			createDocumentsMI = new JsamsMenuItem(
					JsamsI18nResource.MENU_ITEM_CREATE_DOCUMENTS,
					MENU_ICON_PREFIX + "actions/document-new.png");
			salesMenu.add(createDocumentsMI);
			transferDocumentsMI = new JsamsMenuItem(
					JsamsI18nResource.MENU_ITEM_TRANSFER_DOCUMENTS,
					MENU_ICON_PREFIX + "actions/media-seek-forward.png");
			salesMenu.add(transferDocumentsMI);
			salesMenu.add(separators[6]);
			listDocumentsMI = new JsamsMenuItem(
					JsamsI18nResource.MENU_ITEM_LIST_DOCUMENTS);
			salesMenu.add(listDocumentsMI);
			salesMenu.add(separators[7]);
			estimateMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_ESTIMATE);
			salesMenu.add(estimateMI);
			commandMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_COMMAND);
			salesMenu.add(commandMI);
			deliveryOrderMI = new JsamsMenuItem(
					JsamsI18nResource.MENU_ITEM_DELIVERY_ORDER);
			salesMenu.add(deliveryOrderMI);
			billMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_BILL);
			salesMenu.add(billMI);
			creditNoteMI = new JsamsMenuItem(
					JsamsI18nResource.MENU_ITEM_CREDIT_NOTE);
			salesMenu.add(creditNoteMI);
			mainMenuBar.add(salesMenu);

			windowsMenu = new JsamsMenu(JsamsI18nResource.MENU_WINDOWS);
			closeWindowMI = new JsamsMenuItem(
					JsamsI18nResource.MENU_ITEM_CLOSE_WINDOW);
			windowsMenu.add(closeWindowMI);
			closeAllWindowsMI = new JsamsMenuItem(
					JsamsI18nResource.MENU_ITEM_CLOSE_ALL_WINDOWS);
			windowsMenu.add(closeAllWindowsMI);
			windowsMenu.add(separators[8]);
			nextMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_NEXT,
					MENU_ICON_PREFIX + "actions/go-next.png");
			windowsMenu.add(nextMI);
			previousMI = new JsamsMenuItem(
					JsamsI18nResource.MENU_ITEM_PREVIOUS, MENU_ICON_PREFIX
							+ "actions/go-previous.png");
			windowsMenu.add(previousMI);
			mainMenuBar.add(windowsMenu);

			helpMenu = new JsamsMenu(JsamsI18nResource.MENU_HELP);
			helpMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_HELP,
					MENU_ICON_PREFIX + "apps/help-browser.png");
			helpMenu.add(helpMI);
			helpMenu.add(separators[9]);
			aboutMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_ABOUT,
					MENU_ICON_PREFIX + "categories/applications-office.png");
			helpMenu.add(aboutMI);
			mainMenuBar.add(helpMenu);

			setJMenuBar(mainMenuBar);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private ActionListener exitListener() {
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JsamsDesktop.instance.stopNow();
			}
		};
		return listener;
	}

	private ActionListener societyEditListener(final JsamsMainFrame mainFrame) {
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				new EditSocietyFrame();
			}
		};
		return listener;
	}

}
