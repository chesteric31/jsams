package be.jsams.client.model.panel.management;

import java.util.List;

import javax.swing.ListSelectionModel;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.dialog.management.EditAgentDialog;
import be.jsams.client.model.panel.AbstractSearchPanel;
import be.jsams.client.model.table.AgentTableModel;
import be.jsams.client.swing.listener.search.AgentTableMouseListener;
import be.jsams.client.validator.search.SearchAgentValidator;
import be.jsams.common.bean.builder.management.AgentBeanBuilder;
import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.server.service.management.AgentService;

/**
 * {@link AbstractSearchPanel} for agents objects.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class SearchAgentPanel extends
        AbstractSearchPanel<
        AgentBean, AgentTableMouseListener, AgentService, SearchAgentValidator, AgentTableModel> {

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
     * @param validator the {@link SearchAgentValidator}
     * @param agentTableModel the {@link AgentTableModel}
     * @param showButtons a boolean that indicates if we have to display the
     *            buttons to manage the content: add, remove and modify
     * @param selectionMode the selection mode to use
     */
    public SearchAgentPanel(AgentBean model, AgentTableMouseListener listener, AgentService service,
            SearchAgentValidator validator, AgentTableModel agentTableModel, final boolean showButtons,
            int selectionMode) {
        super(model, listener, service, validator, agentTableModel, showButtons, selectionMode);
    }

    /**
     * Constructor.
     * 
     * @param model the {@link AgentBean}
     * @param listener the {@link AgentTableMouseListener}
     * @param service the {@link AgentService}
     * @param validator the {@link SearchAgentValidator}
     * @param agentTableModel the {@link AgentTableModel}
     */
    public SearchAgentPanel(AgentBean model, AgentTableMouseListener listener, AgentService service,
            SearchAgentValidator validator, AgentTableModel agentTableModel) {
        this(model, listener, service, validator, agentTableModel, true, ListSelectionModel.SINGLE_SELECTION);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performButtonAdd() {
        AgentBeanBuilder builder = JsamsApplicationContext.getAgentBeanBuilder();
        AgentBean bean = builder.build(null, JsamsDesktop.getInstance().getCurrentSociety());
        new EditAgentDialog(JsamsI18nResource.TITLE_EDIT_AGENT, bean);
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

//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    protected void performButtonRemove() {
//        int selectedRow = getResultTable().getSelectedRow();
//        if (selectedRow > -1) {
//            int selectedRowModel = getResultTable().convertRowIndexToModel(selectedRow);
//            AgentTableModel model = (AgentTableModel) getResultTable().getModel();
//            AgentBean beanToDelete = model.getRow(selectedRowModel);
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
//      getResultTable().setModel(model);
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
