package be.jsams.client.swing.component;

import javax.swing.JFrame;
import javax.swing.UIManager;

import be.jsams.client.i18n.I18nString;

/**
 * Generic {@link JFrame} for Jsams application.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class JsamsFrame extends JFrame {

	/**
	 * Serial Version
	 */
	private static final long serialVersionUID = 5732252811794674139L;
	
	public JsamsFrame() {
		super();
		setNativeLookAndFeel();
	}
	
	public JsamsFrame(final I18nString title) {
		this();
		setTitle(title);
	}

	/**
	 * Sets the main title of the frame.
	 * 
	 * @param title
	 *            the {@link I18nString} title
	 */
	public void setTitle(final I18nString title) {
		setTitle(title.getTranslation());
	}

	/**
	 * Sets the native look and feel.
	 */
	protected void setNativeLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
