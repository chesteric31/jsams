package be.jsams.client.model.table;

import java.util.Arrays;
import java.util.List;

import be.jsams.client.i18n.I18nString;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.server.model.ProductCategory;

/**
 * {@link JsamsTableModel} for {@link ProductCategory} object.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class ProductCategoryTableModel extends JsamsTableModel<ProductCategory> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -7016107019455922155L;

    /**
     * The columns name
     */
    private List<I18nString> columnNames = Arrays.asList(JsamsI18nResource.COLUMN_ID,
            JsamsI18nResource.COLUMN_LABEL_EN, JsamsI18nResource.COLUMN_LABEL_FR, JsamsI18nResource.COLUMN_LABEL_NL);

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
            return getData().get(rowIndex).getLabel();
        case 2:
            return getData().get(rowIndex).getLabelFr();
        case 3:
            return getData().get(rowIndex).getLabelNl();
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
            return String.class;
        case 3:
            return String.class;
        default:
            return Object.class;
        }
    }
}
