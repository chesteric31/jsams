package be.jsams.client.swing.component;

import javax.swing.JMenuItem;

import be.jsams.client.i18n.I18nString;

/**
 * Menu Item with internationalization.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class JsamsMenuItem extends JMenuItem {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 6988054593643268473L;

	public JsamsMenuItem() {
		super();
	}

	public JsamsMenuItem(final I18nString text) {
		super(text.getTranslation());
	}
	
}
