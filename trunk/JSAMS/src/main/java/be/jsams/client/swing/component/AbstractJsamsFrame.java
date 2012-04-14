package be.jsams.client.swing.component;

import java.util.Enumeration;
import java.util.ResourceBundle;

import javax.swing.JFrame;

import be.jsams.client.i18n.I18nManager;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.swing.utils.IconUtil;

/**
 * Generic {@link JFrame} with internalization, icon and native look and feel
 * capabilities.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public abstract class AbstractJsamsFrame extends JFrame {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 5732252811794674139L;

    /**
     * The default resource bundle, integrating with the i18n component.
     */
    public static final ResourceBundle RESOURCE_BUNDLE = new ResourceBundle() {

        @Override
        public Enumeration<String> getKeys() {
            throw new UnsupportedOperationException();
        }

        @Override
        protected Object handleGetObject(final String key) {
            return I18nManager.getInstance().translate(key);
        }
    };

    /**
     * Constructor.
     */
    public AbstractJsamsFrame() {
        super();
    }

    /**
     * Constructor.
     * 
     * @param title the {@link I18nString}
     */
    public AbstractJsamsFrame(final I18nString title) {
        this();
        setTitle(title);
    }

    /**
     * Constructor.
     * 
     * @param title the {@link I18nString}
     * @param iconFileName the icon path name
     */
    public AbstractJsamsFrame(final I18nString title, final String iconFileName) {
        this(title);
        setIconImage(IconUtil.buildIcon(IconUtil.TITLE_ICON_PREFIX + iconFileName));
    }

    /**
     * Sets the main title of the frame.
     * 
     * @param title the {@link I18nString} title
     */
    public void setTitle(final I18nString title) {
        setTitle(title.getTranslation());
    }

}
