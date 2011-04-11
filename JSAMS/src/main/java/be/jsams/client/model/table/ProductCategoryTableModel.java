package be.jsams.client.model.table;

import java.util.Arrays;
import java.util.List;

import be.jsams.client.i18n.I18nString;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.common.bean.model.management.ProductCategoryBean;

/**
 * {@link JsamsTableModel} for {@link ProductCategoryBean} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class ProductCategoryTableModel extends JsamsTableModel<ProductCategoryBean> {

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
     * Constructor
     * 
     * @param listModel
     *            a list of {@link ProductCategoryBean}
     */
    public ProductCategoryTableModel(List<ProductCategoryBean> listModel) {
        super(listModel);
    }

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
        ProductCategoryBean category = (ProductCategoryBean) getRow(rowIndex);
        switch (columnIndex) {
        case ZERO:
            return category.getId();
        case ONE:
            return category.getLabel();
        case TWO:
            return category.getLabelFr();
        case THREE:
            return category.getLabelNl();
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
            return String.class;
        default:
            return Object.class;
        }
    }
}