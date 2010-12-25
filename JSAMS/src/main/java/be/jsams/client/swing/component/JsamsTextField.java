package be.jsams.client.swing.component;

import javax.swing.JTextField;

import be.jsams.client.i18n.I18nString;

/**
 * An extension of {@link JTextField} with translation and label to use.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class JsamsTextField extends JTextField {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 410786496726208439L;

	private String label;
	
	public JsamsTextField(final int columns, final I18nString label) {
		super(columns);
		this.label = label.getTranslation();
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(I18nString label) {
		this.label = label.getTranslation();
	}
	
}
