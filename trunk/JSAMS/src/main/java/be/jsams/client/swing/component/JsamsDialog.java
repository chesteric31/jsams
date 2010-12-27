package be.jsams.client.swing.component;

import java.awt.Image;

import javax.swing.JDialog;

import be.jsams.client.desktop.JsamsMainFrame;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.swing.utils.IconUtil;

/**
 * 
 * 
 * @author chesteric31
 * @version $Rev$ $Date:: $ $Author$
 */
public class JsamsDialog extends JDialog {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 707067802180334657L;

	public JsamsDialog(final JsamsMainFrame parent, final I18nString title) {
		super(parent, title.getTranslation(), true);
	}

	public JsamsDialog(final JsamsMainFrame parent, final I18nString title,
			final String iconFileName) {
		super(parent, title.getTranslation(), true);
		Image defaultIcon = IconUtil.buildIcon(iconFileName);
		setIconImage(defaultIcon);
	}

}
