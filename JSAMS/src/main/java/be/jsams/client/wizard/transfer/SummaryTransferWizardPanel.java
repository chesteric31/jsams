package be.jsams.client.wizard.transfer;

import java.awt.Font;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.plaf.FontUIResource;

import be.jsams.client.i18n.I18nLabelResource;
import be.jsams.client.model.dialog.AbstractWizardDialog;
import be.jsams.client.validator.wizard.SummaryTransferValidator;
import be.jsams.client.wizard.JsamsWizardComponent;
import be.jsams.client.wizard.JsamsWizardPanel;
import be.jsams.common.bean.model.sale.AbstractDocumentBean;
import be.jsams.common.bean.model.sale.detail.BillDetailBean;
import be.jsams.common.bean.model.sale.detail.CommandDetailBean;
import be.jsams.common.bean.model.sale.detail.DeliveryOrderDetailBean;
import be.jsams.common.bean.model.sale.detail.EstimateDetailBean;
import be.jsams.common.bean.model.transfer.TransferBean;

/**
 * Summary {@link JsamsWizardPanel} to show the transfers to do.
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
        super(parent, component, model, I18nLabelResource.LABEL_TRANSFER_SUMMARY, validator);
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
        updateContainer();
        super.update();
    }

    /**
     * Update the panel container.
     */
    private void updateContainer() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JTextArea area = new JTextArea();
        area.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 12));
        area.append(I18nLabelResource.LABEL_SOURCE_TYPE.getTranslation());
        String doublePointSeparator = " : ";
        area.append(doublePointSeparator);
        String newLine = "\n";
        String tab = "\t";
        String sourceTypeTranslation = getSourceTypeTranslation(getModel().getSourceType());
        String detailsTypeTranslation = I18nLabelResource.LABEL_DETAILS.getTranslation();
        area.append(sourceTypeTranslation + newLine);

        area.append(I18nLabelResource.LABEL_DESTINATION_TYPE.getTranslation());
        area.append(doublePointSeparator);
        area.append(getDestinationTypeTranslation(getModel().getDestinationType()) + newLine);

        area.append(I18nLabelResource.LABEL_TRANSFER_MODE.getTranslation());
        area.append(doublePointSeparator);
        area.append(getTransferModeTranslation(getModel().getTransferMode()) + newLine);

        List<? extends AbstractDocumentBean<?, ?>> documents = getModel().getDocuments();
        if (documents != null && !documents.isEmpty()) {
            area.append(sourceTypeTranslation);
            area.append(doublePointSeparator);
            for (AbstractDocumentBean<?, ?> doc : documents) {
                area.append(tab + doc.getId() + newLine);
            }
        }

        switch (getModel().getSourceType()) {
        case 1:
            Map<Long, List<EstimateDetailBean>> estimates = getModel().getEstimateDetails();
            if (estimates != null && !estimates.isEmpty()) {
                area.append(detailsTypeTranslation);
                area.append(doublePointSeparator);
                for (List<EstimateDetailBean> list : estimates.values()) {
                    for (EstimateDetailBean bean : list) {
                        area.append(tab + bean.getId() + newLine);
                    }
                }
            }
            break;
        case 2:
            Map<Long, List<CommandDetailBean>> commands = getModel().getCommandDetails();
            if (commands != null && !commands.isEmpty()) {
                area.append(detailsTypeTranslation);
                area.append(doublePointSeparator);
                for (List<CommandDetailBean> list : commands.values()) {
                    for (CommandDetailBean bean : list) {
                        area.append(tab + bean.getId() + newLine);
                    }
                }
            }
            break;
        case 3:
            Map<Long, List<DeliveryOrderDetailBean>> orders = getModel().getDeliveryOrderDetails();
            if (orders != null && !orders.isEmpty()) {
                area.append(detailsTypeTranslation);
                area.append(doublePointSeparator);
                for (List<DeliveryOrderDetailBean> list : orders.values()) {
                    for (DeliveryOrderDetailBean bean : list) {
                        area.append(tab + bean.getId() + newLine);
                    }
                }
            }
            break;
        case 4:
            Map<Long, List<BillDetailBean>> bills = getModel().getBillDetails();
            if (bills != null && !bills.isEmpty()) {
                area.append(detailsTypeTranslation);
                area.append(doublePointSeparator);
                for (List<BillDetailBean> list : bills.values()) {
                    for (BillDetailBean bean : list) {
                        area.append(tab + bean.getId() + newLine);
                    }
                }
            }
            break;
        default:
            break;
        }

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
            sourceTypeTranslation = I18nLabelResource.LABEL_ESTIMATE.getTranslation();
            break;
        case 2:
            sourceTypeTranslation = I18nLabelResource.LABEL_COMMAND.getTranslation();
            break;
        case 3:
            sourceTypeTranslation = I18nLabelResource.LABEL_DELIVERY_ORDER.getTranslation();
            break;
        case 4:
            sourceTypeTranslation = I18nLabelResource.LABEL_BILL.getTranslation();
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
            destinationTypeTranslation = I18nLabelResource.LABEL_COMMAND.getTranslation();
            break;
        case 2:
            destinationTypeTranslation = I18nLabelResource.LABEL_DELIVERY_ORDER.getTranslation();
            break;
        case 3:
            destinationTypeTranslation = I18nLabelResource.LABEL_BILL.getTranslation();
            break;
        case 4:
            destinationTypeTranslation = I18nLabelResource.LABEL_CREDIT_NOTE.getTranslation();
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
            transferModeTranslation = I18nLabelResource.LABEL_FULL_TRANSFER.getTranslation();
            break;
        case 2:
            transferModeTranslation = I18nLabelResource.LABEL_PARTIAL_TRANSFER.getTranslation();
            break;
        case 3:
            transferModeTranslation = I18nLabelResource.LABEL_FULL_GROUPED_TRANSFER.getTranslation();
            break;
        case 4:
            transferModeTranslation = I18nLabelResource.LABEL_PARTIAL_GROUPED_TRANSFER.getTranslation();
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
        getParentDialog().getStatusBar().clear();
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
        getModel().clearDetails();
        List<? extends AbstractDocumentBean<?, ?>> documents = getModel().getDocuments();
        if (documents != null) {
            documents.clear();
        }
        switchPanel(panelToSwitch);
    }

}
