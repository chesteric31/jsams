package be.jsams.client.model.panel.sale;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.dialog.sale.EditBillDialog;
import be.jsams.client.model.panel.AbstractSearchPanel;
import be.jsams.client.model.table.BillTableModel;
import be.jsams.client.swing.listener.BillTableMouseListener;
import be.jsams.client.validator.SearchBillValidator;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.sale.BillBean;
import be.jsams.server.service.sale.BillService;

/**
 * {@link AbstractSearchPanel} for {@link BillBean} objects.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class SearchBillPanel extends
        AbstractSearchPanel<BillBean, BillTableMouseListener, BillService, SearchBillValidator> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 4113451180402839180L;

    private static final Log LOGGER = LogFactory.getLog(SearchBillPanel.class);

    private final boolean debug = LOGGER.isDebugEnabled();

    /**
     * Constructor
     * 
     * @param bean the {@link BillBean}
     * @param listener the {@link BillTableMouseListener}
     * @param service the {@link BillService}
     * @param validator the {@link SearchBillValidator}
     * @param showButtons a boolean that indicates if we have to display the buttons to manage the content: add, remove
     *            and modify
     */
    public SearchBillPanel(BillBean bean, BillTableMouseListener listener, BillService service,
            SearchBillValidator validator, boolean showButtons) {
        super(bean, listener, service, validator, showButtons);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performButtonAdd() {
        SocietyBean currentSociety = JsamsDesktop.getInstance().getCurrentSociety();
        CustomerBean customerBean = JsamsApplicationContext.getCustomerBeanBuilder().build(null, currentSociety);
        BillBean bean = new BillBean(currentSociety, customerBean);
        new EditBillDialog(JsamsI18nResource.TITLE_EDIT_BILL, bean);
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
            BillTableModel model = (BillTableModel) getResultTable().getModel();
            BillBean beanToModify = model.getRow(selectedRowModel);
            if (debug) {
                LOGGER.debug("The bill to modify: " + beanToModify);
            }
            new EditBillDialog(JsamsI18nResource.TITLE_EDIT_BILL, beanToModify);
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
            BillTableModel model = (BillTableModel) getResultTable().getModel();
            BillBean beanToDelete = model.getRow(selectedRowModel);
            getService().delete(beanToDelete);
            model.remove(selectedRowModel);
            updateUI();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performCancel() {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performOk() {
        final BillBean criteria = getModel();
        if (super.prePerformOk(criteria)) {
            List<BillBean> bills = ((BillService) super.getService()).findByCriteria(criteria);
            fillTable(bills);
            super.postPerformOk();
        }
    }

    /**
     * Fills the data table.
     * 
     * @param bills the {@link BillBean} list
     */
    private void fillTable(final List<BillBean> bills) {
        BillTableModel model = new BillTableModel(bills);
        getResultTable().setModel(model);
        getResultTable().repaint();
    }

}
