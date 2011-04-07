package be.jsams.client.swing.component;

import java.text.DecimalFormat;
import java.text.Format;

import javax.swing.JFormattedTextField;
import javax.swing.text.DefaultFormatterFactory;

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
     *            the {@link Format} to use
     */
    public JsamsFormattedTextField(final Format format) {
        super(format);
    }

    /**
     * Constructor
     * 
     * @param formatter
     *            the {@link AbstractFormatter} to use
     */
    public JsamsFormattedTextField(final AbstractFormatter formatter) {
        super(formatter);
    }

    /**
     * Constructor
     * 
     * @param factory
     *            the {@link DefaultFormatterFactory}
     */
    public JsamsFormattedTextField(DefaultFormatterFactory factory) {
        super(factory);
    }

}
