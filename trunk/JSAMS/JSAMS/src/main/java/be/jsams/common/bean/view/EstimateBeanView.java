package be.jsams.common.bean.view;

import javax.swing.JPanel;

import be.jsams.common.bean.model.EstimateBean;

/**
 * 
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EstimateBeanView extends AbstractView<EstimateBean, JPanel, JPanel> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -1438658754345137680L;

    /**
     * Constructor
     * 
     * @param bean the {@link EstimateBean}
     */
    public EstimateBeanView(EstimateBean bean) {
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
