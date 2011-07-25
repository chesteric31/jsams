package be.jsams.client.wizard.transfer;

import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.wizard.JsamsWizardComponent;
import be.jsams.client.wizard.JsamsWizardPanel;
import be.jsams.common.bean.model.transfer.TransferBean;

/**
 * {@link JsamsWizardPanel} to choose the document details to transfer. 
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class DocumentDetailsChooserWizardPanel extends JsamsWizardPanel<TransferBean> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 643645734661314291L;

    /**
     * Constructor.
     * 
     * @param component the {@link JsamsWizardComponent}
     * @param model the model
     */
    public DocumentDetailsChooserWizardPanel(JsamsWizardComponent component, TransferBean model) {
        super(component, model, JsamsI18nLabelResource.LABEL_TRANSFER_CHOOSE_DOCUMENT_DETAILS);
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
