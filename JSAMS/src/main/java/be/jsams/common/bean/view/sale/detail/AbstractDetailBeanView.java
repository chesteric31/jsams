package be.jsams.common.bean.view.sale.detail;

import javax.swing.JComponent;
import javax.swing.JPanel;

import be.jsams.common.bean.model.sale.detail.AbstractDetailBean;
import be.jsams.common.bean.view.AbstractBeanView;

/**
 * Abstract class bean view for document detail.
 * 
 * @param <B> an extension of {@link AbstractDetailBean}
 * @param <J> an extension of {@link JComponent}
 * @param <K> an extension of {@link JPanel}
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public abstract class AbstractDetailBeanView<B extends AbstractDetailBean<?, ?, ?>,
        J extends JComponent, K extends JPanel> extends AbstractBeanView<B, J, K> {

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
        // TODO Auto-generated constructor stub
    }
    
}
