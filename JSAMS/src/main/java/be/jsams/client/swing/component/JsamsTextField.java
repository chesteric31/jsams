package be.jsams.client.swing.component;

import javax.swing.JTextField;

/**
 * An extension of {@link JTextField}.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class JsamsTextField extends JTextField {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 410786496726208439L;

    /**
     * Default constructor.
     */
    public JsamsTextField() {
        super();
    }

    /**
     * Constructor.
     * 
     * @param columns the columns number
     */
    public JsamsTextField(final int columns) {
        super(columns);
    }

}
