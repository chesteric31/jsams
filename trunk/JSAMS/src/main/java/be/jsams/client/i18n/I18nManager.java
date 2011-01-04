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

	/**
	 * Constructor
	 */
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

	/**
	 * 
	 * @return the {@link Locale}
	 */
	public Locale getLocale() {
		return locale;
	}

	/**
	 * 
	 * @param locale
	 *            the {@link Locale} to set
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	/**
	 * Translates into a {@link String} the {@link I18nString} parameter with
	 * the default {@link Locale}.
	 * 
	 * @param i18nString
	 *            the {@link I18nString} to translate
	 * @return the translated {@link String}
	 */
	public String translate(I18nString i18nString) {
		return translate(i18nString, locale);
	}

	/**
	 * Translates into a {@link String} the {@link I18nString} parameter with
	 * the parameter {@link Locale}.
	 * 
	 * @param i18nString
	 *            the {@link I18nString} to translate
	 * @param locale
	 *            the {@link Locale} to use
	 * @return the translated {@link String}
	 */
	public String translate(I18nString i18nString, Locale locale) {
		return translate(i18nString.getKey(), i18nString.getArguments(), locale);
	}

	/**
	 * Translates into a {@link String} the key resource bundle key parameter
	 * with the default {@link Locale} and the arguments table.
	 * 
	 * @param key
	 *            the key resource bundle to use
	 * @param arguments
	 *            the table arguments
	 * @return the translated {@link String}
	 */
	public String translate(String key, Object[] arguments) {
		return translate(key, arguments, locale);
	}

	/**
	 * Translates into a {@link String} the key resource bundle key parameter
	 * with the default {@link Locale}.
	 * 
	 * @param key
	 *            the key resource bundle to use
	 * @return the translated {@link String}
	 */
	public String translate(String key) {
		return translate(key, locale);
	}

	/**
	 * Translates into a {@link String} the key resource bundle key parameter
	 * and the parameter {@link Locale}.
	 * 
	 * @param key
	 *            the key resource bundle to use
	 * @param locale
	 *            the {@link Locale} to use
	 * @return the translated {@link String}
	 */
	public String translate(String key, Locale locale) {
		return translate(key, null, locale);
	}

	/**
	 * Translates into a {@link String} the key resource bundle with table
	 * arguments and defined {@link Locale}.
	 * 
	 * @param key
	 *            the key resource bundle
	 * @param arguments
	 *            the table arguments
	 * @param locale
	 *            the {@link Locale} to use
	 * @return the translated {@link String}
	 */
	private String translate(String key, Object[] arguments, Locale locale) {
		String translation = null;
		if (key.equals(EMPTY_STRING)) {
			translation = EMPTY_STRING;
		} else {
			MessageSource messageSource = I18nApplicationContext
					.getMessageSource();
			translation = messageSource.getMessage(key, arguments, locale);
		}
		return translation;
	}
}
