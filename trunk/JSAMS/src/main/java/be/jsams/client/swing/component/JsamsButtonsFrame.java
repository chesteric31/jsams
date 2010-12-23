package be.jsams.client.swing.component;

import java.awt.BorderLayout;

import be.jsams.client.i18n.I18nString;

/**
 * {@link JsamsFrame} with a {@link JsamsButtonsPanel}
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public abstract class JsamsButtonsFrame extends JsamsFrame {

	/**
	 * Serial Version
	 */
	private static final long serialVersionUID = -7641496562484006756L;
	
	private JsamsButtonsPanel buttonsPanel;

	public JsamsButtonsFrame(final I18nString title, final String iconFileName) {
		super(title, iconFileName);
		buttonsPanel = new JsamsButtonsPanel(this, true, true, true);
		add(buttonsPanel, BorderLayout.SOUTH);
	}
	
	public JsamsButtonsFrame(final I18nString title) {
		this(title, null);
	}
	
	protected abstract void performOk();
	
	protected abstract void performCancel();
	
	protected abstract void performReset();

}
