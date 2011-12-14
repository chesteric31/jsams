package be.jsams.common.bean.view.management;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.swing.action.SearchAgentAction;
import be.jsams.client.swing.component.JsamsButton;
import be.jsams.client.swing.component.JsamsDialog;
import be.jsams.client.swing.component.AbstractJsamsFrame;
import be.jsams.client.swing.component.JsamsTextField;
import be.jsams.client.swing.utils.IconUtil;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.ContactInformationBean;
import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.common.bean.view.AbstractBeanView;
import be.jsams.common.bean.view.Editable;
import be.jsams.common.bean.view.Searchable;
import be.jsams.common.bean.view.ViewFactory;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.layout.FormLayout;

/**
 * {@link AbstractBeanView} for {@link AgentBean}.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class AgentBeanView extends AbstractBeanView<AgentBean> implements Editable<JPanel>, Searchable<JPanel> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -9188637266101801474L;

    private JsamsButton buttonSearchAgent = new JsamsButton(IconUtil.MENU_ICON_PREFIX
            + "categories/applications-development.png");

    /**
     * Constructor
     * 
     * @param bean the {@link AgentBean}
     */
    public AgentBeanView(AgentBean bean) {
        super(bean);
    }

    /**
     * {@inheritDoc}
     */
    public JPanel createEditView() {
        JPanel panel = new JPanel();
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
        JPanel generalPanel = buildGeneralTab();
        tabbedPane.add(JsamsI18nResource.PANEL_GENERAL.getTranslation(), generalPanel);
        JPanel addressPanel = getBean().getAddress().buildView().createEditView();
        addressPanel.setBorder(Borders.DIALOG_BORDER);
        tabbedPane.add(JsamsI18nResource.PANEL_ADDRESS.getTranslation(), addressPanel);
        JPanel contactPanel = getBean().getContactInformation().buildView().createEditView();
        contactPanel.setBorder(Borders.DIALOG_BORDER);
        tabbedPane.add(JsamsI18nResource.PANEL_CONTACT_INFORMATIONS.getTranslation(), contactPanel);

        panel.add(tabbedPane);

        return panel;
    }

    /**
     * Builds the general tab panel.
     * 
     * @return the general tab panel
     */
    private JPanel buildGeneralTab() {
        AgentBean bean = getBean();
        ViewFactory<AgentBean> viewFactory = getViewFactory();
        JsamsTextField textFieldName = viewFactory.createBindingTextComponent(bean, AgentBean.NAME_PROPERTY, true,
                false);
        JsamsTextField textFieldFunction = viewFactory.createBindingTextComponent(bean, AgentBean.FUNCTION_PROPERTY,
                true, false);
        FormLayout layout = new FormLayout("right:p, 3dlu, 75dlu, 3dlu, right:p, 3dlu, p:grow", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, AbstractJsamsFrame.RESOURCE_BUNDLE);
        final int maxColumnSpan = 5;
        builder.setDefaultDialogBorder();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_CIVILITY.getKey(), bean.getCivility().buildView()
                .createEditView());
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_NAME.getKey(), textFieldName, maxColumnSpan);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_FUNCTION.getKey(), textFieldFunction, maxColumnSpan);
        return builder.getPanel();
    }

    /**
     * {@inheritDoc}
     */
    public JPanel createSearchView() {
        AgentBean bean = getBean();
        ViewFactory<AgentBean> viewFactory = getViewFactory();
        JsamsTextField textFieldName = viewFactory.createBindingTextComponent(bean, AgentBean.NAME_PROPERTY, false,
                false);
        JsamsTextField textFieldFunction = viewFactory.createBindingTextComponent(bean, AgentBean.FUNCTION_PROPERTY,
                false, false);
        AddressBean address = bean.getAddress();
        ViewFactory<AddressBean> viewAddressFactory = address.buildView().getViewFactory();
        JsamsTextField textFieldCity = viewAddressFactory.createBindingTextComponent(address,
                AddressBean.CITY_PROPERTY, false, false);
        JsamsTextField textFieldZipCode = viewAddressFactory.createBindingTextComponent(address,
                AddressBean.ZIP_CODE_PROPERTY, false, false);
        ContactInformationBean contactInformation = bean.getContactInformation();
        ViewFactory<ContactInformationBean> viewContactFactory = contactInformation.buildView().getViewFactory();
        JsamsTextField textFieldPhone = viewContactFactory.createBindingTextComponent(contactInformation,
                ContactInformationBean.PHONE_PROPERTY, false, false);
        FormLayout layout = new FormLayout(
                "right:p, 3dlu, p:grow, 3dlu, right:p, 3dlu, p:grow, 3dlu, right:p, 3dlu, 50dlu", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, AbstractJsamsFrame.RESOURCE_BUNDLE);
        builder.setDefaultDialogBorder();
        final int maxColumnSpan = 5;
        builder.appendI15d(JsamsI18nLabelResource.LABEL_NAME.getKey(), textFieldName);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_FUNCTION.getKey(), textFieldFunction, maxColumnSpan);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PHONE.getKey(), textFieldPhone);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_CITY.getKey(), textFieldCity);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_ZIP_CODE.getKey(), textFieldZipCode);

        return builder.getPanel();
    }

    /**
     * Create a custom view
     * 
     * @return a {@link JPanel}
     */
    public JPanel createCustomView() {
        final AgentBean bean = getBean();
        final JsamsDialog dialog = new JsamsDialog(null, JsamsI18nResource.TITLE_SEARCH_AGENT,
                IconUtil.TITLE_ICON_PREFIX + "categories/applications-development.png");
        ViewFactory<AgentBean> helper = new ViewFactory<AgentBean>();
        buttonSearchAgent.setAction(new SearchAgentAction("", buttonSearchAgent.getIcon(), bean, dialog));
        JsamsTextField textFieldName = helper.createBindingTextComponent(bean, AgentBean.NAME_PROPERTY, false, true);
        FormLayout layout = new FormLayout("p:grow, 3dlu, p", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, AbstractJsamsFrame.RESOURCE_BUNDLE);
        builder.append(textFieldName);
        builder.append(buttonSearchAgent);

        return builder.getPanel();
    }

}
