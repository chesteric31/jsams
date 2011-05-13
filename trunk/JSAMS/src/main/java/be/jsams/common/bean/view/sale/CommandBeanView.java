package be.jsams.common.bean.view.sale;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.panel.management.SearchProductPanel;
import be.jsams.client.model.table.AbstractJsamsTableModel;
import be.jsams.client.model.table.CommandDetailTableModel;
import be.jsams.client.model.table.ProductTableModel;
import be.jsams.client.renderer.JsamsTableCellRenderer;
import be.jsams.client.swing.component.JsamsButton;
import be.jsams.client.swing.component.JsamsDialog;
import be.jsams.client.swing.component.JsamsFormattedTextField;
import be.jsams.client.swing.component.JsamsFrame;
import be.jsams.client.swing.component.JsamsTable;
import be.jsams.client.swing.component.JsamsTextField;
import be.jsams.client.swing.listener.ProductTableMouseListener;
import be.jsams.client.swing.utils.IconUtil;
import be.jsams.client.validator.SearchProductValidator;
import be.jsams.common.bean.builder.ProductBeanBuilder;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.PeriodBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.common.bean.model.sale.CommandBean;
import be.jsams.common.bean.model.sale.CommandDetailBean;
import be.jsams.common.bean.view.ViewFactory;

import com.jgoodies.common.collect.ArrayListModel;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.FormLayout;
import com.toedter.calendar.JDateChooser;

/**
 * Implementation of all sorts of views for {@link CommandBean} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CommandBeanView extends AbstractDocumentBeanView<CommandBean, JPanel, JPanel> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 7008183975354064256L;

    /**
     * Constructor
     * 
     * @param bean the {@link CommandBean}
     */
    public CommandBeanView(CommandBean bean) {
        super(bean);
    }

    /**
     * {@inheritDoc}
     */
    public JPanel createEditView() {
        CommandBean bean = getBean();
        final int three = 3;
        ViewFactory<CommandBean> helper = new ViewFactory<CommandBean>();

        JCheckBox transferred = helper.createBindingBooleanComponent(bean, CommandBean.TRANSFERRED_PROPERTY, false,
                false);
        JDateChooser creationDate = helper.createBindingDateComponent(bean, CommandBean.CREATION_DATE_PROPERTY, false,
                false);
        JsamsFormattedTextField discountRate = helper.createBindingDecimalComponent(bean,
                CommandBean.DISCOUNT_RATE_PROPERTY, false, false);
        JsamsTextField remark = helper.createBindingTextComponent(bean, CommandBean.REMARK_PROPERTY, false, false);

        FormLayout layout = new FormLayout("right:p, 3dlu, p:grow, 3dlu, right:p, 3dlu, p", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, JsamsFrame.RESOURCE_BUNDLE);
        builder.setDefaultDialogBorder();
        CustomerBean customer = bean.getCustomer();
        JPanel customerPanel = customer.getView().createCustomView();
        customer.addPropertyChangeListener(handleCustomerChangeListener());
        builder.appendI15d(JsamsI18nLabelResource.LABEL_CUSTOMER_NAME.getKey(), customerPanel);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_CREATION_DATE.getKey(), creationDate);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_DELIVERY_ADDRESS.getKey(), bean.getDeliveryAddress().getView()
                .createEditView());
        builder.appendI15d(JsamsI18nLabelResource.LABEL_BILLING_ADDRESS.getKey(), bean.getBillingAddress().getView()
                .createEditView());
        builder.appendI15d(JsamsI18nLabelResource.LABEL_AGENT.getKey(), bean.getAgent().getView().createCustomView());
        builder.appendI15d(JsamsI18nLabelResource.LABEL_TRANSFERRED.getKey(), transferred);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_DEFAULT_DISCOUNT_RATE.getKey(), discountRate);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_REMARK.getKey(), remark);

        builder.nextLine();
        List<CommandDetailBean> details = bean.getDetails();
        int maxColumnSpan = builder.getColumnCount();

        CommandDetailTableModel tableModel = new CommandDetailTableModel(details);
        ViewFactory<CommandDetailBean> detailView = new ViewFactory<CommandDetailBean>();
        setDetailsTable(detailView.createBindingTableComponent(tableModel, false, false));
        getDetailsTable().addMouseListener(handleProductEditing());

        JTableHeader tableHeader = getDetailsTable().getTableHeader();
        TableCellRenderer headerRenderer = tableHeader.getDefaultRenderer();

        ((DefaultTableCellRenderer) headerRenderer).setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        getDetailsTable().setAutoCreateRowSorter(true);
        JsamsTableCellRenderer defaultCellRenderer = new JsamsTableCellRenderer();
        getDetailsTable().setDefaultRenderer(Long.class, defaultCellRenderer);
        getDetailsTable().setDefaultRenderer(Integer.class, defaultCellRenderer);
        getDetailsTable().setDefaultRenderer(Double.class, defaultCellRenderer);
        getDetailsTable().setDefaultRenderer(String.class, defaultCellRenderer);

        builder.appendI15dSeparator(JsamsI18nResource.PANEL_COMMAND_DETAILS.getKey());
        builder.append(new JScrollPane(getDetailsTable()), maxColumnSpan);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.PAGE_AXIS));
        southPanel.setBorder(BorderFactory.createEtchedBorder());

        JsamsButton buttonAdd = buildButtonAdd();
        JsamsButton buttonRemove = buildButtonRemove();
        JsamsButton buttonModify = buildButtonModify();
        JsamsButton[] buttons = new JsamsButton[three];
        buttons[0] = buttonAdd;
        buttons[1] = buttonRemove;
        buttons[2] = buttonModify;
        southPanel.add(ButtonBarFactory.buildCenteredBar(buttons));
        builder.append(southPanel, maxColumnSpan);

        return builder.getPanel();
    }

    /**
     * Handler for editing of {@link CommandDetailBean} table.
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
                // TODO Auto-generated method stub

            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                Object source = e.getSource();
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
                                        CommandDetailTableModel detailModel
                                            = (CommandDetailTableModel) getDetailsTable().getModel();
                                        CommandDetailBean selectedDetailBean = detailModel
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
                                new SearchProductValidator(), false);

                        dialog.add(searchPanel);
                        dialog.pack();
                        dialog.setLocationRelativeTo(((JsamsTable) source).getRootPane());
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
                AddressBean deliveryAddress = getBean().getDeliveryAddress();
                AddressBean customerDeliveryAddress = source.getDeliveryAddress();
                deliveryAddress.refresh(customerDeliveryAddress);
            }
        };
    }

    /**
     * Builds the adding button.
     * 
     * @return the adding {@link JsamsButton}
     */
    private JsamsButton buildButtonAdd() {
        JsamsButton buttonAdd = new JsamsButton(IconUtil.MENU_ICON_PREFIX + "actions/list-add.png");
        buttonAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CommandBean bean = getBean();
                List<CommandDetailBean> details = bean.getDetails();
                CommandDetailBean detail = new CommandDetailBean();
                detail.setCommand(bean);
                detail.setDiscountRate(bean.getDiscountRate());
                detail.setQuantity(1);
                details.add(detail);
                detail.setListModel(new ArrayListModel<CommandDetailBean>(details));
                bean.setDetails(details);
                ((AbstractJsamsTableModel<?>) getDetailsTable().getModel()).setListModel(detail.getListModel());
                // getDetailsTable().repaint();
            }
        });
        return buttonAdd;
    }

    /**
     * Builds the removing button.
     * 
     * @return the removing {@link JsamsButton}
     */
    private JsamsButton buildButtonRemove() {
        JsamsButton buttonRemove = new JsamsButton(IconUtil.MENU_ICON_PREFIX + "actions/list-remove.png");
        buttonRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = getDetailsTable().getSelectedRow();
                List<CommandDetailBean> details = getBean().getDetails();
                if (selectedRow > -1) {
                    int selectedRowModel = getDetailsTable().convertRowIndexToModel(selectedRow);
                    CommandDetailTableModel model = (CommandDetailTableModel) getDetailsTable().getModel();
                    details.remove(model.getRow(selectedRowModel));
                    getBean().setDetails(details);
                    model.setListModel(new ArrayListModel<CommandDetailBean>(details));
                    // getDetailsTable().repaint();
                }
            }
        });
        return buttonRemove;
    }

    /**
     * Builds the modifying button.
     * 
     * @return the modifying {@link JsamsButton}
     */
    private JsamsButton buildButtonModify() {
        JsamsButton buttonModify = new JsamsButton(IconUtil.MENU_ICON_PREFIX + "apps/accessories-text-editor.png");
        buttonModify.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO
            }
        });
        return buttonModify;
    }

    /**
     * {@inheritDoc}
     */
    public JPanel createSearchView() {
        CommandBean bean = getBean();
        ViewFactory<CommandBean> helper = new ViewFactory<CommandBean>();

        JCheckBox transferred = helper.createBindingBooleanComponent(bean, CommandBean.TRANSFERRED_PROPERTY, false,
                false);
        ViewFactory<PeriodBean> periodHelper = new ViewFactory<PeriodBean>();
        JDateChooser startDate = periodHelper.createBindingDateComponent(bean.getPeriod(),
                PeriodBean.START_DATE_PROPERTY, false, false);
        JDateChooser endDate = periodHelper.createBindingDateComponent(bean.getPeriod(), PeriodBean.END_DATE_PROPERTY,
                false, false);
        ViewFactory<AddressBean> addressHelper = new ViewFactory<AddressBean>();
        JsamsTextField textFieldCity = addressHelper.createBindingTextComponent(bean.getBillingAddress(),
                AddressBean.CITY_PROPERTY, false, false);
        JsamsTextField textFieldZipCode = addressHelper.createBindingTextComponent(bean.getBillingAddress(),
                AddressBean.ZIP_CODE_PROPERTY, false, false);
        FormLayout layout = new FormLayout(
                "right:p, 3dlu, p:grow, 3dlu, right:p, 3dlu, p:grow, 3dlu, right:p, 3dlu, p:grow", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, JsamsFrame.RESOURCE_BUNDLE);
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

}