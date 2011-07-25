package be.jsams.common.bean.view.management;

import javax.swing.JPanel;

import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.renderer.TranslatableComboBoxRenderer;
import be.jsams.client.swing.component.AbstractJsamsFrame;
import be.jsams.client.swing.component.JsamsComboBox;
import be.jsams.client.swing.component.JsamsTextField;
import be.jsams.common.bean.model.management.ProductCategoryBean;
import be.jsams.common.bean.view.AbstractBeanView;
import be.jsams.common.bean.view.Editable;
import be.jsams.common.bean.view.Searchable;
import be.jsams.common.bean.view.ViewFactory;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Customized views for {@link ProductCategoryBean}.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class ProductCategoryBeanView extends AbstractBeanView<ProductCategoryBean> implements Editable<JPanel>,
        Searchable<JPanel> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 6523573947459795801L;

    /**
     * Constructor
     * 
     * @param bean the {@link ProductCategoryBean}
     */
    public ProductCategoryBeanView(ProductCategoryBean bean) {
        super(bean);
    }

    /**
     * Creates custom view {@link JsamsComboBox}.
     * 
     * @return the {@link JsamsComboBox} view
     */
    public JsamsComboBox createCustomView() {
        ViewFactory<ProductCategoryBean> viewFactory = getViewFactory();
        JsamsComboBox comboBox = viewFactory.createBindingComboComponent(getBean(), true, false,
                new TranslatableComboBoxRenderer());
        return comboBox;
    }

    /**
     * {@inheritDoc}
     */
    public JPanel createSearchView() {
        ProductCategoryBean bean = getBean();
        ViewFactory<ProductCategoryBean> helper = new ViewFactory<ProductCategoryBean>();
        JsamsTextField textFieldLabel = helper.createBindingTextComponent(bean, ProductCategoryBean.LABEL_PROPERTY,
                false, false);
        JsamsTextField textFieldLabelFr = helper.createBindingTextComponent(bean,
                ProductCategoryBean.LABEL_FR_PROPERTY, false, false);
        JsamsTextField textFieldLabelNl = helper.createBindingTextComponent(bean,
                ProductCategoryBean.LABEL_NL_PROPERTY, false, false);
        FormLayout layout = new FormLayout("right:p, 3dlu, p:grow", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, AbstractJsamsFrame.RESOURCE_BUNDLE);
        builder.setDefaultDialogBorder();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PRODUCT_CATEGORY_EN.getKey(), textFieldLabel);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PRODUCT_CATEGORY_FR.getKey(), textFieldLabelFr);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PRODUCT_CATEGORY_NL.getKey(), textFieldLabelNl);
        builder.nextLine();
        return builder.getPanel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel createEditView() {
        ProductCategoryBean bean = getBean();
        ViewFactory<ProductCategoryBean> helper = new ViewFactory<ProductCategoryBean>();
        JsamsTextField textFieldLabel = helper.createBindingTextComponent(bean, ProductCategoryBean.LABEL_PROPERTY,
                true, false);
        JsamsTextField textFieldLabelFr = helper.createBindingTextComponent(bean,
                ProductCategoryBean.LABEL_FR_PROPERTY, true, false);
        JsamsTextField textFieldLabelNl = helper.createBindingTextComponent(bean,
                ProductCategoryBean.LABEL_NL_PROPERTY, true, false);
        FormLayout layout = new FormLayout("right:p, 3dlu, p:grow, 3dlu", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, AbstractJsamsFrame.RESOURCE_BUNDLE);
        builder.setDefaultDialogBorder();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PRODUCT_CATEGORY_EN.getKey(), textFieldLabel);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PRODUCT_CATEGORY_FR.getKey(), textFieldLabelFr);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PRODUCT_CATEGORY_NL.getKey(), textFieldLabelNl);
        return builder.getPanel();
    }

}
