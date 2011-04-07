package be.jsams.client.model.table;

import java.util.Arrays;
import java.util.List;

import be.jsams.client.i18n.I18nString;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.common.bean.model.sale.EstimateDetailBean;

/**
 * 
 * 
 * @author chesteric31
 * @version $$Rev$$ $$Date::                  $$ $$Author$$
 */
public class EstimateDetailTableModel extends JsamsTableModel<EstimateDetailBean> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -6461186432772120925L;

    /**
     * The columns name
     */
    private static List<I18nString> columnsName = Arrays.asList(JsamsI18nResource.COLUMN_PRODUCT_ID,
            JsamsI18nResource.COLUMN_PRODUCT_NAME, JsamsI18nResource.COLUMN_QUANTITY, JsamsI18nResource.COLUMN_PRICE,
            JsamsI18nResource.COLUMN_DISCOUNT_RATE, JsamsI18nResource.COLUMN_VAT_APPLICABE);

    /**
     * Constructor
     * 
     * @param listBean a list of {@link EstimateDetailBean}
     */
    public EstimateDetailTableModel(List<EstimateDetailBean> listBean) {
        super(listBean);
    }

    /**
     * @return the columns count
     */
    public int getColumnCount() {
        return columnsName.size();
    }

    /**
     * @param rowIndex the row
     * @param columnIndex the column
     * @return the value following the row and column
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        EstimateDetailBean detail = (EstimateDetailBean) getRow(rowIndex);
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
            return detail.getPrice();
        case FOUR:
            return detail.getDiscountRate();
        case FIVE:
            return detail.getVatApplicable();
        default:
            return "";
        }
    }

    /**
     * @param columnIndex the column
     * @return the column name
     */
    public String getColumnName(int columnIndex) {
        return columnsName.get(columnIndex).getTranslation();
    }

    /**
     * @param columnIndex the column
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
            return Integer.class;
        case THREE:
            return Double.class;
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
        EstimateDetailBean detail = (EstimateDetailBean) getRow(row);
        String stringValue = value.toString();
        switch (col) {
        case TWO:
            detail.setQuantity(Integer.parseInt(stringValue));
            break;
        case THREE:
            detail.setPrice(Double.parseDouble(stringValue));
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
