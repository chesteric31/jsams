package be.jsams.client.model.table.management;

import java.util.Arrays;
import java.util.List;

import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.i18n.UserContext;
import be.jsams.client.model.table.AbstractJsamsTableModel;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.ContactInformationBean;
import be.jsams.common.bean.model.LegalFormBean;
import be.jsams.common.bean.model.PaymentModeBean;
import be.jsams.common.bean.model.management.CustomerBean;

/**
 * {@link AbstractJsamsTableModel} for {@link CustomerBean} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CustomerTableModel extends AbstractJsamsTableModel<CustomerBean> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 5657883688442221105L;

    /**
     * Default constructor.
     */
    public CustomerTableModel() {
        super();
    }
    
    /**
     * Constructor.
     * 
     * @param listModel a list of {@link CustomerBean}
     */
    public CustomerTableModel(List<CustomerBean> listModel) {
        super(listModel);
        setColumnNames(Arrays.asList(JsamsI18nResource.COLUMN_ID, JsamsI18nResource.COLUMN_NAME,
                JsamsI18nResource.COLUMN_LEGAL_FORM, JsamsI18nResource.COLUMN_ZIP_CODE,
                JsamsI18nResource.COLUMN_PAYMENT_MODE, JsamsI18nResource.COLUMN_PHONE));
    }

    /**
     * {@inheritDoc}
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        CustomerBean customer = (CustomerBean) getRow(rowIndex);
        switch (columnIndex) {
        case ZERO:
            return customer.getId();
        case ONE:
            return customer.getName();
        case TWO:
            LegalFormBean legalForm = customer.getLegalForm();
            if (legalForm != null) {
                if (UserContext.isDutch()) {
                    return legalForm.getLabelNl();
                } else if (UserContext.isFrench()) {
                    return legalForm.getLabelFr();
                } else {
                    return legalForm.getLabel();
                }
            } else {
                return "";
            }
        case THREE:
            AddressBean billingAddress = customer.getBillingAddress();
            if (billingAddress != null) {
                return billingAddress.getZipCode();
            }
        case FOUR:
            PaymentModeBean paymentMode = customer.getPaymentMode();
            if (paymentMode != null) {
                if (UserContext.isDutch()) {
                    return paymentMode.getLabelNl();
                } else if (UserContext.isFrench()) {
                    return paymentMode.getLabelFr();
                } else {
                    return paymentMode.getLabel();
                }
            }
        case FIVE:
            ContactInformationBean contactInformation = customer.getContactInformation();
            if (contactInformation != null) {
                return contactInformation.getPhone();
            }
        default:
            return "";
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
        case ZERO:
            return Long.class;
        case ONE:
        case TWO:
        case THREE:
        case FOUR:
        case FIVE:
            return String.class;
        default:
            return Object.class;
        }
    }

}
