package be.jsams.client.model.panel;

import java.math.BigDecimal;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.dialog.EditAgentDialog;
import be.jsams.client.model.table.AgentTableModel;
import be.jsams.client.renderer.JsamsTableCellRenderer;
import be.jsams.client.swing.listener.AgentTableMouseListener;
import be.jsams.common.bean.model.AgentBean;
import be.jsams.server.service.AgentService;

/**
 * Search {@link JPanel} for agents objects.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class SearchAgentPanel extends SearchPanel<AgentBean, AgentTableMouseListener, AgentService> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 4879600730360818739L;

    private static final Log LOGGER = LogFactory.getLog(SearchAgentPanel.class);

    private boolean debug = LOGGER.isDebugEnabled();

    /**
     * Constructor.
     * 
     * @param model
     *            the {@link AgentBean}
     * @param listener
     *            the {@link AgentTableMouseListener}
     * @param service
     *            the {@link AgentService}
     * @param showButtons
     *            a boolean that indicates if we have to display the buttons to manage the content: add, remove and
     *            modify
     */
    public SearchAgentPanel(AgentBean model, AgentTableMouseListener listener, AgentService service,
            final boolean showButtons) {
        super(model, listener, service, showButtons);
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
        new EditAgentDialog(JsamsI18nResource.TITLE_EDIT_AGENT, new AgentBean());
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
            AgentTableModel model = (AgentTableModel) getResultTable().getModel();
            AgentBean beanToModify = model.getRow(selectedRowModel);
            if (debug) {
                LOGGER.debug("The agent to modify: " + beanToModify);
            }
            new EditAgentDialog(JsamsI18nResource.TITLE_EDIT_AGENT, beanToModify);
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
            AgentTableModel model = (AgentTableModel) getResultTable().getModel();
            AgentBean beanToDelete = model.getRow(selectedRowModel);
            if (debug) {
                LOGGER.debug("The agent to delete: " + beanToDelete);
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
        final AgentBean criteria = getModel();
        List<AgentBean> agents = ((AgentService) super.getService()).findByCriteria(criteria);

        fillTable(agents);
    }

    /**
     * Fills the data table.
     * 
     * @param agents
     *            the {@link AgentBean} list
     */
    private void fillTable(final List<AgentBean> agents) {
        AgentTableModel model = new AgentTableModel(agents);
        getResultTable().setModel(model);

        JTableHeader tableHeader = getResultTable().getTableHeader();
        TableCellRenderer headerRenderer = tableHeader.getDefaultRenderer();

        ((DefaultTableCellRenderer) headerRenderer).setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        getResultTable().setAutoCreateRowSorter(true);
        JsamsTableCellRenderer defaultCellRenderer = new JsamsTableCellRenderer();
        getResultTable().setDefaultRenderer(Long.class, defaultCellRenderer);
        getResultTable().setDefaultRenderer(Integer.class, defaultCellRenderer);
        getResultTable().setDefaultRenderer(Double.class, defaultCellRenderer);
        getResultTable().setDefaultRenderer(BigDecimal.class, defaultCellRenderer);
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
