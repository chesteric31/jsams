package be.jsams.client.wizard.transfer;

import be.jsams.client.desktop.JsamsMainFrame;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.wizard.JsamsWizardDialog;

/**
 * Transfer wizard dialog for documents.
 *
 * @author chesteric31
 * @version $Revision$ $Date::              $ $Author$
 */
public class TransferWizardDialog extends JsamsWizardDialog {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -721106898877741343L;

    /**
     * Constructor.
     * 
     * @param parent the {@link JsamsMainFrame} parent
     * @param title the {@link I18nString} title
     * @param iconFileName the icon file name for the frame
     * @param logoFileName the logo file name for the wizard panel
     */
    public TransferWizardDialog(JsamsMainFrame parent, I18nString title, String iconFileName, String logoFileName) {
        super(parent, title, iconFileName, logoFileName);
        buildPanels();
    }

    /**
     * Build panels.
     */
    private void buildPanels() {
        ChooserWizardPanel panel = new ChooserWizardPanel(getComponent());
        getComponent().addPanel(0, panel);
    }

}
