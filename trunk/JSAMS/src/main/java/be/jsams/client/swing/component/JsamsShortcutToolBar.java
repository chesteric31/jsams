package be.jsams.client.swing.component;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.swing.action.NewCustomerAction;

/**
 * 
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class JsamsShortcutToolBar extends JPanel {

	/**
	 * Serial Version
	 */
	private static final long serialVersionUID = 8357027337848797984L;
	
	private JsamsButton newCustomerButton;
	private JsamsButton newEstimateButton;
	private JsamsButton newBillButton;
	private JsamsButton newProductButton;
	private JsamsButton statisticsButton;
	
	public JsamsShortcutToolBar() {
		super();
		initComponents();
	}
	
	private void initComponents() {
		JPanel panel = new JPanel(new FlowLayout());
		newCustomerButton = new JsamsButton(JsamsI18nResource.BUTTON_NEW_CUSTOMER);
		newCustomerButton.addActionListener(new NewCustomerAction());
		newEstimateButton = new JsamsButton(JsamsI18nResource.BUTTON_NEW_ESTIMATE);
		newBillButton = new JsamsButton(JsamsI18nResource.BUTTON_NEW_BILL);
		newProductButton = new JsamsButton(JsamsI18nResource.BUTTON_NEW_PRODUCT);
		statisticsButton = new JsamsButton(JsamsI18nResource.BUTTON_STATISTICS);
		
		panel.add(newCustomerButton);
		panel.add(newEstimateButton);
		panel.add(newBillButton);
		panel.add(newProductButton);
		panel.add(statisticsButton);
		this.add(panel);
	}
}
