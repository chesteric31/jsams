package be.jsams.common.bean.view;

import javax.swing.JPanel;

import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.swing.component.JsamsFormattedTextField;
import be.jsams.client.swing.component.JsamsFrame;
import be.jsams.client.swing.component.JsamsTextField;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.management.ProductBean;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

/**
 * 
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class ProductBeanView extends AbstractView<ProductBean, JPanel, JPanel> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -6271701607970284767L;

    /**
     * Constructor
     * 
     * @param bean
     *            the {@link ProductBean}
     */
    public ProductBeanView(ProductBean bean) {
        super(bean);
    }

    /**
     * {@inheritDoc}
     */
    public JPanel createEditView() {
        ProductBean bean = getBean();
        ViewFactory<ProductBean> helper = new ViewFactory<ProductBean>();
        JsamsTextField textFieldName = helper.createBindingTextComponent(bean, ProductBean.NAME_PROPERTY, true, false);
        JsamsFormattedTextField textFieldPrice = helper.createBindingDecimalComponent(bean, ProductBean.PRICE_PROPERTY,
                true, false);
        JsamsFormattedTextField textFieldStockQuantity = helper.createBindingIntComponent(bean,
                ProductBean.QUANTITYSTOCK_PROPERTY, true, false);
        JsamsFormattedTextField textFieldReorderLevel = helper.createBindingIntComponent(bean,
                ProductBean.REORDERLEVEL_PROPERTY, false, false);
        JsamsFormattedTextField textFieldVatApplicable = helper.createBindingDecimalComponent(bean,
                CustomerBean.VATAPPLICABLE_PROPERTY, true, false);
        FormLayout layout = new FormLayout("right:p, 3dlu, 50dlu, 3dlu, right:p, 3dlu, 50dlu", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, JsamsFrame.RESOURCE_BUNDLE);
        final int maxColumnSpan = 5;
        builder.setDefaultDialogBorder();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PRODUCT_LABEL.getKey(), textFieldName, maxColumnSpan);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PRODUCT_CATEGORY.getKey(), bean.getCategory().getView()
                .createCustomView(), maxColumnSpan);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PRODUCT_PRICE.getKey(), textFieldPrice);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PRODUCT_STOCK_QUANTITY.getKey(), textFieldStockQuantity);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PRODUCT_REORDER_LEVEL.getKey(), textFieldReorderLevel);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PRODUCT_VAT_APPLICABLE.getKey(), textFieldVatApplicable);

        return builder.getPanel();
    }

    /**
     * {@inheritDoc}
     */
    public JPanel createSearchView() {
        ProductBean bean = getBean();
        ViewFactory<ProductBean> helper = new ViewFactory<ProductBean>();
        JsamsTextField textFieldName = helper.createBindingTextComponent(bean, ProductBean.NAME_PROPERTY, false, false);
        JsamsFormattedTextField textFieldPrice = helper.createBindingDecimalComponent(bean, ProductBean.PRICE_PROPERTY,
                false, false);
        JsamsFormattedTextField textFieldStockQuantity = helper.createBindingIntComponent(bean,
                ProductBean.QUANTITYSTOCK_PROPERTY, false, false);
        JsamsFormattedTextField textFieldReorderLevel = helper.createBindingIntComponent(bean,
                ProductBean.REORDERLEVEL_PROPERTY, false, false);
        JsamsFormattedTextField textFieldVatApplicable = helper.createBindingDecimalComponent(bean,
                ProductBean.VATAPPLICABLE_PROPERTY, false, false);
        FormLayout layout = new FormLayout("right:p, 3dlu, p:grow, 3dlu, " + "right:p, 3dlu, p:grow, 3dlu, "
                + "right:p, 3dlu, p:grow, 3dlu, " + "right:p, 3dlu, p:grow", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, JsamsFrame.RESOURCE_BUNDLE);
        builder.setDefaultDialogBorder();
        final int maxColumnSpan = 5;
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PRODUCT_LABEL.getKey(), textFieldName, maxColumnSpan);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PRODUCT_CATEGORY.getKey(), bean.getCategory().getView()
                .createCustomView(), maxColumnSpan);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PRODUCT_PRICE.getKey(), textFieldPrice);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PRODUCT_STOCK_QUANTITY.getKey(), textFieldStockQuantity);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PRODUCT_REORDER_LEVEL.getKey(), textFieldReorderLevel);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PRODUCT_VAT_APPLICABLE.getKey(), textFieldVatApplicable);

        return builder.getPanel();
    }

}
