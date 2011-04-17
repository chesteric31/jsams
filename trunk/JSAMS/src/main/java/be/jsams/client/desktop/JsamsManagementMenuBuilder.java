package be.jsams.client.desktop;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JSeparator;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.panel.SearchAgentPanel;
import be.jsams.client.model.panel.SearchCustomerPanel;
import be.jsams.client.model.panel.SearchProductCategoryPanel;
import be.jsams.client.model.panel.SearchProductPanel;
import be.jsams.client.swing.component.JsamsMenu;
import be.jsams.client.swing.component.JsamsMenuItem;
import be.jsams.client.swing.listener.AgentTableMouseListener;
import be.jsams.client.swing.listener.CustomerTableMouseListener;
import be.jsams.client.swing.listener.ProductCategoryTableMouseListener;
import be.jsams.client.swing.listener.ProductTableMouseListener;
import be.jsams.common.bean.builder.ProductCategoryBeanBuilder;
import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.common.bean.model.management.ProductCategoryBean;

/**
 * Specific menu builder for management menu.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class JsamsManagementMenuBuilder extends AbstractMenuBuilder {

    private JsamsMenu managementMenu;
    private JsamsMenuItem customersMI;
    private JsamsMenuItem agentsMI;
    private JsamsMenuItem productsCategoryMI;
    private JsamsMenuItem productsMI;

    private JsamsMainFrame parent;

    /**
     * Constructor
     * 
     * @param parent
     *            the {@link JsamsMainFrame} parent
     */
    public JsamsManagementMenuBuilder(final JsamsMainFrame parent) {
        this.parent = parent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsamsMenu build() {
        managementMenu = new JsamsMenu(JsamsI18nResource.MENU_MANAGEMENT);
        // per default: false, true if a current society is set
        // managementMenu.setEnabled(false);
        customersMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_CUSTOMERS, "apps/system-users.png");
        customersMI.setAction(customersAction(customersMI.getText(), customersMI.getIcon()));
        managementMenu.add(customersMI);
        agentsMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_AGENTS, "categories/applications-development.png");
        agentsMI.setAction(agentsAction(agentsMI.getText(), agentsMI.getIcon()));
        managementMenu.add(agentsMI);
        managementMenu.add(new JSeparator());
        productsCategoryMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_PRODUCTS_CATEGORY);
        productsCategoryMI
                .setAction(productsCategoryAction(productsCategoryMI.getText(), productsCategoryMI.getIcon()));
        managementMenu.add(productsCategoryMI);
        productsMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_PRODUCTS, "apps/preferences-desktop-theme.png");
        productsMI.setAction(productsAction(productsMI.getText(), productsMI.getIcon()));
        managementMenu.add(productsMI);
        return managementMenu;
    }

    /**
     * {@link AbstractAction} for customer menu item.
     * 
     * @param text
     *            the text to display
     * @param icon
     *            the {@link Icon} to display
     * @return an {@link Action} for the searching of customers
     */
    private Action customersAction(String text, Icon icon) {
        AbstractAction action = new AbstractAction() {
            /**
             * Serial
             */
            private static final long serialVersionUID = -8367998985097440307L;

            public void actionPerformed(ActionEvent event) {
                SearchCustomerPanel searchPanel = new SearchCustomerPanel(new CustomerBean(JsamsDesktop.getInstance()
                        .getCurrentSociety()), new CustomerTableMouseListener(), JsamsApplicationContext
                        .getCustomerService(), true);
                parent.getTabbedPane().addTab(JsamsI18nResource.TITLE_SEARCH_CUSTOMER, "apps/system-users.png",
                        searchPanel);
            }
        };
        action.putValue(Action.NAME, text);
        action.putValue(Action.SMALL_ICON, icon);
        return action;
    }

    /**
     * {@link AbstractAction} for agent menu item.
     * 
     * @param text
     *            the text to display
     * @param icon
     *            the {@link Icon} to display
     * @return an {@link Action} for the searching of agents
     */
    private Action agentsAction(String text, Icon icon) {
        AbstractAction action = new AbstractAction() {
            /**
             * Serial Version UID
             */
            private static final long serialVersionUID = 3233472575375812337L;

            public void actionPerformed(ActionEvent event) {
                SearchAgentPanel searchPanel = new SearchAgentPanel(new AgentBean(JsamsDesktop.getInstance()
                        .getCurrentSociety()), new AgentTableMouseListener(),
                        JsamsApplicationContext.getAgentService(), true);
                parent.getTabbedPane().addTab(JsamsI18nResource.TITLE_SEARCH_AGENT,
                        "categories/applications-development.png", searchPanel);
            }
        };
        action.putValue(Action.NAME, text);
        action.putValue(Action.SMALL_ICON, icon);
        return action;
    }

    /**
     * {@link AbstractAction} for product menu item.
     * 
     * @param text
     *            the text to display
     * @param icon
     *            the {@link Icon} to display
     * @return a {@link Action} for the searching of products
     */
    private Action productsAction(String text, Icon icon) {
        AbstractAction action = new AbstractAction() {
            /**
             * Serial
             */
            private static final long serialVersionUID = 3233472575375812337L;

            public void actionPerformed(ActionEvent event) {
                SearchProductPanel searchPanel = new SearchProductPanel(new ProductBean(true),
                        new ProductTableMouseListener(), JsamsApplicationContext.getProductService(), true);
                parent.getTabbedPane().addTab(JsamsI18nResource.TITLE_SEARCH_PRODUCT,
                        "apps/preferences-desktop-theme.png", searchPanel);
            }
        };
        action.putValue(Action.NAME, text);
        action.putValue(Action.SMALL_ICON, icon);
        return action;
    }

    /**
     * {@link AbstractAction} for product category menu item.
     * 
     * @param text
     *            the text to display
     * @param icon
     *            the {@link Icon} to display
     * @return a {@link Action} for the searching of product categories
     */
    private Action productsCategoryAction(String text, Icon icon) {
        AbstractAction action = new AbstractAction() {
            /**
             * Serial Version UID
             */
            private static final long serialVersionUID = -1558165346800183997L;

            public void actionPerformed(ActionEvent event) {
                ProductCategoryBeanBuilder builder = new ProductCategoryBeanBuilder();
                builder.setDao(JsamsApplicationContext.getProductCategoryDao());
                ProductCategoryBean categoryBean = builder.build(false);
                categoryBean.setSociety(JsamsDesktop.getInstance().getCurrentSociety());
                SearchProductCategoryPanel searchPanel = new SearchProductCategoryPanel(categoryBean,
                        new ProductCategoryTableMouseListener(), JsamsApplicationContext.getProductCategoryService(),
                        true);
                parent.getTabbedPane().addTab(JsamsI18nResource.TITLE_SEARCH_PRODUCT_CATEGORY, null, searchPanel);
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
        return managementMenu;
    }

}
