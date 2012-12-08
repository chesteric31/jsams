package be.jsams.client.model.table.sale;

import java.util.Arrays;
import java.util.List;

import be.jsams.client.i18n.I18nResource;
import be.jsams.client.model.table.AbstractJsamsTableModel;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.common.bean.model.sale.CommandMediator;
import be.jsams.common.bean.model.sale.detail.CommandDetailBean;

/**
 * Customized table model for {@link CommandDetailBean}.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CommandDetailTableModel extends AbstractJsamsTableModel<CommandDetailBean> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -6559193493434025054L;

    /**
     * Constructor.
     * 
     * @param listBean a list of {@link CommandDetailBean}
     * @param mediator the mediator
     */
    public CommandDetailTableModel(List<CommandDetailBean> listBean, CommandMediator mediator) {
        super(listBean);
        setColumnNames(Arrays.asList(I18nResource.COLUMN_PRODUCT_ID, I18nResource.COLUMN_PRODUCT_NAME,
                I18nResource.COLUMN_QUANTITY, I18nResource.COLUMN_PRICE,
                I18nResource.COLUMN_TRANSFERRED, I18nResource.COLUMN_DISCOUNT_RATE,
                I18nResource.COLUMN_VAT_APPLICABLE));
        if (listBean != null && !listBean.isEmpty()) {
            for (CommandDetailBean bean : listBean) {
                bean.setMediator(mediator);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        CommandDetailBean detail = (CommandDetailBean) getRow(rowIndex);
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
            return detail.isTransferred();
        case FIVE:
            return detail.getDiscountRate();
        case SIX:
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
        case FOUR:
            return Boolean.class;
        case THREE:
        case FIVE:
        case SIX:
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
        CommandDetailBean detail = (CommandDetailBean) getRow(row);
        String stringValue = value.toString();
        switch (col) {
        case TWO:
            detail.setQuantity(Integer.parseInt(stringValue));
            break;
        case THREE:
            detail.setPrice(Double.parseDouble(stringValue));
            break;
        case FOUR:
            detail.setTransferred(Boolean.parseBoolean(stringValue));
            break;
        case FIVE:
            detail.setDiscountRate(Double.parseDouble(stringValue));
            break;
        case SIX:
            detail.setVatApplicable(Double.parseDouble(stringValue));
            break;
        default:
        }
    }

}
