package be.jsams.common.bean.view;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import be.jsams.client.renderer.TranslatableComboBoxRenderer;
import be.jsams.common.bean.model.LegalFormBean;

/**
 * 
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class LegalFormBeanView extends AbstractView<LegalFormBean, JComboBox, JPanel> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -1884652505559007185L;

    /**
     * Constructor
     * 
     * @param bean the {@link LegalFormBean}
     */
    public LegalFormBeanView(LegalFormBean bean) {
        super(bean);
        // TODO Auto-generated constructor stub
    }

    /**
     * {@inheritDoc}
     */
    public JComboBox createEditView() {
        JComboBox combobox = null;
        ViewFactory<LegalFormBean> helper = new ViewFactory<LegalFormBean>();
        combobox = helper.createBindingComboComponent(getBean(), true, false, new TranslatableComboBoxRenderer());
        return combobox;
    }

    /**
     * {@inheritDoc}
     */
    public JPanel createSearchView() {
        // TODO Auto-generated method stub
        return null;
    }

}
