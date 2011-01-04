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

	/**
	 * Constructor
	 * 
	 * @param key
	 *            the key resource bundle
	 */
	public I18nString(String key) {
		this(key, NO_ARGUMENTS);
	}

	/**
	 * Constructor
	 * 
	 * @param key
	 *            the key resource bundle
	 * @param arguments
	 *            the table arguments
	 */
	public I18nString(String key, Object[] arguments) {
		setKey(key);
		setArguments(arguments);
	}

	/**
	 * 
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * 
	 * @param key
	 *            the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * 
	 * @return the arguments table
	 */
	public Object[] getArguments() {
		return arguments;
	}

	/**
	 * 
	 * @param arguments
	 *            the table arguments to set
	 */
	public void setArguments(Object[] arguments) {
		this.arguments = arguments;
	}

	/**
	 * 
	 * @return the {@link String} translation
	 */
	public String getTranslation() {
		return I18nManager.getInstance().translate(this);
	}

	/**
	 * 
	 * @return the empty {@link I18nString}
	 */
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
