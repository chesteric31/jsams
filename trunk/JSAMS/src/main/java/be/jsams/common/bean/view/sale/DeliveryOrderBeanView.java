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
import be.jsams.client.model.table.sale.DeliveryOrderDetailTableModel;
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
import be.jsams.common.bean.model.PeriodBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.common.bean.model.sale.CommandBean;
import be.jsams.common.bean.model.sale.DeliveryOrderBean;
import be.jsams.common.bean.model.sale.detail.DeliveryOrderDetailBean;
import be.jsams.common.bean.view.Editable;
import be.jsams.common.bean.view.Searchable;
import be.jsams.common.bean.view.ViewFactory;
import be.jsams.common.bean.view.management.CustomerBeanView;

import com.jgoodies.common.collect.ArrayListModel;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.toedter.calendar.JDateChooser;

/**
 * Implementation of all sorts of views for {@link DeliveryOrderBean} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class DeliveryOrderBeanView extends AbstractDocumentBeanView<DeliveryOrderBean> implements Editable<JPanel>,
        Searchable<JPanel> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 4995396899905110598L;

    /**
     * Constructor.
     * 
     * @param bean the {@link DeliveryOrderBean}
     */
    public DeliveryOrderBeanView(DeliveryOrderBean bean) {
        super(bean);
    }

    /**
     * {@inheritDoc}
     */
    public JPanel createEditView() {
        DeliveryOrderBean bean = getBean();
        ViewFactory<DeliveryOrderBean> viewFactory = getViewFactory();

        JCheckBox transferred = viewFactory.createBindingBooleanComponent(bean, DeliveryOrderBean.TRANSFERRED_PROPERTY,
                false, false);
        JDateChooser creationDate = viewFactory.createBindingDateComponent(bean,
                DeliveryOrderBean.CREATION_DATE_PROPERTY, false, false);
        JsamsFormattedTextField discountRate = viewFactory.createBindingDecimalComponent(bean,
                DeliveryOrderBean.DISCOUNT_RATE_PROPERTY, false, false);
        JsamsTextField remark = viewFactory.createBindingTextComponent(bean, DeliveryOrderBean.REMARK_PROPERTY, false,
                false);
        JsamsFormattedTextField totalEt = viewFactory.createBindingDecimalComponent(bean,
                CommandBean.TOTAL_ET_PROPERTY, false, true);
        JsamsFormattedTextField totalVat = viewFactory.createBindingDecimalComponent(bean,
                CommandBean.TOTAL_VAT_PROPERTY, false, true);
        JsamsFormattedTextField totalAti = viewFactory.createBindingDecimalComponent(bean,
                CommandBean.TOTAL_ATI_PROPERTY, false, true);

        FormLayout layout = new FormLayout("right:p, 3dlu, p:grow, 3dlu, right:p, 3dlu, p", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, AbstractJsamsFrame.RESOURCE_BUNDLE);
        int maxColumnSpan = builder.getColumnCount();
        builder.setDefaultDialogBorder();
        CustomerBean customer = bean.getCustomer();
        CustomerBeanView customerView = customer.getView();
        JPanel customerPanel = customerView.createCustomView();
        customer.addPropertyChangeListener(customerListener());
        builder.appendI15d(JsamsI18nLabelResource.LABEL_CUSTOMER_NAME.getKey(), customerPanel);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_CREATION_DATE.getKey(), creationDate);
        builder.nextLine();
        // - 2 for the label and the space
        builder.appendI15d(JsamsI18nLabelResource.LABEL_DELIVERY_ADDRESS.getKey(), bean.getDeliveryAddress().getView()
                .createEditView(), maxColumnSpan - 2);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_TRANSFERRED.getKey(), transferred);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_DEFAULT_DISCOUNT_RATE.getKey(), discountRate);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_REMARK.getKey(), remark);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_TOTAL_ET.getKey(), totalEt);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_TOTAL_VAT.getKey(), totalVat);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_TOTAL_ATI.getKey(), totalAti);

        builder.nextLine();
        List<DeliveryOrderDetailBean> details = bean.getDetails();

        DeliveryOrderDetailTableModel tableModel = new DeliveryOrderDetailTableModel(details, bean.getMediator());
        ViewFactory<DeliveryOrderDetailBean> detailView = new ViewFactory<DeliveryOrderDetailBean>();
        setDetailsTable(detailView.createBindingTableComponent(tableModel, false, false));
        getDetailsTable().addMouseListener(productListener());
        updateDetailsTableRendering();

        builder.appendI15dSeparator(JsamsI18nResource.PANEL_DELIVERY_ORDER_DETAILS.getKey());
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
                        DeliveryOrderDetailTableModel detailModel = (DeliveryOrderDetailTableModel) getDetailsTable()
                                .getModel();
                        DeliveryOrderDetailBean selectedDetailBean = detailModel.getRow(detailSelectedRowModel);
                        selectedDetailBean.setProduct(selectedBean);
                        selectedDetailBean.setVatApplicable(selectedBean.getVatApplicable());
                        selectedDetailBean.setPrice(selectedBean.getPrice());
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
     * {@inheritDoc}
     */
    protected JsamsButton buildButtonAdd() {
        JsamsButton buttonAdd = new JsamsButton(IconUtil.MENU_ICON_PREFIX + "actions/list-add.png");
        buttonAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DeliveryOrderBean bean = getBean();
                List<DeliveryOrderDetailBean> details = bean.getDetails();
                DeliveryOrderDetailBean detail = new DeliveryOrderDetailBean();
                detail.setMediator(bean.getMediator());
                detail.setDeliveryOrder(bean);
                detail.setDiscountRate(bean.getDiscountRate());
                detail.setQuantity(1);
                details.add(detail);
                detail.setListModel(new ArrayListModel<DeliveryOrderDetailBean>(details));
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
                List<DeliveryOrderDetailBean> details = getBean().getDetails();
                if (selectedRow > -1) {
                    int selectedRowModel = getDetailsTable().convertRowIndexToModel(selectedRow);
                    DeliveryOrderDetailTableModel model = (DeliveryOrderDetailTableModel) getDetailsTable().getModel();
                    details.remove(model.getRow(selectedRowModel));
                    getBean().setDetails(details);
                    model.setListModel(new ArrayListModel<DeliveryOrderDetailBean>(details));
                }
            }
        });
        return buttonRemove;
    }

    /**
     * {@inheritDoc}
     */
    public JPanel createSearchView() {
        DeliveryOrderBean bean = getBean();
        ViewFactory<DeliveryOrderBean> viewFactory = getViewFactory();

        JCheckBox transferred = viewFactory.createBindingBooleanComponent(bean, CommandBean.TRANSFERRED_PROPERTY,
                false, false);
        PeriodBean period = bean.getPeriod();
        ViewFactory<PeriodBean> viewPeriodFactory = period.getView().getViewFactory();
        JDateChooser startDate = viewPeriodFactory.createBindingDateComponent(period, PeriodBean.START_DATE_PROPERTY,
                false, false);
        JDateChooser endDate = viewPeriodFactory.createBindingDateComponent(period, PeriodBean.END_DATE_PROPERTY,
                false, false);
        AddressBean address = bean.getDeliveryAddress();
        ViewFactory<AddressBean> viewAddressFactory = address.getView().getViewFactory();
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
        builder.appendI15d(JsamsI18nLabelResource.LABEL_TRANSFERRED.getKey(), transferred);

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

    /**
     * {@inheritDoc}
     */
    @Override
    protected void changeCustomer(PropertyChangeEvent evt) {
        CustomerBean source = (CustomerBean) evt.getSource();
        AddressBean deliveryAddress = getBean().getDeliveryAddress();
        AddressBean customerDeliveryAddress = source.getDeliveryAddress();
        deliveryAddress.refresh(customerDeliveryAddress);
    }

}
