package be.jsams.client.swing.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.dialog.sale.EditBillDialog;
import be.jsams.common.bean.model.sale.BillBean;

/**
 * {@link AbstractAction} to launch {@link EditBillDialog}.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class NewBillAction extends AbstractAction {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 5450837976674786984L;

    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        BillBean bean = new BillBean(JsamsDesktop.getInstance().getCurrentSociety());
        new EditBillDialog(JsamsI18nResource.TITLE_EDIT_BILL, bean);
    }

}
