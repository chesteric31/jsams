package be.jsams.client.swing.component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import be.jsams.client.i18n.I18nString;

/**
 * An extension of {@link JLabel} with internationalization and icon.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class JsamsLabel extends JLabel {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -7371023160047637190L;

    /**
     * Constructor.
     */
    public JsamsLabel() {
        super();
    }

    /**
     * Constructor.
     * 
     * @param text the text
     */
    public JsamsLabel(final String text) {
        super(text);
    }

    /**
     * Constructor.
     * 
     * @param text the translatable {@link I18nString}
     */
    public JsamsLabel(final I18nString text) {
        super(text.getTranslation());
    }

    /**
     * Constructor.
     * 
     * @param text the translatable {@link I18nString}
     * @param icon the icon
     */
    public JsamsLabel(final I18nString text, final ImageIcon icon) {
        super(text.getTranslation(), icon, SwingConstants.LEFT);
    }

    /**
     * Constructor.
     * 
     * @param icon the icon
     */
    public JsamsLabel(final ImageIcon icon) {
        super(icon, SwingConstants.LEFT);
    }

    /**
     * @param text set the text label
     */
    public void setText(I18nString text) {
        super.setText(text.getTranslation());
    }
    
}
