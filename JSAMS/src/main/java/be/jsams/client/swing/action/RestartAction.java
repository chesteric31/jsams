package be.jsams.client.swing.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import be.jsams.client.desktop.JsamsDesktop;

/**
 * 
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class RestartAction extends AbstractAction {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 3368161354924539915L;

    /**
     * Constructor
     *  
     * @param text the action text
     * @param icon the icon to display
     */
    public RestartAction(String text, Icon icon) {
        super(text, icon);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {
        JsamsDesktop.getInstance().restart();
    }

}
