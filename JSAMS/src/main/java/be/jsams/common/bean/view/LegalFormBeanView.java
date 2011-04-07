package be.jsams.common.bean.view;

import javax.swing.JPanel;

import be.jsams.client.renderer.TranslatableComboBoxRenderer;
import be.jsams.client.swing.component.JsamsComboBox;
import be.jsams.common.bean.model.LegalFormBean;

/**
 * Implementation of all sorts of views for {@link LegalFormBean} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class LegalFormBeanView extends AbstractView<LegalFormBean, JsamsComboBox, JPanel> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -1884652505559007185L;

    /**
     * Constructor
     * 
     * @param bean
     *            the {@link LegalFormBean}
     */
    public LegalFormBeanView(LegalFormBean bean) {
        super(bean);
    }

    /**
     * {@inheritDoc}
     */
    public JsamsComboBox createEditView() {
        ViewFactory<LegalFormBean> helper = new ViewFactory<LegalFormBean>();
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
