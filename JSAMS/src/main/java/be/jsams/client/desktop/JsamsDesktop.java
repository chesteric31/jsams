package be.jsams.client.desktop;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import be.jsams.client.i18n.I18nString;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.server.model.Society;

/**
 * Jsams desktop.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class JsamsDesktop {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 4428593979427620070L;

	private Society currentSociety = null;

	private I18nString title = new I18nString("title.application_no_arguments");

	private JsamsMainFrame frame = null;

	protected static JsamsDesktop instance = null;

	private ChooseSocietyFrame chooseSocietyFrame = null;

	public static final Dimension screen = Toolkit.getDefaultToolkit()
			.getScreenSize();
	public static final Dimension center = new Dimension((int) screen
			.getWidth(), (int) screen.getHeight());

	public JsamsDesktop() {
		instance = this;
		initComponents();
	}

	/**
	 * Initializes the window.
	 */
	private void initComponents() {
		frame = new JsamsMainFrame(title);
		chooseSocietyFrame = new ChooseSocietyFrame(
				JsamsI18nResource.TITLE_CHOOSE_SOCIETY);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				stopNow();
			}
		});
	}

	public void start() {
		frame.setVisible(true);
		chooseSocietyFrame.setBounds(screen.width / 2
				- chooseSocietyFrame.getWidth() / 2, screen.height / 2
				- chooseSocietyFrame.getHeight() / 2, chooseSocietyFrame
				.getWidth(), chooseSocietyFrame.getHeight());
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

	public Society getCurrentSociety() {
		return currentSociety;
	}

	public void setCurrentSociety(Society currentSociety) {
		this.currentSociety = currentSociety;
		I18nString newTitle = new I18nString("title.application",
				new Object[] { this.currentSociety.getLabel() });
		frame.setTitle(newTitle);
	}

}
