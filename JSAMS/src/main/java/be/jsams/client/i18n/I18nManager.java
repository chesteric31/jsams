package be.jsams.client.i18n;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

/**
 * Responsible for handling all i18n related tasks.
 * 
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class I18nManager implements MessageSourceAware {

	private MessageSource messageSource;

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

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	public MessageSource getMessageSource() {
		return messageSource;
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
			translation = messageSource.getMessage(key, arguments, locale);
		}
		return translation;
	}
}
