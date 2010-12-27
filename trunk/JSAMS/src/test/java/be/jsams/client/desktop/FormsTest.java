package be.jsams.client.desktop;

import java.awt.BorderLayout;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import be.jsams.client.swing.component.JsamsFrame;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.FormLayout;

public class FormsTest extends JFrame {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 3987201626700818022L;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final FormsTest f = new FormsTest();
		f.setDefaultCloseOperation(EXIT_ON_CLOSE);
		// FormLayout layout = new FormLayout(
		// "right:pref, 3dlu, pref, 3dlu, pref",
		// "pref, 5dlu");
		// DefaultFormBuilder builder = new DefaultFormBuilder(layout);
		// builder.setDefaultDialogBorder();
		// builder.append("Please select a society");
		// builder.nextLine();
		// builder.appendSeparator();
		// builder.append("Availables", new JComboBox(new Object[]{"Society 1",
		// "Society 2", "Society 3"}));
		// builder.append(new JButton("New"));
		// builder.nextLine();
		// builder.appendSeparator();
		// builder.nextLine();
		// JButton buttonOk = new JButton("OK");
		// buttonOk.addActionListener(new ActionListener() {
		//			
		// public void actionPerformed(ActionEvent e) {
		// f.setTitle("blabla");
		// }
		// });
		// builder.append(buttonOk, new JButton("Cancel"));

		FormLayout layoutAddress = new FormLayout(
				"right:p, 3dlu, 50dlu, 3dlu, right:p, 3dlu, 50dlu, 3dlu, right:p, 3dlu, 50dlu",
				"p");
		DefaultFormBuilder builder = new DefaultFormBuilder(layoutAddress, JsamsFrame.RESOURCE_BUNDLE);
		builder.setDefaultDialogBorder();
		builder.append("Name", new JTextField(50), 9);
		builder.nextLine();
		builder.appendSeparator("Address");
		builder.append("Street", new JTextField(50), 9);
		builder.nextLine();
		builder.append("Number", new JTextField(10));
		builder.append("Box", new JTextField(10), 1);
		builder.append("Zip code", new JTextField(10), 1);
		builder.nextLine();

		builder.appendSeparator("Contact informations");
		builder.append("Phone", new JTextField(50), 9);
		builder.nextLine();
		builder.append("Fax", new JTextField(50), 9);
		builder.nextLine();
		builder.append("Mobile", new JTextField(50), 9);
		builder.nextLine();
		builder.append("Email", new JTextField(50), 9);
		builder.nextLine();
		builder.append("Website", new JTextField(50), 9);
		builder.nextLine();
		builder.appendSeparator("Misc");
		builder.append("Legal form", new JComboBox(new Object[] { "Mr", "Ms",
				"Me" }), 9);
		builder.nextLine();
		builder.append("Capital", new JTextField(50), 9);
		builder.nextLine();
		builder.append("Activity", new JTextField(50), 9);
		builder.nextLine();
		builder.append("Responsible", new JTextField(50), 9);
		builder.nextLine();
		builder.append("VAT number", new JTextField(50), 9);
		builder.nextLine();

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(builder.getPanel(), BorderLayout.CENTER);
		JButton buttonOk = new JButton("OK");
		JButton buttonCancel = new JButton("Cancel");
		JButton buttonReset = new JButton("Reset");
		BorderLayout buttonsLayout = new BorderLayout();
		buttonsLayout.setVgap(10);
		JPanel buttonsBar = new JPanel(buttonsLayout);
		JSeparator separator = new JSeparator();
		buttonsBar.add(separator, BorderLayout.NORTH);
		buttonsBar.add(ButtonBarFactory.buildCenteredBar(new JButton[] {
				buttonOk, buttonCancel, buttonReset }), BorderLayout.CENTER);
		buttonsBar.add(new JLabel(), BorderLayout.SOUTH);
		mainPanel.add(buttonsBar, BorderLayout.SOUTH);
		f.add(mainPanel);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		System.out.println(Locale.getDefault().getLanguage());
	}

}
