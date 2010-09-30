package be.jsams.client.swing.component;

import javax.swing.JButton;

import be.jsams.client.i18n.I18nString;

/**
 * Button with internationalization.
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
	
}
