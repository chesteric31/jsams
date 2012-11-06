package be.jsams.client.wizard.transfer;

import javax.swing.ListSelectionModel;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.model.panel.sale.SearchBillPanel;
import be.jsams.client.model.panel.sale.SearchCommandPanel;
import be.jsams.client.model.panel.sale.SearchDeliveryOrderPanel;
import be.jsams.client.model.panel.sale.SearchEstimatePanel;
import be.jsams.client.model.table.sale.BillTableModel;
import be.jsams.client.model.table.sale.CommandTableModel;
import be.jsams.client.model.table.sale.DeliveryOrderTableModel;
import be.jsams.client.model.table.sale.EstimateTableModel;
import be.jsams.client.swing.listener.wizard.BillWizardMultipleSelectionTableML;
import be.jsams.client.swing.listener.wizard.CommandWizardMultipleSelectionTableML;
import be.jsams.client.swing.listener.wizard.DeliveryOrderWizardMultipleSelectionTableML;
import be.jsams.client.swing.listener.wizard.EstimateWizardMultipleSelectionTableML;
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
        super(parent, component, model, validator, JsamsI18nLabelResource.LABEL_TRANSFER_CHOOSE_DOCUMENTS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateContainer() {
        int source = getModel().getSourceType();
        SocietyBean currentSociety = JsamsDesktop.getInstance().getCurrentSociety();
        CustomerBean customer = JsamsApplicationContext.getCustomerBeanBuilder().build(null, currentSociety);
        AgentBean agent = JsamsApplicationContext.getAgentBeanBuilder().build(null, currentSociety);

        switch (source) {
        case 1:
            EstimateBean estimate = new EstimateBean(currentSociety, customer, agent);
            estimate.setTransferred(false);
            estimate.setView(buildEstimateView(estimate));
            SearchEstimatePanel<EstimateWizardMultipleSelectionTableML> estimatePanel
                = new SearchEstimatePanel<EstimateWizardMultipleSelectionTableML>(
                    estimate, new EstimateWizardMultipleSelectionTableML(getModel()),
                    JsamsApplicationContext.getEstimateService(), new SearchEstimateValidator(),
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
                    JsamsApplicationContext.getCommandService(), new SearchCommandValidator(), new CommandTableModel(),
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
                    JsamsApplicationContext.getDeliveryOrderService(), new SearchDeliveryOrderValidator(),
                    new DeliveryOrderTableModel(), false, ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            this.add(deliveryOrderPanel);
            break;
        case 4:
            PaymentModeBeanBuilder builder = JsamsApplicationContext.getPaymentModeBeanBuilder();
            PaymentModeBean mode = builder.build();
            BillBean bill = new BillBean(currentSociety, customer, mode);
            bill.setView(buildBillView(bill));
            SearchBillPanel<BillWizardMultipleSelectionTableML> billPanel
                = new SearchBillPanel<BillWizardMultipleSelectionTableML>(
                    bill, new BillWizardMultipleSelectionTableML(getModel()), JsamsApplicationContext.getBillService(),
                    new SearchBillValidator(), new BillTableModel(), false,
                    ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            this.add(billPanel);
            break;
        default:
            break;
        }
    }

}
