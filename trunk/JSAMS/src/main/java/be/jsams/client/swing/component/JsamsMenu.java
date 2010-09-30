package be.jsams.client.swing.component;

import javax.swing.JMenu;

import be.jsams.client.i18n.I18nString;

/**
 * Menu with internationalization.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class JsamsMenu extends JMenu {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -8701971312491140614L;
	
	public JsamsMenu() {
		super();
	}
	
	public JsamsMenu(final I18nString text) {
		super(text.getTranslation());
	}

}
