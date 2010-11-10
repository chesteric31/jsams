package be.jsams.client.swing.component;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import be.jsams.client.i18n.JsamsI18nResource;

import com.jgoodies.forms.factories.ButtonBarFactory;

/**
 * Custom {@link JPanel} with OK, Cancel and Reset buttons.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class JsamsOkCancelResetButtonPanel extends JPanel {

	private static final int DEFAULT_V_GAP = 10;

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 8542255661439665645L;

	public JsamsOkCancelResetButtonPanel() {
		super();
		initComponents();
	}

	private void initComponents() {
		JsamsButton buttonOk = new JsamsButton(JsamsI18nResource.BUTTON_OK);
		JsamsButton buttonCancel = new JsamsButton(
				JsamsI18nResource.BUTTON_CANCEL);
		JsamsButton buttonReset = new JsamsButton(
				JsamsI18nResource.BUTTON_RESET);
		BorderLayout buttonsLayout = new BorderLayout();
		buttonsLayout.setVgap(DEFAULT_V_GAP);
		this.setLayout(buttonsLayout);
		JSeparator separator = new JSeparator();
		this.add(separator, BorderLayout.NORTH);
		this.add(ButtonBarFactory.buildCenteredBar(new JButton[] { buttonOk,
				buttonCancel, buttonReset }), BorderLayout.CENTER);
		this.add(new JLabel(), BorderLayout.SOUTH);
	}

}
