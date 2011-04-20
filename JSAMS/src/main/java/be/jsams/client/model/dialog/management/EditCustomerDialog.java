package be.jsams.client.model.dialog.management;

import javax.swing.JPanel;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.model.dialog.AbstractEditDialog;
import be.jsams.client.validator.EditCustomerValidator;
import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.model.CivilityBean;
import be.jsams.common.bean.model.LegalFormBean;
import be.jsams.common.bean.model.PaymentModeBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.view.CivilityBeanView;
import be.jsams.common.bean.view.LegalFormBeanView;
import be.jsams.common.bean.view.PaymentModeBeanView;
import be.jsams.common.bean.view.management.CustomerBeanView;
import be.jsams.server.model.Civility;
import be.jsams.server.model.LegalForm;
import be.jsams.server.model.PaymentMode;
import be.jsams.server.service.CustomerService;

import com.jgoodies.validation.view.ValidationComponentUtils;

/**
 * Edit Customer {@link AbstractEditDialog}, to create or update a {@link CustomerBean} object.
 * 
 * @author chesteric31
 * @version $$Rev$$ $$Date::                  $$ $$Author$$
 */
public class EditCustomerDialog extends AbstractEditDialog<CustomerBean, EditCustomerValidator, CustomerService> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 6898471936119469349L;

    /**
     * Constructor
     * 
     * @param title
     *            the {@link I18nString} title
     * @param model
     *            the {@link CustomerBean} model
     */
    public EditCustomerDialog(final I18nString title, CustomerBean model) {
        super(null, title, "apps/system-users.png");
        super.setModel(model);
        super.setValidator(new EditCustomerValidator());
        super.setService(JsamsApplicationContext.getCustomerService());
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    protected void initComponents() {
        CustomerBeanView view = getModel().getView();
        JPanel panel = view.createEditView();
        getContentPane().add(panel);
        ValidationComponentUtils.updateComponentTreeMandatoryBorder(this);
        pack();
    }

    /**
     * {@inheritDoc}
     */
    public void performOk() {
        CustomerBean customer = getModel();
        AbstractIdentityBean<LegalForm, LegalFormBeanView> legalForm = customer.getLegalForm().getSelection();
        if (legalForm != null) {
            customer.setLegalForm((LegalFormBean) legalForm);
        }
        AbstractIdentityBean<Civility, CivilityBeanView> civility = customer.getCivility().getSelection();
        if (civility != null) {
            customer.setCivility((CivilityBean) civility);
        }
        AbstractIdentityBean<PaymentMode, PaymentModeBeanView> paymentMode = customer.getPaymentMode().getSelection();
        if (paymentMode != null) {
            customer.setPaymentMode((PaymentModeBean) paymentMode);
        }
        super.postPerformOk(customer);
    }

}
