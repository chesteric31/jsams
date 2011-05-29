package be.jsams.client.swing.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.client.i18n.JsamsI18nResource;
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
        CustomerBean customerBean = JsamsApplicationContext.getCustomerBeanBuilder().build(null,
                JsamsDesktop.getInstance().getCurrentSociety());
        new EditCustomerDialog(JsamsI18nResource.TITLE_EDIT_CUSTOMER, customerBean);
    }

}
