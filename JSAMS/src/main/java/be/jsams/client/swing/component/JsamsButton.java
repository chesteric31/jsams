package be.jsams.client.swing.component;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import be.jsams.client.i18n.I18nString;
import be.jsams.client.swing.utils.IconUtil;

/**
 * An extension of {@link JButton} with internationalization and icon.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class JsamsButton extends JButton {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 4932686000771764506L;

	public JsamsButton() {
		super();
	}

	public JsamsButton(final I18nString text) {
		super(text.getTranslation());
	}

	public JsamsButton(final I18nString text, final String iconFileName) {
		this(text);
		setIcon(new ImageIcon(IconUtil.buildIcon(iconFileName)));
	}

	public JsamsButton(final String iconFileName) {
		super(new ImageIcon(IconUtil.buildIcon(iconFileName)));
	}

}
