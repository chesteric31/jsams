package be.jsams.client.swing.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import be.jsams.client.model.dialog.EditGeneralParametersDialog;

/**
 * Action to show the general parameters.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class GeneralParametersAction extends AbstractAction {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 2558055247303262035L;

    /**
     * Constructor
     *  
     * @param text the action text
     * @param icon the icon to display
     */
    public GeneralParametersAction(String text, Icon icon) {
        super(text, icon);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        new EditGeneralParametersDialog();
    }

}
