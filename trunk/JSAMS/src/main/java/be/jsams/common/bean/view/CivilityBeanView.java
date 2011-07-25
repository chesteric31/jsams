package be.jsams.common.bean.view;

import be.jsams.client.renderer.TranslatableComboBoxRenderer;
import be.jsams.client.swing.component.JsamsComboBox;
import be.jsams.common.bean.model.CivilityBean;

/**
 * Implementation of view for {@link CivilityBean} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CivilityBeanView extends AbstractBeanView<CivilityBean> implements Editable<JsamsComboBox> {

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
        ViewFactory<CivilityBean> viewFactory = getViewFactory();
        JsamsComboBox comboBox = viewFactory.createBindingComboComponent(getBean(), true, false,
                new TranslatableComboBoxRenderer());
        return comboBox;
    }

}
