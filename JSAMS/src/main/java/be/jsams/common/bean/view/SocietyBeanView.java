package be.jsams.common.bean.view;

import javax.swing.JPanel;

import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.swing.component.JsamsFormattedTextField;
import be.jsams.client.swing.component.JsamsFrame;
import be.jsams.client.swing.component.JsamsTextField;
import be.jsams.common.bean.model.SocietyBean;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

/**
 * 
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class SocietyBeanView extends AbstractBeanView<SocietyBean, JPanel, JPanel> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -6050081974816301545L;

    /**
     * Constructor
     * 
     * @param bean the {@link SocietyBean}
     */
    public SocietyBeanView(SocietyBean bean) {
        super(bean);
    }

    /**
     * {@inheritDoc}
     */
    public JPanel createEditView() {
        SocietyBean bean = getBean();
        ViewFactory<SocietyBean> helper = new ViewFactory<SocietyBean>();
        JsamsTextField textFieldName = helper.createBindingTextComponent(bean, SocietyBean.NAME_PROPERTY, true, false);
        JsamsFormattedTextField textFieldCapital = helper.createBindingDecimalComponent(bean,
                SocietyBean.CAPITAL_PROPERTY, true, false);
        JsamsTextField textFieldActivity = helper.createBindingTextComponent(bean, SocietyBean.ACTIVITY_PROPERTY, true,
                false);
        JsamsTextField textFieldResponsible = helper.createBindingTextComponent(bean, SocietyBean.RESPONSIBLE_PROPERTY,
                false, false);
        JsamsTextField textFieldVatNumber = helper.createBindingTextComponent(bean, SocietyBean.VATNUMBER_PROPERTY,
                false, false);
        FormLayout layout = new FormLayout("right:p, 3dlu, 262dlu", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, JsamsFrame.RESOURCE_BUNDLE);
        builder.setDefaultDialogBorder();
        final int maxColumnSpan = 3;
        builder.appendI15d(JsamsI18nLabelResource.LABEL_NAME.getKey(), textFieldName, 1);
        builder.nextLine();
        builder.append(bean.getAddress().getView().createEditView(), maxColumnSpan);
        builder.nextLine();
        builder.append(bean.getContactInformation().getView().createEditView(), maxColumnSpan);
        builder.nextLine();
        builder.appendSeparator(JsamsI18nLabelResource.LABEL_MISC.getTranslation());
        builder.appendI15d(JsamsI18nLabelResource.LABEL_LEGAL_FORM.getKey(), bean.getLegalForm().getView()
                .createEditView(), 1);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_CAPITAL.getKey(), textFieldCapital, 1);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_ACTIVITY.getKey(), textFieldActivity, 1);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_RESPONSIBLE.getKey(), textFieldResponsible, 1);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_VAT_NUMBER.getKey(), textFieldVatNumber, 1);
        builder.nextLine();
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
