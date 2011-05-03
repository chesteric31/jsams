package be.jsams.common.bean.view;

import javax.swing.JPanel;

import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.swing.component.JsamsFrame;
import be.jsams.common.bean.model.PeriodBean;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.toedter.calendar.JDateChooser;

/**
 * 
 * 
 * @author chesteric31
 * @version $$Rev$$ $$Date::                  $$ $$Author$$
 */
public class PeriodBeanView extends AbstractView<PeriodBean, JPanel, JPanel> implements Viewable<JPanel, JPanel> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 7336587793387011579L;

    /**
     * Constructor
     * 
     * @param bean
     *            the {@link PeriodBean}
     */
    public PeriodBeanView(PeriodBean bean) {
        super(bean);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel createEditView() {
        PeriodBean bean = getBean();
        ViewFactory<PeriodBean> helper = new ViewFactory<PeriodBean>();
        JDateChooser startDateChooser = helper.createBindingDateComponent(bean, PeriodBean.START_DATE_PROPERTY, false,
                false);
        JDateChooser endDateChooser = helper.createBindingDateComponent(bean, PeriodBean.END_DATE_PROPERTY, false,
                false);
        FormLayout layout = new FormLayout("right:p, 3dlu, 75dlu, 3dlu, right:p, 3dlu, p:grow", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, JsamsFrame.RESOURCE_BUNDLE);
        builder.setDefaultDialogBorder();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_START_DATE.getKey(), startDateChooser);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_END_DATE.getKey(), endDateChooser);
        return builder.getPanel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel createSearchView() {
        // TODO Auto-generated method stub
        return null;
    }

}
