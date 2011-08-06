package be.jsams.client.model.table;

import java.util.Arrays;
import java.util.List;

import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.common.bean.model.sale.detail.CommandDetailBean;

/**
 * Customized table model for {@link CommandDetailBean} wizard purpose
 * 
 * @author chesteric31
 * @version $$Rev: 794 $$ $$Date:: 2011-06-05 18:01#$$ $$Author: chesteric31 $$
 */
public class CommandDetailWizardTableModel extends AbstractJsamsTableModel<CommandDetailBean> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 6723240746447143729L;

    /**
     * Constructor
     * 
     * @param listBean a list of {@link CommandDetailBean}
     */
    public CommandDetailWizardTableModel(List<CommandDetailBean> listBean) {
        super(listBean);
        setColumnNames(Arrays.asList(JsamsI18nResource.COLUMN_COMMAND_ID, JsamsI18nResource.COLUMN_PRODUCT_ID,
                JsamsI18nResource.COLUMN_PRODUCT_NAME, JsamsI18nResource.COLUMN_QUANTITY,
                JsamsI18nResource.COLUMN_PRICE, JsamsI18nResource.COLUMN_DISCOUNT_RATE,
                JsamsI18nResource.COLUMN_VAT_APPLICABLE));
    }

    /**
     * {@inheritDoc}
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        CommandDetailBean detail = (CommandDetailBean) getRow(rowIndex);
        ProductBean product = detail.getProduct();
        switch (columnIndex) {
        case ZERO:
            return detail.getCommand().getId();
        case ONE:
            if (product != null) {
                return product.getId();
            } else {
                return "";
            }
        case TWO:
            if (product != null) {
                return product.getName();
            } else {
                return "";
            }
        case THREE:
            return detail.getQuantity();
        case FOUR:
            return detail.getPrice();
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
            return Long.class;
        case TWO:
            return String.class;
        case THREE:
            return Integer.class;
        case FOUR:
            return Double.class;
        case FIVE:
            return Double.class;
        case SIX:
            return Double.class;
        default:
            return Object.class;
        }
    }

}
