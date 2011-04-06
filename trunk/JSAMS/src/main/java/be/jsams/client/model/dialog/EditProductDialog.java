package be.jsams.client.model.dialog;

import javax.swing.JPanel;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.validator.EditProductValidator;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.common.bean.model.management.ProductCategoryBean;
import be.jsams.common.bean.view.ProductBeanView;
import be.jsams.server.service.ProductService;

import com.jgoodies.validation.view.ValidationComponentUtils;

/**
 * Edit Product {@link AbstractEditDialog}, to create or update a Product object.
 * 
 * @author chesteric31
 * @version $$Rev$$ $$Date::                  $$ $$Author$$
 */
public class EditProductDialog extends AbstractEditDialog<ProductBean, EditProductValidator, ProductService> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -5931469580616365674L;

    /**
     * Constructor
     * 
     * @param title
     *            the {@link I18nString} title
     * @param model
     *            the {@link ProductBean} model
     */
    public EditProductDialog(final I18nString title, ProductBean model) {
        super(null, title, "apps/preferences-desktop-theme.png");
        super.setModel(model);
        super.setValidator(new EditProductValidator());
        super.setService(JsamsApplicationContext.getProductService());
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    protected void initComponents() {
        ProductBeanView view = getModel().getView();
        JPanel panel = view.createEditView();
        getContentPane().add(panel);
        ValidationComponentUtils.updateComponentTreeMandatoryBorder(this);
        pack();
    }

    /**
     * {@inheritDoc}
     */
    public void performOk() {
        ProductBean product = getModel();
        product.setCategory((ProductCategoryBean) product.getCategory().getSelection());
        super.postPerformOk(product);
    }

}
