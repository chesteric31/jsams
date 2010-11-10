package be.jsams.client.desktop;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.i18n.UserContext;
import be.jsams.client.swing.component.JsamsFrame;
import be.jsams.client.swing.component.JsamsOkCancelResetButtonPanel;
import be.jsams.server.dao.LegalFormDao;
import be.jsams.server.model.LegalForm;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Edit society {@link JsamsFrame}.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EditSocietyFrame extends JsamsFrame {

	private static final int MAX_COLUMN_SPAN = 9;

	private static final int MAX_CHARACTERS = 50;

	private LegalFormDao legalFormDao;

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 4225744372592399187L;

	public EditSocietyFrame() {
		super();
		initComponents();
	}

	public LegalFormDao getLegalFormDao() {
		return legalFormDao;
	}

	public void setLegalFormDao(LegalFormDao legalFormDao) {
		this.legalFormDao = legalFormDao;
	}

	private void initComponents() {
		setTitle(JsamsI18nResource.TITLE_EDIT_SOCIETY);
		FormLayout layoutAddress = new FormLayout(
				"right:p, 3dlu, 50dlu, 3dlu, right:p, 3dlu, 50dlu, 3dlu, right:p, 3dlu, 50dlu",
				"p");
		DefaultFormBuilder builder = new DefaultFormBuilder(layoutAddress);
		builder.setDefaultDialogBorder();
		builder.append(JsamsI18nResource.LABEL_NAME.getTranslation(),
				new JTextField(MAX_CHARACTERS), MAX_COLUMN_SPAN);
		builder.nextLine();
		builder.appendSeparator(JsamsI18nResource.LABEL_ADDRESS
				.getTranslation());
		builder.append(JsamsI18nResource.LABEL_STREET.getTranslation(),
				new JTextField(MAX_CHARACTERS), MAX_COLUMN_SPAN);
		builder.nextLine();
		builder.append(JsamsI18nResource.LABEL_NUMBER.getTranslation(),
				new JTextField(10));
		builder.append(JsamsI18nResource.LABEL_BOX.getTranslation(),
				new JTextField(10), 1);
		builder.append(JsamsI18nResource.LABEL_ZIP_CODE.getTranslation(),
				new JTextField(10), 1);
		builder.append(JsamsI18nResource.LABEL_CITY.getTranslation(),
				new JTextField(MAX_CHARACTERS), MAX_COLUMN_SPAN);
		builder.append(JsamsI18nResource.LABEL_COUNTRY.getTranslation(),
				new JTextField(MAX_CHARACTERS), MAX_COLUMN_SPAN);
		builder.nextLine();

		builder.appendSeparator(JsamsI18nResource.LABEL_CONTACT_INFORMATIONS
				.getTranslation());
		builder.append(JsamsI18nResource.LABEL_PHONE.getTranslation(),
				new JTextField(MAX_CHARACTERS), MAX_COLUMN_SPAN);
		builder.nextLine();
		builder.append(JsamsI18nResource.LABEL_FAX.getTranslation(),
				new JTextField(MAX_CHARACTERS), MAX_COLUMN_SPAN);
		builder.nextLine();
		builder.append(JsamsI18nResource.LABEL_MOBILE.getTranslation(),
				new JTextField(MAX_CHARACTERS), MAX_COLUMN_SPAN);
		builder.nextLine();
		builder.append(JsamsI18nResource.LABEL_EMAIL.getTranslation(),
				new JTextField(MAX_CHARACTERS), MAX_COLUMN_SPAN);
		builder.nextLine();
		builder.append(JsamsI18nResource.LABEL_WEBSITE.getTranslation(),
				new JTextField(MAX_CHARACTERS), MAX_COLUMN_SPAN);
		builder.nextLine();
		builder.appendSeparator(JsamsI18nResource.LABEL_MISC.getTranslation());
		List<LegalForm> allLegalForms = JsamsApplicationContext
				.getLegalFormService().findAll();
		LinkedList<LegalForm> list = new LinkedList<LegalForm>();
		list.addFirst(null);
		list.addAll(allLegalForms);
		final JComboBox comboBox = new JComboBox(list.toArray());
		comboBox.setRenderer(new ListCellRenderer() {

			protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				String theText = null;

				JLabel renderer = (JLabel) defaultRenderer
						.getListCellRendererComponent(list, value, index,
								isSelected, cellHasFocus);
				if (value instanceof LegalForm) {
					if (UserContext.isFrench()) {
						theText= ((LegalForm) value).getLabelFr();
					} else if (UserContext.isDutch()) {
						theText= ((LegalForm) value).getLabelNl();
					} else {
						theText= ((LegalForm) value).getLabel();
					}
				} else {
					theText = "";
				}
				renderer.setText(theText);
				return renderer;
			}
		});

		builder.append(JsamsI18nResource.LABEL_LEGAL_FORM.getTranslation(),
				comboBox, MAX_COLUMN_SPAN);
		builder.nextLine();
		builder.append(JsamsI18nResource.LABEL_CAPITAL.getTranslation(),
				new JTextField(MAX_CHARACTERS), MAX_COLUMN_SPAN);
		builder.nextLine();
		builder.append(JsamsI18nResource.LABEL_ACTIVITY.getTranslation(),
				new JTextField(MAX_CHARACTERS), MAX_COLUMN_SPAN);
		builder.nextLine();
		builder.append(JsamsI18nResource.LABEL_RESPONSIBLE.getTranslation(),
				new JTextField(MAX_CHARACTERS), MAX_COLUMN_SPAN);
		builder.nextLine();
		builder.append(JsamsI18nResource.LABEL_VAT_NUMBER.getTranslation(),
				new JTextField(MAX_CHARACTERS), MAX_COLUMN_SPAN);
		builder.nextLine();

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(builder.getPanel(), BorderLayout.CENTER);
		mainPanel.add(new JsamsOkCancelResetButtonPanel(), BorderLayout.SOUTH);
		add(mainPanel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
