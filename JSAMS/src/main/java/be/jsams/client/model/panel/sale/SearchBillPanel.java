package be.jsams.client.model.panel.sale;

import java.awt.event.MouseListener;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.dialog.sale.EditBillDialog;
import be.jsams.client.model.panel.AbstractSearchPanel;
import be.jsams.client.model.table.BillTableModel;
import be.jsams.client.validator.search.SearchBillValidator;
import be.jsams.common.bean.model.PaymentModeBean;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.sale.BillBean;
import be.jsams.server.service.sale.BillService;

/**
 * {@link AbstractSearchPanel} for {@link BillBean} objects.
 * 
 * @param <L> a customized {@link MouseListener}
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class SearchBillPanel<L extends MouseListener> extends
        AbstractSearchPanel<BillBean, L, BillService, SearchBillValidator, BillTableModel> {

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
     * @param listener the {@link MouseListener}
     * @param service the {@link BillService}
     * @param validator the {@link SearchBillValidator}
     * @param billTableModel the {@link BillTableModel}
     * @param showButtons a boolean that indicates if we have to display the buttons to manage the content: add, remove
     *            and modify
     */
    public SearchBillPanel(BillBean bean, L listener, BillService service,
            SearchBillValidator validator, BillTableModel billTableModel, boolean showButtons) {
        super(bean, listener, service, validator, billTableModel, showButtons);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performButtonAdd() {
        SocietyBean currentSociety = JsamsDesktop.getInstance().getCurrentSociety();
        CustomerBean customer = JsamsApplicationContext.getCustomerBeanBuilder().build(null, currentSociety);
        PaymentModeBean mode = JsamsApplicationContext.getPaymentModeBeanBuilder().build();
        BillBean bean = new BillBean(currentSociety, customer, mode);
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

//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    protected void performButtonRemove() {
//        int selectedRow = getResultTable().getSelectedRow();
//        if (selectedRow > -1) {
//            int selectedRowModel = getResultTable().convertRowIndexToModel(selectedRow);
//            BillTableModel model = (BillTableModel) getResultTable().getModel();
//            BillBean beanToDelete = model.getRow(selectedRowModel);
//            getService().delete(beanToDelete);
//            model.remove(selectedRowModel);
//            updateUI();
//        }
//    }

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
//        getResultTable().setModel(model);
        super.setTableModel(model);
        getResultTable().repaint();
    }

}
