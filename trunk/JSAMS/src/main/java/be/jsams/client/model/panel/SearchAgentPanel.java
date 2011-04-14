package be.jsams.client.model.panel;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.dialog.EditAgentDialog;
import be.jsams.client.model.table.AgentTableModel;
import be.jsams.client.renderer.JsamsTableCellRenderer;
import be.jsams.client.swing.listener.AgentTableMouseListener;
import be.jsams.client.validator.EditAgentValidator;
import be.jsams.client.validator.SearchAgentValidator;
import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.server.service.AgentService;

/**
 * Search {@link JPanel} for agents objects.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class SearchAgentPanel extends
        AbstractSearchPanel<AgentBean, AgentTableMouseListener, AgentService, EditAgentValidator> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 4879600730360818739L;

    /**
     * Constructor.
     * 
     * @param model the {@link AgentBean}
     * @param listener the {@link AgentTableMouseListener}
     * @param service the {@link AgentService}
     * @param showButtons a boolean that indicates if we have to display the
     *            buttons to manage the content: add, remove and modify
     */
    public SearchAgentPanel(AgentBean model, AgentTableMouseListener listener, AgentService service,
            final boolean showButtons) {
        super(model, listener, service, showButtons);
        super.setValidator(new SearchAgentValidator());
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
        new EditAgentDialog(JsamsI18nResource.TITLE_EDIT_AGENT, new AgentBean(JsamsDesktop.getInstance()
                .getCurrentSociety()));
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
        if (super.prePerformOk(criteria)) {
            List<AgentBean> agents = ((AgentService) super.getService()).findByCriteria(criteria);
            fillTable(agents);
            super.postPerformOk();
        }
    }

    /**
     * Fills the data table.
     * 
     * @param agents the {@link AgentBean} list
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
