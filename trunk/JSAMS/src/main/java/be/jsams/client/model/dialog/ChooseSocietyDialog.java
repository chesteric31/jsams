package be.jsams.client.model.dialog;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.client.desktop.JsamsMainFrame;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.renderer.NamedComboBoxRenderer;
import be.jsams.client.swing.component.JsamsButton;
import be.jsams.client.swing.component.JsamsButtonsInterface;
import be.jsams.client.swing.component.JsamsButtonsPanel;
import be.jsams.client.swing.component.JsamsDialog;
import be.jsams.client.swing.component.JsamsFrame;
import be.jsams.client.swing.utils.IconUtil;
import be.jsams.server.model.Society;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Choose society {@link JsamsDialog}.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class ChooseSocietyDialog extends JsamsDialog implements
		JsamsButtonsInterface {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 237617341189579756L;

	private JComboBox comboBox = null;

	private JsamsButtonsPanel buttonsPanel;

	private JsamsMainFrame parent;

	/**
	 * Constructor
	 * 
	 * @param parent the {@link JsamsMainFrame}
	 * @param title the {@link I18nString} title
	 */
	public ChooseSocietyDialog(final JsamsMainFrame parent,
			final I18nString title) {
		super(parent, title);
		this.parent = parent;
		buttonsPanel = new JsamsButtonsPanel(this, true, true, true);
		add(buttonsPanel, BorderLayout.SOUTH);
		initComponents();
	}

	/**
	 * Initializes all the components
	 */
	private void initComponents() {
		FormLayout layout = new FormLayout(
				"right:pref, 3dlu, pref, 3dlu, pref", "pref, 5dlu");
		DefaultFormBuilder builder = new DefaultFormBuilder(layout,
				JsamsFrame.RESOURCE_BUNDLE);
		builder.setDefaultDialogBorder();
		builder.append(JsamsI18nResource.LABEL_CHOOSE_SOCIETY.getTranslation());
		builder.nextLine();
		builder.appendSeparator();
		List<Society> allSocieties = JsamsApplicationContext
				.getSocietyService().findAll();
		comboBox = new JComboBox(allSocieties.toArray());
		comboBox.setRenderer(new NamedComboBoxRenderer());
		builder.append(JsamsI18nResource.LABEL_CHOOSE_SOCIETY_AVAILABLES
				.getTranslation(), comboBox);
		JsamsButton buttonNewSociety = buildButtonNewSociety();
		builder.append(buttonNewSociety);
		builder.nextLine();
		JPanel panel = builder.getPanel();
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(panel, BorderLayout.CENTER);

		add(mainPanel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}

	public void performOk() {
		Object selectedItem = comboBox.getSelectedItem();
		if (comboBox.getSelectedIndex() >= 0 && selectedItem != null) {
			JsamsDesktop jsamsDesktop = JsamsDesktop.getInstance();
			jsamsDesktop.setCurrentSociety(((Society) selectedItem));
		}
		dispose();
	}

	public void performCancel() {
		dispose();
	}

	public void performReset() {
		comboBox.setSelectedIndex(0);
	}

	private JsamsButton buildButtonNewSociety() {
		JsamsButton buttonNewSociety = new JsamsButton(
				JsamsI18nResource.BUTTON_CHOOSE_SOCIETIES_NEW,
				IconUtil.MENU_ICON_PREFIX + "actions/folder-new.png");
		buttonNewSociety.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();
				new EditSocietyDialog(parent,
						JsamsI18nResource.TITLE_EDIT_SOCIETY, null);
			}
		});
		return buttonNewSociety;
	}
}
