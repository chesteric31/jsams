package be.jsams.client.swing.component;

import javax.swing.ImageIcon;
import javax.swing.JMenu;

import be.jsams.client.i18n.I18nString;
import be.jsams.client.swing.utils.IconUtil;

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

	public JsamsMenu(final I18nString text, final String iconFileName) {
		this(text);
		ImageIcon defaultIcon = new ImageIcon(IconUtil.buildIcon(iconFileName));
		setIcon(defaultIcon);
	}


}
