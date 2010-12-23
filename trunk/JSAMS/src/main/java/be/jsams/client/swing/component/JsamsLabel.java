package be.jsams.client.swing.component;

import javax.swing.JLabel;

import be.jsams.client.i18n.I18nString;

/**
 * Label with internationalization.
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

}