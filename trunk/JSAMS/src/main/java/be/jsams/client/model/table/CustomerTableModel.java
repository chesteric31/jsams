package be.jsams.client.model.table;

import java.util.Arrays;
import java.util.List;

import be.jsams.client.i18n.I18nString;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.i18n.UserContext;
import be.jsams.server.model.Customer;
import be.jsams.server.model.LegalForm;
import be.jsams.server.model.PaymentMode;

/**
 * {@link JsamsTableModel} for {@link Customer} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CustomerTableModel extends JsamsTableModel<Customer> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 5657883688442221105L;

    /**
     * The columns name
     */
    private List<I18nString> columnNames = Arrays.asList(JsamsI18nResource.COLUMN_ID, JsamsI18nResource.COLUMN_NAME,
            JsamsI18nResource.COLUMN_LEGAL_FORM, JsamsI18nResource.COLUMN_ZIP_CODE,
            JsamsI18nResource.COLUMN_PAYMENT_MODE, JsamsI18nResource.COLUMN_PHONE);

    /**
     * @return the columns count
     */
    public int getColumnCount() {
        return columnNames.size();
    }

    /**
     * @param rowIndex
     *            the row
     * @param columnIndex
     *            the column
     * @return the value following the row and column
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
        case ZERO:
            return getData().get(rowIndex).getId();
        case ONE:
            return getData().get(rowIndex).getName();
        case TWO:
            LegalForm legalForm = getData().get(rowIndex).getLegalForm();
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
            return getData().get(rowIndex).getBillingAddress().getZipCode();
        case FOUR:
            PaymentMode paymentMode = getData().get(rowIndex).getPaymentMode();
            if (UserContext.isDutch()) {
                return paymentMode.getLabelNl();
            } else if (UserContext.isFrench()) {
                return paymentMode.getLabelFr();
            } else {
                return paymentMode.getLabel();
            }
        case FIVE:
            return getData().get(rowIndex).getContactInformation().getPhone();
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
        return columnNames.get(columnIndex).getTranslation();
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
            return Integer.class;
        case FOUR:
            return String.class;
        case FIVE:
            return String.class;
        default:
            return Object.class;
        }
    }

}
