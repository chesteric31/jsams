package be.jsams.client.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuBar;
import javax.swing.JSeparator;

import be.jsams.client.i18n.I18nString;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.swing.component.JsamsFrame;
import be.jsams.client.swing.component.JsamsMenu;
import be.jsams.client.swing.component.JsamsMenuItem;

/**
 * Jsams Main frame that contains all the components.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class JsamsMainFrame extends JsamsFrame {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 8570689474653666931L;
	private JMenuBar mainMenuBar;
	private JsamsMenu fileMenu;
	private JsamsMenu editMenu;
	private JsamsMenu salesMenu;
	private JsamsMenu helpMenu;
	private JsamsMenuItem helpMenuItem;
	private JsamsMenuItem pasteMenuItem;
	private JsamsMenuItem aboutMenuItem;
	private JsamsMenuItem copyMenuItem;
	private JsamsMenuItem cutMenuItem;
	private JsamsMenuItem exitMenuItem;
	private JsamsMenuItem societyParametersMenuItem;
	private JsamsMenuItem printerParametersMenuItem;
	private JsamsMenuItem estimateMenuItem;
	private JsamsMenuItem commandMenuItem;
	private JsamsMenuItem deliveryOrderMenuItem;
	private JsamsMenuItem billMenuItem;
	private JsamsMenuItem creditNoteMenuItem;
	private JSeparator firstFileSeparator;
	private JSeparator secondFileSeparator;
	private JSeparator helpSeparator;

	public JsamsMainFrame(final I18nString title) {
		super(title);
		initComponents();
	}
	
	private void initComponents() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		try {
			mainMenuBar = new JMenuBar();
			fileMenu = new JsamsMenu(JsamsI18nResource.MENU_FILE);
			mainMenuBar.add(fileMenu);
			societyParametersMenuItem = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_SOCIETY_PARAMETERS);
			societyParametersMenuItem.addActionListener(societyEditListener(this));
			fileMenu.add(societyParametersMenuItem);
			firstFileSeparator = new JSeparator();
			fileMenu.add(firstFileSeparator);
			printerParametersMenuItem = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_PRINTER_PARAMETERS);
			fileMenu.add(printerParametersMenuItem);
			secondFileSeparator = new JSeparator();
			fileMenu.add(secondFileSeparator);
			exitMenuItem = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_EXIT_APPLICATION);
			fileMenu.add(exitMenuItem);
			exitMenuItem.addActionListener(exitListener());
			editMenu = new JsamsMenu(JsamsI18nResource.MENU_EDIT);
			mainMenuBar.add(editMenu);
			cutMenuItem = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_CUT);
			editMenu.add(cutMenuItem);
			copyMenuItem = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_COPY);
			editMenu.add(copyMenuItem);
			pasteMenuItem = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_PASTE);
			editMenu.add(pasteMenuItem);
			salesMenu = new JsamsMenu(JsamsI18nResource.MENU_SALES);
			estimateMenuItem = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_ESTIMATE);
			commandMenuItem = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_COMMAND);
			deliveryOrderMenuItem = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_DELIVERY_ORDER);
			billMenuItem = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_BILL);
			creditNoteMenuItem = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_CREDIT_NOTE);
			salesMenu.add(estimateMenuItem);
			salesMenu.add(commandMenuItem);
			salesMenu.add(deliveryOrderMenuItem);
			salesMenu.add(billMenuItem);
			salesMenu.add(creditNoteMenuItem);
			mainMenuBar.add(salesMenu);
			helpMenu = new JsamsMenu(JsamsI18nResource.MENU_HELP);
			mainMenuBar.add(helpMenu);
			helpMenuItem = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_HELP);
			helpMenu.add(helpMenuItem);
			helpSeparator = new JSeparator();
			helpMenu.add(helpSeparator);
			aboutMenuItem = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_ABOUT);
			helpMenu.add(aboutMenuItem);
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
//				new SocietyEditFrame(JsamsI18nResource.TITLE_EDIT_SOCIETY_PARAMETERS, mainFrame);
			}
		};
		return listener;
	}

}
