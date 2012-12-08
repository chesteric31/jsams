package be.jsams.client.swing.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import be.jsams.client.context.ApplicationContext;
import be.jsams.client.desktop.Desktop;
import be.jsams.client.i18n.I18nResource;
import be.jsams.client.model.dialog.sale.EditBillDialog;
import be.jsams.common.bean.model.PaymentModeBean;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.sale.BillBean;
import be.jsams.common.bean.model.sale.BillMediator;

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
        SocietyBean currentSociety = Desktop.getInstance().getCurrentSociety();
        CustomerBean customer = ApplicationContext.getCustomerBeanBuilder().build(null, currentSociety);
        PaymentModeBean mode = ApplicationContext.getPaymentModeBeanBuilder().build();
        BillBean bean = new BillBean(currentSociety, customer, mode);
        BillMediator mediator = new BillMediator();
        mediator.setBillBean(bean);
        bean.setMediator(mediator);
        new EditBillDialog(I18nResource.TITLE_EDIT_BILL, bean);
    }

}
