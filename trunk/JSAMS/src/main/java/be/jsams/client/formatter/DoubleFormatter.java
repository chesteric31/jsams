package be.jsams.client.formatter;

import java.text.ParseException;

import javax.swing.text.NumberFormatter;

/**
 * 
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class DoubleFormatter extends NumberFormatter {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -6531391127643012678L;

    /**
     * {@inheritDoc}
     */
    public String valueToString(final Object object) throws ParseException {
        Number number = (Number) object;
        if (number != null) {
            double doubleValue = number.doubleValue();
            number = new Double(doubleValue);
        }
        return super.valueToString(number);
    }

    /**
     * {@inheritDoc}
     */
    public Object stringToValue(final String string) throws ParseException {
        Number number = (Number) super.stringToValue(string);
        if (number != null) {
            double doubleValue = number.doubleValue();
            number = new Double(doubleValue);
        }
        return number;
    }
    
}
