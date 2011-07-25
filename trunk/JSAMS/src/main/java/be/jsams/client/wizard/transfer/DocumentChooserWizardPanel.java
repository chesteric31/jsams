package be.jsams.client.wizard.transfer;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.model.panel.sale.SearchBillPanel;
import be.jsams.client.model.panel.sale.SearchCommandPanel;
import be.jsams.client.model.panel.sale.SearchDeliveryOrderPanel;
import be.jsams.client.model.panel.sale.SearchEstimatePanel;
import be.jsams.client.model.table.BillTableModel;
import be.jsams.client.model.table.CommandTableModel;
import be.jsams.client.model.table.DeliveryOrderTableModel;
import be.jsams.client.model.table.EstimateTableModel;
import be.jsams.client.swing.listener.BillTableMouseListener;
import be.jsams.client.swing.listener.CommandTableMouseListener;
import be.jsams.client.swing.listener.DeliveryOrderTableMouseListener;
import be.jsams.client.swing.listener.EstimateTableMouseListener;
import be.jsams.client.validator.SearchBillValidator;
import be.jsams.client.validator.SearchCommandValidator;
import be.jsams.client.validator.SearchDeliveryOrderValidator;
import be.jsams.client.validator.SearchEstimateValidator;
import be.jsams.client.wizard.JsamsWizardComponent;
import be.jsams.client.wizard.JsamsWizardPanel;
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
 * {@link JsamsWizardPanel} to choose the document to transfer. 
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class DocumentChooserWizardPanel extends JsamsWizardPanel<TransferBean> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 8795118733995010793L;
    private final int noOneSelected = 0;
    private final int finishSelected = 5;
    private int selectedOption = noOneSelected; // 0 is no selected option

    /**
     * Constructor.
     * 
     * @param component the {@link JsamsWizardComponent}
     * @param model the model
     */
    public DocumentChooserWizardPanel(JsamsWizardComponent component, TransferBean model) {
        super(component, model, JsamsI18nLabelResource.LABEL_TRANSFER_CHOOSE_DOCUMENT);
        initComponents();
    }

    /**
     * Initialize the components.
     */
    private void initComponents() {
        int source = getModel().getSourceType();
        SocietyBean currentSociety = JsamsDesktop.getInstance().getCurrentSociety();
        CustomerBean customer = JsamsApplicationContext.getCustomerBeanBuilder().build(null, currentSociety);
        AgentBean agent = JsamsApplicationContext.getAgentBeanBuilder().build(null, currentSociety);

        switch (source) {
        case 1:
            EstimateBean estimate = new EstimateBean(currentSociety, customer, agent);
            SearchEstimatePanel estimatePanel = new SearchEstimatePanel(estimate, new EstimateTableMouseListener(),
                    JsamsApplicationContext.getEstimateService(), new SearchEstimateValidator(),
                    new EstimateTableModel(), true);
            this.add(estimatePanel);
            break;
        case 2:
            CommandBean command = new CommandBean(currentSociety, customer, agent);
            SearchCommandPanel commandPanel = new SearchCommandPanel(command, new CommandTableMouseListener(),
                    JsamsApplicationContext.getCommandService(), new SearchCommandValidator(), new CommandTableModel(),
                    true);
            this.add(commandPanel);
            break;
        case 3:
            DeliveryOrderBean deliveryOrder = new DeliveryOrderBean(currentSociety, customer);
            SearchDeliveryOrderPanel deliveryOrderPanel = new SearchDeliveryOrderPanel(deliveryOrder,
                    new DeliveryOrderTableMouseListener(), JsamsApplicationContext.getDeliveryOrderService(),
                    new SearchDeliveryOrderValidator(), new DeliveryOrderTableModel(), true);
            this.add(deliveryOrderPanel);
            break;
        case 4:
            PaymentModeBeanBuilder builder = JsamsApplicationContext.getPaymentModeBeanBuilder();
            PaymentModeBean mode = builder.build();
            BillBean bill = new BillBean(currentSociety, customer, mode);
            SearchBillPanel billPanel = new SearchBillPanel(bill, new BillTableMouseListener(),
                    JsamsApplicationContext.getBillService(), new SearchBillValidator(), new BillTableModel(), true);
            this.add(billPanel);
            break;
        default:
            break;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update() {
        boolean nextEnabled = false;
        if (isRadioSelected()) {
            nextEnabled = true;
        }
        setNextButtonEnabled(nextEnabled);
        boolean finishEnabled = selectedOption == finishSelected;
        setFinishButtonEnabled(finishEnabled);
        setBackButtonEnabled(true);
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
    

    /**
     * @return true if one radio is selected, false otherwise
     */
    private boolean isRadioSelected() {
        return true;
    }

}
