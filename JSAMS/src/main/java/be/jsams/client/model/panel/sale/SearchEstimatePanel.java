package be.jsams.client.model.panel.sale;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.dialog.sale.EditEstimateDialog;
import be.jsams.client.model.panel.AbstractSearchPanel;
import be.jsams.client.model.table.EstimateTableModel;
import be.jsams.client.swing.listener.EstimateTableMouseListener;
import be.jsams.client.validator.SearchEstimateValidator;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.sale.EstimateBean;
import be.jsams.server.service.sale.EstimateService;

/**
 * {@link AbstractSearchPanel} for {@link EstimateBean} objects.
 * 
 * @author chesteric31
 * @version $Rev: 711 $ $Date::                  $ $Author$
 */
public class SearchEstimatePanel extends
        AbstractSearchPanel<EstimateBean, EstimateTableMouseListener,
        EstimateService, SearchEstimateValidator, EstimateTableModel> {

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
     * @param listener the {@link EstimateTableMouseListener}
     * @param service the {@link EstimateService}
     * @param validator the {@link SearchEstimateValidator} 
     * @param estimateTableModel the {@link EstimateTableModel}
     * @param showButtons a boolean that indicates if we have to display the
     *            buttons to manage the content: add, remove and modify
     */
    public SearchEstimatePanel(EstimateBean model, EstimateTableMouseListener listener, EstimateService service,
            SearchEstimateValidator validator, EstimateTableModel estimateTableModel, final boolean showButtons) {
        super(model, listener, service, validator, estimateTableModel, showButtons);
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

//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    protected void performButtonRemove() {
//        int selectedRow = getResultTable().getSelectedRow();
//        if (selectedRow > -1) {
//            int selectedRowModel = getResultTable().convertRowIndexToModel(selectedRow);
//            EstimateTableModel model = (EstimateTableModel) getResultTable().getModel();
//            EstimateBean beanToDelete = model.getRow(selectedRowModel);
//            getService().delete(beanToDelete);
//            model.remove(selectedRowModel);
//            updateUI();
//        }
//    }

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
//        getResultTable().setModel(model);
        super.setTableModel(model);
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
