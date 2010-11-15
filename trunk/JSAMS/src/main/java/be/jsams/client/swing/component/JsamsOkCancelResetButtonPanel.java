package be.jsams.client.swing.component;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import be.jsams.client.i18n.JsamsI18nResource;

import com.jgoodies.forms.factories.ButtonBarFactory;

/**
 * Custom {@link JPanel} with OK, Cancel and Reset buttons.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 * @param <P>
 *            a extension of {@link JsamsFrame}, a parent frame
 */
public class JsamsOkCancelResetButtonPanel extends JPanel {

	private static final int DEFAULT_V_GAP = 10;

	private JsamsFrame parent;

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 8542255661439665645L;

	public JsamsOkCancelResetButtonPanel() {
		super();
		initComponents();
	}

	public JsamsOkCancelResetButtonPanel(JsamsFrame parent) {
		this();
		this.parent = parent;
	}

	private void initComponents() {
		JsamsButton buttonOk = buildButtonOk();
		JsamsButton buttonCancel = buildButtonCancel();
		JsamsButton buttonReset = buildButtonReset();
		BorderLayout buttonsLayout = new BorderLayout();
		buttonsLayout.setVgap(DEFAULT_V_GAP);
		this.setLayout(buttonsLayout);
		JSeparator separator = new JSeparator();
		this.add(separator, BorderLayout.NORTH);
		this.add(ButtonBarFactory.buildCenteredBar(new JButton[] { buttonOk,
				buttonCancel, buttonReset }), BorderLayout.CENTER);
		// following line for more space
		this.add(new JLabel(), BorderLayout.SOUTH);
	}

	private JsamsButton buildButtonOk() {
		JsamsButton buttonOk = new JsamsButton(JsamsI18nResource.BUTTON_OK);
		return buttonOk;
	}

	private JsamsButton buildButtonCancel() {
		JsamsButton buttonCancel = new JsamsButton(
				JsamsI18nResource.BUTTON_CANCEL);
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// close the parent frame
				parent.dispose();
			}
		});
		return buttonCancel;
	}

	private JsamsButton buildButtonReset() {
		JsamsButton buttonReset = new JsamsButton(
				JsamsI18nResource.BUTTON_RESET);
		buttonReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Class<?> clazz = parent.getClass();
				Field[] fields = clazz.getFields();
				for (Field field : fields) {
					try {
						Object value = field.get(parent);
						if (value instanceof JTextField) {
							((JTextField) value).setText(null);
						} else if (value instanceof JComboBox) {
							((JComboBox) value).setSelectedIndex(0);
						}
					} catch (IllegalArgumentException e1) {
						e1.printStackTrace();
					} catch (IllegalAccessException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		return buttonReset;
	}

}
