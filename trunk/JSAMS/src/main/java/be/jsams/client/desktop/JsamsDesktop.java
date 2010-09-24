package be.jsams.client.desktop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;

import be.jsams.client.i18n.I18nString;

/**
 * Jsams desktop.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class JsamsDesktop {

	private Map<String, JMenu> entryPoints = new HashMap<String, JMenu>();

	// private SwingRegioContainer container = new SwingTabbedRegioContainer();

	/** The menu bar for the main window */
	private JMenuBar menuBar;

	private final JFrame frame = new JFrame();

	// protected StatusBar statusBar;

	protected JToolBar toolBar;

	private static JsamsDesktop instance = null;

	public JsamsDesktop(final I18nString title) {
		instance = this;

		getMainWindow().addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				stopNow();
			}
		});
		setMainWindowTitle(title);
		setNativeLookAndFeel();
		initComponents();
	}

	/**
	 * Initialize the regio containers. By default uses SDI. Layout of the regio
	 * containers can be defined in subclass in case of MDI.
	 */
	private void initComponents() {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		screen = new Dimension((int) screen.getWidth(), (int) screen
				.getHeight());

//		getContainer().setDesktop(this);
//		getMainWindow().setContentPane(getContainer());
		getMainWindow().setSize(screen);
		getMainWindow().setExtendedState(JFrame.MAXIMIZED_BOTH);

//		container.setDesktop(this);

		menuBar = new JMenuBar();
		getMainWindow().setJMenuBar(menuBar);

		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0,
				Color.WHITE));
//		toolBar.setBackground(new Color(235, 235, 235));
		toolBar.setBorderPainted(true);
//		addFirstSeparator();
		getMainWindow().getContentPane().add(toolBar, BorderLayout.NORTH);

//		statusBar = new StatusBar();
//		getMainWindow().getContentPane().add(statusBar, BorderLayout.SOUTH);
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
	public JFrame getMainWindow() {
		return frame;
	}

	/**
	 * Sets the title in the title bar of the main window.
	 * 
	 * @param title
	 *            a translatable title.
	 */
	public void setMainWindowTitle(I18nString title) {
		frame.setTitle(title.getTranslation());
	}

	/**
	 * Exits the application after having shown a confirmation dialog.
	 */
	public void stopNow() {
		int confirm = JOptionPane.showConfirmDialog(getMainWindow(), new I18nString("close.confirmation"));
		if (confirm == 0) {
			frame.dispose();
			System.exit(0);
		}
	}

	/**
	 * Sets native look and feel.
	 */
	private void setNativeLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
