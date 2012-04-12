package be.jsams.common.bean.view.transfer;

import javax.swing.JPanel;

import be.jsams.common.bean.model.transfer.TransferBean;
import be.jsams.common.bean.view.AbstractBeanView;
import be.jsams.common.bean.view.Wizardable;

/**
 * 
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class TransferBeanView extends AbstractBeanView<TransferBean> implements Wizardable<JPanel> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -2879683877037752402L;

    /**
     * @param bean the {@link TransferBean}
     */
    public TransferBeanView(TransferBean bean) {
        super(bean);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel createWizardView() {
        throw new UnsupportedOperationException();
    }

}
