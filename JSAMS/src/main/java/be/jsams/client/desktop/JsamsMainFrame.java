package be.jsams.client.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JSeparator;
import javax.swing.UIManager;

import be.jsams.client.i18n.I18nString;
import be.jsams.client.i18n.JsamsI18nResource;

/**
 * 
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class JsamsMainFrame extends JFrame {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 8570689474653666931L;
	private JsamsMenuItem helpMenuItem;
	private JsamsMenu helpMenu;
	private JSeparator helpSeparator;
	private JsamsMenuItem pasteMenuItem;
	private JsamsMenuItem aboutMenuItem;
	private JsamsMenuItem copyMenuItem;
	private JsamsMenuItem cutMenuItem;
	private JsamsMenu editMenu;
	private JsamsMenuItem exitMenuItem;
	private JSeparator firstFileSeparator;
	private JSeparator secondFileSeparator;
	private JsamsMenuItem societyParametersMenuItem;
	private JsamsMenuItem printerParametersMenuItem;
	private JsamsMenu fileMenu;
	private JMenuBar mainMenuBar;

	public JsamsMainFrame() {
		super();
		initComponents();
	}
	
	private void initComponents() {
		try {
			setNativeLookAndFeel();
			mainMenuBar = new JMenuBar();
			setJMenuBar(mainMenuBar);
			fileMenu = new JsamsMenu(JsamsI18nResource.MENU_FILE);
			mainMenuBar.add(fileMenu);
			societyParametersMenuItem = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_SOCIETY_PARAMETERS);
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
			helpMenu = new JsamsMenu(JsamsI18nResource.MENU_HELP);
			mainMenuBar.add(helpMenu);
			helpMenuItem = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_HELP);
			helpMenu.add(helpMenuItem);
			helpSeparator = new JSeparator();
			helpMenu.add(helpSeparator);
			aboutMenuItem = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_ABOUT);
			helpMenu.add(aboutMenuItem);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sets the main title of the frame.
	 * 
	 * @param title the {@link I18nString} title
	 */
	protected void setMainTitle(final I18nString title) {
		setTitle(title.getTranslation());
	}
	
	private ActionListener exitListener() {
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JsamsDesktop.instance.stopNow();
			}
		};
		return listener;
	}
	
	private void setNativeLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
