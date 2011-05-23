package be.jsams.server.model.sale.detail;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import be.jsams.common.bean.model.sale.detail.CommandDetailBean;
import be.jsams.server.model.sale.Command;

/**
 * Command detail (line) entity object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@Entity
@Table(name = "COMMAND_DETAIL")
public class CommandDetail extends AbstractDetail {

    private boolean transferred;

    private Command command;

    /**
     * Constructor.
     */
    public CommandDetail() {
        super();
    }

    /**
     * Constructor.
     * 
     * @param bean the {@link CommandDetailBean}
     * @param command the {@link Command} model
     */
    public CommandDetail(final CommandDetailBean bean, final Command command) {
        super(bean);
        this.transferred = bean.isTransferred();
    }

    /**
     * 
     * @return true if the {@link CommandDetail} was transferred to a bill
     *         detail, false otherwise
     */
    @Column(name = "TRANSFERRED")
    public boolean isTransferred() {
        return transferred;
    }

    /**
     * 
     * @param transferred the transferred boolean to set (true, this
     *            {@link CommandDetail} is transferred to a bill detail, false
     *            otherwise
     */
    public void setTransferred(boolean transferred) {
        this.transferred = transferred;
    }

    /**
     * 
     * @return the {@link Command}
     */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "FK_COMMAND")
    public Command getCommand() {
        return command;
    }

    /**
     * 
     * @param command the {@link Command} to set
     */
    public void setCommand(Command command) {
        this.command = command;
    }

}
