package be.jsams.common.bean.model.sale;

import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.view.CommandDetailBeanView;
import be.jsams.server.model.CommandDetail;

/**
 * 
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CommandDetailBean extends AbstractIdentityBean<CommandDetail, CommandDetailBeanView> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 6666469865590217242L;

    /**
     * Constructor.
     * 
     * @param model the {@link CommandDetail}
     */
    public CommandDetailBean(CommandDetail model) {
        super(model);
        // TODO Auto-generated constructor stub
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandDetailBeanView getView() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void refresh(AbstractIdentityBean<?, ?> bean) {
        // TODO Auto-generated method stub
        
    }

}
