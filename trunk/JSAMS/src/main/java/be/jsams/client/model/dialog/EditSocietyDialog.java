package be.jsams.client.model.dialog;

import javax.swing.JPanel;

import be.jsams.client.context.ApplicationContext;
import be.jsams.client.desktop.Desktop;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.validator.edit.EditSocietyValidator;
import be.jsams.common.bean.model.LegalFormBean;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.view.SocietyBeanView;
import be.jsams.server.service.SocietyService;

import com.jgoodies.validation.view.ValidationComponentUtils;

/**
 * Edit society {@link AbstractEditDialog}, to create or update a
 * {@link SocietyBean} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EditSocietyDialog extends AbstractEditDialog<SocietyBean, EditSocietyValidator, SocietyService> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 4225744372592399187L;

    private boolean success = false;

    /**
     * Constructor.
     * 
     * @param title the {@link I18nString} title
     * @param model the {@link SocietyBean} model
     */
    public EditSocietyDialog(final I18nString title, SocietyBean model) {
        super(null, title, model, new EditSocietyValidator(), ApplicationContext.getSocietyService());
    }

    /**
     * {@inheritDoc}
     */
    public void initComponents() {
        setOriginalModel(ApplicationContext.getSocietyBeanBuilder().build(false));
        getOriginalModel().refresh(getModel());
        SocietyBeanView view = getModel().buildView();
        JPanel panel = view.createEditView();
        getContentPane().add(panel);
        ValidationComponentUtils.updateComponentTreeMandatoryBorder(this);
        pack();
    }

    /**
     * {@inheritDoc}
     */
    public void performOk() {
        SocietyBean society = getModel();
        // set to the object the selection
        LegalFormBean legalForm = (LegalFormBean) society.getLegalForm().getSelection();
        if (legalForm != null) {
            society.getLegalForm().refresh(legalForm);
        }
        SocietyBean persistedSociety = postPerformOk(society);
        if (persistedSociety != null) {
            Desktop.getInstance().setCurrentSociety(persistedSociety);
            success = true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performCancel() {
        super.performCancel();
        success = false;
    }

    /**
     * @return the success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * @param success the success to set
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

}
