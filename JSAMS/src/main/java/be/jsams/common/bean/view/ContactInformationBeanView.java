package be.jsams.common.bean.view;

import javax.swing.JPanel;

import be.jsams.client.i18n.I18nLabelResource;
import be.jsams.client.swing.component.AbstractJsamsFrame;
import be.jsams.client.swing.component.JsamsTextField;
import be.jsams.common.bean.model.ContactInformationBean;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Customized view for {@link ContactInformationBean}.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::   $Author$
 */
public class ContactInformationBeanView extends AbstractBeanView<ContactInformationBean> implements Editable<JPanel> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 5063759475826319119L;

    /**
     * Constructor.
     * 
     * @param bean the {@link ContactInformationBean}
     */
    public ContactInformationBeanView(ContactInformationBean bean) {
        super(bean);
    }

    /**
     * {@inheritDoc}
     */
    public JPanel createEditView() {
        ContactInformationBean bean = getBean();
        ViewFactory<ContactInformationBean> viewFactory = getViewFactory();
        JsamsTextField textFieldPhone = viewFactory.createBindingTextComponent(bean,
                ContactInformationBean.PHONE_PROPERTY, true, false);
        JsamsTextField textFieldFax = viewFactory.createBindingTextComponent(bean, ContactInformationBean.FAX_PROPERTY,
                false, false);
        JsamsTextField textFieldMobile = viewFactory.createBindingTextComponent(bean,
                ContactInformationBean.MOBILE_PROPERTY, false, false);
        JsamsTextField textFieldEmail = viewFactory.createBindingTextComponent(bean,
                ContactInformationBean.EMAIL_PROPERTY, false, false);
        JsamsTextField textFieldWebsite = viewFactory.createBindingTextComponent(bean,
                ContactInformationBean.WEBSITE_PROPERTY, false, false);
        FormLayout layout = new FormLayout(
                "right:p, 3dlu, 70dlu, 3dlu, right:p, 3dlu, 70dlu, 3dlu, right:p, 3dlu, 70dlu", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, AbstractJsamsFrame.RESOURCE_BUNDLE);
        final int maxColumnSpan = 5;
        builder.appendSeparator(I18nLabelResource.LABEL_CONTACT_INFORMATIONS.getTranslation());
        builder.appendI15d(I18nLabelResource.LABEL_PHONE.getKey(), textFieldPhone);
        builder.appendI15d(I18nLabelResource.LABEL_FAX.getKey(), textFieldFax);
        builder.appendI15d(I18nLabelResource.LABEL_MOBILE.getKey(), textFieldMobile);
        builder.nextLine();
        builder.appendI15d(I18nLabelResource.LABEL_EMAIL.getKey(), textFieldEmail);
        builder.appendI15d(I18nLabelResource.LABEL_WEBSITE.getKey(), textFieldWebsite, maxColumnSpan);
        return builder.getPanel();
    }
    
}
