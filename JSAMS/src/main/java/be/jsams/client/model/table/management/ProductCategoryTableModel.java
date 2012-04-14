package be.jsams.client.model.table.management;

import java.util.Arrays;
import java.util.List;

import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.table.AbstractJsamsTableModel;
import be.jsams.common.bean.model.management.ProductCategoryBean;

/**
 * {@link AbstractJsamsTableModel} for {@link ProductCategoryBean} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class ProductCategoryTableModel extends AbstractJsamsTableModel<ProductCategoryBean> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -7016107019455922155L;

    /**
     * Default constructor.
     */
    public ProductCategoryTableModel() {
        super();
    }
    
    /**
     * Constructor.
     * 
     * @param listModel a list of {@link ProductCategoryBean}
     */
    public ProductCategoryTableModel(List<ProductCategoryBean> listModel) {
        super(listModel);
        setColumnNames(Arrays.asList(JsamsI18nResource.COLUMN_ID, JsamsI18nResource.COLUMN_LABEL_EN,
                JsamsI18nResource.COLUMN_LABEL_FR, JsamsI18nResource.COLUMN_LABEL_NL));
    }

    /**
     * {@inheritDoc}
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
     * {@inheritDoc}
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
        case ZERO:
            return Long.class;
        case ONE:
        case TWO:
        case THREE:
            return String.class;
        default:
            return Object.class;
        }
    }
}
