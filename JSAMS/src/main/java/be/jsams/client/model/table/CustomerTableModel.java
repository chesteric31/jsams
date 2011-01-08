package be.jsams.client.model.table;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import be.jsams.client.i18n.I18nString;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.i18n.UserContext;
import be.jsams.server.model.Customer;
import be.jsams.server.model.PaymentMode;

/**
 * {@link AbstractTableModel} for {@link Customer} object.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CustomerTableModel extends AbstractTableModel {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 5657883688442221105L;

    /**
     * The columns name
     */
    private List<I18nString> columnNames = Arrays.asList(JsamsI18nResource.COLUMN_ID, JsamsI18nResource.COLUMN_NAME,
            JsamsI18nResource.COLUMN_ZIP_CODE, JsamsI18nResource.COLUMN_PAYMENT_MODE, JsamsI18nResource.COLUMN_PHONE);

    /**
     * The list of {@link Customer}
     */
    private List<Customer> data;

    /**
     * 
     * @return a list of {@link Customer}
     */
    public List<Customer> getData() {
        return data;
    }

    /**
     * 
     * @param data
     *            the list of {@link Customer} to set
     */
    public void setData(List<Customer> data) {
        this.data = data;
    }

    /**
     * @return the columns count
     */
    public int getColumnCount() {
        return columnNames.size();
    }

    /**
     * @return the rows count
     */
    public int getRowCount() {
        return data.size();
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
        case 0:
            return data.get(rowIndex).getId();
        case 1:
            return data.get(rowIndex).getName();
        case 2:
            return data.get(rowIndex).getBillingAddress().getZipCode();
        case 3:
            PaymentMode paymentMode = data.get(rowIndex).getPaymentMode();
            if (UserContext.isDutch()) {
                return paymentMode.getLabelNl();
            } else if (UserContext.isFrench()) {
                return paymentMode.getLabelFr();
            } else {
                return paymentMode.getLabel();
            }
        case 4:
            return data.get(rowIndex).getContactInformation().getPhone();
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
        case 0:
            return Long.class;
        case 1:
            return String.class;
        case 2:
            return Integer.class;
        case 3:
            return String.class;
        case 4:
            return String.class;
        default:
            return Object.class;
        }
    }

}
