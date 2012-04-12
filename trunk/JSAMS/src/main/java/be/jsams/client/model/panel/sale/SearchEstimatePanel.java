package be.jsams.client.model.panel.sale;

import java.awt.event.MouseListener;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.dialog.sale.EditEstimateDialog;
import be.jsams.client.model.table.EstimateTableModel;
import be.jsams.client.validator.search.SearchEstimateValidator;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.sale.EstimateBean;
import be.jsams.server.service.pdf.impl.PdfEstimateServiceImpl;
import be.jsams.server.service.sale.EstimateService;

/**
 * {@link AbstractSaleSearchPanel} for {@link EstimateBean} objects.
 * 
 * @param <L> a customized {@link MouseListener}
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class SearchEstimatePanel<L extends MouseListener> extends
        AbstractSaleSearchPanel<EstimateBean, L, EstimateService, SearchEstimateValidator, EstimateTableModel> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -7701480812937524634L;

    private static final Log LOGGER = LogFactory.getLog(SearchEstimatePanel.class);
    private final boolean debug = LOGGER.isDebugEnabled();

    /**
     * Constructor.
     * 
     * @param model the {@link EstimateBean}
     * @param listener the {@link MouseListener}
     * @param service the {@link EstimateService}
     * @param validator the {@link SearchEstimateValidator}
     * @param estimateTableModel the {@link EstimateTableModel}
     * @param showButtons a boolean that indicates if we have to display the
     *            buttons to manage the content: add, remove and modify
     * @param selectionMode the selection mode to use
     */
    public SearchEstimatePanel(EstimateBean model, L listener, EstimateService service,
            SearchEstimateValidator validator, EstimateTableModel estimateTableModel, final boolean showButtons,
            int selectionMode) {
        super(model, listener, service, validator, estimateTableModel, showButtons, selectionMode);
    }

    /**
     * Constructor.
     * 
     * @param model the {@link EstimateBean}
     * @param listener the {@link MouseListener}
     * @param service the {@link EstimateService}
     * @param validator the {@link SearchEstimateValidator}
     * @param estimateTableModel the {@link EstimateTableModel}
     */
    public SearchEstimatePanel(EstimateBean model, L listener, EstimateService service,
            SearchEstimateValidator validator, EstimateTableModel estimateTableModel) {
        super(model, listener, service, validator, estimateTableModel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performButtonAdd() {
        SocietyBean currentSociety = JsamsDesktop.getInstance().getCurrentSociety();
        CustomerBean customer = JsamsApplicationContext.getCustomerBeanBuilder().build(null, currentSociety);
        AgentBean agent = JsamsApplicationContext.getAgentBeanBuilder().build(null, currentSociety);
        EstimateBean bean = new EstimateBean(currentSociety, customer, agent);
        new EditEstimateDialog(JsamsI18nResource.TITLE_EDIT_ESTIMATE, bean);
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
            EstimateTableModel model = (EstimateTableModel) getResultTable().getModel();
            EstimateBean beanToModify = model.getRow(selectedRowModel);
            if (debug) {
                LOGGER.debug("The estimate to modify: " + beanToModify);
            }
            new EditEstimateDialog(JsamsI18nResource.TITLE_EDIT_ESTIMATE, beanToModify);
            updateUI();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performOk() {
        final EstimateBean criteria = getModel();
        if (super.prePerformOk(criteria)) {
            List<EstimateBean> estimates = ((EstimateService) super.getService()).findByCriteria(criteria);
            fillTable(estimates);
            super.postPerformOk();
        }
    }

    /**
     * Fills the data table.
     * 
     * @param estimates the {@link EstimateBean} list
     */
    private void fillTable(final List<EstimateBean> estimates) {
        EstimateTableModel model = new EstimateTableModel(estimates);
        super.setTableModel(model);
        getResultTable().repaint();
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
    protected void performButtonPdf() {
        int selectedRow = getResultTable().getSelectedRow();
        if (selectedRow > -1) {
            int selectedRowModel = getResultTable().convertRowIndexToModel(selectedRow);
            EstimateTableModel model = (EstimateTableModel) getResultTable().getModel();
            EstimateBean beanToPdf = model.getRow(selectedRowModel);
            PdfEstimateServiceImpl pdfService = JsamsApplicationContext.getPdfEstimateService();
            pdfService.generatePdf(beanToPdf);
        }
    }

}
