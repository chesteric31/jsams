package be.jsams.client.i18n;

/**
 * An i18n String / message.
 * 
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class I18nString {

	private static I18nString emptyString = null;

	public static final Object[] NO_ARGUMENTS = new Object[0];

	private String key;

	private Object[] arguments = NO_ARGUMENTS;

	public I18nString(String key) {
		this(key, NO_ARGUMENTS);
	}

	public I18nString(String key, Object[] arguments) {
		setKey(key);
		setArguments(arguments);
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object[] getArguments() {
		return arguments;
	}

	public void setArguments(Object[] arguments) {
		this.arguments = arguments;
	}

	public String getTranslation() {
		return I18nManager.getInstance().translate(this);
	}

	public static synchronized I18nString getEmptyString() {
		if (emptyString == null) {
			emptyString = new I18nString("");
		}

		return (emptyString);
	}

	public String toString() {
		return getTranslation();
	}
}
