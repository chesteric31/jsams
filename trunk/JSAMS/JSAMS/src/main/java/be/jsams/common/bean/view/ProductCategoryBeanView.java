package be.jsams.common.bean.view;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.renderer.TranslatableComboBoxRenderer;
import be.jsams.client.swing.component.JsamsFrame;
import be.jsams.common.bean.model.ProductCategoryBean;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

/**
 * 
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class ProductCategoryBeanView extends AbstractView<ProductCategoryBean, JComboBox, JPanel> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 6523573947459795801L;

    /**
     * Constructor
     * 
     * @param bean
     *            the {@link ProductCategoryBean}
     */
    public ProductCategoryBeanView(ProductCategoryBean bean) {
        super(bean);
    }

    /**
     * {@inheritDoc}
     */
    public JComboBox createEditView() {
        ViewFactory<ProductCategoryBean> helper = new ViewFactory<ProductCategoryBean>();
        JComboBox comboBox = helper.createBindingComboComponent(getBean(), true, false,
                new TranslatableComboBoxRenderer());
        return comboBox;
    }

    /**
     * {@inheritDoc}
     */
    public JPanel createSearchView() {
        ProductCategoryBean bean = getBean();
        ViewFactory<ProductCategoryBean> helper = new ViewFactory<ProductCategoryBean>();
        JTextField textFieldLabel = helper.createBindingTextComponent(bean, ProductCategoryBean.LABEL_PROPERTY, false,
                false);
        JTextField textFieldLabelFr = helper.createBindingTextComponent(bean, ProductCategoryBean.LABEL_FR_PROPERTY,
                false, false);
        JTextField textFieldLabelNl = helper.createBindingTextComponent(bean, ProductCategoryBean.LABEL_NL_PROPERTY,
                false, false);
        FormLayout layout = new FormLayout("right:p, 3dlu, p:grow", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, JsamsFrame.RESOURCE_BUNDLE);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PRODUCT_CATEGORY_EN.getKey(), textFieldLabel);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PRODUCT_CATEGORY_FR.getKey(), textFieldLabelFr);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PRODUCT_CATEGORY_NL.getKey(), textFieldLabelNl);
        builder.nextLine();
        return builder.getPanel();
    }

}
