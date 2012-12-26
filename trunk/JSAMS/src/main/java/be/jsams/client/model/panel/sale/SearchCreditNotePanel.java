package be.jsams.client.model.panel.sale;

import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.ListSelectionModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.client.context.ApplicationContext;
import be.jsams.client.desktop.Desktop;
import be.jsams.client.i18n.I18nResource;
import be.jsams.client.model.dialog.sale.EditCreditNoteDialog;
import be.jsams.client.model.table.sale.CreditNoteTableModel;
import be.jsams.client.validator.search.sale.SearchCreditNoteValidator;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.sale.CreditNoteBean;
import be.jsams.common.bean.model.sale.CreditNoteMediator;
import be.jsams.server.service.pdf.impl.PdfCreditNoteServiceImpl;
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
     * Constructor.
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
     * Constructor.
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
        SocietyBean currentSociety = Desktop.getInstance().getCurrentSociety();
        CustomerBean customerBean = ApplicationContext.getCustomerBeanBuilder().build(null, currentSociety);
        CreditNoteBean bean = new CreditNoteBean(currentSociety, customerBean);
        CreditNoteMediator mediator = new CreditNoteMediator();
        mediator.setCreditNoteBean(bean);
        bean.setMediator(mediator);
        new EditCreditNoteDialog(I18nResource.TITLE_EDIT_CREDIT_NOTE, bean);
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
            CreditNoteMediator mediator = new CreditNoteMediator();
            mediator.setCreditNoteBean(beanToModify);
            beanToModify.setMediator(mediator);
            if (debug) {
                LOGGER.debug("The credit note to modify: " + beanToModify);
            }
            new EditCreditNoteDialog(I18nResource.TITLE_EDIT_CREDIT_NOTE, beanToModify);
            updateUI();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performCancel() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performOk() {
        final CreditNoteBean criteria = getModel();
        if (super.prePerformOk(criteria)) {
            List<CreditNoteBean> creditNotes = ((CreditNoteService) super.getService()).findByCriteria(criteria);
            fillTable(creditNotes);
            if (creditNotes != null && !creditNotes.isEmpty()) {
                for (CreditNoteBean bean : creditNotes) {
                    CreditNoteMediator mediator = new CreditNoteMediator();
                    mediator.setCreditNoteBean(bean);
                    bean.setMediator(mediator);
                }
            }
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
        super.setTableModel(model);
        getResultTable().repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String performButtonPdf(boolean viewReport, CreditNoteBean bean) {
        String pdf = "";
        if (bean != null) {
            PdfCreditNoteServiceImpl pdfService = ApplicationContext.getPdfCreditNoteService();
            pdf = pdfService.generatePdf(bean, viewReport);
        }
        return pdf;
    }
}
