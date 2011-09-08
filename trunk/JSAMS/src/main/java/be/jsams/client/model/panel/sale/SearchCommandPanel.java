package be.jsams.client.model.panel.sale;

import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.ListSelectionModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.dialog.sale.EditCommandDialog;
import be.jsams.client.model.panel.AbstractSearchPanel;
import be.jsams.client.model.table.CommandTableModel;
import be.jsams.client.validator.search.SearchCommandValidator;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.sale.CommandBean;
import be.jsams.server.service.sale.CommandService;

/**
 * {@link AbstractSearchPanel} for {@link CommandBean} objects.
 * 
 * @param <L> a customized {@link MouseListener}
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class SearchCommandPanel<L extends MouseListener> extends
        AbstractSearchPanel<CommandBean, L, CommandService, SearchCommandValidator, CommandTableModel> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -494667273780356685L;

    private static final Log LOGGER = LogFactory.getLog(SearchCommandPanel.class);
    private final boolean debug = LOGGER.isDebugEnabled();

    /**
     * Constructor.
     * 
     * @param model the {@link CommandBean}
     * @param listener the {@link MouseListener}
     * @param service the {@link CommandService}
     * @param validator the {@link SearchCommandValidator}
     * @param commandTableModel the {@link CommandTableModel}
     * @param showButtons a boolean that indicates if we have to display the
     *            buttons to manage the content: add, remove and modify
     * @param selectionMode the selection mode to use
     */
    public SearchCommandPanel(CommandBean model, L listener, CommandService service, SearchCommandValidator validator,
            CommandTableModel commandTableModel, final boolean showButtons, int selectionMode) {
        super(model, listener, service, validator, commandTableModel, showButtons, selectionMode);
    }

    /**
     * Constructor
     * 
     * @param bean the {@link CommandBean}
     * @param listener the {@link MouseListener}
     * @param service the {@link CommandService}
     * @param validator the {@link SearchCommandValidator}
     * @param commandTableModel the {@link CommandTableModel}
     */
    public SearchCommandPanel(CommandBean bean, L listener, CommandService service,
            SearchCommandValidator validator, CommandTableModel commandTableModel) {
        super(bean, listener, service, validator, commandTableModel, true, ListSelectionModel.SINGLE_SELECTION);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performButtonAdd() {
        SocietyBean currentSociety = JsamsDesktop.getInstance().getCurrentSociety();
        CustomerBean customer = JsamsApplicationContext.getCustomerBeanBuilder().build(null, currentSociety);
        AgentBean agent = JsamsApplicationContext.getAgentBeanBuilder().build(null, currentSociety);
        CommandBean bean = new CommandBean(currentSociety, customer, agent);
        new EditCommandDialog(JsamsI18nResource.TITLE_EDIT_COMMAND, bean);
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
            CommandTableModel model = (CommandTableModel) getResultTable().getModel();
            CommandBean beanToModify = model.getRow(selectedRowModel);
            if (debug) {
                LOGGER.debug("The command to modify: " + beanToModify);
            }
            new EditCommandDialog(JsamsI18nResource.TITLE_EDIT_COMMAND, beanToModify);
            updateUI();
        }
    }

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
        final CommandBean criteria = getModel();
        if (super.prePerformOk(criteria)) {
            List<CommandBean> commands = ((CommandService) super.getService()).findByCriteria(criteria);
            fillTable(commands);
            super.postPerformOk();
        }
    }

    /**
     * Fills the data table.
     * 
     * @param commands the {@link CommandBean} list
     */
    private void fillTable(final List<CommandBean> commands) {
        CommandTableModel model = new CommandTableModel(commands);
//        getResultTable().setModel(model);
        super.setTableModel(model);
        getResultTable().repaint();
    }

}
