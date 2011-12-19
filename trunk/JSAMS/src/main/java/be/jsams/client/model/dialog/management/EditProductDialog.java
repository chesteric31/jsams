package be.jsams.client.model.dialog.management;

import javax.swing.JPanel;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.model.dialog.AbstractEditDialog;
import be.jsams.client.validator.edit.EditProductValidator;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.common.bean.model.management.ProductCategoryBean;
import be.jsams.common.bean.view.management.ProductBeanView;
import be.jsams.server.service.management.ProductService;

import com.jgoodies.validation.view.ValidationComponentUtils;

/**
 * Edit Product {@link AbstractEditDialog}, to create or update a Product
 * object.
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
     * @param title the {@link I18nString} title
     * @param model the {@link ProductBean} model
     */
    public EditProductDialog(final I18nString title, ProductBean model) {
        super(null, title, model, new EditProductValidator(), JsamsApplicationContext.getProductService());
    }

    /**
     * {@inheritDoc}
     */
    public void initComponents() {
        setOriginalModel(new ProductBean());
        getOriginalModel().refresh(getModel());
        ProductBeanView view = getModel().buildView();
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
        // FIXME: workaround
        SocietyBean society = product.getCategory().getSociety();
        ProductCategoryBean selection = (ProductCategoryBean) product.getCategory().getSelection();
        if (selection != null) {
            selection.setSociety(society);
        }
        product.setCategory(selection);
        super.postPerformOk(product);
    }

}
