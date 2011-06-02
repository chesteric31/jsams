package be.jsams.common.bean.model.sale.detail;

import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.model.sale.CommandBean;
import be.jsams.common.bean.view.sale.detail.CommandDetailBeanView;
import be.jsams.server.model.sale.detail.CommandDetail;

/**
 * {@link AbstractIdentityBean} for {@link CommandDetail} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CommandDetailBean extends AbstractDetailBean<CommandDetail, CommandDetailBeanView, CommandBean> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 6666469865590217242L;

    private boolean transferred;

    private CommandBean command;

    public static final String TRANSFERRED_PROPERTY = "transferred";

    // private ObservableList<CommandDetailBean> list = new
    // ArrayListModel<CommandDetailBean>();

    /**
     * Default constructor
     */
    public CommandDetailBean() {
        super();
    }

    /**
     * Constructor
     * 
     * @param model the {@link CommandDetail}
     * @param command the {@link CommandBean}
     */
    public CommandDetailBean(CommandDetail model, CommandBean command) {
        super(model, command);
        this.transferred = model.isTransferred();
    }

    /**
     * @return the transferred
     */
    public boolean isTransferred() {
        return transferred;
    }

    /**
     * @param transferred the transferred to set
     */
    public void setTransferred(boolean transferred) {
        boolean oldValue = this.transferred;
        this.transferred = transferred;
        firePropertyChange(TRANSFERRED_PROPERTY, oldValue, this.transferred);
    }

    /**
     * @return the command
     */
    public CommandBean getCommand() {
        return command;
    }

    /**
     * @param command the command to set
     */
    public void setCommand(CommandBean command) {
        this.command = command;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandDetailBeanView getView() {
        return new CommandDetailBeanView(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        super.clear();
        setTransferred(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh(AbstractIdentityBean<?, ?> bean) {
        super.refresh(bean);
        CommandDetailBean other = (CommandDetailBean) bean;
        command.refresh(other.getCommand());
        setTransferred(other.isTransferred());
    }

}
