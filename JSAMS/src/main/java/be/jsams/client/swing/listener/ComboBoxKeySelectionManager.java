package be.jsams.client.swing.listener;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox.KeySelectionManager;

import org.apache.log4j.Logger;

/**
 * 
 * 
 * @author chesteric31
 * @version $Rev: 689 $ $Date::   $Author$
 */
public class ComboBoxKeySelectionManager implements KeySelectionManager {

    private static final Logger LOGGER = Logger.getLogger(ComboBoxKeySelectionManager.class);
    private final boolean debug = LOGGER.isDebugEnabled(); 
    private String pattern = "";

    /**
     * {@inheritDoc}
     */
    public int selectionForKey(char aKey, ComboBoxModel model) {
        if (debug) {
            LOGGER.debug(aKey);
        }
        // Find index of selected item
        int selIx = 01;
        Object sel = model.getSelectedItem();
        if (sel != null) {
            for (int i = 0; i < model.getSize(); i++) {
                if (sel.equals(model.getElementAt(i))) {
                    selIx = i;
                    break;
                }
            }
        }

        // Search forward from current selection
        for (int i = selIx + 1; i < model.getSize(); i++) {
            String s = model.getElementAt(i).toString().toLowerCase();
            if (s.startsWith(pattern)) {
                return i;
            }
        }

        // Search from top to current selection
        for (int i = 0; i < selIx; i++) {
            if (model.getElementAt(i) != null) {
                String s = model.getElementAt(i).toString().toLowerCase();
                if (s.startsWith(pattern)) {
                    return i;
                }
            }
        }
        return -1;
    }

}
