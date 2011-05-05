package be.jsams.client.model.dialog.sale;

import javax.swing.JPanel;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.model.dialog.AbstractEditDialog;
import be.jsams.client.validator.EditDeliveryOrderValidator;
import be.jsams.common.bean.model.sale.DeliveryOrderBean;
import be.jsams.common.bean.view.sale.DeliveryOrderBeanView;
import be.jsams.server.service.sale.DeliveryOrderService;

import com.jgoodies.validation.view.ValidationComponentUtils;

/**
 * Edit Delivery Order {@link AbstractEditDialog}, to create or update a {@link DeliveryOrderBean} object.
 *
 * @author ebinard
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EditDeliveryOrderDialog extends AbstractEditDialog<DeliveryOrderBean,
        EditDeliveryOrderValidator, DeliveryOrderService> {

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
        super(null, title);
        super.setModel(model);
        super.setValidator(new EditDeliveryOrderValidator());
        super.setService(JsamsApplicationContext.getDeliveryOrderService());
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initComponents() {
        DeliveryOrderBeanView view = getModel().getView();
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
