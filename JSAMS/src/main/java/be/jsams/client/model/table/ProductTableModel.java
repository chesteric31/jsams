package be.jsams.client.model.table;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import be.jsams.client.i18n.I18nString;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.i18n.UserContext;
import be.jsams.server.model.Product;
import be.jsams.server.model.ProductCategory;

/**
 * {@link JsamsTableModel} for {@link Product} object.
 * 
 * @author chesteric31
 * @version $$Rev$$ $$Date::                  $$ $$Author$$
 */
public class ProductTableModel extends JsamsTableModel<Product> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 5631609209979319706L;

    /**
     * The columns name
     */
    private List<I18nString> columnNames = Arrays.asList(JsamsI18nResource.COLUMN_ID, JsamsI18nResource.COLUMN_NAME,
            JsamsI18nResource.COLUMN_PRICE, JsamsI18nResource.COLUMN_STOCK_QUANTITY,
            JsamsI18nResource.COLUMN_REORDER_LEVEL, JsamsI18nResource.COLUMN_VAT_APPLICABE,
            JsamsI18nResource.COLUMN_PRODUCT_CATEGORY);

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
        case 0:
            return getData().get(rowIndex).getId();
        case 1:
            return getData().get(rowIndex).getName();
        case 2:
            return getData().get(rowIndex).getPrice();
        case 3:
            return getData().get(rowIndex).getQuantityStock();
        case 4:
            return getData().get(rowIndex).getReorderLevel();
        case 5:
            return getData().get(rowIndex).getVatApplicable().doubleValue();
        case 6:
            ProductCategory category = getData().get(rowIndex).getCategory();
            if (UserContext.isDutch()) {
                return category.getLabelNl();
            } else if (UserContext.isFrench()) {
                return category.getLabelFr();
            } else {
                return category.getLabel();
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
            return BigDecimal.class;
        case 3:
            return Integer.class;
        case 4:
            return Integer.class;
        case 5:
            return Double.class;
        case 6:
            return String.class;
        default:
            return Object.class;
        }
    }

}
