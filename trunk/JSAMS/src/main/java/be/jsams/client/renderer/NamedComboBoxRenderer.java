package be.jsams.client.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import be.jsams.common.bean.model.AbstractNamedIdentityBean;

/**
 * Combo box renderer for object that have name column from DB.
 * 
 * @author chesteric31
 * @version $$Rev$$ $$Date::                  $$ $$Author$$
 */
public class NamedComboBoxRenderer implements ListCellRenderer {

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
            theText = ((AbstractNamedIdentityBean<?, ?>) value).getName();
        }
        renderer.setText(theText);
        return renderer;
    }

}
