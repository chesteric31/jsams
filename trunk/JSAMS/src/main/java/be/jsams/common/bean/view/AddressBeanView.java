package be.jsams.common.bean.view;

import javax.swing.JPanel;

import be.jsams.client.i18n.I18nLabelResource;
import be.jsams.client.swing.component.AbstractJsamsFrame;
import be.jsams.client.swing.component.JsamsTextField;
import be.jsams.common.bean.model.AddressBean;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

/**
 * View of {@link AddressBean}.
 * 
 * @author chesteric31
 * @version $$Rev$$ $$Date::                  $$ $$Author$$
 */
public class AddressBeanView extends AbstractBeanView<AddressBean> implements Editable<JPanel> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -7792116284599504350L;

    /**
     * Constructor
     * 
     * @param bean the {@link AddressBean}
     */
    public AddressBeanView(AddressBean bean) {
        super(bean);
    }

    /**
     * {@inheritDoc}
     */
    public JPanel createEditView() {
        AddressBean bean = getBean();
        ViewFactory<AddressBean> viewFactory = getViewFactory();
        JsamsTextField textFieldBox = viewFactory.createBindingTextComponent(bean, AddressBean.BOX_PROPERTY, false,
                false);
        JsamsTextField textFieldStreet = viewFactory.createBindingTextComponent(bean, AddressBean.STREET_PROPERTY,
                true, false);
        JsamsTextField textFieldNumber = viewFactory.createBindingTextComponent(bean, AddressBean.NUMBER_PROPERTY,
                true, false);
        JsamsTextField textFieldZipCode = viewFactory.createBindingTextComponent(bean, AddressBean.ZIP_CODE_PROPERTY,
                true, false);
        JsamsTextField textFieldCity = viewFactory.createBindingTextComponent(bean, AddressBean.CITY_PROPERTY, true,
                false);
        JsamsTextField textFieldCountry = viewFactory.createBindingTextComponent(bean, AddressBean.COUNTRY_PROPERTY,
                true, false);
        FormLayout layout = new FormLayout(
                "right:p, 3dlu, 45dlu, 3dlu, right:p, 3dlu, 45dlu, 3dlu, right:p, 3dlu, 45dlu:grow", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, AbstractJsamsFrame.RESOURCE_BUNDLE);
        final int maxColumnSpan = 9;
        builder.appendSeparator(I18nLabelResource.LABEL_ADDRESS.getTranslation());
        builder.appendI15d(I18nLabelResource.LABEL_STREET.getKey(), textFieldStreet, maxColumnSpan);
        builder.nextLine();
        builder.appendI15d(I18nLabelResource.LABEL_NUMBER.getKey(), textFieldNumber);
        builder.appendI15d(I18nLabelResource.LABEL_BOX.getKey(), textFieldBox);
        builder.appendI15d(I18nLabelResource.LABEL_ZIP_CODE.getKey(), textFieldZipCode);
        builder.nextLine();
        builder.appendI15d(I18nLabelResource.LABEL_CITY.getKey(), textFieldCity, maxColumnSpan);
        builder.nextLine();
        builder.appendI15d(I18nLabelResource.LABEL_COUNTRY.getKey(), textFieldCountry, maxColumnSpan);
        return builder.getPanel();
    }

}
