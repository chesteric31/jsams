package be.jsams.client.model.dialog.sale;

import javax.swing.JPanel;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.model.dialog.AbstractEditDialog;
import be.jsams.client.validator.edit.EditBillValidator;
import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.model.sale.BillBean;
import be.jsams.common.bean.view.PaymentModeBeanView;
import be.jsams.common.bean.view.sale.BillBeanView;
import be.jsams.server.model.PaymentMode;
import be.jsams.server.service.sale.BillService;

import com.jgoodies.validation.view.ValidationComponentUtils;

/**
 * Edit Command {@link AbstractEditDialog}, to create or update a
 * {@link BillBean} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EditBillDialog extends AbstractEditDialog<BillBean, EditBillValidator, BillService> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 1751391551336992500L;

    /**
     * Constructor
     * 
     * @param title the {@link I18nString} title
     * @param model the {@link BillBean} model
     */
    public EditBillDialog(final I18nString title, BillBean model) {
        super(null, title, model, new EditBillValidator(), JsamsApplicationContext.getBillService());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initComponents() {
        BillBeanView view = getModel().getView();
        JPanel panel = view.createEditView();
        getContentPane().add(panel);
        ValidationComponentUtils.updateComponentTreeMandatoryBorder(this);
        pack();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performOk() {
        BillBean model = getModel();
        AbstractIdentityBean<PaymentMode, PaymentModeBeanView> paymentMode = model.getPaymentMode().getSelection();
        if (paymentMode != null) {
            model.getPaymentMode().refresh(paymentMode);
        }
        super.postPerformOk(model);
    }

}
