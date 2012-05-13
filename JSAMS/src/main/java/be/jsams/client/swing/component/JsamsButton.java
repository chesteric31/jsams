package be.jsams.client.swing.component;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import be.jsams.client.i18n.I18nString;
import be.jsams.client.swing.utils.IconUtil;

/**
 * An extension of {@link JButton} with internationalization and icon.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class JsamsButton extends JButton {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 4932686000771764506L;

    /**
     * Constructor.
     * 
     * @param text the {@link I18nString} translatable label
     */
    public JsamsButton(final I18nString text) {
        super(text.getTranslation());
    }

    /**
     * Constructor.
     * 
     * @param text the {@link I18nString} translatable label
     * @param toolTip the {@link I18nString} translatable tool tip
     */
    public JsamsButton(final I18nString text, final I18nString toolTip) {
        this(text);
        setToolTipText(toolTip);
    }
    

    /**
     * Constructor.
     * 
     * @param text the {@link I18nString} translatable label
     * @param iconFileName the icon path file name
     */
    public JsamsButton(final I18nString text, final String iconFileName) {
        this(text);
        setIcon(new ImageIcon(IconUtil.buildIcon(iconFileName)));
    }

    /**
     * Constructor.
     * 
     * @param iconFileName the icon path file name
     */
    public JsamsButton(final String iconFileName) {
        super(new ImageIcon(IconUtil.buildIcon(iconFileName)));
    }

    /**
     * Sets the tool tip for this button.
     * 
     * @param toolTip the {@link I18nString} tool tip
     */
    public void setToolTipText(I18nString toolTip) {
        setToolTipText(toolTip.getTranslation());
    }

}
