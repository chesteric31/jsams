package be.jsams.client.model.table;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import be.jsams.client.i18n.I18nString;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.common.bean.model.sale.EstimateBean;

/**
 * {@link JsamsTableModel} for {@link EstimateBean} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EstimateTableModel extends JsamsTableModel<EstimateBean> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 8310299690507417519L;

    /**
     * The columns name
     */
    private static List<I18nString> columnsName = Arrays.asList(JsamsI18nResource.COLUMN_ID,
            JsamsI18nResource.COLUMN_CREATION_DATE, JsamsI18nResource.COLUMN_TRANSFERRED,
            JsamsI18nResource.COLUMN_REMARK, JsamsI18nResource.COLUMN_DISCOUNT_RATE);

    /**
     * Constructor
     * 
     * @param listBean
     *            a list of {@link EstimateBean}
     */
    public EstimateTableModel(List<EstimateBean> listBean) {
        super(listBean);
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
        EstimateBean estimate = (EstimateBean) getRow(rowIndex);
        switch (columnIndex) {
        case ZERO:
            return estimate.getId();
        case ONE:
            return estimate.getCreationDate();
        case TWO:
            return estimate.isTransferred();
        case THREE:
            return estimate.getRemark();
        case FOUR:
            return estimate.getDiscountRate();
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
            return Date.class;
        case TWO:
            return Boolean.class;
        case THREE:
            return String.class;
        case FOUR:
            return Double.class;
        default:
            return Object.class;
        }
    }

}
