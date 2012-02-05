package be.jsams.common.bean.validator;

/**
 * Validates a String variable with a regular expression.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public final class StringValidator {
    
    /**
     * Constructor.
     */
    private StringValidator() {
    }

    /**
     * Validates a String variable.
     * 
     * @param toValidate the String variable to validate
     * @return true, if valid following regular expression, false otherwise
     */
    public static boolean validate(final String toValidate) {
        return toValidate.matches("[a-zA-ZÀ-ÿ0-9 -_]+");
    }

}
