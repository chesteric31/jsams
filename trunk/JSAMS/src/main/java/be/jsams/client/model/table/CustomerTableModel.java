package be.jsams.client.model.table;

import java.util.Arrays;
import java.util.List;

import be.jsams.client.i18n.I18nString;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.i18n.UserContext;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.ContactInformationBean;
import be.jsams.common.bean.model.LegalFormBean;
import be.jsams.common.bean.model.PaymentModeBean;
import be.jsams.common.bean.model.management.CustomerBean;

/**
 * {@link JsamsTableModel} for {@link CustomerBean} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CustomerTableModel extends JsamsTableModel<CustomerBean> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 5657883688442221105L;

    /**
     * The columns name
     */
    private static List<I18nString> columnsName = Arrays.asList(JsamsI18nResource.COLUMN_ID,
            JsamsI18nResource.COLUMN_NAME, JsamsI18nResource.COLUMN_LEGAL_FORM, JsamsI18nResource.COLUMN_ZIP_CODE,
            JsamsI18nResource.COLUMN_PAYMENT_MODE, JsamsI18nResource.COLUMN_PHONE);

    /**
     * Constructor
     * 
     * @param listModel
     *            a list of {@link CustomerBean}
     */
    public CustomerTableModel(List<CustomerBean> listModel) {
        super(listModel);
    }

    /**
     * @return the columns count
     */
    public int getColumnCount() {
        return columnsName.size();
    }

    /**
     * @param rowIndex
     *            the row
     * @param columnIndex
     *            the column
     * @return the value following the row and column
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
     * @param columnIndex
     *            the column
     * @return the column name
     */
    public String getColumnName(int columnIndex) {
        return columnsName.get(columnIndex).getTranslation();
    }

    /**
     * @param columnIndex
     *            the column
     * @return the column class
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
        case ZERO:
            return Long.class;
        case ONE:
            return String.class;
        case TWO:
            return String.class;
        case THREE:
            return String.class;
        case FOUR:
            return String.class;
        case FIVE:
            return String.class;
        default:
            return Object.class;
        }
    }

}
