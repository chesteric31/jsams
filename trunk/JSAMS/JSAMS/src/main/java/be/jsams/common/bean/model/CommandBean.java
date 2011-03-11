package be.jsams.common.bean.model;

import be.jsams.common.bean.view.CommandBeanView;
import be.jsams.server.model.Command;

/**
 * 
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CommandBean extends AbstractIdentityBean<Command, CommandBeanView> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -2213182386643300641L;

    /**
     * Constructor.
     * 
     * @param model the {@link Command}
     */
    public CommandBean(Command model) {
        super(model);
        // TODO Auto-generated constructor stub
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandBeanView getView() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        
    }


}
