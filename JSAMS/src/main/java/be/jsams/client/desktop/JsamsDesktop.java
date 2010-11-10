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

	private String currentSociety = "";

	private I18nString title = new I18nString("title.application",
			new Object[] { currentSociety });

	private final JsamsMainFrame frame = new JsamsMainFrame(title);

	private ChooseSocietyFrame chooseSocietyFrame = new ChooseSocietyFrame(
			JsamsI18nResource.TITLE_CHOOSE_SOCIETY);

	protected static JsamsDesktop instance = null;

	public JsamsDesktop() {
		instance = this;

		getMainWindow().addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				stopNow();
			}
		});
		initComponents();
	}

	/**
	 * Initializes the window.
	 */
	private void initComponents() {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension center = new Dimension((int) screen.getWidth(), (int) screen
				.getHeight());

		getMainWindow().setSize(center);
		getMainWindow().setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	public void start() {
		frame.setVisible(true);
		chooseSocietyFrame.setVisible(true);
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
	 * Exits the application after having shown a confirmation dialog.
	 */
	public void stopNow() {
		int confirm = JOptionPane.showConfirmDialog(getMainWindow(),
				JsamsI18nResource.EXIT_APPLICATION_CONFIRMATION);
		if (confirm == 0) {
			frame.dispose();
			System.exit(0);
		}
	}

	public String getCurrentSociety() {
		return currentSociety;
	}

	public void setCurrentSociety(String currentSociety) {
		this.currentSociety = currentSociety;
		title.setArguments(new Object[] { this.currentSociety });
		this.frame.setTitle(title);
	}

}
