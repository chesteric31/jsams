package be.jsams.client.model.table.sale;

import java.util.Arrays;
import java.util.List;

import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.table.AbstractJsamsTableModel;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.common.bean.model.sale.detail.EstimateDetailBean;

/**
 * Customized table model for {@link EstimateDetailBean} wizard purpose
 * 
 * @author chesteric31
 * @version $$Rev: 794 $$ $$Date:: 2011-06-05 18:01#$$ $$Author: chesteric31 $$
 */
public class EstimateDetailWizardTableModel extends AbstractJsamsTableModel<EstimateDetailBean> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 7809823846324140033L;

    /**
     * Constructor.
     * 
     * @param listBean a list of {@link EstimateDetailBean}
     */
    public EstimateDetailWizardTableModel(List<EstimateDetailBean> listBean) {
        super(listBean);
        setColumnNames(Arrays.asList(JsamsI18nResource.COLUMN_ESTIMATE_ID, JsamsI18nResource.COLUMN_PRODUCT_ID,
                JsamsI18nResource.COLUMN_PRODUCT_NAME, JsamsI18nResource.COLUMN_QUANTITY,
                JsamsI18nResource.COLUMN_PRICE, JsamsI18nResource.COLUMN_DISCOUNT_RATE,
                JsamsI18nResource.COLUMN_VAT_APPLICABLE));
    }

    /**
     * {@inheritDoc}
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        EstimateDetailBean detail = (EstimateDetailBean) getRow(rowIndex);
        ProductBean product = detail.getProduct();
        switch (columnIndex) {
        case ZERO:
            return detail.getEstimate().getId();
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
        case ONE:
            return Long.class;
        case TWO:
            return String.class;
        case THREE:
            return Integer.class;
        case FOUR:
        case FIVE:
        case SIX:
            return Double.class;
        default:
            return Object.class;
        }
    }

}
