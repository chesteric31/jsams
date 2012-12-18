package be.jsams.common.bean.model.sale.detail;

import be.jsams.common.bean.builder.ProductBeanBuilder;
import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.model.sale.CommandBean;
import be.jsams.common.bean.model.sale.CommandMediator;
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
    
    private CommandMediator mediator;

    /**
     * Default constructor.
     */
    public CommandDetailBean() {
        super();
        setView(buildView());
    }

    /**
     * Constructor.
     * 
     * @param model the {@link CommandDetail}
     * @param command the {@link CommandBean}
     * @param productBeanBuilder the {@link ProductBeanBuilder}
     */
    public CommandDetailBean(CommandDetail model, CommandBean command, ProductBeanBuilder productBeanBuilder) {
        super(model, command, productBeanBuilder);
        this.command = command;
        this.transferred = model.isTransferred();
        setView(buildView());
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
    public CommandDetailBeanView buildView() {
        return new CommandDetailBeanView(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        super.clear();
        setTransferred(false);
        setCommand(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh(AbstractIdentityBean<?, ?> bean) {
        super.refresh(bean);
        CommandDetailBean other = (CommandDetailBean) bean;
        if (other != null) {
            command.refresh(other.getCommand());
            setTransferred(other.isTransferred());
        }
    }

    /**
     * @return the mediator
     */
    public CommandMediator getMediator() {
        return mediator;
    }

    /**
     * @param mediator the mediator to set
     */
    public void setMediator(CommandMediator mediator) {
        this.mediator = mediator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setQuantity(int quantity) {
        super.setQuantity(quantity);
        mediator.updateTotals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPrice(Double price) {
        super.setPrice(price);
        mediator.updateTotals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVatApplicable(Double vatApplicable) {
        super.setVatApplicable(vatApplicable);
        mediator.updateTotals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDiscountRate(Double discountRate) {
        super.setDiscountRate(discountRate);
        mediator.updateTotals();
    }

}
