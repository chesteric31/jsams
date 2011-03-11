package be.jsams.common.bean.view;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import be.jsams.client.renderer.TranslatableComboBoxRenderer;
import be.jsams.common.bean.model.CivilityBean;

/**
 * 
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CivilityBeanView extends AbstractView<CivilityBean, JComboBox, JPanel> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 5777589355870087599L;
    
    /**
     * Constructor
     * 
     * @param bean the {@link CivilityBean}
     */
    public CivilityBeanView(CivilityBean bean) {
        super(bean);
        // TODO Auto-generated constructor stub
    }

    /**
     * {@inheritDoc}
     */
    public JComboBox createEditView() {
        JComboBox comboBox = null;
        ViewFactory<CivilityBean> helper = new ViewFactory<CivilityBean>();
        comboBox = helper.createBindingComboComponent(getBean(), true, false, new TranslatableComboBoxRenderer());
        return comboBox;
    }

    /**
     * {@inheritDoc}
     */
    public JPanel createSearchView() {
        // TODO Auto-generated method stub
        return null;
    }

}
