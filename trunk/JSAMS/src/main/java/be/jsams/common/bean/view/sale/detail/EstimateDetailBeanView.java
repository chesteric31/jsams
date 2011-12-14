package be.jsams.common.bean.view.sale.detail;

import javax.swing.JPanel;

import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.swing.component.AbstractJsamsFrame;
import be.jsams.client.swing.component.JsamsTextField;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.PeriodBean;
import be.jsams.common.bean.model.sale.EstimateBean;
import be.jsams.common.bean.model.sale.detail.EstimateDetailBean;
import be.jsams.common.bean.view.Searchable;
import be.jsams.common.bean.view.ViewFactory;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.toedter.calendar.JDateChooser;

/**
 * Implementation of all sorts of views for {@link EstimateDetailBean} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EstimateDetailBeanView extends AbstractDetailBeanView<EstimateDetailBean> implements Searchable<JPanel> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 1156444686459091375L;

    /**
     * Constructor
     * 
     * @param bean
     *            the {@link EstimateDetailBean}
     */
    public EstimateDetailBeanView(EstimateDetailBean bean) {
        super(bean);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel createSearchView() {
        EstimateDetailBean bean = getBean();
        EstimateBean estimate = bean.getEstimate();
        PeriodBean period = estimate.getPeriod();
        ViewFactory<PeriodBean> viewPeriodFactory = period.buildView().getViewFactory();
        JDateChooser startDate = viewPeriodFactory.createBindingDateComponent(period, PeriodBean.START_DATE_PROPERTY,
                false, false);
        JDateChooser endDate = viewPeriodFactory.createBindingDateComponent(period, PeriodBean.END_DATE_PROPERTY,
                false, false);
        AddressBean address = estimate.getBillingAddress();
        ViewFactory<AddressBean> viewAddressFactory = address.buildView().getViewFactory();
        JsamsTextField textFieldCity = viewAddressFactory.createBindingTextComponent(address,
                AddressBean.CITY_PROPERTY, false, false);
        JsamsTextField textFieldZipCode = viewAddressFactory.createBindingTextComponent(address,
                AddressBean.ZIP_CODE_PROPERTY, false, false);
        FormLayout layout = new FormLayout(
                "right:p, 3dlu, p:grow, 3dlu, right:p, 3dlu, p:grow, 3dlu, right:p, 3dlu, p:grow", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, AbstractJsamsFrame.RESOURCE_BUNDLE);
        builder.setDefaultDialogBorder();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_CUSTOMER_NAME.getKey(), estimate.getCustomer().buildView()
                .createCustomView());
        builder.appendI15d(JsamsI18nLabelResource.LABEL_START_DATE.getKey(), startDate);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_END_DATE.getKey(), endDate);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_CITY.getKey(), textFieldCity);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_ZIP_CODE.getKey(), textFieldZipCode);

        return builder.getPanel();
    }

}
