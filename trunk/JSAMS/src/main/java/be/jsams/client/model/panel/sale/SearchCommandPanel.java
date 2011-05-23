package be.jsams.client.model.panel.sale;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.dialog.sale.EditCommandDialog;
import be.jsams.client.model.panel.AbstractSearchPanel;
import be.jsams.client.model.table.CommandTableModel;
import be.jsams.client.swing.listener.CommandTableMouseListener;
import be.jsams.client.validator.SearchCommandValidator;
import be.jsams.common.bean.model.sale.CommandBean;
import be.jsams.server.service.sale.CommandService;

/**
 * {@link AbstractSearchPanel} for {@link CommandBean} objects.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class SearchCommandPanel extends
        AbstractSearchPanel<CommandBean, CommandTableMouseListener, CommandService, SearchCommandValidator> {

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
     * @param listener the {@link CommandTableMouseListener}
     * @param service the {@link CommandService}
     * @param validator the {@link SearchCommandValidator}
     * @param showButtons a boolean that indicates if we have to display the
     *            buttons to manage the content: add, remove and modify
     */
    public SearchCommandPanel(CommandBean model, CommandTableMouseListener listener, CommandService service,
            SearchCommandValidator validator, final boolean showButtons) {
        super(model, listener, service, validator, showButtons);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performButtonAdd() {
        CommandBean bean = new CommandBean(JsamsDesktop.getInstance().getCurrentSociety());
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
    protected void performButtonRemove() {
        int selectedRow = getResultTable().getSelectedRow();
        if (selectedRow > -1) {
            int selectedRowModel = getResultTable().convertRowIndexToModel(selectedRow);
            CommandTableModel model = (CommandTableModel) getResultTable().getModel();
            CommandBean beanToDelete = model.getRow(selectedRowModel);
            getService().delete(beanToDelete);
            model.remove(selectedRowModel);
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
        getResultTable().setModel(model);
        getResultTable().repaint();
    }

}
