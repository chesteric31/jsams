package be.jsams.client.wizard.transfer;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.model.dialog.AbstractWizardDialog;
import be.jsams.client.validator.wizard.SummaryTransferValidator;
import be.jsams.client.wizard.JsamsWizardComponent;
import be.jsams.client.wizard.JsamsWizardPanel;
import be.jsams.common.bean.model.transfer.TransferBean;

/**
 * 
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class SummaryTransferWizardPanel extends JsamsWizardPanel<TransferBean, SummaryTransferValidator> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -8814602138117475739L;

    /**
     * Constructor.
     * 
     * @param parent the {@link TransferWizardDialog} parent
     * @param component the {@link JsamsWizardComponent}
     * @param model the model
     * @param validator the {@link SummaryTransferValidator}
     */
    public SummaryTransferWizardPanel(AbstractWizardDialog<?, ?> parent, JsamsWizardComponent component,
            TransferBean model, SummaryTransferValidator validator) {
        super(parent, component, model, JsamsI18nLabelResource.LABEL_TRANSFER_SUMMARY, validator);
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
        setNextButtonEnabled(false);
        setFinishButtonEnabled(true);
        setBackButtonEnabled(true);
        updateContainer();
        super.update();
    }

    /**
     * Update the panel container. 
     */
    private void updateContainer() {
        JTextArea area = new JTextArea(5, 20);
        area.append(JsamsI18nLabelResource.LABEL_SOURCE_TYPE.getTranslation());
        String doublePointSeparator = " : ";
        area.append(doublePointSeparator);
        String newLine = "\n";
        area.append(getSourceTypeTranslation(getModel().getSourceType()) + newLine);

        area.append(JsamsI18nLabelResource.LABEL_DESTINATION_TYPE.getTranslation());
        area.append(doublePointSeparator);
        area.append(getDestinationTypeTranslation(getModel().getDestinationType()) + newLine);

        area.append(JsamsI18nLabelResource.LABEL_TRANSFER_MODE.getTranslation());
        area.append(doublePointSeparator);
        area.append(getTransferModeTranslation(getModel().getTransferMode()) + newLine);
        
        JScrollPane scrollPane = new JScrollPane(area); 
        area.setEditable(false);
        this.add(scrollPane);
    }

    /**
     * Retrieve the source type translation.
     * 
     * @param sourceType the source type
     * @return the translation of the source type
     */
    private String getSourceTypeTranslation(int sourceType) {
        String sourceTypeTranslation = "";
        switch (sourceType) {
        case 1:
            sourceTypeTranslation = JsamsI18nLabelResource.LABEL_ESTIMATE.getTranslation();
            break;
        case 2:
            sourceTypeTranslation = JsamsI18nLabelResource.LABEL_COMMAND.getTranslation();
            break;
        case 3:
            sourceTypeTranslation = JsamsI18nLabelResource.LABEL_DELIVERY_ORDER.getTranslation();
            break;
        case 4:
            sourceTypeTranslation = JsamsI18nLabelResource.LABEL_BILL.getTranslation();
            break;
        default:
            break;
        }
        return sourceTypeTranslation;
    }

    /**
     * Retrieve the destination type translation.
     * 
     * @param destinationType the destination type
     * @return the translation of the destination type
     */
    private String getDestinationTypeTranslation(int destinationType) {
        String destinationTypeTranslation = "";
        switch (destinationType) {
        case 1:
            destinationTypeTranslation = JsamsI18nLabelResource.LABEL_COMMAND.getTranslation();
            break;
        case 2:
            destinationTypeTranslation = JsamsI18nLabelResource.LABEL_DELIVERY_ORDER.getTranslation();
            break;
        case 3:
            destinationTypeTranslation = JsamsI18nLabelResource.LABEL_BILL.getTranslation();
            break;
        case 4:
            destinationTypeTranslation = JsamsI18nLabelResource.LABEL_CREDIT_NOTE.getTranslation();
            break;
        default:
            break;
        }
        return destinationTypeTranslation;
    }

    /**
     * Retrieve the transfer mode translation.
     * 
     * @param transferMode the transfer mode
     * @return the translation of the transfer mode
     */
    private String getTransferModeTranslation(int transferMode) {
        String transferModeTranslation = "";
        switch (transferMode) {
        case 1:
            transferModeTranslation = JsamsI18nLabelResource.LABEL_FULL_TRANSFER.getTranslation();
            break;
        case 2:
            transferModeTranslation = JsamsI18nLabelResource.LABEL_PARTIAL_TRANSFER.getTranslation();
            break;
        case 3:
            transferModeTranslation = JsamsI18nLabelResource.LABEL_FULL_GROUPED_TRANSFER.getTranslation();
            break;
        case 4:
            transferModeTranslation = JsamsI18nLabelResource.LABEL_PARTIAL_GROUPED_TRANSFER.getTranslation();
            break;
        default:
            break;
        }
        return transferModeTranslation;
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
        remove(getComponentCount() - 1);
        int panelToSwitch = 0;
        switch (getModel().getTransferMode()) {
        case 1:
            panelToSwitch = TransferWizardDialog.FIRTH_PANEL_FULL_MODE;
            break;
        case 2:
            panelToSwitch = TransferWizardDialog.FIFTH_PANEL_PARTIAL_MODE;
            break;
        case 3:
            panelToSwitch = TransferWizardDialog.FIRTH_PANEL_FULL_GROUPED_MODE;
            break;
        case 4:
            panelToSwitch = TransferWizardDialog.FIFTH_PANEL_PARTIAL_GROUPED_MODE;
            break;
        default:
            break;
        }
        switchPanel(panelToSwitch);
    }

}
