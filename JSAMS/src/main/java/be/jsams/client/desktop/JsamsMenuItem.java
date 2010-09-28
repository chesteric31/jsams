package be.jsams.client.desktop;

import javax.swing.JMenuItem;

import be.jsams.client.i18n.I18nString;

/**
 * 
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
