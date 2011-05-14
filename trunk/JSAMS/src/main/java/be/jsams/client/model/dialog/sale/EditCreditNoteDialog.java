package be.jsams.client.model.dialog.sale;

import javax.swing.JPanel;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.model.dialog.AbstractEditDialog;
import be.jsams.client.validator.EditCreditNoteValidator;
import be.jsams.common.bean.model.sale.CreditNoteBean;
import be.jsams.common.bean.view.sale.CreditNoteBeanView;
import be.jsams.server.service.sale.CreditNoteService;

import com.jgoodies.validation.view.ValidationComponentUtils;

/**
 * Edit Command {@link AbstractEditDialog}, to create or update a
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
     * Constructor
     * 
     * @param title the {@link I18nString} title
     * @param model the {@link CreditNoteBean} model
     */
    public EditCreditNoteDialog(final I18nString title, CreditNoteBean model) {
        super(null, title);
        super.setModel(model);
        super.setValidator(new EditCreditNoteValidator());
        super.setService(JsamsApplicationContext.getCreditNoteService());
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initComponents() {
        CreditNoteBeanView view = getModel().getView();
        JPanel panel = view.createEditView();
        getContentPane().add(panel);
        ValidationComponentUtils.updateComponentTreeMandatoryBorder(this);
        pack();
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
