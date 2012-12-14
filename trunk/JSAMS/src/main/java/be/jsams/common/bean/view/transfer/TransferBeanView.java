package be.jsams.common.bean.view.transfer;

import be.jsams.common.bean.model.transfer.TransferBean;
import be.jsams.common.bean.view.AbstractBeanView;

/**
 * View of a {@link TransferBean}.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class TransferBeanView extends AbstractBeanView<TransferBean> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -2879683877037752402L;

    /**
     * Constructor.
     * 
     * @param bean the {@link TransferBean}
     */
    public TransferBeanView(TransferBean bean) {
        super(bean);
    }

}
