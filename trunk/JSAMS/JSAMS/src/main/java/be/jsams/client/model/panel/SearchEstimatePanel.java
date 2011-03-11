package be.jsams.client.model.panel;

import javax.swing.JPanel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.client.swing.listener.EstimateMouseTableListener;
import be.jsams.common.bean.model.EstimateBean;
import be.jsams.server.service.EstimateService;

/**
 * Search {@link JPanel} for Estimate objects.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class SearchEstimatePanel extends SearchPanel<EstimateBean, EstimateMouseTableListener, EstimateService> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -7701480812937524634L;

    protected static final Log LOGGER = LogFactory.getLog(SearchEstimatePanel.class);

    /**
     * Constructor.
     * 
     * @param m
     *            the {@link EstimateBean}
     * @param l
     *            the {@link EstimateMouseTableListener}
     * @param s
     *            the {@link EstimateService}
     * @param showManagementButtons
     *            a boolean that indicates if we have to display the buttons to manage the content: add, remove and
     *            modify
     */
    public SearchEstimatePanel(EstimateBean m, EstimateMouseTableListener l, EstimateService s,
            final boolean showManagementButtons) {
        super(m, l, s, showManagementButtons);
    }

    @Override
    protected JPanel buildSearchCriteriaPanel() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void performButtonAdd() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void performButtonModify() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void performButtonRemove() {
        // TODO Auto-generated method stub

    }

    @Override
    public void performOk() {
        // TODO Auto-generated method stub

    }

    @Override
    public void performCancel() {
        // TODO Auto-generated method stub
        
    }

}
