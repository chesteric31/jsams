package be.jsams.client.model.dialog;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.swing.component.JsamsFrame;
import be.jsams.client.swing.component.JsamsTextField;
import be.jsams.client.validator.ProductCategoryValidator;
import be.jsams.common.bean.model.ProductCategoryBean;
import be.jsams.server.service.ProductCategoryService;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.validation.view.ValidationComponentUtils;

/**
 * Edit Product category {@link AbstractEditDialog}, to create or update a Product category object.
 * 
 * @author chesteric31
 * @version $$Rev$$ $$Date::                  $$ $$Author$$
 */
public class EditProductCategoryDialog extends
        AbstractEditDialog<ProductCategoryBean, ProductCategoryValidator, ProductCategoryService> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -6175694767018019085L;

    private static final int MAX_CHARACTERS = 50;

    private JsamsTextField textFieldLabel = new JsamsTextField(MAX_CHARACTERS);
    private JsamsTextField textFieldLabelFr = new JsamsTextField(MAX_CHARACTERS);
    private JsamsTextField textFieldLabelNl = new JsamsTextField(MAX_CHARACTERS);

    /**
     * Constructor
     * 
     * @param title
     *            the {@link I18nString} title
     * @param model
     *            the {@link ProductCategoryBean} model
     */
    public EditProductCategoryDialog(final I18nString title, ProductCategoryBean model) {
        super(null, title);
        super.setModel(model);
        super.setValidator(new ProductCategoryValidator());
        super.setService(JsamsApplicationContext.getProductCategoryService());
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    protected void initComponents() {
        fillData();
        FormLayout layout = new FormLayout("right:p, 3dlu, p:grow, 3dlu", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, JsamsFrame.RESOURCE_BUNDLE);
        builder.setDefaultDialogBorder();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PRODUCT_CATEGORY_EN.getKey(), textFieldLabel);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PRODUCT_CATEGORY_FR.getKey(), textFieldLabelFr);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PRODUCT_CATEGORY_NL.getKey(), textFieldLabelNl);

        setMandatoryFields();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(builder.getPanel(), BorderLayout.CENTER);
        add(mainPanel);
        ValidationComponentUtils.updateComponentTreeMandatoryBorder(this);

        pack();
    }

    /**
     * Sets all mandatory fields.
     */
    private void setMandatoryFields() {
        ValidationComponentUtils.setMandatory(textFieldLabel, true);
        ValidationComponentUtils.setMandatory(textFieldLabelFr, true);
        ValidationComponentUtils.setMandatory(textFieldLabelNl, true);
    }

    /**
     * Fills all the data in case of update.
     */
    private void fillData() {
        if (getModel() != null) {
            textFieldLabel.setText(getModel().getLabel());
            textFieldLabelFr.setText(getModel().getLabelFr());
            textFieldLabelNl.setText(getModel().getLabelNl());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performOk() {
        ProductCategoryBean category = getModel();
        category.setLabel(textFieldLabel.getText());
        category.setLabelFr(textFieldLabelFr.getText());
        category.setLabelNl(textFieldLabelNl.getText());
        super.postPerformOk(category);
    }

}
