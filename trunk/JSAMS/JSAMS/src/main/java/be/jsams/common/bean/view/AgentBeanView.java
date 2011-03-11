package be.jsams.common.bean.view;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.swing.action.SearchAgentAction;
import be.jsams.client.swing.component.JsamsButton;
import be.jsams.client.swing.component.JsamsDialog;
import be.jsams.client.swing.component.JsamsFrame;
import be.jsams.client.swing.utils.IconUtil;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.AgentBean;
import be.jsams.common.bean.model.ContactInformationBean;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

/**
 * 
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class AgentBeanView extends AbstractView<AgentBean, JPanel, JPanel> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -9188637266101801474L;

    private JsamsButton buttonSearchAgent = new JsamsButton(IconUtil.MENU_ICON_PREFIX
            + "categories/applications-development.png");

    /**
     * Constructor
     * 
     * @param bean
     *            the {@link AgentBean}
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
        tabbedPane.add(JsamsI18nResource.PANEL_ADDRESS.getTranslation(), getBean().getAddress().getView()
                .createEditView());
        tabbedPane.add(JsamsI18nResource.PANEL_CONTACT_INFORMATIONS.getTranslation(), getBean().getContactInformation()
                .getView().createEditView());

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
        ViewFactory<AgentBean> helper = new ViewFactory<AgentBean>();
        JTextField textFieldName = helper.createBindingTextComponent(bean, AgentBean.NAME_PROPERTY, true, false);
        JTextField textFieldFunction = helper.createBindingTextComponent(bean, AgentBean.FUNCTION_PROPERTY, false,
                false);
        FormLayout layout = new FormLayout("right:p, 3dlu, 75dlu, 3dlu, right:p, 3dlu, p:grow", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, JsamsFrame.RESOURCE_BUNDLE);
        final int maxColumnSpan = 5;
        builder.setDefaultDialogBorder();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_CIVILITY.getKey(), bean.getCivility().getView()
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
        ViewFactory<AgentBean> helper = new ViewFactory<AgentBean>();
        JTextField textFieldName = helper.createBindingTextComponent(bean, AgentBean.NAME_PROPERTY, false, false);
        JTextField textFieldFunction = helper.createBindingTextComponent(bean, AgentBean.FUNCTION_PROPERTY, false,
                false);
        ViewFactory<AddressBean> addressHelper = new ViewFactory<AddressBean>();
        JTextField textFieldCity = addressHelper.createBindingTextComponent(bean.getAddress(),
                AddressBean.CITY_PROPERTY, true, false);
        JTextField textFieldZipCode = addressHelper.createBindingTextComponent(bean.getAddress(),
                AddressBean.ZIPCODE_PROPERTY, true, false);
        ViewFactory<ContactInformationBean> contactHelper = new ViewFactory<ContactInformationBean>();
        JTextField textFieldPhone = contactHelper.createBindingTextComponent(bean.getContactInformation(),
                ContactInformationBean.PHONE_PROPERTY, false, false);
        FormLayout layout = new FormLayout(
                "right:p, 3dlu, p:grow, 3dlu, right:p, 3dlu, p:grow, 3dlu, right:p, 3dlu, 50dlu", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, JsamsFrame.RESOURCE_BUNDLE);
        builder.setDefaultDialogBorder();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_NAME.getKey(), textFieldName);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_FUNCTION.getKey(), textFieldFunction);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PHONE.getKey(), textFieldPhone);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_CITY.getKey(), textFieldCity);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_ZIP_CODE.getKey(), textFieldZipCode);

        return builder.getPanel();
    }

    /**
     * Creates a custom view
     * 
     * @return a {@link JPanel}
     */
    public JPanel createCustomView() {
        final AgentBean bean = getBean();
        final JsamsDialog dialog = new JsamsDialog(null, JsamsI18nResource.TITLE_SEARCH_AGENT,
                IconUtil.TITLE_ICON_PREFIX + "categories/applications-development.png");
        ViewFactory<AgentBean> helper = new ViewFactory<AgentBean>();
        buttonSearchAgent.setAction(new SearchAgentAction("", buttonSearchAgent.getIcon(), bean, dialog));
        JTextField textFieldName = helper.createBindingTextComponent(bean, AgentBean.NAME_PROPERTY, false, true);
        FormLayout layout = new FormLayout("175dlu, 3dlu, p", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, JsamsFrame.RESOURCE_BUNDLE);
        builder.append(textFieldName);
        builder.append(buttonSearchAgent);

        return builder.getPanel();
    }

}
