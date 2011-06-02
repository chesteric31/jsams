package be.jsams.client.model.panel.sale;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.dialog.sale.EditCreditNoteDialog;
import be.jsams.client.model.panel.AbstractSearchPanel;
import be.jsams.client.model.table.CreditNoteTableModel;
import be.jsams.client.swing.listener.CreditNoteTableMouseListener;
import be.jsams.client.validator.SearchCreditNoteValidator;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.sale.CreditNoteBean;
import be.jsams.server.service.sale.CreditNoteService;

/**
 * {@link AbstractSearchPanel} for {@link CreditNoteBean} objects.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class SearchCreditNotePanel extends
        AbstractSearchPanel<CreditNoteBean, CreditNoteTableMouseListener,
        CreditNoteService, SearchCreditNoteValidator, CreditNoteTableModel> {

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
     * @param listener the {@link CreditNoteTableMouseListener}
     * @param service the {@link CreditNoteService}
     * @param validator the {@link SearchCreditNoteValidator}
     * @param creditNoteTableModel the {@link CreditNoteTableModel}
     * @param showButtons a boolean that indicates if we have to display the
     *            buttons to manage the content: add, remove and modify
     */
    public SearchCreditNotePanel(CreditNoteBean bean, CreditNoteTableMouseListener listener, CreditNoteService service,
            SearchCreditNoteValidator validator, CreditNoteTableModel creditNoteTableModel, boolean showButtons) {
        super(bean, listener, service, validator, creditNoteTableModel, showButtons);
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

}
