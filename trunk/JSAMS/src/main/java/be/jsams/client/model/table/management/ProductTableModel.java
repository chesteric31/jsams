package be.jsams.client.model.table.management;

import java.util.Arrays;
import java.util.List;

import be.jsams.client.i18n.I18nResource;
import be.jsams.client.i18n.UserContext;
import be.jsams.client.model.table.AbstractJsamsTableModel;
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
     * Constructor.
     * 
     * @param listModel a list of {@link ProductBean}
     */
    public ProductTableModel(List<ProductBean> listModel) {
        super(listModel);
        setColumnNames(Arrays.asList(I18nResource.COLUMN_ID, I18nResource.COLUMN_NAME,
                I18nResource.COLUMN_PRICE, I18nResource.COLUMN_STOCK_QUANTITY,
                I18nResource.COLUMN_REORDER_LEVEL, I18nResource.COLUMN_VAT_APPLICABLE,
                I18nResource.COLUMN_PRODUCT_CATEGORY));
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
        case THREE:
        case FOUR:
            return Integer.class;
        case TWO:
        case FIVE:
            return Double.class;
        case ONE:
        case SIX:
            return String.class;
        default:
            return Object.class;
        }
    }

}
