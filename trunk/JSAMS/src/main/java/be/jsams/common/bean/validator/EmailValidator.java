package be.jsams.common.bean.validator;

/**
 * Validates a email String variable with a regular expression.
 * 
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public final class EmailValidator {

    /**
     * Constructor.
     */
    private EmailValidator() {
    }

    /**
     * Validates a email String variable.
     * 
     * @param email the email String variable to validate
     * @return true, if valid following regular expression, false otherwise
     */
    public static boolean validate(final String email) {
        String pattern = "^" + // start of the line
                "[_A-Za-z0-9-]+" + // must start with string in the bracket [ ],
                                   // must contains one or more (+)
                "(" + // start of group #1
                "\\.[_A-Za-z0-9-]+" + // follow by a dot "." and string in the
                                      // bracket [ ], must contains one or more
                                      // (+)
                ")*" + // end of group #1, this group is optional (*)
                "@" + // must contains a "@" symbol
                "[A-Za-z0-9]+" + // follow by string in the bracket [ ], must
                                 // contains one or more (+)
                "(" + // start of group #2 - first level TLD checking
                "\\.[A-Za-z0-9]+" + // follow by a dot "." and string in the
                                    // bracket [ ], must contains one or more
                                    // (+)
                ")*" + // end of group #2, this group is optional (*)
                "(" + // start of group #3 - second level TLD checking
                "\\.[A-Za-z]{2,}" + // follow by a dot "." and string in the
                                    // bracket [ ], with minimum length of 2
                ")" + // end of group #3
                "$"; // end of the line
        return email.matches(pattern);
    }

}
