package be.jsams.client.model.table;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import be.jsams.client.i18n.I18nString;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.server.model.ProductCategory;

/**
 * {@link AbstractTableModel} for {@link ProductCategory} object.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class ProductCategoryTableModel extends AbstractTableModel {

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
     * The list of {@link ProductCategory}
     */
    private List<ProductCategory> data;

    /**
     * 
     * @return a list of {@link ProductCategory}
     */
    public List<ProductCategory> getData() {
        return data;
    }

    /**
     * 
     * @param data
     *            the list of {@link ProductCategory} to set
     */
    public void setData(List<ProductCategory> data) {
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
            return data.get(rowIndex).getLabel();
        case 2:
            return data.get(rowIndex).getLabelFr();
        case 3:
            return data.get(rowIndex).getLabelNl();
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
