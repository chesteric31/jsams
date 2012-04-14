package be.jsams.client.formatter;

import java.text.ParseException;

import javax.swing.text.NumberFormatter;

/**
 * Custom {@link NumberFormatter} for double object.
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
            number = Double.valueOf(doubleValue);
        }
        return super.valueToString(number);
    }

    /**
     * {@inheritDoc}
     */
    public Object stringToValue(final String string) throws ParseException {
        Number number = null;
        if (string != null) {
            number = (Number) super.stringToValue(string);
            double doubleValue = number.doubleValue();
            number = Double.valueOf(doubleValue);
        }
        return number;
    }
    
}
