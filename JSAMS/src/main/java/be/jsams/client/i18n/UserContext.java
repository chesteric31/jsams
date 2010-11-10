package be.jsams.client.i18n;

import java.util.Locale;

/**
 * Provides an interface for accessing current user related information.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class UserContext {

	public static final String ENGLISH = "en";

	public static final String FRENCH = "fr";

	public static final String DUTCH = "nl";

	public static Locale getLocale() {
		return Locale.getDefault();
	}

	public static String getLanguage() {
		String currentLanguage = getLocale().getLanguage();
		return currentLanguage;
	}

	public static boolean isFrench() {
		return FRENCH.equalsIgnoreCase(getLanguage());
	}

	public static boolean isDutch() {
		return DUTCH.equalsIgnoreCase(getLanguage());
	}

	public static boolean isEnglish() {
		return !isFrench() && !isDutch();
	}

}
