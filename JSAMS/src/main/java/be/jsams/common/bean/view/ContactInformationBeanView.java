package be.jsams.common.bean.view;

import javax.swing.JPanel;

import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.swing.component.JsamsFrame;
import be.jsams.client.swing.component.JsamsTextField;
import be.jsams.common.bean.model.ContactInformationBean;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

/**
 * 
 * 
 * @author chesteric31
 * @version $Rev$ $Date::   $Author$
 */
public class ContactInformationBeanView extends AbstractBeanView<ContactInformationBean, JPanel, JPanel> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 5063759475826319119L;

    /**
     * Constructor
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
        ViewFactory<ContactInformationBean> helper = new ViewFactory<ContactInformationBean>();
        JsamsTextField textFieldPhone = helper.createBindingTextComponent(bean, ContactInformationBean.PHONE_PROPERTY,
                true, false);
        JsamsTextField textFieldFax = helper.createBindingTextComponent(bean, ContactInformationBean.FAX_PROPERTY,
                false, false);
        JsamsTextField textFieldMobile = helper.createBindingTextComponent(bean,
                ContactInformationBean.MOBILE_PROPERTY, false, false);
        JsamsTextField textFieldEmail = helper.createBindingTextComponent(bean, ContactInformationBean.EMAIL_PROPERTY,
                false, false);
        JsamsTextField textFieldWebsite = helper.createBindingTextComponent(bean,
                ContactInformationBean.WEBSITE_PROPERTY, false, false);

        FormLayout layout = new FormLayout(
                "right:p, 3dlu, 70dlu, 3dlu, right:p, 3dlu, 70dlu, 3dlu, right:p, 3dlu, 70dlu", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, JsamsFrame.RESOURCE_BUNDLE);
        final int maxColumnSpan = 5;
        // builder.setDefaultDialogBorder();
        builder.appendSeparator(JsamsI18nLabelResource.LABEL_CONTACT_INFORMATIONS.getTranslation());
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PHONE.getKey(), textFieldPhone);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_FAX.getKey(), textFieldFax);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_MOBILE.getKey(), textFieldMobile);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_EMAIL.getKey(), textFieldEmail);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_WEBSITE.getKey(), textFieldWebsite, maxColumnSpan);
        return builder.getPanel();
    }

    /**
     * {@inheritDoc}
     */
    public JPanel createSearchView() {
        // TODO Auto-generated method stub
        return null;
    }

}
