package be.jsams.client.model.table.sale;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import be.jsams.client.i18n.I18nResource;
import be.jsams.client.i18n.UserContext;
import be.jsams.client.model.table.AbstractJsamsTableModel;
import be.jsams.common.bean.model.PaymentModeBean;
import be.jsams.common.bean.model.sale.BillBean;

/**
 * {@link AbstractJsamsTableModel} for {@link BillBean} object.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class BillTableModel extends AbstractJsamsTableModel<BillBean> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 8018300192292517259L;

    /**
     * Default constructor.
     */
    public BillTableModel() {
        super();
    }
    
    /**
     * Constructor.
     * 
     * @param listBean a list of {@link BillBean}
     */
    public BillTableModel(List<BillBean> listBean) {
        super(listBean);
        setColumnNames(Arrays.asList(I18nResource.COLUMN_ID, I18nResource.COLUMN_CREATION_DATE,
                I18nResource.COLUMN_PAID, I18nResource.COLUMN_CLOSED, I18nResource.COLUMN_PAYMENT_MODE,
                I18nResource.COLUMN_DISCOUNT_RATE));
    }

    /**
     * {@inheritDoc}
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        BillBean bean = (BillBean) getRow(rowIndex);
        switch (columnIndex) {
        case ZERO:
            return bean.getId();
        case ONE:
            return bean.getCreationDate();
        case TWO:
            return bean.getPaymentDate();
        case THREE:
            return bean.isClosed();
        case FOUR:
            PaymentModeBean mode = bean.getPaymentMode();
            if (UserContext.isDutch()) {
                return mode.getLabelNl();
            } else if (UserContext.isFrench()) {
                return mode.getLabelFr();
            } else {
                return mode.getLabel();
            }
        case FIVE:
            return bean.getDiscountRate();
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
        case THREE:
            return Boolean.class;
        case FOUR:
            return String.class;
        case FIVE:
            return Double.class;
        default:
            return Object.class;
        }
    }
    
}
