package be.jsams.client.i18n;

import java.util.Locale;

/**
 * Provides an interface for accessing current user related information.
 * 
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class UserContext {

	public static Locale getLocale() {
		return Locale.getDefault();
	}

	public static String getLanguage() {
		String currentLanguage = UserContext.getLocale().getLanguage();
		return currentLanguage;
	}

}
