package be.jsams.client.i18n;

import java.util.Locale;

import org.springframework.context.MessageSource;

/**
 * Responsible for handling all i18n related tasks.
 * 
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class I18nManager {

	/**
	 * Empty string.
	 */
	private static final String EMPTY_STRING = "";

	private Locale locale = Locale.getDefault();

	/**
	 * The singleton I18nManager instance.
	 */
	private static final I18nManager INSTANCE = new I18nManager();

	public I18nManager() {
	}

	/**
	 * Gets the instance.
	 * 
	 * @return the instance
	 */
	public static I18nManager getInstance() {
		return INSTANCE;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
	public String translate(I18nString i18nString) {
		return translate(i18nString, locale);
	}

	public String translate(I18nString i18nString, Locale locale) {
		return translate(i18nString.getKey(), i18nString.getArguments(), locale);
	}

	public String translate(String key, Object[] arguments) {
		return translate(key, arguments, locale);
	}

	public String translate(String key) {
		return translate(key, locale);
	}

	public String translate(String key, Locale locale) {
		return translate(key, null, locale);
	}

	private String translate(String key, Object[] arguments, Locale locale) {
		String translation = null;
		if (key.equals(EMPTY_STRING)) {
			translation = EMPTY_STRING;
		} else {
			MessageSource messageSource = I18nApplicationContext.getMessageSource();
			translation = messageSource.getMessage(key, arguments, locale);
		}
		return translation;
	}
}
