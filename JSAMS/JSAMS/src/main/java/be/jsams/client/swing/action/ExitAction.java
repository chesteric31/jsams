package be.jsams.client.swing.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import be.jsams.client.desktop.JsamsDesktop;

/**
 * Exit action to quit the application. 
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class ExitAction extends AbstractAction {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -8518161605125192287L;

    /**
     * Constructor
     *  
     * @param text the action text
     * @param icon the icon to display
     */
    public ExitAction(String text, Icon icon) {
        super(text, icon);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {
        JsamsDesktop.getInstance().stopNow();
    }

}
