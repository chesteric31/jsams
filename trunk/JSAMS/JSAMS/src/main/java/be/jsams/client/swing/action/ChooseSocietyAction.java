package be.jsams.client.swing.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.dialog.OpenSocietyDialog;

/**
 * Choose society action that opens the {@link OpenSocietyDialog}.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class ChooseSocietyAction extends AbstractAction {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 7443911457561542259L;

    /**
     * Constructor
     *  
     * @param text the action text
     * @param icon the icon to display
     */
    public ChooseSocietyAction(String text, Icon icon) {
        super(text, icon);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {
        new OpenSocietyDialog(JsamsI18nResource.TITLE_OPEN_SOCIETY);
    }

}
