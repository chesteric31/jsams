package be.jsams.common.bean.view;

import javax.swing.JPanel;

import be.jsams.common.bean.model.EstimateDetailBean;

/**
 * 
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EstimateDetailBeanView extends AbstractView<EstimateDetailBean, JPanel, JPanel> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 1156444686459091375L;

    /**
     * Constructor
     * 
     * @param bean the {@link EstimateDetailBean}
     */
    public EstimateDetailBeanView(EstimateDetailBean bean) {
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