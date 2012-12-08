package be.jsams.client.model.panel.management;

import java.util.List;

import javax.swing.ListSelectionModel;
import javax.swing.table.TableRowSorter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.client.context.ApplicationContext;
import be.jsams.client.desktop.Desktop;
import be.jsams.client.i18n.I18nResource;
import be.jsams.client.model.dialog.management.EditCustomerDialog;
import be.jsams.client.model.panel.AbstractSearchPanel;
import be.jsams.client.model.table.management.CustomerTableModel;
import be.jsams.client.swing.listener.search.management.CustomerTableMouseListener;
import be.jsams.client.validator.search.management.SearchCustomerValidator;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.server.service.management.CustomerService;

/**
 * {@link AbstractSearchPanel} for Customer objects.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class SearchCustomerPanel
        extends AbstractSearchPanel<CustomerBean, CustomerTableMouseListener,
        CustomerService, SearchCustomerValidator, CustomerTableModel> {

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
     * @param validator the {@link SearchCustomerValidator}
     * @param customerTableModel the {@link CustomerTableModel}
     * @param showButtons a boolean that indicates if we have to display the
     *            buttons to manage the content: add, remove and modify
     * @param selectionMode the selection mode to use
     */
    public SearchCustomerPanel(CustomerBean model, CustomerTableMouseListener listener, CustomerService service,
            SearchCustomerValidator validator, CustomerTableModel customerTableModel, final boolean showButtons,
            int selectionMode) {
        super(model, listener, service, validator, customerTableModel, showButtons, selectionMode);
    }

    /**
     * Constructor.
     * 
     * @param model the {@link CustomerBean}
     * @param listener the {@link CustomerTableMouseListener}
     * @param service the {@link CustomerService}
     * @param validator the {@link SearchCustomerValidator}
     * @param customerTableModel the {@link CustomerTableModel}
     */
    public SearchCustomerPanel(CustomerBean model, CustomerTableMouseListener listener, CustomerService service,
            SearchCustomerValidator validator, CustomerTableModel customerTableModel) {
        this(model, listener, service, validator, customerTableModel, true, ListSelectionModel.SINGLE_SELECTION);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performButtonAdd() {
        CustomerBean customerBean = ApplicationContext.getCustomerBeanBuilder().build(null,
                Desktop.getInstance().getCurrentSociety());
        new EditCustomerDialog(I18nResource.TITLE_EDIT_CUSTOMER, customerBean);
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
            new EditCustomerDialog(I18nResource.TITLE_EDIT_CUSTOMER, beanToModify);
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
        super.setTableModel(model);
        getResultTable().setRowSorter(new TableRowSorter<CustomerTableModel>(model));
        getResultTable().repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performCancel() {
    }

}
