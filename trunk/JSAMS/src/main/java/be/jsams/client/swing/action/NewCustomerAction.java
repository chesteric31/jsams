package be.jsams.client.swing.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import be.jsams.client.context.ApplicationContext;
import be.jsams.client.desktop.Desktop;
import be.jsams.client.i18n.I18nResource;
import be.jsams.client.model.dialog.management.EditCustomerDialog;
import be.jsams.common.bean.model.management.CustomerBean;

/**
 * {@link AbstractAction} to launch {@link EditCustomerDialog}.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class NewCustomerAction extends AbstractAction {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -872702115060030751L;

    /**
     * {@inheritDoc}
     */
    public void actionPerformed(ActionEvent e) {
        CustomerBean customerBean = ApplicationContext.getCustomerBeanBuilder().build(null,
                Desktop.getInstance().getCurrentSociety());
        new EditCustomerDialog(I18nResource.TITLE_EDIT_CUSTOMER, customerBean);
    }

}
