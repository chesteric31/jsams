package be.jsams.client.wizard.transfer;

import javax.swing.ListSelectionModel;

import be.jsams.client.context.ApplicationContext;
import be.jsams.client.desktop.Desktop;
import be.jsams.client.i18n.I18nLabelResource;
import be.jsams.client.model.panel.sale.SearchBillPanel;
import be.jsams.client.model.panel.sale.SearchCommandPanel;
import be.jsams.client.model.panel.sale.SearchDeliveryOrderPanel;
import be.jsams.client.model.panel.sale.SearchEstimatePanel;
import be.jsams.client.model.table.sale.BillTableModel;
import be.jsams.client.model.table.sale.CommandTableModel;
import be.jsams.client.model.table.sale.DeliveryOrderTableModel;
import be.jsams.client.model.table.sale.EstimateTableModel;
import be.jsams.client.swing.listener.wizard.selection.document.multiple.BillWizardMultipleSelectionTableML;
import be.jsams.client.swing.listener.wizard.selection.document.multiple.CommandWizardMultipleSelectionTableML;
import be.jsams.client.swing.listener.wizard.selection.document.multiple.DeliveryOrderWizardMultipleSelectionTableML;
import be.jsams.client.swing.listener.wizard.selection.document.multiple.EstimateWizardMultipleSelectionTableML;
import be.jsams.client.validator.search.sale.SearchBillValidator;
import be.jsams.client.validator.search.sale.SearchCommandValidator;
import be.jsams.client.validator.search.sale.SearchDeliveryOrderValidator;
import be.jsams.client.validator.search.sale.SearchEstimateValidator;
import be.jsams.client.validator.wizard.DocumentsValidator;
import be.jsams.client.wizard.JsamsWizardComponent;
import be.jsams.common.bean.builder.PaymentModeBeanBuilder;
import be.jsams.common.bean.model.PaymentModeBean;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.sale.BillBean;
import be.jsams.common.bean.model.sale.CommandBean;
import be.jsams.common.bean.model.sale.DeliveryOrderBean;
import be.jsams.common.bean.model.sale.EstimateBean;
import be.jsams.common.bean.model.transfer.TransferBean;

/**
 * {@link AbstractDocumentChooserWizardPanel} to choose the documents to transfer.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class DocumentsChooserWizardPanel extends AbstractDocumentChooserWizardPanel<DocumentsValidator> {

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
    public DocumentsChooserWizardPanel(TransferWizardDialog parent, JsamsWizardComponent component,
            TransferBean model, DocumentsValidator validator) {
        super(parent, component, model, validator, I18nLabelResource.LABEL_TRANSFER_CHOOSE_DOCUMENTS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateContainer() {
        int source = getModel().getSourceType();
        SocietyBean currentSociety = Desktop.getInstance().getCurrentSociety();
        CustomerBean customer = ApplicationContext.getCustomerBeanBuilder().build(null, currentSociety);
        AgentBean agent = ApplicationContext.getAgentBeanBuilder().build(null, currentSociety);

        switch (source) {
        case 1:
            EstimateBean estimate = new EstimateBean(currentSociety, customer, agent);
            estimate.setTransferred(false);
            estimate.setView(buildEstimateView(estimate));
            SearchEstimatePanel<EstimateWizardMultipleSelectionTableML> estimatePanel
                = new SearchEstimatePanel<EstimateWizardMultipleSelectionTableML>(
                    estimate, new EstimateWizardMultipleSelectionTableML(getModel()),
                    ApplicationContext.getEstimateService(), new SearchEstimateValidator(),
                    new EstimateTableModel(), false, ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            this.add(estimatePanel);
            break;
        case 2:
            CommandBean command = new CommandBean(currentSociety, customer, agent);
            command.setTransferred(false);
            command.setView(buildCommandView(command));
            SearchCommandPanel<CommandWizardMultipleSelectionTableML> commandPanel
                = new SearchCommandPanel<CommandWizardMultipleSelectionTableML>(
                    command, new CommandWizardMultipleSelectionTableML(getModel()),
                    ApplicationContext.getCommandService(), new SearchCommandValidator(), new CommandTableModel(),
                    false, ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            this.add(commandPanel);
            break;
        case 3:
            DeliveryOrderBean deliveryOrder = new DeliveryOrderBean(currentSociety, customer);
            deliveryOrder.setTransferred(false);
            deliveryOrder.setView(buildDeliveryOrderView(deliveryOrder));
            SearchDeliveryOrderPanel<DeliveryOrderWizardMultipleSelectionTableML> deliveryOrderPanel
                = new SearchDeliveryOrderPanel<DeliveryOrderWizardMultipleSelectionTableML>(
                    deliveryOrder, new DeliveryOrderWizardMultipleSelectionTableML(getModel()),
                    ApplicationContext.getDeliveryOrderService(), new SearchDeliveryOrderValidator(),
                    new DeliveryOrderTableModel(), false, ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            this.add(deliveryOrderPanel);
            break;
        case 4:
            PaymentModeBeanBuilder builder = ApplicationContext.getPaymentModeBeanBuilder();
            PaymentModeBean mode = builder.build();
            BillBean bill = new BillBean(currentSociety, customer, mode);
            bill.setView(buildBillView(bill));
            SearchBillPanel<BillWizardMultipleSelectionTableML> billPanel
                = new SearchBillPanel<BillWizardMultipleSelectionTableML>(
                    bill, new BillWizardMultipleSelectionTableML(getModel()), ApplicationContext.getBillService(),
                    new SearchBillValidator(), new BillTableModel(), false,
                    ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            this.add(billPanel);
            break;
        default:
            break;
        }
    }

}
