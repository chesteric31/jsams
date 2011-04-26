package be.jsams.client.model.panel.sale;

import java.util.Date;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.dialog.sale.EditEstimateDialog;
import be.jsams.client.model.panel.AbstractSearchPanel;
import be.jsams.client.model.table.EstimateTableModel;
import be.jsams.client.renderer.JsamsBooleanTableCellRenderer;
import be.jsams.client.renderer.JsamsTableCellRenderer;
import be.jsams.client.swing.listener.EstimateTableMouseListener;
import be.jsams.client.validator.EditEstimateValidator;
import be.jsams.client.validator.SearchEstimateValidator;
import be.jsams.common.bean.model.sale.EstimateBean;
import be.jsams.server.service.EstimateService;

/**
 * Search {@link JPanel} for {@link EstimateBean} objects.
 * 
 * @author chesteric31
 * @version $Rev: 711 $ $Date::                  $ $Author$
 */
public class SearchEstimatePanel extends
        AbstractSearchPanel<EstimateBean, EstimateTableMouseListener, EstimateService, EditEstimateValidator> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -7701480812937524634L;

    private static final Log LOGGER = LogFactory.getLog(SearchEstimatePanel.class);

    private final boolean debug = LOGGER.isDebugEnabled();

    /**
     * Constructor.
     * 
     * @param model
     *            the {@link EstimateBean}
     * @param listener
     *            the {@link EstimateTableMouseListener}
     * @param service
     *            the {@link EstimateService}
     * @param showButtons
     *            a boolean that indicates if we have to display the buttons to manage the content: add, remove and
     *            modify
     */
    public SearchEstimatePanel(EstimateBean model, EstimateTableMouseListener listener, EstimateService service,
            final boolean showButtons) {
        super(model, listener, service, showButtons);
        super.setValidator(new SearchEstimateValidator());
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
        new EditEstimateDialog(JsamsI18nResource.TITLE_EDIT_ESTIMATE, new EstimateBean());
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
    protected void performButtonRemove() {
        int selectedRow = getResultTable().getSelectedRow();
        if (selectedRow > -1) {
            int selectedRowModel = getResultTable().convertRowIndexToModel(selectedRow);
            EstimateTableModel model = (EstimateTableModel) getResultTable().getModel();
            EstimateBean beanToDelete = model.getRow(selectedRowModel);
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
     * @param estimates
     *            the {@link EstimateBean} list
     */
    private void fillTable(final List<EstimateBean> estimates) {
        EstimateTableModel model = new EstimateTableModel(estimates);
        getResultTable().setModel(model);

        JTableHeader tableHeader = getResultTable().getTableHeader();
        TableCellRenderer headerRenderer = tableHeader.getDefaultRenderer();

        ((DefaultTableCellRenderer) headerRenderer).setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        getResultTable().setAutoCreateRowSorter(true);
        JsamsTableCellRenderer defaultCellRenderer = new JsamsTableCellRenderer();
        getResultTable().setDefaultRenderer(Long.class, defaultCellRenderer);
        getResultTable().setDefaultRenderer(Integer.class, defaultCellRenderer);
        getResultTable().setDefaultRenderer(Double.class, defaultCellRenderer);
        getResultTable().setDefaultRenderer(String.class, defaultCellRenderer);
        getResultTable().setDefaultRenderer(Boolean.class, new JsamsBooleanTableCellRenderer());
        getResultTable().setDefaultRenderer(Date.class, defaultCellRenderer);

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
