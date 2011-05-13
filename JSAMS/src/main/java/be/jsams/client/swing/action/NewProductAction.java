package be.jsams.client.swing.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.dialog.management.EditProductDialog;
import be.jsams.common.bean.builder.ProductBeanBuilder;

/**
 * {@link AbstractAction} to launch {@link EditProductDialog}.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class NewProductAction extends AbstractAction {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 3836214162446560188L;

    /**
     * {@inheritDoc}
     */
    public void actionPerformed(ActionEvent e) {
        ProductBeanBuilder builder = new ProductBeanBuilder();
        builder.setSociety(JsamsDesktop.getInstance().getCurrentSociety());
        new EditProductDialog(JsamsI18nResource.TITLE_EDIT_PRODUCT, builder.build(true, false));
    }

}
