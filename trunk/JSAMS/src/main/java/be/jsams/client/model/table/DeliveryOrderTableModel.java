package be.jsams.client.model.table;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.common.bean.model.sale.DeliveryOrderBean;

/**
 * {@link AbstractJsamsTableModel} for {@link DeliveryOrderBean} object.
 *
 * @author ebinard
 * @version $Rev$ $Date::                  $ $Author$
 */
public class DeliveryOrderTableModel extends AbstractJsamsTableModel<DeliveryOrderBean> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -84296226501937365L;

    /**
     * Constructor
     * 
     * @param listBean a list of {@link DeliveryOrderBean}
     */
    public DeliveryOrderTableModel(List<DeliveryOrderBean> listBean) {
        super(listBean);
        setColumnNames(Arrays.asList(JsamsI18nResource.COLUMN_ID, JsamsI18nResource.COLUMN_CREATION_DATE,
                JsamsI18nResource.COLUMN_TRANSFERRED, JsamsI18nResource.COLUMN_REMARK,
                JsamsI18nResource.COLUMN_DISCOUNT_RATE));
    }

    /**
     * {@inheritDoc}
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        DeliveryOrderBean deliveryOrder = (DeliveryOrderBean) getRow(rowIndex);
        switch (columnIndex) {
        case ZERO:
            return deliveryOrder.getId();
        case ONE:
            return deliveryOrder.getCreationDate();
        case TWO:
            return deliveryOrder.isTransferred();
        case THREE:
            return deliveryOrder.getRemark();
        case FOUR:
            return deliveryOrder.getDiscountRate();
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
