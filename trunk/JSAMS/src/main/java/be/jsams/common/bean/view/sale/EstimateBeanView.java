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

import be.jsams.client.context.ApplicationContext;
import be.jsams.client.desktop.Desktop;
import be.jsams.client.i18n.I18nLabelResource;
import be.jsams.client.i18n.I18nResource;
import be.jsams.client.model.panel.management.SearchProductPanel;
import be.jsams.client.model.table.AbstractJsamsTableModel;
import be.jsams.client.model.table.management.ProductTableModel;
import be.jsams.client.model.table.sale.EstimateDetailTableModel;
import be.jsams.client.swing.component.AbstractJsamsFrame;
import be.jsams.client.swing.component.JsamsButton;
import be.jsams.client.swing.component.JsamsDialog;
import be.jsams.client.swing.component.JsamsFormattedTextField;
import be.jsams.client.swing.component.JsamsTable;
import be.jsams.client.swing.component.JsamsTextField;
import be.jsams.client.swing.listener.search.management.ProductTableMouseListener;
import be.jsams.client.swing.utils.IconResource;
import be.jsams.client.swing.utils.IconUtil;
import be.jsams.client.validator.search.management.SearchProductValidator;
import be.jsams.common.bean.builder.ProductBeanBuilder;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.PeriodBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.common.bean.model.sale.EstimateBean;
import be.jsams.common.bean.model.sale.detail.EstimateDetailBean;
import be.jsams.common.bean.view.Editable;
import be.jsams.common.bean.view.Searchable;
import be.jsams.common.bean.view.ViewFactory;
import be.jsams.common.bean.view.management.CustomerBeanView;

import com.jgoodies.common.collect.ArrayListModel;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.toedter.calendar.JDateChooser;

/**
 * Implementation of all sorts of views for {@link EstimateBean} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EstimateBeanView extends AbstractDocumentBeanView<EstimateBean> implements Editable<JPanel>,
        Searchable<JPanel> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -1438658754345137680L;

    /**
     * Constructor.
     * 
     * @param bean the {@link EstimateBean}
     */
    public EstimateBeanView(EstimateBean bean) {
        super(bean);
    }

    /**
     * {@inheritDoc}
     */
    public JPanel createEditView() {
        EstimateBean bean = getBean();
        ViewFactory<EstimateBean> viewFactory = getViewFactory();

        JCheckBox transferred = viewFactory.createBindingBooleanComponent(bean, EstimateBean.TRANSFERRED_PROPERTY,
                false, false);
        JDateChooser creationDate = viewFactory.createBindingDateComponent(bean, EstimateBean.CREATION_DATE_PROPERTY,
                false, false);
        JsamsFormattedTextField discountRate = viewFactory.createBindingDecimalComponent(bean,
                EstimateBean.DISCOUNT_RATE_PROPERTY, false, false);
        JsamsTextField remark = viewFactory
                .createBindingTextComponent(bean, EstimateBean.REMARK_PROPERTY, false, false);
        JsamsFormattedTextField totalEt = viewFactory.createBindingDecimalComponent(bean,
                EstimateBean.TOTAL_ET_PROPERTY, false, true);
        JsamsFormattedTextField totalVat = viewFactory.createBindingDecimalComponent(bean,
                EstimateBean.TOTAL_VAT_PROPERTY, false, true);
        JsamsFormattedTextField totalAti = viewFactory.createBindingDecimalComponent(bean,
                EstimateBean.TOTAL_ATI_PROPERTY, false, true);

        FormLayout layout = new FormLayout("right:p, 3dlu, p:grow, 3dlu, right:p, 3dlu, p", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, AbstractJsamsFrame.RESOURCE_BUNDLE);
        final int maxColumnSpanI15d = 5;
        final int maxColumnSpan = builder.getColumnCount();
        builder.setDefaultDialogBorder();
        CustomerBean customer = bean.getCustomer();
        CustomerBeanView customerView = customer.getView();
        JPanel customerPanel = customerView.createCustomView();
        customer.addPropertyChangeListener(customerListener());
        builder.appendI15d(I18nLabelResource.LABEL_CUSTOMER_NAME.getKey(), customerPanel);
        builder.appendI15d(I18nLabelResource.LABEL_CREATION_DATE.getKey(), creationDate);
        builder.nextLine();
        builder.appendI15d(I18nLabelResource.LABEL_BILLING_ADDRESS.getKey(), bean.getBillingAddress().getView()
                .createEditView(), maxColumnSpanI15d);

        builder.appendI15d(I18nLabelResource.LABEL_AGENT.getKey(), bean.getAgent().getView().createCustomView());
        builder.appendI15d(I18nLabelResource.LABEL_TRANSFERRED.getKey(), transferred);
        builder.nextLine();
        builder.appendI15d(I18nLabelResource.LABEL_DEFAULT_DISCOUNT_RATE.getKey(), discountRate);
        builder.appendI15d(I18nLabelResource.LABEL_REMARK.getKey(), remark);

        builder.appendI15d(I18nLabelResource.LABEL_TOTAL_ET.getKey(), totalEt);
        builder.appendI15d(I18nLabelResource.LABEL_TOTAL_VAT.getKey(), totalVat);
        builder.appendI15d(I18nLabelResource.LABEL_TOTAL_ATI.getKey(), totalAti);

        builder.nextLine();
        List<EstimateDetailBean> details = bean.getDetails();

        EstimateDetailTableModel tableModel = new EstimateDetailTableModel(details, bean.getMediator());
        ViewFactory<EstimateDetailBean> detailView = new ViewFactory<EstimateDetailBean>();
        setDetailsTable(detailView.createBindingTableComponent(tableModel, false, false));
        getDetailsTable().addMouseListener(productListener());
        updateDetailsTableRendering();

        builder.appendI15dSeparator(I18nResource.PANEL_ESTIMATE_DETAILS.getKey());
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
        final JsamsDialog dialog = new JsamsDialog(null, I18nResource.TITLE_SEARCH_PRODUCT);
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
                        EstimateDetailTableModel detailModel = (EstimateDetailTableModel) getDetailsTable().getModel();
                        EstimateDetailBean selectedDetailBean = detailModel.getRow(detailSelectedRowModel);
                        selectedDetailBean.setPrice(selectedBean.getPrice());
                        selectedDetailBean.setProduct(selectedBean);
                        selectedDetailBean.setVatApplicable(selectedBean.getVatApplicable());
                        getDetailsTable().repaint();
                        dialog.dispose();
                    }
                }
            }
        };
        ProductBeanBuilder builder = ApplicationContext.getProductBeanBuilder();
        builder.setSociety(Desktop.getInstance().getCurrentSociety());
        SearchProductPanel searchPanel = new SearchProductPanel(builder.build(true, true), customListener,
                ApplicationContext.getProductService(), new SearchProductValidator(), new ProductTableModel(),
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
        JsamsButton buttonAdd = new JsamsButton(IconUtil.MENU_ICON_PREFIX + IconResource.ADD);
        buttonAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EstimateBean bean = getBean();
                List<EstimateDetailBean> details = bean.getDetails();
                EstimateDetailBean detail = new EstimateDetailBean();
                detail.setMediator(bean.getMediator());
                detail.setEstimate(bean);
                detail.setDiscountRate(bean.getDiscountRate());
                detail.setQuantity(1);
                details.add(detail);
                detail.setListModel(new ArrayListModel<EstimateDetailBean>(details));
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
        JsamsButton buttonRemove = new JsamsButton(IconUtil.MENU_ICON_PREFIX + IconResource.REMOVE);
        buttonRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = getDetailsTable().getSelectedRow();
                List<EstimateDetailBean> details = getBean().getDetails();
                if (selectedRow > -1) {
                    int selectedRowModel = getDetailsTable().convertRowIndexToModel(selectedRow);
                    EstimateDetailTableModel model = (EstimateDetailTableModel) getDetailsTable().getModel();
                    EstimateDetailBean row = model.getRow(selectedRowModel);
                    details.remove(row);
                    getBean().setDetails(details);
                    model.setListModel(new ArrayListModel<EstimateDetailBean>(details));
                }
            }
        });
        return buttonRemove;
    }

    /**
     * {@inheritDoc}
     */
    public JPanel createSearchView() {
        EstimateBean bean = getBean();
        ViewFactory<EstimateBean> viewFactory = getViewFactory();

        JCheckBox transferred = viewFactory.createBindingBooleanComponent(bean, EstimateBean.TRANSFERRED_PROPERTY,
                false, false);
        PeriodBean period = bean.getPeriod();
        ViewFactory<PeriodBean> viewPeriodFactory = period.getView().getViewFactory();
        JDateChooser startDate = viewPeriodFactory.createBindingDateComponent(period, PeriodBean.START_DATE_PROPERTY,
                false, false);
        JDateChooser endDate = viewPeriodFactory.createBindingDateComponent(period, PeriodBean.END_DATE_PROPERTY,
                false, false);
        AddressBean address = bean.getBillingAddress();
        ViewFactory<AddressBean> viewAddressFactory = address.getView().getViewFactory();
        JsamsTextField textFieldCity = viewAddressFactory.createBindingTextComponent(address,
                AddressBean.CITY_PROPERTY, false, false);
        JsamsTextField textFieldZipCode = viewAddressFactory.createBindingTextComponent(address,
                AddressBean.ZIP_CODE_PROPERTY, false, false);
        FormLayout layout = new FormLayout(
                "right:p, 3dlu, p:grow, 3dlu, right:p, 3dlu, p:grow, 3dlu, right:p, 3dlu, p:grow", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, AbstractJsamsFrame.RESOURCE_BUNDLE);
        builder.setDefaultDialogBorder();
        builder.appendI15d(I18nLabelResource.LABEL_CUSTOMER_NAME.getKey(), bean.getCustomer().getView()
                .createCustomView());
        builder.appendI15d(I18nLabelResource.LABEL_START_DATE.getKey(), startDate);
        builder.appendI15d(I18nLabelResource.LABEL_END_DATE.getKey(), endDate);
        builder.nextLine();
        builder.appendI15d(I18nLabelResource.LABEL_CITY.getKey(), textFieldCity);
        builder.appendI15d(I18nLabelResource.LABEL_ZIP_CODE.getKey(), textFieldZipCode);
        builder.appendI15d(I18nLabelResource.LABEL_TRANSFERRED.getKey(), transferred);

        return builder.getPanel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void release() {
        CustomerBean customer = getBean().getCustomer();
        for (PropertyChangeListener listener : customer.getPropertyChangeListeners()) {
            customer.removePropertyChangeListener(listener);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void changeCustomer(PropertyChangeEvent evt) {
        CustomerBean source = (CustomerBean) evt.getSource();
        AddressBean billingAddress = getBean().getBillingAddress();
        AddressBean customerBillingAddress = source.getBillingAddress();
        billingAddress.refresh(customerBillingAddress);
    }

}
