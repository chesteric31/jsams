package be.jsams.client.swing.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import be.jsams.client.desktop.EditCustomerFrame;
import be.jsams.client.i18n.JsamsI18nResource;

/**
 * 
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class NewCustomerListener implements ActionListener {

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		EditCustomerFrame editCustomerFrame = new EditCustomerFrame(JsamsI18nResource.TITLE_EDIT_CUSTOMER, null);
		editCustomerFrame.setVisible(true);
		editCustomerFrame.setResizable(false);
		editCustomerFrame.setLocationRelativeTo(null);
	}

}
