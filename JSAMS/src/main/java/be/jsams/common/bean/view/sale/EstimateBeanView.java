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
import javax.swing.table.TableModel;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.panel.SearchProductPanel;
import be.jsams.client.model.table.EstimateDetailTableModel;
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
import be.jsams.common.bean.builder.ProductBeanBuilder;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.common.bean.model.sale.EstimateBean;
import be.jsams.common.bean.model.sale.EstimateDetailBean;
import be.jsams.common.bean.model.sale.PeriodBean;
import be.jsams.common.bean.view.AbstractView;
import be.jsams.common.bean.view.ViewFactory;

import com.jgoodies.common.collect.ArrayListModel;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.FormLayout;
import com.toedter.calendar.JDateChooser;

/**
 * Implementation of all sorts of views for {@link EstimateBean} object.
 * 
 * @author chesteric31
 * @version $Rev: 706 $ $Date:: 2011-04-18 13:24#$ $Author: chesteric31 $
 */
public class EstimateBeanView extends AbstractView<EstimateBean, JPanel, JPanel> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -1438658754345137680L;

    private JsamsTable table;

    /**
     * Constructor
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
        final int three = 3;
        ViewFactory<EstimateBean> helper = new ViewFactory<EstimateBean>();

        JCheckBox transferred = helper.createBindingBooleanComponent(bean, EstimateBean.TRANSFERRED_PROPERTY, false,
                false);
        JDateChooser creationDate = helper.createBindingDateComponent(bean, EstimateBean.CREATION_DATE_PROPERTY, false,
                false);
        JsamsFormattedTextField discountRate = helper.createBindingDecimalComponent(bean,
                EstimateBean.DISCOUNT_RATE_PROPERTY, false, false);
        JsamsTextField remark = helper.createBindingTextComponent(bean, EstimateBean.REMARK_PROPERTY, false, false);

        FormLayout layout = new FormLayout("right:p, 3dlu, p:grow, 3dlu, right:p, 3dlu, p", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, JsamsFrame.RESOURCE_BUNDLE);
        builder.setDefaultDialogBorder();
        CustomerBean customer = bean.getCustomer();
        JPanel customerPanel = customer.getView().createCustomView();
        customer.addPropertyChangeListener(handleCustomerChangeListener());
        builder.appendI15d(JsamsI18nLabelResource.LABEL_CUSTOMER_NAME.getKey(), customerPanel);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_CREATION_DATE.getKey(), creationDate);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_AGENT.getKey(), bean.getAgent().getView().createCustomView());
        builder.appendI15d(JsamsI18nLabelResource.LABEL_BILLING_ADDRESS.getKey(), bean.getBillingAddress().getView()
                .createEditView());
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_TRANSFERRED.getKey(), transferred);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_DEFAULT_DISCOUNT_RATE.getKey(), discountRate);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_REMARK.getKey(), remark);

        builder.nextLine();
        List<EstimateDetailBean> details = bean.getDetails();
        int maxColumnSpan = builder.getColumnCount();

        EstimateDetailTableModel tableModel = new EstimateDetailTableModel(details);
        ViewFactory<EstimateDetailBean> detailView = new ViewFactory<EstimateDetailBean>();
        table = detailView.createBindingTableComponent(tableModel, false, false);

        // we add per default one detail
        // final EstimateDetailBean detailBean = details.get(0);
        // detailBean.setListModel(new
        // ArrayListModel<EstimateDetailBean>(details));
        // table = detailBean.getView().createEditView();
        table.addMouseListener(handleProductEditing());

        JTableHeader tableHeader = table.getTableHeader();
        TableCellRenderer headerRenderer = tableHeader.getDefaultRenderer();

        ((DefaultTableCellRenderer) headerRenderer).setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        table.setAutoCreateRowSorter(true);
        JsamsTableCellRenderer defaultCellRenderer = new JsamsTableCellRenderer();
        table.setDefaultRenderer(Long.class, defaultCellRenderer);
        table.setDefaultRenderer(Integer.class, defaultCellRenderer);
        table.setDefaultRenderer(Double.class, defaultCellRenderer);
        table.setDefaultRenderer(String.class, defaultCellRenderer);

        builder.appendI15dSeparator(JsamsI18nResource.PANEL_ESTIMATE_DETAILS.getKey());
        builder.append(new JScrollPane(table), maxColumnSpan);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.PAGE_AXIS));
        southPanel.setBorder(BorderFactory.createEtchedBorder());

        JsamsButton buttonAdd = buildButtonAdd(table.getModel());
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
     * Handler for editing of {@link EstimateDetailBean} table.
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
                int selectedColumn = table.getSelectedColumn();
                System.out.println("clickcount " + e.getClickCount());
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
                                        int detailSelectedRow = table.getSelectedRow();
                                        int detailSelectedRowModel = table.convertRowIndexToModel(detailSelectedRow);
                                        EstimateDetailTableModel detailModel = (EstimateDetailTableModel) table
                                                .getModel();
                                        EstimateDetailBean selectedDetailBean = detailModel
                                                .getRow(detailSelectedRowModel);
                                        selectedDetailBean.setPrice(selectedBean.getPrice());
                                        selectedDetailBean.setProduct(selectedBean);
                                        selectedDetailBean.setVatApplicable(selectedBean.getVatApplicable());
                                        table.repaint();
                                        dialog.dispose();
                                    }
                                }
                            }
                        };
                        ProductBeanBuilder builder = new ProductBeanBuilder();
                        SearchProductPanel searchPanel = new SearchProductPanel(builder.build(true,
                                true), customListener, JsamsApplicationContext.getProductService(), false);

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
            }
        };
    }

    /**
     * Builds the adding button.
     * 
     * @param tableModel the {@link TableModel}
     * @return the adding {@link JsamsButton}
     */
    private JsamsButton buildButtonAdd(final TableModel tableModel) {
        JsamsButton buttonAdd = new JsamsButton(IconUtil.MENU_ICON_PREFIX + "actions/list-add.png");
        buttonAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<EstimateDetailBean> details = getBean().getDetails();
                EstimateDetailBean detail = new EstimateDetailBean();
                detail.setEstimate(getBean());
                details.add(detail);
                detail.setListModel(new ArrayListModel<EstimateDetailBean>(details));
                getBean().setDetails(details);
                ((EstimateDetailTableModel) tableModel).setListModel(detail.getListModel());
                table.repaint();
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
                int selectedRow = table.getSelectedRow();
                List<EstimateDetailBean> details = getBean().getDetails();
                if (selectedRow > -1) {
                    int selectedRowModel = table.convertRowIndexToModel(selectedRow);
                    EstimateDetailTableModel model = (EstimateDetailTableModel) table.getModel();
                    details.remove(model.getRow(selectedRowModel));
                    getBean().setDetails(details);
                    model.setListModel(new ArrayListModel<EstimateDetailBean>(details));
                    table.repaint();
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
            }
        });
        return buttonModify;
    }

    /**
     * {@inheritDoc}
     */
    public JPanel createSearchView() {
        EstimateBean bean = getBean();
        ViewFactory<EstimateBean> helper = new ViewFactory<EstimateBean>();

        JCheckBox transferred = helper.createBindingBooleanComponent(bean, EstimateBean.TRANSFERRED_PROPERTY, false,
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
