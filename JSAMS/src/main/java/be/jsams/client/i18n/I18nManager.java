package be.jsams.client.i18n;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

public class I18nManager implements MessageSourceAware {

	private MessageSource messageSource;

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

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public String translate(String key, Object[] parameters) {
		return messageSource.getMessage(key, parameters, locale);
	}

}
