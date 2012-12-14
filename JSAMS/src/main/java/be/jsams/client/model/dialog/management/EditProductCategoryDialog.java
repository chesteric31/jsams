package be.jsams.client.model.dialog.management;

import javax.swing.JPanel;

import be.jsams.client.context.ApplicationContext;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.model.dialog.AbstractEditDialog;
import be.jsams.client.validator.edit.management.EditProductCategoryValidator;
import be.jsams.common.bean.model.management.ProductCategoryBean;
import be.jsams.common.bean.view.management.ProductCategoryBeanView;
import be.jsams.server.service.management.ProductCategoryService;

/**
 * Edit Product category {@link AbstractEditDialog}, to create or update a
 * Product category object.
 * 
 * @author chesteric31
 * @version $$Rev$$ $$Date::                  $$ $$Author$$
 */
public class EditProductCategoryDialog extends
        AbstractEditDialog<ProductCategoryBean, EditProductCategoryValidator, ProductCategoryService> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -6175694767018019085L;

    /**
     * Constructor.
     * 
     * @param title the {@link I18nString} title
     * @param model the {@link ProductCategoryBean} model
     */
    public EditProductCategoryDialog(final I18nString title, ProductCategoryBean model) {
        super(null, title, model, new EditProductCategoryValidator(), ApplicationContext.getProductCategoryService());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveOriginalModel() {
        setOriginalModel(new ProductCategoryBean());
        getOriginalModel().refresh(getModel());
    }

    /**
     * {@inheritDoc}
     */
    public JPanel initComponents() {
        ProductCategoryBeanView view = getModel().buildView();
        return view.createEditView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performOk() {
        ProductCategoryBean category = getModel();
        super.postPerformOk(category);
    }

}
