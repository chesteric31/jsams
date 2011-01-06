package be.jsams.client.swing.component;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;

/**
 * Customized {@link JFormattedTextField}.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class JsamsFormattedTextField extends JFormattedTextField {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -3209976333589745967L;

    /**
     * Default constructor
     */
    public JsamsFormattedTextField() {
        this(DecimalFormat.getInstance());
    }

    /**
     * Constructor
     * 
     * @param format
     *            the {@link NumberFormat} to use
     */
    public JsamsFormattedTextField(final NumberFormat format) {
        super(format);
    }

}
