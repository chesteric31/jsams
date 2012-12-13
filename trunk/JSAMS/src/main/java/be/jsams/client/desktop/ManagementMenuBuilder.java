package be.jsams.client.desktop;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JSeparator;

import be.jsams.client.context.ApplicationContext;
import be.jsams.client.i18n.I18nResource;
import be.jsams.client.model.panel.management.SearchAgentPanel;
import be.jsams.client.model.panel.management.SearchCustomerPanel;
import be.jsams.client.model.panel.management.SearchProductCategoryPanel;
import be.jsams.client.model.panel.management.SearchProductPanel;
import be.jsams.client.model.table.management.AgentTableModel;
import be.jsams.client.model.table.management.CustomerTableModel;
import be.jsams.client.model.table.management.ProductCategoryTableModel;
import be.jsams.client.model.table.management.ProductTableModel;
import be.jsams.client.swing.component.JsamsMenu;
import be.jsams.client.swing.component.JsamsMenuItem;
import be.jsams.client.swing.listener.search.management.AgentTableMouseListener;
import be.jsams.client.swing.listener.search.management.CustomerTableMouseListener;
import be.jsams.client.swing.listener.search.management.ProductCategoryTableMouseListener;
import be.jsams.client.swing.listener.search.management.ProductTableMouseListener;
import be.jsams.client.swing.utils.IconResource;
import be.jsams.client.validator.search.management.SearchAgentValidator;
import be.jsams.client.validator.search.management.SearchCustomerValidator;
import be.jsams.client.validator.search.management.SearchProductCategoryValidator;
import be.jsams.client.validator.search.management.SearchProductValidator;
import be.jsams.common.bean.builder.ProductBeanBuilder;
import be.jsams.common.bean.builder.ProductCategoryBeanBuilder;
import be.jsams.common.bean.builder.management.AgentBeanBuilder;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.management.ProductCategoryBean;

/**
 * Specific menu builder for management menu.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class ManagementMenuBuilder extends AbstractMenuBuilder {

    private JsamsMenu managementMenu;
    private JsamsMenuItem customersMI;
    private JsamsMenuItem agentsMI;
    private JsamsMenuItem productsCategoryMI;
    private JsamsMenuItem productsMI;

    private MainFrame parent;

    /**
     * Constructor.
     * 
     * @param parent the {@link MainFrame} parent
     */
    public ManagementMenuBuilder(final MainFrame parent) {
        this.parent = parent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsamsMenu build() {
        managementMenu = new JsamsMenu(I18nResource.MENU_MANAGEMENT);
        customersMI = new JsamsMenuItem(I18nResource.MENU_ITEM_CUSTOMERS, IconResource.CUSTOMER);
        customersMI.setAction(customersAction(customersMI.getText(), customersMI.getIcon()));
        managementMenu.add(customersMI);
        agentsMI = new JsamsMenuItem(I18nResource.MENU_ITEM_AGENTS, IconResource.AGENT);
        agentsMI.setAction(agentsAction(agentsMI.getText(), agentsMI.getIcon()));
        managementMenu.add(agentsMI);
        managementMenu.add(new JSeparator());
        productsCategoryMI = new JsamsMenuItem(I18nResource.MENU_ITEM_PRODUCTS_CATEGORY);
        productsCategoryMI
                .setAction(productsCategoryAction(productsCategoryMI.getText(), productsCategoryMI.getIcon()));
        managementMenu.add(productsCategoryMI);
        productsMI = new JsamsMenuItem(I18nResource.MENU_ITEM_PRODUCTS, IconResource.PRODUCT);
        productsMI.setAction(productsAction(productsMI.getText(), productsMI.getIcon()));
        managementMenu.add(productsMI);

        enableMenuItems(false);

        return managementMenu;
    }

    /**
     * {@link AbstractAction} for customer menu item.
     * 
     * @param text the text to display
     * @param icon the {@link Icon} to display
     * @return an {@link Action} for the searching of customers
     */
    private Action customersAction(String text, Icon icon) {
        AbstractAction action = new AbstractAction(text, icon) {
            /**
             * Serial Version UID
             */
            private static final long serialVersionUID = -8367998985097440307L;

            public void actionPerformed(ActionEvent event) {
                CustomerBean customerBean = ApplicationContext.getCustomerBeanBuilder().build(null,
                        Desktop.getInstance().getCurrentSociety());
                SearchCustomerPanel searchPanel = new SearchCustomerPanel(customerBean,
                        new CustomerTableMouseListener(), ApplicationContext.getCustomerService(),
                        new SearchCustomerValidator(), new CustomerTableModel());
                parent.getTabbedPane().addTab(I18nResource.TITLE_SEARCH_CUSTOMER, IconResource.CUSTOMER, searchPanel);
            }
        };
        return action;
    }

    /**
     * {@link AbstractAction} for agent menu item.
     * 
     * @param text the text to display
     * @param icon the {@link Icon} to display
     * @return an {@link Action} for the searching of agents
     */
    private Action agentsAction(String text, Icon icon) {
        AbstractAction action = new AbstractAction(text, icon) {
            /**
             * Serial Version UID
             */
            private static final long serialVersionUID = 3233472575375812337L;

            public void actionPerformed(ActionEvent event) {
                AgentBeanBuilder builder = ApplicationContext.getAgentBeanBuilder();
                AgentBean bean = builder.build(null, Desktop.getInstance().getCurrentSociety());
                SearchAgentPanel searchPanel = new SearchAgentPanel(bean, new AgentTableMouseListener(),
                        ApplicationContext.getAgentService(), new SearchAgentValidator(), new AgentTableModel());
                parent.getTabbedPane().addTab(I18nResource.TITLE_SEARCH_AGENT, IconResource.AGENT, searchPanel);
            }
        };
        return action;
    }

    /**
     * {@link AbstractAction} for product menu item.
     * 
     * @param text the text to display
     * @param icon the {@link Icon} to display
     * @return a {@link Action} for the searching of products
     */
    private Action productsAction(String text, Icon icon) {
        AbstractAction action = new AbstractAction(text, icon) {
            /**
             * Serial
             */
            private static final long serialVersionUID = 3233472575375812337L;

            public void actionPerformed(ActionEvent event) {
                ProductBeanBuilder builder = new ProductBeanBuilder();
                builder.setSociety(Desktop.getInstance().getCurrentSociety());
                SearchProductPanel searchPanel = new SearchProductPanel(builder.build(true, true),
                        new ProductTableMouseListener(), ApplicationContext.getProductService(),
                        new SearchProductValidator(), new ProductTableModel());
                parent.getTabbedPane().addTab(I18nResource.TITLE_SEARCH_PRODUCT, IconResource.PRODUCT, searchPanel);
            }
        };
        return action;
    }

    /**
     * {@link AbstractAction} for product category menu item.
     * 
     * @param text the text to display
     * @param icon the {@link Icon} to display
     * @return a {@link Action} for the searching of product categories
     */
    private Action productsCategoryAction(String text, Icon icon) {
        AbstractAction action = new AbstractAction(text, icon) {
            /**
             * Serial Version UID
             */
            private static final long serialVersionUID = -1558165346800183997L;

            public void actionPerformed(ActionEvent event) {
                SocietyBean currentSociety = Desktop.getInstance().getCurrentSociety();
                ProductCategoryBeanBuilder builder = new ProductCategoryBeanBuilder(false, currentSociety);
                builder.setDao(ApplicationContext.getProductCategoryDao());
                ProductCategoryBean categoryBean = builder.build();
                SearchProductCategoryPanel searchPanel = new SearchProductCategoryPanel(categoryBean,
                        new ProductCategoryTableMouseListener(), ApplicationContext.getProductCategoryService(),
                        new SearchProductCategoryValidator(), new ProductCategoryTableModel());
                parent.getTabbedPane().addTab(I18nResource.TITLE_SEARCH_PRODUCT_CATEGORY, null, searchPanel);
            }
        };
        return action;
    }

    /**
     * @return the created {@link JsamsMenu}
     */
    public JsamsMenu getMenu() {
        return managementMenu;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void enableMenuItems(boolean value) {
        customersMI.setEnabled(value);
        agentsMI.setEnabled(value);
        productsCategoryMI.setEnabled(value);
        productsMI.setEnabled(value);
    }

}
