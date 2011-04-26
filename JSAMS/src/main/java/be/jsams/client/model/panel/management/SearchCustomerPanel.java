package be.jsams.client.model.panel.management;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.dialog.management.EditCustomerDialog;
import be.jsams.client.model.panel.AbstractSearchPanel;
import be.jsams.client.model.table.CustomerTableModel;
import be.jsams.client.renderer.JsamsTableCellRenderer;
import be.jsams.client.swing.listener.CustomerTableMouseListener;
import be.jsams.client.validator.EditCustomerValidator;
import be.jsams.client.validator.SearchCustomerValidator;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.server.service.CustomerService;

/**
 * Search {@link JPanel} for Customer objects.
 * 
 * @author chesteric31
 * @version $Rev: 710 $ $Date::                  $ $Author$
 */
public class SearchCustomerPanel extends
        AbstractSearchPanel<CustomerBean, CustomerTableMouseListener, CustomerService, EditCustomerValidator> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 2222078506888522042L;

    private static final Log LOGGER = LogFactory.getLog(SearchCustomerPanel.class);

    private final boolean debug = LOGGER.isDebugEnabled();

    /**
     * Constructor.
     * 
     * @param model the {@link CustomerBean}
     * @param listener the {@link CustomerTableMouseListener}
     * @param service the {@link CustomerService}
     * @param showButtons a boolean that indicates if we have to display the
     *            buttons to manage the content: add, remove and modify
     */
    public SearchCustomerPanel(CustomerBean model, CustomerTableMouseListener listener, CustomerService service,
            final boolean showButtons) {
        super(model, listener, service, showButtons);
        super.setValidator(new SearchCustomerValidator());
        super.buildMainPanel(buildSearchCriteriaPanel());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected JPanel buildSearchCriteriaPanel() {
        return getModel().getView().createSearchView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performButtonAdd() {
        new EditCustomerDialog(JsamsI18nResource.TITLE_EDIT_CUSTOMER, new CustomerBean(JsamsDesktop.getInstance()
                .getCurrentSociety()));
        updateUI();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performButtonModify() {
        int selectedRow = getResultTable().getSelectedRow();
        if (selectedRow > -1) {
            int selectedRowModel = getResultTable().convertRowIndexToModel(selectedRow);
            CustomerTableModel model = (CustomerTableModel) getResultTable().getModel();
            CustomerBean beanToModify = model.getRow(selectedRowModel);
            if (debug) {
                LOGGER.debug("The customer to modify: " + beanToModify);
            }
            new EditCustomerDialog(JsamsI18nResource.TITLE_EDIT_CUSTOMER, beanToModify);
            updateUI();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performButtonRemove() {
        int selectedRow = getResultTable().getSelectedRow();
        if (selectedRow > -1) {
            int selectedRowModel = getResultTable().convertRowIndexToModel(selectedRow);
            CustomerTableModel model = (CustomerTableModel) getResultTable().getModel();
            CustomerBean beanToDelete = model.getRow(selectedRowModel);
            if (debug) {
                LOGGER.debug("The customer to delete: " + beanToDelete);
            }
            getService().delete(beanToDelete);
            model.remove(selectedRowModel);
            updateUI();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performOk() {
        final CustomerBean criteria = getModel();
        if (super.prePerformOk(criteria)) {
            List<CustomerBean> customers = ((CustomerService) super.getService()).findByCriteria(criteria);
            fillTable(customers);
            super.postPerformOk();
        }
    }

    /**
     * Fills the data table.
     * 
     * @param customers the {@link CustomerBean} list
     */
    private void fillTable(final List<CustomerBean> customers) {
        CustomerTableModel model = new CustomerTableModel(customers);
        getResultTable().setModel(model);
        getResultTable().setRowSorter(new TableRowSorter<CustomerTableModel>(model));

        JTableHeader tableHeader = getResultTable().getTableHeader();
        TableCellRenderer headerRenderer = tableHeader.getDefaultRenderer();

        ((DefaultTableCellRenderer) headerRenderer).setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        getResultTable().setAutoCreateRowSorter(true);
        JsamsTableCellRenderer defaultCellRenderer = new JsamsTableCellRenderer();
        getResultTable().setDefaultRenderer(Long.class, defaultCellRenderer);
        getResultTable().setDefaultRenderer(String.class, defaultCellRenderer);
        getResultTable().setDefaultRenderer(Double.class, defaultCellRenderer);
        getResultTable().setDefaultRenderer(String.class, defaultCellRenderer);

        getResultTable().repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performCancel() {
        // TODO Auto-generated method stub

    }

}
