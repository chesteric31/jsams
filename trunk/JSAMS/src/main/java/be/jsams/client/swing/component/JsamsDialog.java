package be.jsams.client.swing.component;

import java.awt.Image;

import javax.swing.JDialog;

import be.jsams.client.desktop.JsamsMainFrame;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.swing.utils.IconUtil;

/**
 * Customized {@link JDialog}.
 * 
 * @author chesteric31
 * @version $$Rev$$ $$Date::                  $$ $$Author$$
 */
public class JsamsDialog extends JDialog {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 707067802180334657L;

    /**
     * Constructor.
     * 
     * @param parent the {@link JsamsMainFrame} parent
     * @param title the {@link I18nString} translatable String
     */
    public JsamsDialog(final JsamsMainFrame parent, final I18nString title) {
        super(parent, title.getTranslation(), true);
    }

    /**
     * Constructor.
     * 
     * @param parent the {@link JsamsMainFrame} parent
     * @param title the {@link I18nString} translatable String
     * @param iconFileName the icon path name
     */
    public JsamsDialog(final JsamsMainFrame parent, final I18nString title, final String iconFileName) {
        super(parent, title.getTranslation(), true);
        Image defaultIcon = IconUtil.buildIcon(iconFileName);
        setIconImage(defaultIcon);
    }

    /**
     * Constructor.
     * 
     * @param parent the {@link JsamsDialog} parent
     */
    public JsamsDialog(JsamsDialog parent) {
        super(parent);
    }

    /**
     * Constructor.
     * 
     * @param parent the {@link AbstractJsamsFrame} parent
     */
    public JsamsDialog(AbstractJsamsFrame parent) {
        super(parent);
    }

}
