package be.jsams.common.bean.view.sale.detail;

import be.jsams.common.bean.model.sale.detail.AbstractDetailBean;
import be.jsams.common.bean.view.AbstractBeanView;

/**
 * Abstract class bean view for document detail.
 * 
 * @param <B> an extension of {@link AbstractDetailBean}
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public abstract class AbstractDetailBeanView<B extends AbstractDetailBean<?, ?, ?>> extends AbstractBeanView<B> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -2665729122862924509L;

    /**
     * Constructor.
     * 
     * @param bean the detail document bean
     */
    public AbstractDetailBeanView(B bean) {
        super(bean);
    }

}
