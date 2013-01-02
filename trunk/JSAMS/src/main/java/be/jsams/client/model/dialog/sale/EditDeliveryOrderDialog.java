package be.jsams.client.model.dialog.sale;

import javax.swing.JPanel;

import be.jsams.client.context.ApplicationContext;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.model.dialog.AbstractEditDialog;
import be.jsams.client.validator.edit.sale.EditDeliveryOrderValidator;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.sale.DeliveryOrderBean;
import be.jsams.common.bean.view.sale.DeliveryOrderBeanView;
import be.jsams.server.model.management.Customer;
import be.jsams.server.service.sale.DeliveryOrderService;

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
     * Constructor.
     * 
     * @param title the {@link I18nString} title
     * @param model the {@link DeliveryOrderBean} model
     */
    public EditDeliveryOrderDialog(final I18nString title, DeliveryOrderBean model) {
        super(null, title, model, new EditDeliveryOrderValidator(), ApplicationContext.getDeliveryOrderService());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveOriginalModel() {
        SocietyBean society = getModel().getSociety();
        CustomerBean customer = getModel().getCustomer();
        DeliveryOrderBean originalModel = new DeliveryOrderBean(society, customer);
        CustomerBean customerBean = ApplicationContext.getCustomerBeanBuilder().build(new Customer(customer), society);
        originalModel.setCustomer(customerBean);
        setOriginalModel(originalModel);
        getOriginalModel().refresh(getModel());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel initComponents() {
        DeliveryOrderBeanView view = getModel().getView();
        return view.createEditView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performOk() {
        DeliveryOrderBean model = getModel();
        super.postPerformOk(model);
    }

}
