package be.jsams.client.model.table;

import java.util.Arrays;
import java.util.List;

import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.i18n.UserContext;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.common.bean.model.management.ProductCategoryBean;

/**
 * {@link AbstractJsamsTableModel} for {@link ProductBean} object.
 * 
 * @author chesteric31
 * @version $$Rev$$ $$Date::                  $$ $$Author$$
 */
public class ProductTableModel extends AbstractJsamsTableModel<ProductBean> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 5631609209979319706L;

    /**
     * Default constructor.
     */
    public ProductTableModel() {
        super();
    }

    /**
     * Constructor
     * 
     * @param listModel a list of {@link ProductBean}
     */
    public ProductTableModel(List<ProductBean> listModel) {
        super(listModel);
        setColumnNames(Arrays.asList(JsamsI18nResource.COLUMN_ID, JsamsI18nResource.COLUMN_NAME,
                JsamsI18nResource.COLUMN_PRICE, JsamsI18nResource.COLUMN_STOCK_QUANTITY,
                JsamsI18nResource.COLUMN_REORDER_LEVEL, JsamsI18nResource.COLUMN_VAT_APPLICABE,
                JsamsI18nResource.COLUMN_PRODUCT_CATEGORY));
    }

    /**
     * {@inheritDoc}
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        ProductBean product = (ProductBean) getRow(rowIndex);
        switch (columnIndex) {
        case ZERO:
            return product.getId();
        case ONE:
            return product.getName();
        case TWO:
            return product.getPrice();
        case THREE:
            return product.getQuantityStock();
        case FOUR:
            return product.getReorderLevel();
        case FIVE:
            return product.getVatApplicable().doubleValue();
        case SIX:
            ProductCategoryBean category = product.getCategory();
            if (UserContext.isDutch()) {
                return category.getLabelNl();
            } else if (UserContext.isFrench()) {
                return category.getLabelFr();
            } else {
                return category.getLabel();
            }
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
            return Double.class;
        case THREE:
            return Integer.class;
        case FOUR:
            return Integer.class;
        case FIVE:
            return Double.class;
        case SIX:
            return String.class;
        default:
            return Object.class;
        }
    }

}
