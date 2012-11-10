package be.jsams.client.desktop;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JSeparator;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.panel.sale.SearchBillPanel;
import be.jsams.client.model.panel.sale.SearchCommandPanel;
import be.jsams.client.model.panel.sale.SearchCreditNotePanel;
import be.jsams.client.model.panel.sale.SearchDeliveryOrderPanel;
import be.jsams.client.model.panel.sale.SearchEstimatePanel;
import be.jsams.client.model.table.sale.BillTableModel;
import be.jsams.client.model.table.sale.CommandTableModel;
import be.jsams.client.model.table.sale.CreditNoteTableModel;
import be.jsams.client.model.table.sale.DeliveryOrderTableModel;
import be.jsams.client.model.table.sale.EstimateTableModel;
import be.jsams.client.swing.component.JsamsMenu;
import be.jsams.client.swing.component.JsamsMenuItem;
import be.jsams.client.swing.listener.search.sale.BillTableMouseListener;
import be.jsams.client.swing.listener.search.sale.CommandTableMouseListener;
import be.jsams.client.swing.listener.search.sale.CreditNoteTableMouseListener;
import be.jsams.client.swing.listener.search.sale.DeliveryOrderTableMouseListener;
import be.jsams.client.swing.listener.search.sale.EstimateTableMouseListener;
import be.jsams.client.validator.search.sale.SearchBillValidator;
import be.jsams.client.validator.search.sale.SearchCommandValidator;
import be.jsams.client.validator.search.sale.SearchCreditNoteValidator;
import be.jsams.client.validator.search.sale.SearchDeliveryOrderValidator;
import be.jsams.client.validator.search.sale.SearchEstimateValidator;
import be.jsams.client.wizard.transfer.TransferWizardDialog;
import be.jsams.common.bean.builder.PaymentModeBeanBuilder;
import be.jsams.common.bean.model.PaymentModeBean;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.sale.BillBean;
import be.jsams.common.bean.model.sale.CommandBean;
import be.jsams.common.bean.model.sale.CommandMediator;
import be.jsams.common.bean.model.sale.CreditNoteBean;
import be.jsams.common.bean.model.sale.DeliveryOrderBean;
import be.jsams.common.bean.model.sale.EstimateBean;
import be.jsams.common.bean.model.sale.EstimateMediator;

/**
 * Specific menu builder for sales menu.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class JsamsSalesMenuBuilder extends AbstractMenuBuilder {

    private JsamsMenu salesMenu;
    private JsamsMenuItem transferDocumentsMI;
    private JsamsMenuItem estimateMI;
    private JsamsMenuItem commandMI;
    private JsamsMenuItem deliveryOrderMI;
    private JsamsMenuItem billMI;
    private JsamsMenuItem creditNoteMI;

    private JsamsMainFrame parent;

    /**
     * Constructor
     * 
     * @param parent the {@link JsamsMainFrame} parent
     */
    public JsamsSalesMenuBuilder(final JsamsMainFrame parent) {
        this.parent = parent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsamsMenu build() {
        salesMenu = new JsamsMenu(JsamsI18nResource.MENU_SALES);
        transferDocumentsMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_TRANSFER_DOCUMENTS,
                "actions/media-seek-forward.png");
        transferDocumentsMI.setAction(transferAction(transferDocumentsMI.getText(), transferDocumentsMI.getIcon()));
        salesMenu.add(transferDocumentsMI);
        salesMenu.add(new JSeparator());
        estimateMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_ESTIMATE);
        estimateMI.setAction(estimatesAction(estimateMI.getText(), estimateMI.getIcon()));
        salesMenu.add(estimateMI);
        commandMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_COMMAND);
        commandMI.setAction(commandsAction(commandMI.getText(), commandMI.getIcon()));
        salesMenu.add(commandMI);
        deliveryOrderMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_DELIVERY_ORDER);
        deliveryOrderMI.setAction(deliveryOrdersAction(deliveryOrderMI.getText(), deliveryOrderMI.getIcon()));
        salesMenu.add(deliveryOrderMI);
        billMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_BILL);
        billMI.setAction(billsAction(billMI.getText(), billMI.getIcon()));
        salesMenu.add(billMI);
        creditNoteMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_CREDIT_NOTE);
        creditNoteMI.setAction(creditNotesAction(creditNoteMI.getText(), creditNoteMI.getIcon()));
        salesMenu.add(creditNoteMI);
        
        enableMenuItems(false);
        
        return salesMenu;
    }

    /**
     * {@link AbstractAction} for transfer documents menu item.
     * 
     * @param text the text to display
     * @param icon the {@link Icon} to display
     * @return an {@link Action} for the transfer of documents
     */
    private Action transferAction(String text, Icon icon) {
        AbstractAction action = new AbstractAction() {
            /**
             * Serial Version UID
             */
            private static final long serialVersionUID = 6779073820658986370L;

            public void actionPerformed(ActionEvent event) {
                new TransferWizardDialog(parent,
                        JsamsI18nResource.TITLE_TRANSFER_DOCUMENTS, "actions/media-seek-forward.png",
                        "images/transfer_left_right.png");
            }
        };
        action.putValue(Action.NAME, text);
        action.putValue(Action.SMALL_ICON, icon);
        return action;

    }

    /**
     * {@link AbstractAction} for estimate menu item.
     * 
     * @param text the text to display
     * @param icon the {@link Icon} to display
     * @return an {@link Action} for the searching of estimates
     */
    private Action estimatesAction(String text, Icon icon) {
        AbstractAction action = new AbstractAction() {
            /**
             * Serial Version UID
             */
            private static final long serialVersionUID = 3569088526731341971L;

            public void actionPerformed(ActionEvent event) {
                SocietyBean currentSociety = JsamsDesktop.getInstance().getCurrentSociety();
                CustomerBean customer = JsamsApplicationContext.getCustomerBeanBuilder().build(null, currentSociety);
                AgentBean agent = JsamsApplicationContext.getAgentBeanBuilder().build(null, currentSociety);
                EstimateBean bean = new EstimateBean(currentSociety, customer, agent);
                EstimateMediator mediator = new EstimateMediator();
                mediator.setEstimateBean(bean);
                bean.setMediator(mediator);
                SearchEstimatePanel<EstimateTableMouseListener> searchPanel
                    = new SearchEstimatePanel<EstimateTableMouseListener>(
                        bean, new EstimateTableMouseListener(), JsamsApplicationContext.getEstimateService(),
                        new SearchEstimateValidator(), new EstimateTableModel());
                parent.getTabbedPane().addTab(JsamsI18nResource.TITLE_SEARCH_ESTIMATE, null, searchPanel);
            }
        };
        action.putValue(Action.NAME, text);
        action.putValue(Action.SMALL_ICON, icon);
        return action;
    }

    /**
     * {@link AbstractAction} for command menu item.
     * 
     * @param text the text to display
     * @param icon the {@link Icon} to display
     * @return an {@link Action} for the searching of commands
     */
    private Action commandsAction(String text, Icon icon) {
        AbstractAction action = new AbstractAction() {
            /**
             * Serial Version UID
             */
            private static final long serialVersionUID = 2179785949720803623L;

            public void actionPerformed(ActionEvent event) {
                SocietyBean currentSociety = JsamsDesktop.getInstance().getCurrentSociety();
                CustomerBean customer = JsamsApplicationContext.getCustomerBeanBuilder().build(null, currentSociety);
                AgentBean agent = JsamsApplicationContext.getAgentBeanBuilder().build(null, currentSociety);
                CommandBean bean = new CommandBean(currentSociety, customer, agent);
                CommandMediator mediator = new CommandMediator();
                mediator.setCommandBean(bean);
                bean.setMediator(mediator);
                SearchCommandPanel<CommandTableMouseListener> searchPanel
                    = new SearchCommandPanel<CommandTableMouseListener>(
                        bean, new CommandTableMouseListener(), JsamsApplicationContext.getCommandService(),
                        new SearchCommandValidator(), new CommandTableModel());
                parent.getTabbedPane().addTab(JsamsI18nResource.TITLE_SEARCH_COMMAND, null, searchPanel);
            }
        };
        action.putValue(Action.NAME, text);
        action.putValue(Action.SMALL_ICON, icon);
        return action;
    }

    /**
     * {@link AbstractAction} for delivery order menu item.
     * 
     * @param text the text to display
     * @param icon the {@link Icon} to display
     * @return an {@link Action} for the searching of delivery orders
     */
    private Action deliveryOrdersAction(String text, Icon icon) {
        AbstractAction action = new AbstractAction() {
            /**
             * Serial Version UID
             */
            private static final long serialVersionUID = 4468697905990569193L;

            public void actionPerformed(ActionEvent event) {
                SocietyBean currentSociety = JsamsDesktop.getInstance().getCurrentSociety();
                CustomerBean customerBean = JsamsApplicationContext.getCustomerBeanBuilder()
                        .build(null, currentSociety);
                DeliveryOrderBean bean = new DeliveryOrderBean(currentSociety, customerBean);
                SearchDeliveryOrderPanel<DeliveryOrderTableMouseListener> searchPanel
                    = new SearchDeliveryOrderPanel<DeliveryOrderTableMouseListener>(
                        bean, new DeliveryOrderTableMouseListener(), JsamsApplicationContext.getDeliveryOrderService(),
                        new SearchDeliveryOrderValidator(), new DeliveryOrderTableModel());
                parent.getTabbedPane().addTab(JsamsI18nResource.TITLE_SEARCH_DELIVERY_ORDER, null, searchPanel);
            }
        };
        action.putValue(Action.NAME, text);
        action.putValue(Action.SMALL_ICON, icon);
        return action;
    }

    /**
     * {@link AbstractAction} for bill menu item.
     * 
     * @param text the text to display
     * @param icon the {@link Icon} to display
     * @return an {@link Action} for the searching of bills
     */
    private Action billsAction(String text, Icon icon) {
        AbstractAction action = new AbstractAction() {
            /**
             * Serial Version UID
             */
            private static final long serialVersionUID = 5123918962354748462L;

            public void actionPerformed(ActionEvent event) {
                SocietyBean currentSociety = JsamsDesktop.getInstance().getCurrentSociety();
                CustomerBean customer = JsamsApplicationContext.getCustomerBeanBuilder().build(null, currentSociety);
                PaymentModeBeanBuilder builder = JsamsApplicationContext.getPaymentModeBeanBuilder();
                PaymentModeBean mode = builder.build();
                BillBean bean = new BillBean(currentSociety, customer, mode);
                SearchBillPanel<BillTableMouseListener> searchPanel = new SearchBillPanel<BillTableMouseListener>(bean,
                        new BillTableMouseListener(), JsamsApplicationContext.getBillService(),
                        new SearchBillValidator(), new BillTableModel());
                parent.getTabbedPane().addTab(JsamsI18nResource.TITLE_SEARCH_BILL, null, searchPanel);
            }
        };
        action.putValue(Action.NAME, text);
        action.putValue(Action.SMALL_ICON, icon);
        return action;
    }

    /**
     * {@link AbstractAction} for credit note menu item.
     * 
     * @param text the text to display
     * @param icon the {@link Icon} to display
     * @return an {@link Action} for the searching of credit notes
     */
    private Action creditNotesAction(String text, Icon icon) {
        AbstractAction action = new AbstractAction() {
            /**
             * Serial Version UID
             */
            private static final long serialVersionUID = 1834120131584668841L;

            public void actionPerformed(ActionEvent event) {
                SocietyBean currentSociety = JsamsDesktop.getInstance().getCurrentSociety();
                CustomerBean customerBean = JsamsApplicationContext.getCustomerBeanBuilder()
                        .build(null, currentSociety);
                CreditNoteBean bean = new CreditNoteBean(currentSociety, customerBean);
                SearchCreditNotePanel<CreditNoteTableMouseListener> searchPanel
                    = new SearchCreditNotePanel<CreditNoteTableMouseListener>(
                        bean, new CreditNoteTableMouseListener(), JsamsApplicationContext.getCreditNoteService(),
                        new SearchCreditNoteValidator(), new CreditNoteTableModel());
                parent.getTabbedPane().addTab(JsamsI18nResource.TITLE_SEARCH_CREDIT_NOTE, null, searchPanel);
            }
        };
        action.putValue(Action.NAME, text);
        action.putValue(Action.SMALL_ICON, icon);
        return action;
    }

    /**
     * @return the created {@link JsamsMenu}
     */
    public JsamsMenu getMenu() {
        return salesMenu;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void enableMenuItems(boolean value) {
        transferDocumentsMI.setEnabled(value);
        estimateMI.setEnabled(value);
        commandMI.setEnabled(value);
        deliveryOrderMI.setEnabled(value);
        billMI.setEnabled(value);
        creditNoteMI.setEnabled(value);
    }

}
