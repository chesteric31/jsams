package be.jsams.client.wizard.transfer;

import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.validator.wizard.DocumentsValidator;
import be.jsams.client.wizard.JsamsWizardComponent;
import be.jsams.client.wizard.JsamsWizardPanel;
import be.jsams.common.bean.model.transfer.TransferBean;

/**
 * {@link JsamsWizardPanel} to choose the documents to transfer.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class DocumentsChooserWizardPanel extends JsamsWizardPanel<TransferBean, DocumentsValidator> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 1346822758102759358L;

    /**
     * Constructor.
     * 
     * @param parent the {@link TransferWizardDialog} parent
     * @param component the {@link JsamsWizardComponent}
     * @param model the model
     * @param validator the {@link DocumentsValidator}
     */
    public DocumentsChooserWizardPanel(TransferWizardDialog parent,
            JsamsWizardComponent component, TransferBean model, DocumentsValidator validator) {
        super(parent, component, model, JsamsI18nLabelResource.LABEL_TRANSFER_CHOOSE_DOCUMENTS, validator);
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
        switchPanel(TransferWizardDialog.THIRD_PANEL);
    }

}
