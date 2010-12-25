package be.jsams.client.swing.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.frame.EditCustomerFrame;

/**
 * {@link AbstractAction} to launch {@link EditCustomerFrame}.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class NewCustomerAction extends AbstractAction {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -872702115060030751L;

	public void actionPerformed(ActionEvent e) {
		EditCustomerFrame editCustomerFrame = new EditCustomerFrame(
				JsamsI18nResource.TITLE_EDIT_CUSTOMER, null);
		editCustomerFrame.setLocationRelativeTo(null);
		editCustomerFrame.setResizable(false);
		editCustomerFrame.setVisible(true);
	}

}
