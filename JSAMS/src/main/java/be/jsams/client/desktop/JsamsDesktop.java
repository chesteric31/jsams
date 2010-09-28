package be.jsams.client.desktop;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import be.jsams.client.i18n.I18nString;
import be.jsams.client.i18n.JsamsI18nResource;

/**
 * Jsams desktop.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class JsamsDesktop {

	private final JsamsMainFrame frame = new JsamsMainFrame();

	protected static JsamsDesktop instance = null;

	public JsamsDesktop(final I18nString title) {
		instance = this;

		getMainWindow().addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				stopNow();
			}
		});
		setMainWindowTitle(title);
		initComponents();
	}

	/**
	 * Initializes the window.
	 */
	private void initComponents() {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		screen = new Dimension((int) screen.getWidth(), (int) screen
				.getHeight());

		getMainWindow().setSize(screen);
		getMainWindow().setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	public void start() {
		frame.setVisible(true);
	}

	/**
	 * Gets the main window.
	 * 
	 * @return the frame in which the application appears (in MDI-mode), or the
	 *         central container (in SDI-mode).
	 */
	public JsamsMainFrame getMainWindow() {
		return frame;
	}

	/**
	 * Sets the title in the title bar of the main window.
	 * 
	 * @param title
	 *            a translatable title.
	 */
	public void setMainWindowTitle(final I18nString title) {
		frame.setMainTitle(title);
	}

	/**
	 * Exits the application after having shown a confirmation dialog.
	 */
	public void stopNow() {
		int confirm = JOptionPane.showConfirmDialog(getMainWindow(), JsamsI18nResource.EXIT_APPLICATION_CONFIRMATION);
		if (confirm == 0) {
			frame.dispose();
			System.exit(0);
		}
	}

}
