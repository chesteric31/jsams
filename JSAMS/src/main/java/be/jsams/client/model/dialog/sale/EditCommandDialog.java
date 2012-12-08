package be.jsams.client.model.dialog.sale;

import javax.swing.JPanel;

import be.jsams.client.context.ApplicationContext;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.model.dialog.AbstractEditDialog;
import be.jsams.client.validator.edit.sale.EditCommandValidator;
import be.jsams.common.bean.model.sale.CommandBean;
import be.jsams.common.bean.view.sale.CommandBeanView;
import be.jsams.server.service.sale.CommandService;

import com.jgoodies.validation.view.ValidationComponentUtils;

/**
 * Edit Command {@link AbstractEditDialog}, to create or update a
 * {@link CommandBean} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EditCommandDialog extends AbstractEditDialog<CommandBean, EditCommandValidator, CommandService> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -5020990836528415188L;

    /**
     * Constructor.
     * 
     * @param title the {@link I18nString} title
     * @param model the {@link CommandBean} model
     */
    public EditCommandDialog(final I18nString title, CommandBean model) {
        super(null, title, model, new EditCommandValidator(), ApplicationContext.getCommandService());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initComponents() {
        CommandBean originalModel = new CommandBean(getModel().getSociety(), getModel().getCustomer(), getModel()
                .getAgent());
        setOriginalModel(originalModel);
        getOriginalModel().refresh(getModel());
        CommandBeanView view = getModel().buildView();
        JPanel panel = view.createEditView();
        getModel().setView(view);
        getContentPane().add(panel);
        ValidationComponentUtils.updateComponentTreeMandatoryBorder(this);
        pack();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performOk() {
        CommandBean command = getModel();
        super.postPerformOk(command);
    }

}
