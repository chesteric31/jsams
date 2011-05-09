package be.jsams.common.bean.view;

import javax.swing.JPanel;

import be.jsams.client.renderer.TranslatableComboBoxRenderer;
import be.jsams.client.swing.component.JsamsComboBox;
import be.jsams.common.bean.model.CivilityBean;

/**
 * Implementation of all sorts of views for {@link CivilityBean} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CivilityBeanView extends AbstractBeanView<CivilityBean, JsamsComboBox, JPanel> {

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
    }

    /**
     * {@inheritDoc}
     */
    public JsamsComboBox createEditView() {
        ViewFactory<CivilityBean> helper = new ViewFactory<CivilityBean>();
        JsamsComboBox comboBox = helper.createBindingComboComponent(getBean(), true, false,
                new TranslatableComboBoxRenderer());
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
