package be.jsams.client.wizard.transfer;

import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.wizard.JsamsWizardComponent;
import be.jsams.client.wizard.JsamsWizardPanel;

/**
 * {@link JsamsWizardPanel} to choose the documents to transfer.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class DocumentsChooserWizardPanel extends JsamsWizardPanel {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 1346822758102759358L;

    /**
     * Constructor.
     * 
     * @param component the {@link JsamsWizardComponent}
     */
    public DocumentsChooserWizardPanel(JsamsWizardComponent component) {
        super(component, JsamsI18nLabelResource.LABEL_TRANSFER_CHOOSE_DOCUMENTS);
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
