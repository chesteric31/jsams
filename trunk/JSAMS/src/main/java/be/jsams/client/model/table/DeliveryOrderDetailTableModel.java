package be.jsams.client.model.table;

import java.util.Arrays;
import java.util.List;

import be.jsams.client.i18n.I18nString;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.common.bean.model.sale.DeliveryOrderDetailBean;

/**
 * Customized table model for {@link DeliveryOrderDetailBean}.
 *
 * @author ebinard
 * @version $Rev$ $Date::                  $ $Author$
 */
public class DeliveryOrderDetailTableModel extends JsamsTableModel<DeliveryOrderDetailBean> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -8763369717901569310L;
    /**
     * The columns name
     */
    private static List<I18nString> columnsName = Arrays.asList(JsamsI18nResource.COLUMN_PRODUCT_ID,
            JsamsI18nResource.COLUMN_PRODUCT_NAME, JsamsI18nResource.COLUMN_QUANTITY, JsamsI18nResource.COLUMN_PRICE,
            JsamsI18nResource.COLUMN_DISCOUNT_RATE, JsamsI18nResource.COLUMN_VAT_APPLICABE);

    /**
     * Constructor
     * 
     * @param listBean a list of {@link DeliveryOrderDetailBean}
     */
    public DeliveryOrderDetailTableModel(List<DeliveryOrderDetailBean> listBean) {
        super(listBean);
    }

    /**
     * @return the columns count
     */
    public int getColumnCount() {
        return columnsName.size();
    }

    /**
     * {@inheritDoc}
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        DeliveryOrderDetailBean detail = (DeliveryOrderDetailBean) getRow(rowIndex);
        ProductBean product = detail.getProduct();
        switch (columnIndex) {
        case ZERO:
            if (product != null) {
                return product.getId();
            } else {
                return "";
            }
        case ONE:
            if (product != null) {
                return product.getName();
            } else {
                return "";
            }
        case TWO:
            return detail.getQuantity();
        case THREE:
            return detail.getDiscountRate();
        case FOUR:
            return detail.getVatApplicable();
        default:
            return "";
        }
    }

    /**
     * {@inheritDoc}
     */
    public String getColumnName(int columnIndex) {
        return columnsName.get(columnIndex).getTranslation();
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
            return String.class;
        case TWO:
            return Integer.class;
        case THREE:
            return Double.class;
        case FOUR:
            return Double.class;
        default:
            return Object.class;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return (columnIndex != 0 && columnIndex != 1);
    }

    /**
     * {@inheritDoc}
     */
    public void setValueAt(Object value, int row, int col) {
        DeliveryOrderDetailBean detail = (DeliveryOrderDetailBean) getRow(row);
        String stringValue = value.toString();
        switch (col) {
        case TWO:
            detail.setQuantity(Integer.parseInt(stringValue));
            break;
        case THREE:
            detail.setDiscountRate(Double.parseDouble(stringValue));
            break;
        case FIVE:
            detail.setVatApplicable(Double.parseDouble(stringValue));
            break;
        default:
        }
    }
    
}
