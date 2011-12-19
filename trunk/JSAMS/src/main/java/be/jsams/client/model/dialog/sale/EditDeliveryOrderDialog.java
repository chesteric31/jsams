package be.jsams.client.model.dialog.sale;

import javax.swing.JPanel;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.model.dialog.AbstractEditDialog;
import be.jsams.client.validator.edit.EditDeliveryOrderValidator;
import be.jsams.common.bean.model.sale.DeliveryOrderBean;
import be.jsams.common.bean.view.sale.DeliveryOrderBeanView;
import be.jsams.server.service.sale.DeliveryOrderService;

import com.jgoodies.validation.view.ValidationComponentUtils;

/**
 * Edit Delivery Order {@link AbstractEditDialog}, to create or update a
 * {@link DeliveryOrderBean} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EditDeliveryOrderDialog extends
        AbstractEditDialog<DeliveryOrderBean, EditDeliveryOrderValidator, DeliveryOrderService> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 6891067908001649339L;

    /**
     * Constructor
     * 
     * @param title the {@link I18nString} title
     * @param model the {@link DeliveryOrderBean} model
     */
    public EditDeliveryOrderDialog(final I18nString title, DeliveryOrderBean model) {
        super(null, title, model, new EditDeliveryOrderValidator(), JsamsApplicationContext.getDeliveryOrderService());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initComponents() {
        setOriginalModel(new DeliveryOrderBean(getModel().getSociety(), getModel().getCustomer()));
        getOriginalModel().refresh(getModel());
        DeliveryOrderBeanView view = getModel().buildView();
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
        DeliveryOrderBean command = getModel();
        super.postPerformOk(command);
    }

}
