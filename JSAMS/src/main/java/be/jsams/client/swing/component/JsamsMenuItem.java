package be.jsams.client.swing.component;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

import be.jsams.client.i18n.I18nString;
import be.jsams.client.swing.utils.IconUtil;

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

    /**
     * Constructor.
     */
    public JsamsMenuItem() {
        super();
    }

    /**
     * Constructor.
     * 
     * @param text
     *            the translatable {@link I18nString}
     */
    public JsamsMenuItem(final I18nString text) {
        super(text.getTranslation());
    }

    /**
     * Constructor.
     * 
     * @param text
     *            the translatable {@link I18nString}
     * @param iconFileName
     *            the icon file name
     */
    public JsamsMenuItem(final I18nString text, final String iconFileName) {
        this(text);
        ImageIcon defaultIcon = new ImageIcon(IconUtil.buildIcon(iconFileName));
        setIcon(defaultIcon);
    }

}
