package be.jsams.common.bean.view;

import be.jsams.common.bean.model.sale.DateBean;

import com.toedter.calendar.JDateChooser;

/**
 * 
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class DateBeanView extends AbstractView<DateBean, JDateChooser, JDateChooser> implements
        Viewable<JDateChooser, JDateChooser> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 1025328377007901509L;

    /**
     * Constructor
     * 
     * @param bean the {@link DateBean}
     */
    public DateBeanView(DateBean bean) {
        super(bean);
        // TODO Auto-generated constructor stub
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JDateChooser createEditView() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JDateChooser createSearchView() {
        // TODO Auto-generated method stub
        return null;
    }
    
    
}
