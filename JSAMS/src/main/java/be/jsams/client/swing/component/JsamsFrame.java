package be.jsams.client.swing.component;

import java.awt.Font;
import java.util.Enumeration;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.client.i18n.I18nManager;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.swing.utils.IconUtil;

/**
 * Generic {@link JFrame} with internalization, icon and native look and feel
 * capabilities.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public abstract class JsamsFrame extends JFrame {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 5732252811794674139L;

    protected final Log LOGGER = LogFactory.getLog(this.getClass());

	/**
	 * The default resource bundle, integrating with the i18n component.
	 */
	public static final ResourceBundle RESOURCE_BUNDLE = new ResourceBundle() {

		@Override
		public Enumeration<String> getKeys() {
			throw new UnsupportedOperationException();
		}

		@Override
		protected Object handleGetObject(String key) {
			return I18nManager.getInstance().translate(key);
		}
	};


	public JsamsFrame() {
		super();
		setNativeLookAndFeel();
		setUIFont(new FontUIResource(Font.SANS_SERIF, Font.PLAIN, 13));
	}

	public JsamsFrame(final I18nString title) {
		this();
		setTitle(title);
	}

	public JsamsFrame(final I18nString title, final String iconFileName) {
		this(title);
		setIconImage(IconUtil.buildIcon(iconFileName));
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
			LOGGER.error(e);
		}
	}

	/**
	 * Sets the default font for all swing components.
	 * 
	 * ex. setUIFont (new javax.swing.plaf.FontUIResource
	 * ("Serif",Font.ITALIC,12));
	 * 
	 * @param fontUIResource
	 *            the {@link FontUIResource}
	 */
	protected void setUIFont(FontUIResource fontUIResource) {
		Enumeration<Object> keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof FontUIResource) {
				UIManager.put(key, fontUIResource);
			}
		}
	}

}
