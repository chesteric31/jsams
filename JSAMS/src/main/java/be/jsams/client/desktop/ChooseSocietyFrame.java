package be.jsams.client.desktop;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.swing.component.JsamsButton;
import be.jsams.client.swing.component.JsamsFrame;
import be.jsams.server.model.Society;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Choose society {@link JsamsFrame}.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class ChooseSocietyFrame extends JsamsFrame {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 237617341189579756L;

	public ChooseSocietyFrame(final I18nString title) {
		super(title);
		initComponents();
	}

	private void initComponents() {
		FormLayout layout = new FormLayout(
				"right:pref, 3dlu, pref, 3dlu, pref", "pref, 5dlu");
		DefaultFormBuilder builder = new DefaultFormBuilder(layout);
		builder.setDefaultDialogBorder();
		builder.append(JsamsI18nResource.LABEL_CHOOSE_SOCIETY.getTranslation());
		builder.nextLine();
		builder.appendSeparator();
		List<Society> allSocieties = JsamsApplicationContext
				.getSocietyService().findAll();
		final JComboBox combobox = new JComboBox(allSocieties.toArray());
		combobox.setRenderer(new ListCellRenderer() {

			protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				String theText = null;

				JLabel renderer = (JLabel) defaultRenderer
						.getListCellRendererComponent(list, value, index,
								isSelected, cellHasFocus);
				if (value instanceof Society) {
					theText = ((Society) value).getLabel();
				} else {
					theText = "";
				}
				renderer.setText(theText);
				return renderer;
			}
		});
		builder.append(JsamsI18nResource.LABEL_CHOOSE_SOCIETY_AVAILABLES
				.getTranslation(), combobox);
		JsamsButton buttonNewSociety = new JsamsButton(
				JsamsI18nResource.BUTTON_CHOOSE_SOCIETIES_NEW);
		buttonNewSociety.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();
				new EditSocietyFrame();
			}
		});
		builder.append(buttonNewSociety);
		builder.nextLine();
		builder.appendSeparator();
		builder.nextLine();
		JsamsButton buttonOk = new JsamsButton(JsamsI18nResource.BUTTON_OK);
		buttonOk.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (combobox.getSelectedIndex() > -1) {
					JsamsDesktop.instance.setCurrentSociety(((Society) combobox
							.getSelectedItem()).getLabel());
					dispose();
				}
			}
		});
		JsamsButton cancelButton = new JsamsButton(
				JsamsI18nResource.BUTTON_CANCEL);
		cancelButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		builder.append(buttonOk, cancelButton);
		JPanel panel = builder.getPanel();

		add(panel);
		pack();
		setLocationRelativeTo(null);
	}
}
