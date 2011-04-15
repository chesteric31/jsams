package be.jsams.client.desktop;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JSeparator;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.panel.SearchEstimatePanel;
import be.jsams.client.swing.component.JsamsMenu;
import be.jsams.client.swing.component.JsamsMenuItem;
import be.jsams.client.swing.listener.EstimateTableMouseListener;
import be.jsams.common.bean.model.sale.EstimateBean;

/**
 * Specific menu builder for sales menu.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
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
        salesMenu.add(commandMI);
        deliveryOrderMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_DELIVERY_ORDER);
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
                        new EstimateTableMouseListener(), JsamsApplicationContext.getEstimateService(), true);
                parent.getTabbedPane().addTab(JsamsI18nResource.TITLE_SEARCH_ESTIMATE, null, searchPanel);
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
