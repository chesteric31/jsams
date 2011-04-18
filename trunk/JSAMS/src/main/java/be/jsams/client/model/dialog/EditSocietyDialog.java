package be.jsams.client.model.dialog;

import javax.swing.JPanel;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.validator.EditSocietyValidator;
import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.view.LegalFormBeanView;
import be.jsams.common.bean.view.SocietyBeanView;
import be.jsams.server.model.LegalForm;
import be.jsams.server.service.SocietyService;

import com.jgoodies.validation.view.ValidationComponentUtils;

/**
 * Edit society {@link AbstractEditDialog}, to create or update a {@link SocietyBean} object.
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
     * Constructor
     * 
     * @param title
     *            the {@link I18nString} title
     * @param model
     *            the {@link SocietyBean} model
     */
    public EditSocietyDialog(final I18nString title, SocietyBean model) {
        super(null, title);
        super.setModel(model);
        super.setValidator(new EditSocietyValidator());
        super.setService(JsamsApplicationContext.getSocietyService());
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    protected void initComponents() {
        SocietyBeanView view = getModel().getView();
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
        AbstractIdentityBean<LegalForm, LegalFormBeanView> legalForm = society.getLegalForm().getSelection();
        if (legalForm != null) {
            society.getLegalForm().refresh(legalForm);
//            society.setLegalForm((LegalFormBean) legalForm);
        }
        SocietyBean persistedSociety = postPerformOk(society);
        if (persistedSociety != null) {
            JsamsDesktop.getInstance().setCurrentSociety(persistedSociety);
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
     * @param success
     *            the success to set
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

}
