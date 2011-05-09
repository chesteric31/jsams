package be.jsams.client.desktop;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JSeparator;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.panel.sale.SearchCommandPanel;
import be.jsams.client.model.panel.sale.SearchDeliveryOrderPanel;
import be.jsams.client.model.panel.sale.SearchEstimatePanel;
import be.jsams.client.swing.component.JsamsMenu;
import be.jsams.client.swing.component.JsamsMenuItem;
import be.jsams.client.swing.listener.CommandTableMouseListener;
import be.jsams.client.swing.listener.DeliveryOrderTableMouseListener;
import be.jsams.client.swing.listener.EstimateTableMouseListener;
import be.jsams.client.validator.SearchCommandValidator;
import be.jsams.client.validator.SearchDeliveryOrderValidator;
import be.jsams.client.validator.SearchEstimateValidator;
import be.jsams.common.bean.model.sale.CommandBean;
import be.jsams.common.bean.model.sale.DeliveryOrderBean;
import be.jsams.common.bean.model.sale.EstimateBean;

/**
 * Specific menu builder for sales menu.
 * 
 * @author chesteric31
 * @version $Rev: 692 $ $Date::                  $ $Author$
 */
public class JsamsSalesMenuBuilder extends AbstractMenuBuilder {

    private JsamsMenu salesMenu;
    private JsamsMenuItem createDocumentsMI;
    private JsamsMenuItem transferDocumentsMI;
    private JsamsMenuItem listDocumentsMI;
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
        // per default: false, true if a current society is set
        // salesMenu.setEnabled(false);
        createDocumentsMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_CREATE_DOCUMENTS, "actions/document-new.png");
        salesMenu.add(createDocumentsMI);
        transferDocumentsMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_TRANSFER_DOCUMENTS,
                "actions/media-seek-forward.png");
        salesMenu.add(transferDocumentsMI);
        salesMenu.add(new JSeparator());
        listDocumentsMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_LIST_DOCUMENTS,
                "apps/internet-news-reader.png");
        salesMenu.add(listDocumentsMI);
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
        salesMenu.add(billMI);
        creditNoteMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_CREDIT_NOTE);
        salesMenu.add(creditNoteMI);
        return salesMenu;
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
                SearchEstimatePanel searchPanel = new SearchEstimatePanel(new EstimateBean(),
                        new EstimateTableMouseListener(), JsamsApplicationContext.getEstimateService(),
                        new SearchEstimateValidator(), true);
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
                SearchCommandPanel searchPanel = new SearchCommandPanel(new CommandBean(),
                        new CommandTableMouseListener(), JsamsApplicationContext.getCommandService(),
                        new SearchCommandValidator(), true);
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
                SearchDeliveryOrderPanel searchPanel = new SearchDeliveryOrderPanel(new DeliveryOrderBean(),
                        new DeliveryOrderTableMouseListener(), JsamsApplicationContext.getDeliveryOrderService(),
                        new SearchDeliveryOrderValidator(), true);
                parent.getTabbedPane().addTab(JsamsI18nResource.TITLE_SEARCH_DELIVERY_ORDER, null, searchPanel);
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

}