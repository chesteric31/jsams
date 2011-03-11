package be.jsams.common.bean.view;

import javax.swing.JPanel;

import be.jsams.common.bean.model.CommandBean;

/**
 * 
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CommandBeanView extends AbstractView<CommandBean, JPanel, JPanel> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 7008183975354064256L;

    /**
     * Constructor
     * 
     * @param bean the {@link CommandBean}
     */
    public CommandBeanView(CommandBean bean) {
        super(bean);
        // TODO Auto-generated constructor stub
    }

    /**
     * {@inheritDoc}
     */
    public JPanel createEditView() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public JPanel createSearchView() {
        // TODO Auto-generated method stub
        return null;
    }

}
