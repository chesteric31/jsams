package be.jsams.client.model.table.sale;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import be.jsams.client.i18n.I18nResource;
import be.jsams.client.model.table.AbstractJsamsTableModel;
import be.jsams.common.bean.model.sale.EstimateBean;

/**
 * {@link AbstractJsamsTableModel} for {@link EstimateBean} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EstimateTableModel extends AbstractJsamsTableModel<EstimateBean> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 8310299690507417519L;

    /**
     * Default constructor.
     */
    public EstimateTableModel() {
        super();
    }
    
    /**
     * Constructor.
     * 
     * @param listBean a list of {@link EstimateBean}
     */
    public EstimateTableModel(List<EstimateBean> listBean) {
        super(listBean);
        setColumnNames(Arrays.asList(I18nResource.COLUMN_ID, I18nResource.COLUMN_CREATION_DATE,
                I18nResource.COLUMN_TRANSFERRED, I18nResource.COLUMN_REMARK,
                I18nResource.COLUMN_DISCOUNT_RATE));
    }

    /**
     * {@inheritDoc}
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
     * {@inheritDoc}
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
