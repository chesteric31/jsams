package be.jsams.client.model.panel;

import javax.swing.JPanel;

import be.jsams.client.swing.listener.CommandTableMouseListener;
import be.jsams.client.validator.EditCommandValidator;
import be.jsams.client.validator.SearchCommandValidator;
import be.jsams.common.bean.model.sale.CommandBean;
import be.jsams.server.service.CommandService;

/**
 * Search {@link JPanel} for {@link CommandBean} objects.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class SearchCommandPanel extends
        AbstractSearchPanel<CommandBean, CommandTableMouseListener, CommandService, EditCommandValidator> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -494667273780356685L;

    /**
     * Constructor.
     * 
     * @param model
     *            the {@link CommandBean}
     * @param listener
     *            the {@link CommandTableMouseListener}
     * @param service
     *            the {@link CommandService}
     * @param showButtons
     *            a boolean that indicates if we have to display the buttons to manage the content: add, remove and
     *            modify
     */
    public SearchCommandPanel(CommandBean model, CommandTableMouseListener listener, CommandService service,
            final boolean showButtons) {
        super(model, listener, service, showButtons);
        super.setValidator(new SearchCommandValidator());
        super.buildMainPanel(buildSearchCriteriaPanel());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected JPanel buildSearchCriteriaPanel() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performButtonAdd() {
        // TODO Auto-generated method stub
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performButtonModify() {
        // TODO Auto-generated method stub
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performButtonRemove() {
        // TODO Auto-generated method stub
        
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
        // TODO Auto-generated method stub
        
    }

}
