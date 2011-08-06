package be.jsams.client.model.table;

import java.util.Arrays;
import java.util.List;

import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.common.bean.model.sale.detail.DeliveryOrderDetailBean;

/**
 * Customized table model for {@link DeliveryOrderDetailBean}.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class DeliveryOrderDetailTableModel extends AbstractJsamsTableModel<DeliveryOrderDetailBean> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -8763369717901569310L;

    /**
     * Constructor
     * 
     * @param listBean a list of {@link DeliveryOrderDetailBean}
     */
    public DeliveryOrderDetailTableModel(List<DeliveryOrderDetailBean> listBean) {
        super(listBean);
        setColumnNames(Arrays.asList(JsamsI18nResource.COLUMN_PRODUCT_ID, JsamsI18nResource.COLUMN_PRODUCT_NAME,
                JsamsI18nResource.COLUMN_QUANTITY, JsamsI18nResource.COLUMN_TRANSFERRED,
                JsamsI18nResource.COLUMN_DISCOUNT_RATE, JsamsI18nResource.COLUMN_VAT_APPLICABLE));
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
            return detail.isTransferred();
        case FOUR:
            return detail.getDiscountRate();
        case FIVE:
            return detail.getVatApplicable();
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
            return String.class;
        case TWO:
            return Integer.class;
        case THREE:
            return Boolean.class;
        case FOUR:
            return Double.class;
        case FIVE:
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
            detail.setTransferred(Boolean.parseBoolean(stringValue));
            break;
        case FOUR:
            detail.setDiscountRate(Double.parseDouble(stringValue));
            break;
        case FIVE:
            detail.setVatApplicable(Double.parseDouble(stringValue));
            break;
        default:
        }
    }

}
