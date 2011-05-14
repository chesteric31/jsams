package be.jsams.common.bean.view.sale.detail;

import javax.swing.JPanel;

import be.jsams.common.bean.model.sale.detail.CommandDetailBean;

/**
 * 
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CommandDetailBeanView extends AbstractDetailBeanView<CommandDetailBean, JPanel, JPanel> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -2106918618904840995L;

    /**
     * Constructor
     * 
     * @param bean the {@link CommandDetailBean}
     */
    public CommandDetailBeanView(CommandDetailBean bean) {
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
