package be.jsams.client.i18n;

import java.util.Locale;

public class UserContext {

	public static Locale getLocale() {
		return Locale.getDefault();
	}
	public static String getLanguage() {
		String currentLanguage = UserContext.getLocale().getLanguage();
		return currentLanguage;
	}

}
