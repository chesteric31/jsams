package be.jsams.client.i18n;

import java.util.Locale;

/**
 * Provides an interface for accessing current user related information.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public final class UserContext {
    
    /** English language constant */
    public static final String ENGLISH = "en";

    /** French language constant */
    public static final String FRENCH = "fr";

    /** Dutch language constant */
    public static final String DUTCH = "nl";

    /**
     * Constructor to avoid to instance this utility class.
     */
    private UserContext() {
    }
    
    /**
     * 
     * @return the default {@link Locale}
     */
    public static Locale getLocale() {
        return Locale.getDefault();
    }

    /**
     * 
     * @return the default Language
     */
    public static String getLanguage() {
        String currentLanguage = getLocale().getLanguage();
        return currentLanguage;
    }

    /**
     * 
     * @return true if French speaking
     */
    public static boolean isFrench() {
        return FRENCH.equalsIgnoreCase(getLanguage());
    }

    /**
     * 
     * @return true if Dutch speaking
     */
    public static boolean isDutch() {
        return DUTCH.equalsIgnoreCase(getLanguage());
    }

    /**
     * 
     * @return true if English speaking (not French and not Dutch)
     */
    public static boolean isEnglish() {
        return !isFrench() && !isDutch();
    }

}
