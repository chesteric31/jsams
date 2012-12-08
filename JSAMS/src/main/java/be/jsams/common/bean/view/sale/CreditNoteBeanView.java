package be.jsams.common.bean.view.sale;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

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
import be.jsams.client.model.table.sale.CreditNoteDetailTableModel;
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
import be.jsams.common.bean.model.sale.CreditNoteBean;
import be.jsams.common.bean.model.sale.detail.CreditNoteDetailBean;
import be.jsams.common.bean.view.Editable;
import be.jsams.common.bean.view.Searchable;
import be.jsams.common.bean.view.ViewFactory;
import be.jsams.common.bean.view.management.CustomerBeanView;

import com.jgoodies.common.collect.ArrayListModel;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.toedter.calendar.JDateChooser;

/**
 * Implementation of all sorts of views for {@link CreditNoteBean} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CreditNoteBeanView extends AbstractDocumentBeanView<CreditNoteBean> implements Editable<JPanel>,
        Searchable<JPanel> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 1286822483256587871L;

    /**
     * Constructor.
     * 
     * @param bean the {@link CreditNoteBean}
     */
    public CreditNoteBeanView(CreditNoteBean bean) {
        super(bean);
    }

    /**
     * {@inheritDoc}
     */
    public JPanel createEditView() {
        CreditNoteBean bean = getBean();
        ViewFactory<CreditNoteBean> viewFactory = getViewFactory();

        JDateChooser creationDate = viewFactory.createBindingDateComponent(bean, CreditNoteBean.CREATION_DATE_PROPERTY,
                false, false);
        JsamsTextField remark = viewFactory.createBindingTextComponent(bean, CreditNoteBean.REMARK_PROPERTY, false,
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
        customer.addPropertyChangeListener(handleCustomerChangeListener());
        builder.appendI15d(JsamsI18nLabelResource.LABEL_CUSTOMER_NAME.getKey(), customerPanel);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_CREATION_DATE.getKey(), creationDate);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_DELIVERY_ADDRESS.getKey(), bean.getBillingAddress().getView()
                .createEditView(), maxColumnSpan - 2);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_REMARK.getKey(), remark, maxColumnSpan - 2);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_TOTAL_ET.getKey(), totalEt);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_TOTAL_VAT.getKey(), totalVat);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_TOTAL_ATI.getKey(), totalAti);
        builder.nextLine();

        List<CreditNoteDetailBean> details = bean.getDetails();

        CreditNoteDetailTableModel tableModel = new CreditNoteDetailTableModel(details, bean.getMediator());
        ViewFactory<CreditNoteDetailBean> detailView = new ViewFactory<CreditNoteDetailBean>();
        setDetailsTable(detailView.createBindingTableComponent(tableModel, false, false));
        getDetailsTable().addMouseListener(handleProductEditing());
        updateDetailsTableRendering();

        builder.appendI15dSeparator(JsamsI18nResource.PANEL_CREDIT_NOTE_DETAILS.getKey());
        builder.appendRow("60dlu");
        builder.append(new JScrollPane(getDetailsTable()), maxColumnSpan);

        JPanel buttonsPanel = buildButtonsPanel();
        builder.append(buttonsPanel, maxColumnSpan);

        return builder.getPanel();
    }

    /**
     * Handler for editing of {@link CreditNoteDetailBean} table.
     * 
     * @return a {@link MouseListener}
     */
    private MouseListener handleProductEditing() {
        return new MouseListener() {

            /**
             * {@inheritDoc}
             */
            @Override
            public void mouseReleased(MouseEvent e) {
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void mousePressed(MouseEvent e) {
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void mouseExited(MouseEvent e) {
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void mouseEntered(MouseEvent e) {
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedColumn = getDetailsTable().getSelectedColumn();
                // only edit dialog for product editing
                if (e.getClickCount() == 2) {
                    if (selectedColumn == 0 || selectedColumn == 1) {
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
                                        int detailSelectedRowModel = getDetailsTable().convertRowIndexToModel(
                                                detailSelectedRow);
                                        CreditNoteDetailTableModel detailModel
                                            = (CreditNoteDetailTableModel) getDetailsTable().getModel();
                                        CreditNoteDetailBean selectedDetailBean = detailModel
                                                .getRow(detailSelectedRowModel);
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
                        SearchProductPanel searchPanel = new SearchProductPanel(builder.build(true, true),
                                customListener, JsamsApplicationContext.getProductService(),
                                new SearchProductValidator(), new ProductTableModel(), false,
                                ListSelectionModel.SINGLE_SELECTION);
                        dialog.add(searchPanel);
                        dialog.setPreferredSize(new Dimension(800, 400));
                        dialog.pack();
                        dialog.setLocationRelativeTo(((JsamsTable) e.getSource()).getRootPane());
                        dialog.setVisible(true);
                    }
                }
            }
        };
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
                CreditNoteBean bean = getBean();
                List<CreditNoteDetailBean> details = bean.getDetails();
                CreditNoteDetailBean detail = new CreditNoteDetailBean();
                detail.setMediator(bean.getMediator());
                detail.setCreditNote(bean);
                detail.setQuantity(1);
                details.add(detail);
                detail.setListModel(new ArrayListModel<CreditNoteDetailBean>(details));
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
                List<CreditNoteDetailBean> details = getBean().getDetails();
                if (selectedRow > -1) {
                    int selectedRowModel = getDetailsTable().convertRowIndexToModel(selectedRow);
                    CreditNoteDetailTableModel model = (CreditNoteDetailTableModel) getDetailsTable().getModel();
                    details.remove(model.getRow(selectedRowModel));
                    getBean().setDetails(details);
                    model.setListModel(new ArrayListModel<CreditNoteDetailBean>(details));
                }
            }
        });
        return buttonRemove;
    }

    /**
     * {@inheritDoc}
     */
    public JPanel createSearchView() {
        CreditNoteBean bean = getBean();
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
        builder.appendI15d(JsamsI18nLabelResource.LABEL_CUSTOMER_NAME.getKey(), bean.getCustomer().getView()
                .createCustomView());
        builder.appendI15d(JsamsI18nLabelResource.LABEL_START_DATE.getKey(), startDate);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_END_DATE.getKey(), endDate);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_CITY.getKey(), textFieldCity);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_ZIP_CODE.getKey(), textFieldZipCode);

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
