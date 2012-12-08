package be.jsams.common.bean.view.sale;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.panel.management.SearchProductPanel;
import be.jsams.client.model.table.AbstractJsamsTableModel;
import be.jsams.client.model.table.management.ProductTableModel;
import be.jsams.client.model.table.sale.BillDetailTableModel;
import be.jsams.client.swing.component.AbstractJsamsFrame;
import be.jsams.client.swing.component.JsamsButton;
import be.jsams.client.swing.component.JsamsDialog;
import be.jsams.client.swing.component.JsamsFormattedTextField;
import be.jsams.client.swing.component.JsamsTable;
import be.jsams.client.swing.component.JsamsTextField;
import be.jsams.client.swing.listener.search.management.ProductTableMouseListener;
import be.jsams.client.swing.utils.IconUtil;
import be.jsams.client.validator.search.management.SearchProductValidator;
import be.jsams.common.bean.builder.ProductBeanBuilder;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.PaymentModeBean;
import be.jsams.common.bean.model.PeriodBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.common.bean.model.sale.BillBean;
import be.jsams.common.bean.model.sale.CommandBean;
import be.jsams.common.bean.model.sale.detail.BillDetailBean;
import be.jsams.common.bean.view.Editable;
import be.jsams.common.bean.view.Searchable;
import be.jsams.common.bean.view.ViewFactory;
import be.jsams.common.bean.view.management.CustomerBeanView;

import com.jgoodies.common.collect.ArrayListModel;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.toedter.calendar.JDateChooser;

/**
 * Implementation of all sorts of views for {@link BillBean} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class BillBeanView extends AbstractDocumentBeanView<BillBean> implements Editable<JPanel>, Searchable<JPanel> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 3539403170916423778L;

    /**
     * Constructor.
     * 
     * @param bean the {@link BillBean}
     */
    public BillBeanView(BillBean bean) {
        super(bean);
    }

    /**
     * {@inheritDoc}
     */
    public JPanel createEditView() {
        BillBean bean = getBean();
        ViewFactory<BillBean> viewFactory = getViewFactory();

        JCheckBox closed = viewFactory.createBindingBooleanComponent(bean, BillBean.CLOSED_PROPERTY, false, false);
        JCheckBox paid = viewFactory.createBindingBooleanComponent(bean, BillBean.PAID_PROPERTY, false, false);
        JDateChooser creationDate = viewFactory.createBindingDateComponent(bean, BillBean.CREATION_DATE_PROPERTY,
                false, false);
        JDateChooser dueDate = viewFactory.createBindingDateComponent(bean, BillBean.DUE_DATE_PROPERTY, false, false);
        JDateChooser firstRememberDate = viewFactory.createBindingDateComponent(bean,
                BillBean.DATE_FIRST_REMEMBER_PROPERTY, false, false);
        JDateChooser secondRememberDate = viewFactory.createBindingDateComponent(bean,
                BillBean.DATE_SECOND_REMEMBER_PROPERTY, false, false);
        JDateChooser formalNoticeDate = viewFactory.createBindingDateComponent(bean,
                BillBean.DATE_FORMAL_NOTICE_PROPERTY, false, false);
        JsamsFormattedTextField discountRate = viewFactory.createBindingDecimalComponent(bean,
                BillBean.DISCOUNT_RATE_PROPERTY, false, false);
        JsamsTextField remark = viewFactory.createBindingTextComponent(bean, BillBean.REMARK_PROPERTY, false, false);
        JsamsFormattedTextField totalEt = viewFactory
                .createBindingDecimalComponent(bean, CommandBean.TOTAL_ET_PROPERTY, false, true);
        JsamsFormattedTextField totalVat = viewFactory
                .createBindingDecimalComponent(bean, CommandBean.TOTAL_VAT_PROPERTY, false, true);
        JsamsFormattedTextField totalAti = viewFactory
                .createBindingDecimalComponent(bean, CommandBean.TOTAL_ATI_PROPERTY, false, true);

        FormLayout layout = new FormLayout(
                "right:p, 3dlu, p:grow, 3dlu, right:p, 3dlu, p:grow, 3dlu, right:p, 3dlu, p:grow, 3dlu", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, AbstractJsamsFrame.RESOURCE_BUNDLE);
        int maxColumnSpan = builder.getColumnCount();
        builder.setDefaultDialogBorder();
        CustomerBean customer = bean.getCustomer();
        CustomerBeanView customerView = customer.getView();
        JPanel customerPanel = customerView.createCustomView();
        customer.addPropertyChangeListener(handleCustomerChangeListener());
        builder.appendI15d(JsamsI18nLabelResource.LABEL_CUSTOMER_NAME.getKey(), customerPanel);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_CREATION_DATE.getKey(), creationDate);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PAYMENT_MODE.getKey(), bean.getPaymentMode().buildView()
                .createEditView());
        builder.appendI15d(JsamsI18nLabelResource.LABEL_BILLING_ADDRESS.getKey(), bean.getBillingAddress().buildView()
                .createEditView());
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PAID.getKey(), paid);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_CLOSED.getKey(), closed);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_REMARK.getKey(), remark);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_DEFAULT_DISCOUNT_RATE.getKey(), discountRate);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_DUE_DATE.getKey(), dueDate);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_FIRST_REMEMBER_DATE.getKey(), firstRememberDate);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_SECOND_REMEMBER_DATE.getKey(), secondRememberDate);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_FORMAL_NOTICE_DATE.getKey(), formalNoticeDate);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_TOTAL_ET.getKey(), totalEt);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_TOTAL_VAT.getKey(), totalVat);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_TOTAL_ATI.getKey(), totalAti);
        builder.nextLine();

        List<BillDetailBean> details = bean.getDetails();

        BillDetailTableModel tableModel = new BillDetailTableModel(details, bean.getMediator());
        ViewFactory<BillDetailBean> detailView = new ViewFactory<BillDetailBean>();
        setDetailsTable(detailView.createBindingTableComponent(tableModel, false, false));
        getDetailsTable().addMouseListener(productListener());
        updateDetailsTableRendering();
        
        builder.appendI15dSeparator(JsamsI18nResource.PANEL_BILL_DETAILS.getKey());
        builder.appendRow("60dlu");
        builder.append(new JScrollPane(getDetailsTable()), maxColumnSpan);

        JPanel buttonsPanel = buildButtonsPanel();
        builder.append(buttonsPanel, maxColumnSpan);

        return builder.getPanel();
    }

    /**
     * {@inheritDoc}
     */
    protected void editProduct(MouseEvent e) {
        final JsamsDialog dialog = new JsamsDialog(null, JsamsI18nResource.TITLE_SEARCH_PRODUCT);
        ProductTableMouseListener customListener = new ProductTableMouseListener() {
            /**
             * {@inheritDoc}
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                JsamsTable productTable = (JsamsTable) e.getSource();
                if (e.getClickCount() == 2) {
                    int selectedRow = productTable.getSelectedRow();
                    if (selectedRow > -1) {
                        int selectedRowModel = productTable.convertRowIndexToModel(selectedRow);
                        ProductTableModel model = (ProductTableModel) productTable.getModel();
                        ProductBean selectedBean = model.getRow(selectedRowModel);
                        int detailSelectedRow = getDetailsTable().getSelectedRow();
                        int detailSelectedRowModel = getDetailsTable().convertRowIndexToModel(detailSelectedRow);
                        BillDetailTableModel detailModel = (BillDetailTableModel) getDetailsTable().getModel();
                        BillDetailBean selectedDetailBean = detailModel.getRow(detailSelectedRowModel);
                        selectedDetailBean.setPrice(selectedBean.getPrice());
                        selectedDetailBean.setProduct(selectedBean);
                        selectedDetailBean.setVatApplicable(selectedBean.getVatApplicable());
                        getDetailsTable().repaint();
                        dialog.dispose();
                    }
                }
            }
        };
        ProductBeanBuilder builder = new ProductBeanBuilder();
        builder.setSociety(JsamsDesktop.getInstance().getCurrentSociety());
        SearchProductPanel searchPanel = new SearchProductPanel(builder.build(true, true), customListener,
                JsamsApplicationContext.getProductService(), new SearchProductValidator(), new ProductTableModel(),
                false, ListSelectionModel.SINGLE_SELECTION);
        dialog.add(searchPanel);
        dialog.setPreferredSize(new Dimension(800, 400));
        dialog.pack();
        dialog.setLocationRelativeTo(((JsamsTable) e.getSource()).getRootPane());
        dialog.setVisible(true);
    }

    /**
     * Handler for customer changing.
     * 
     * @return the {@link PropertyChangeListener}
     */
    private PropertyChangeListener handleCustomerChangeListener() {
        return new PropertyChangeListener() {

            /**
             * {@inheritDoc}
             */
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                CustomerBean source = (CustomerBean) evt.getSource();
                AddressBean billingAddress = getBean().getBillingAddress();
                AddressBean customerBillingAddress = source.getBillingAddress();
                billingAddress.refresh(customerBillingAddress);
                PaymentModeBean paymentMode = getBean().getPaymentMode();
                paymentMode.refresh(source.getPaymentMode());
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    protected JsamsButton buildButtonAdd() {
        JsamsButton buttonAdd = new JsamsButton(IconUtil.MENU_ICON_PREFIX + "actions/list-add.png");
        buttonAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BillBean bean = getBean();
                List<BillDetailBean> details = bean.getDetails();
                BillDetailBean detail = new BillDetailBean();
                detail.setMediator(bean.getMediator());
                detail.setBill(bean);
                detail.setDiscountRate(bean.getDiscountRate());
                detail.setQuantity(1);
                details.add(detail);
                detail.setListModel(new ArrayListModel<BillDetailBean>(details));
                bean.setDetails(details);
                ((AbstractJsamsTableModel<?>) getDetailsTable().getModel()).setListModel(detail.getListModel());
            }
        });
        return buttonAdd;
    }

    /**
     * {@inheritDoc}
     */
    protected JsamsButton buildButtonRemove() {
        JsamsButton buttonRemove = new JsamsButton(IconUtil.MENU_ICON_PREFIX + "actions/list-remove.png");
        buttonRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = getDetailsTable().getSelectedRow();
                List<BillDetailBean> details = getBean().getDetails();
                if (selectedRow > -1) {
                    int selectedRowModel = getDetailsTable().convertRowIndexToModel(selectedRow);
                    BillDetailTableModel model = (BillDetailTableModel) getDetailsTable().getModel();
                    details.remove(model.getRow(selectedRowModel));
                    getBean().setDetails(details);
                    model.setListModel(new ArrayListModel<BillDetailBean>(details));
                }
            }
        });
        return buttonRemove;
    }

    /**
     * {@inheritDoc}
     */
    public JPanel createSearchView() {
        BillBean bean = getBean();
        ViewFactory<BillBean> viewFactory = getViewFactory();

        JCheckBox closed = viewFactory.createBindingBooleanComponent(bean, BillBean.CLOSED_PROPERTY, false, false);
        JCheckBox paid = viewFactory.createBindingBooleanComponent(bean, BillBean.PAID_PROPERTY, false, false);

        PeriodBean period = bean.getPeriod();
        ViewFactory<PeriodBean> viewPeriodFactory = period.buildView().getViewFactory();
        JDateChooser startDate = viewPeriodFactory.createBindingDateComponent(period, PeriodBean.START_DATE_PROPERTY,
                false, false);
        JDateChooser endDate = viewPeriodFactory.createBindingDateComponent(period, PeriodBean.END_DATE_PROPERTY,
                false, false);
        AddressBean address = bean.getBillingAddress();
        ViewFactory<AddressBean> viewAddressFactory = address.buildView().getViewFactory();
        JsamsTextField textFieldCity = viewAddressFactory.createBindingTextComponent(address,
                AddressBean.CITY_PROPERTY, false, false);
        JsamsTextField textFieldZipCode = viewAddressFactory.createBindingTextComponent(address,
                AddressBean.ZIP_CODE_PROPERTY, false, false);
        FormLayout layout = new FormLayout(
                "right:p, 3dlu, p:grow, 3dlu, right:p, 3dlu, p:grow, 3dlu, right:p, 3dlu, p:grow", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, AbstractJsamsFrame.RESOURCE_BUNDLE);
        builder.setDefaultDialogBorder();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_CUSTOMER_NAME.getKey(), bean.getCustomer().getView()
                .createCustomView());
        builder.appendI15d(JsamsI18nLabelResource.LABEL_START_DATE.getKey(), startDate);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_END_DATE.getKey(), endDate);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_CITY.getKey(), textFieldCity);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_ZIP_CODE.getKey(), textFieldZipCode);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PAYMENT_MODE.getKey(), bean.getPaymentMode().buildView()
                .createEditView());
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_CLOSED.getKey(), closed);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PAID.getKey(), paid);

        return builder.getPanel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void release() {
        for (PropertyChangeListener listener : getBean().getCustomer().getPropertyChangeListeners()) {
            getBean().getCustomer().removePropertyChangeListener(listener);
        }
    }

}
