package be.jsams.client.swing.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.dialog.EditSocietyDialog;
import be.jsams.common.bean.model.SocietyBean;

/**
 * Edit action to edit the {@link SocietyBean}.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EditSocietyAction extends AbstractAction {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 3692819655583374043L;
    
    public static final int NEW_ONE_MODE = 0;
    
    public static final int CURRENT_SOCIETY_MODE = 1;
    
    private int editionMode;

    /**
     * Constructor
     * 
     * @param text
     *            the action text
     * @param icon
     *            the icon to display
     * @param mode
     *            the edition mode, 0 = new edition from scratch, 1 = edition of the current society
     */
    public EditSocietyAction(String text, Icon icon, int mode) {
        super(text, icon);
        this.editionMode = mode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (editionMode == NEW_ONE_MODE) {
            new EditSocietyDialog(JsamsI18nResource.TITLE_EDIT_SOCIETY, new SocietyBean());
        } else {
            new EditSocietyDialog(JsamsI18nResource.TITLE_EDIT_SOCIETY, JsamsDesktop.getInstance().getCurrentSociety());
        }
    }
}
