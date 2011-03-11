package be.jsams.client.swing.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

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

    private SocietyBean bean;

    /**
     * Constructor
     *  
     * @param text the action text
     * @param icon the icon to display
     * @param bean the {@link SocietyBean} to edit
     */
    public EditSocietyAction(String text, Icon icon, SocietyBean bean) {
        super(text, icon);
        this.bean = bean;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        new EditSocietyDialog(JsamsI18nResource.TITLE_EDIT_SOCIETY, bean);
    }

}
