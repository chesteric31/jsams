package be.jsams.client.model.panel.sale;

import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.ListSelectionModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.dialog.sale.EditCreditNoteDialog;
import be.jsams.client.model.table.CreditNoteTableModel;
import be.jsams.client.validator.search.SearchCreditNoteValidator;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.sale.CreditNoteBean;
import be.jsams.server.service.sale.CreditNoteService;

/**
 * {@link AbstractSaleSearchPanel} for {@link CreditNoteBean} objects.
 *
 * @param <L> a customized {@link MouseListener}
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class SearchCreditNotePanel<L extends MouseListener> extends
        AbstractSaleSearchPanel<CreditNoteBean, L, CreditNoteService, SearchCreditNoteValidator, CreditNoteTableModel> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 2354692292285708027L;
    
    private static final Log LOGGER = LogFactory.getLog(SearchCreditNotePanel.class);
    private final boolean debug = LOGGER.isDebugEnabled();

    /**
     * Constructor
     * 
     * @param bean the {@link CreditNoteBean}
     * @param listener the {@link MouseListener}
     * @param service the {@link CreditNoteService}
     * @param validator the {@link SearchCreditNoteValidator}
     * @param creditNoteTableModel the {@link CreditNoteTableModel}
     * @param showButtons a boolean that indicates if we have to display the
     *            buttons to manage the content: add, remove and modify
     * @param selectionMode the selection mode to use
     */
    public SearchCreditNotePanel(CreditNoteBean bean, L listener, CreditNoteService service,
            SearchCreditNoteValidator validator, CreditNoteTableModel creditNoteTableModel, boolean showButtons,
            int selectionMode) {
        super(bean, listener, service, validator, creditNoteTableModel, showButtons, selectionMode);
    }

    /**
     * Constructor
     * 
     * @param bean the {@link CreditNoteBean}
     * @param listener the {@link MouseListener}
     * @param service the {@link CreditNoteService}
     * @param validator the {@link SearchCreditNoteValidator}
     * @param creditNoteTableModel the {@link CreditNoteTableModel}
     */
    public SearchCreditNotePanel(CreditNoteBean bean, L listener, CreditNoteService service,
            SearchCreditNoteValidator validator, CreditNoteTableModel creditNoteTableModel) {
        this(bean, listener, service, validator, creditNoteTableModel, true, ListSelectionModel.SINGLE_SELECTION);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performButtonAdd() {
        SocietyBean currentSociety = JsamsDesktop.getInstance().getCurrentSociety();
        CustomerBean customerBean = JsamsApplicationContext.getCustomerBeanBuilder().build(null, currentSociety);
        CreditNoteBean bean = new CreditNoteBean(currentSociety, customerBean);
        new EditCreditNoteDialog(JsamsI18nResource.TITLE_EDIT_CREDIT_NOTE, bean);
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
            CreditNoteTableModel model = (CreditNoteTableModel) getResultTable().getModel();
            CreditNoteBean beanToModify = model.getRow(selectedRowModel);
            if (debug) {
                LOGGER.debug("The credit note to modify: " + beanToModify);
            }
            new EditCreditNoteDialog(JsamsI18nResource.TITLE_EDIT_CREDIT_NOTE, beanToModify);
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
//            CreditNoteTableModel model = (CreditNoteTableModel) getResultTable().getModel();
//            CreditNoteBean beanToDelete = model.getRow(selectedRowModel);
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
        final CreditNoteBean criteria = getModel();
        if (super.prePerformOk(criteria)) {
            List<CreditNoteBean> commands = ((CreditNoteService) super.getService()).findByCriteria(criteria);
            fillTable(commands);
            super.postPerformOk();
        }
    }

    /**
     * Fills the data table.
     * 
     * @param creditNotes the {@link CreditNoteBean} list
     */
    private void fillTable(final List<CreditNoteBean> creditNotes) {
        CreditNoteTableModel model = new CreditNoteTableModel(creditNotes);
//        getResultTable().setModel(model);
        super.setTableModel(model);
        getResultTable().repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performButtonPdf() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

}
