package be.jsams.common.bean.view;

import be.jsams.client.renderer.TranslatableComboBoxRenderer;
import be.jsams.client.swing.component.JsamsComboBox;
import be.jsams.common.bean.model.LegalFormBean;

/**
 * Implementation of view for {@link LegalFormBean} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class LegalFormBeanView extends AbstractBeanView<LegalFormBean> implements Editable<JsamsComboBox> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -1884652505559007185L;

    /**
     * Constructor.
     * 
     * @param bean the {@link LegalFormBean}
     */
    public LegalFormBeanView(LegalFormBean bean) {
        super(bean);
    }

    /**
     * {@inheritDoc}
     */
    public JsamsComboBox createEditView() {
        ViewFactory<LegalFormBean> viewFactory = getViewFactory();
        JsamsComboBox comboBox = viewFactory.createBindingComboComponent(getBean(), true, false,
                new TranslatableComboBoxRenderer());
        return comboBox;
    }

}
