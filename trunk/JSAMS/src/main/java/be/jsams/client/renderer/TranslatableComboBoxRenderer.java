package be.jsams.client.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import be.jsams.client.i18n.UserContext;
import be.jsams.common.bean.model.AbstractTranslatableIdentityBean;

/**
 * Combo box renderer for object that have translatable label from DB.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class TranslatableComboBoxRenderer implements ListCellRenderer {

    private DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

    /**
     * {@inheritDoc}
     */
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
            boolean cellHasFocus) {
        String theText = " ";

        JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index, isSelected,
                cellHasFocus);
        if (value != null) {
            if (UserContext.isFrench()) {
                theText = ((AbstractTranslatableIdentityBean<?, ?>) value).getLabelFr();
            } else if (UserContext.isDutch()) {
                theText = ((AbstractTranslatableIdentityBean<?, ?>) value).getLabelNl();
            } else {
                theText = ((AbstractTranslatableIdentityBean<?, ?>) value).getLabel();
            }
        }
        renderer.setText(theText);
        return renderer;
    }

}
