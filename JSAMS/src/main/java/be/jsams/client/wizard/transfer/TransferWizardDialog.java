package be.jsams.client.wizard.transfer;

import com.jgoodies.validation.view.ValidationComponentUtils;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.desktop.JsamsMainFrame;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.model.dialog.AbstractWizardDialog;
import be.jsams.client.validator.TransferValidator;
import be.jsams.common.bean.model.transfer.TransferBean;
import be.jsams.server.service.transfer.TransferService;

/**
 * Transfer wizard dialog for documents.
 * 
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class TransferWizardDialog extends AbstractWizardDialog<TransferBean, TransferValidator, TransferService> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -721106898877741343L;

    /** First panel to choose the transfer mode */
    public static final int FIRST_PANEL = 0;
    /** Second panel to choose the source document */
    public static final int SECOND_PANEL = 1;
    /** Third panel to choose the destination document */
    public static final int THIRD_PANEL = 2;
    /** Firth panel for the full mode transfer */
    public static final int FIRTH_PANEL_FULL_MODE = 3;
    /** Firth panel for the partial mode transfer */
    public static final int FIRTH_PANEL_PARTIAL_MODE = 4;
    /** Firth panel for the full grouped mode transfer */
    public static final int FIRTH_PANEL_FULL_GROUPED_MODE = 5;
    /** Firth panel for the partial grouped mode transfer */
    public static final int FIRTH_PANEL_PARTIAL_GROUPED_MODE = 6;
    /** Fifth panel for the partial mode transfer */
    public static final int FIFTH_PANEL_PARTIAL_MODE = 7;
    /** Fifth panel for the partial grouped mode transfer */
    public static final int FIFTH_PANEL_PARTIAL_GROUPED_MODE = 8;

    /**
     * Constructor.
     * 
     * @param parent the {@link JsamsMainFrame} parent
     * @param title the {@link I18nString} title
     * @param iconFileName the icon file name for the frame
     * @param logoFileName the logo file name for the wizard panel
     */
    public TransferWizardDialog(JsamsMainFrame parent, I18nString title, String iconFileName, String logoFileName) {
        super(parent, title, iconFileName, logoFileName, new TransferBean(), new TransferValidator(),
                JsamsApplicationContext.getTransferService());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initComponents() {
        SourceChooserWizardPanel sourceChooserPanel = new SourceChooserWizardPanel(this, getComponent(), getModel());
        getComponent().addPanel(FIRST_PANEL, sourceChooserPanel);
        DestinationChooserWizardPanel destinationChooserPanel = new DestinationChooserWizardPanel(this, getComponent(),
                getModel());
        getComponent().addPanel(SECOND_PANEL, destinationChooserPanel);
        TransferModeChooserWizardPanel chooserPanel = new TransferModeChooserWizardPanel(this, getComponent(),
                getModel());
        getComponent().addPanel(THIRD_PANEL, chooserPanel);
        DocumentChooserWizardPanel documentChooserPanel = new DocumentChooserWizardPanel(this, getComponent(),
                getModel());
        getComponent().addPanel(FIRTH_PANEL_FULL_MODE, documentChooserPanel);
        DocumentChooserWizardPanel documentChooserPanel2 = new DocumentChooserWizardPanel(this, getComponent(),
                getModel());
        getComponent().addPanel(FIRTH_PANEL_PARTIAL_MODE, documentChooserPanel2);
        DocumentsChooserWizardPanel documentsChooserPanel = new DocumentsChooserWizardPanel(this, getComponent(),
                getModel());
        getComponent().addPanel(FIRTH_PANEL_FULL_GROUPED_MODE, documentsChooserPanel);
        DocumentsChooserWizardPanel documentsChooserPanel2 = new DocumentsChooserWizardPanel(this, getComponent(),
                getModel());
        getComponent().addPanel(FIRTH_PANEL_PARTIAL_GROUPED_MODE, documentsChooserPanel2);
        DocumentDetailsChooserWizardPanel documentDetailsChooserPanel = new DocumentDetailsChooserWizardPanel(this,
                getComponent(), getModel());
        getComponent().addPanel(FIFTH_PANEL_PARTIAL_MODE, documentDetailsChooserPanel);
        DocumentDetailsChooserWizardPanel documentDetailsChooserPanel2 = new DocumentDetailsChooserWizardPanel(this,
                getComponent(), getModel());
        getComponent().addPanel(FIFTH_PANEL_PARTIAL_GROUPED_MODE, documentDetailsChooserPanel2);
        ValidationComponentUtils.updateComponentTreeMandatoryBorder(this);
//        pack();
    }

}
