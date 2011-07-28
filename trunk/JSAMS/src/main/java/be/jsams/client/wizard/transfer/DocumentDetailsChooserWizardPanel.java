package be.jsams.client.wizard.transfer;

import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.validator.wizard.DocumentDetailsValidator;
import be.jsams.client.wizard.JsamsWizardComponent;
import be.jsams.client.wizard.JsamsWizardPanel;
import be.jsams.common.bean.model.transfer.TransferBean;

/**
 * {@link JsamsWizardPanel} to choose the document details to transfer. 
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class DocumentDetailsChooserWizardPanel extends JsamsWizardPanel<TransferBean, DocumentDetailsValidator> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 643645734661314291L;

    /**
     * Constructor.
     * 
     * @param parent the {@link TransferWizardDialog} parent
     * @param component the {@link JsamsWizardComponent}
     * @param model the model
     * @param validator the {@link DocumentDetailsValidator} 
     */
    public DocumentDetailsChooserWizardPanel(TransferWizardDialog parent, JsamsWizardComponent component,
            TransferBean model, DocumentDetailsValidator validator) {
        super(parent, component, model, JsamsI18nLabelResource.LABEL_TRANSFER_CHOOSE_DOCUMENT_DETAILS, validator);
        initComponents();
    }

    /**
     * Initialize the components.
     */
    private void initComponents() {
    }

    /**
     * {@inheritDoc}
     */
    public void update() {
//        boolean nextEnabled = false;
//        if (isRadioSelected()) {
//            nextEnabled = true;
//        }
//        setNextButtonEnabled(nextEnabled);
//        boolean finishEnabled = selectedOption == finishSelected;
//        setFinishButtonEnabled(finishEnabled);
//        setBackButtonEnabled(true);
    }

    /**
     * {@inheritDoc}
     */
    public void next() {
    }

    /**
     * {@inheritDoc}
     */
    public void back() {
    }

}
