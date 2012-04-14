package be.jsams.client.swing.component;

import javax.swing.ImageIcon;
import javax.swing.JMenu;

import be.jsams.client.i18n.I18nString;
import be.jsams.client.swing.utils.IconUtil;

/**
 * {@link JMenu} with internationalization.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class JsamsMenu extends JMenu {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -8701971312491140614L;

    /**
     * Constructor.
     */
    public JsamsMenu() {
        super();
    }

    /**
     * Constructor.
     * 
     * @param text the translatable {@link I18nString}
     */
    public JsamsMenu(final I18nString text) {
        super(text.getTranslation());
    }

    /**
     * Constructor.
     * 
     * @param text the translatable {@link I18nString}
     * @param iconFileName the icon file name
     */
    public JsamsMenu(final I18nString text, final String iconFileName) {
        this(text);
        ImageIcon defaultIcon = new ImageIcon(IconUtil.buildIcon(iconFileName));
        setIcon(defaultIcon);
    }

}
