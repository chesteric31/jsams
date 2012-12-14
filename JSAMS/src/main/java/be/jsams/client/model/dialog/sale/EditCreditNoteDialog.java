package be.jsams.client.model.dialog.sale;

import javax.swing.JPanel;

import be.jsams.client.context.ApplicationContext;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.model.dialog.AbstractEditDialog;
import be.jsams.client.validator.edit.sale.EditCreditNoteValidator;
import be.jsams.common.bean.model.sale.CreditNoteBean;
import be.jsams.common.bean.view.sale.CreditNoteBeanView;
import be.jsams.server.service.sale.CreditNoteService;

/**
 * Edit Credit Note {@link AbstractEditDialog}, to create or update a
 * {@link CreditNoteBean} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EditCreditNoteDialog extends
        AbstractEditDialog<CreditNoteBean, EditCreditNoteValidator, CreditNoteService> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 7339240296875394194L;

    /**
     * Constructor.
     * 
     * @param title the {@link I18nString} title
     * @param model the {@link CreditNoteBean} model
     */
    public EditCreditNoteDialog(final I18nString title, CreditNoteBean model) {
        super(null, title, model, new EditCreditNoteValidator(), ApplicationContext.getCreditNoteService());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveOriginalModel() {
        setOriginalModel(new CreditNoteBean(getModel().getSociety(), getModel().getCustomer()));
        getOriginalModel().refresh(getModel());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel initComponents() {
        CreditNoteBeanView view = getModel().buildView();
        return view.createEditView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performOk() {
        CreditNoteBean model = getModel();
        super.postPerformOk(model);
    }

}
