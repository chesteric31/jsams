package be.jsams.client.swing.component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import be.jsams.client.i18n.I18nString;

/**
 * An extension of {@link JLabel} with internationalization and icon.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class JsamsLabel extends JLabel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -7371023160047637190L;

	public JsamsLabel() {
		super();
	}

	public JsamsLabel(final I18nString text) {
		super(text.getTranslation());
	}

	public JsamsLabel(final I18nString text, final ImageIcon icon) {
		super(text.getTranslation(), icon, SwingConstants.LEFT);
	}

}
