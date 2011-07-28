package be.jsams.client.wizard.transfer;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.desktop.JsamsMainFrame;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.model.dialog.AbstractWizardDialog;
import be.jsams.client.validator.wizard.DestinationValidator;
import be.jsams.client.validator.wizard.DocumentDetailsValidator;
import be.jsams.client.validator.wizard.DocumentValidator;
import be.jsams.client.validator.wizard.DocumentsValidator;
import be.jsams.client.validator.wizard.SourceValidator;
import be.jsams.client.validator.wizard.SummaryTransferValidator;
import be.jsams.client.validator.wizard.TransferModeValidator;
import be.jsams.common.bean.model.transfer.TransferBean;
import be.jsams.server.service.transfer.TransferService;

/**
 * Transfer wizard dialog for documents.
 * 
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class TransferWizardDialog extends AbstractWizardDialog<TransferBean, TransferService> {

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
    /** Summary panel */
    public static final int SUMMARY_PANEL = 9;

    /**
     * Constructor.
     * 
     * @param parent the {@link JsamsMainFrame} parent
     * @param title the {@link I18nString} title
     * @param iconFileName the icon file name for the frame
     * @param logoFileName the logo file name for the wizard panel
     */
    public TransferWizardDialog(JsamsMainFrame parent, I18nString title, String iconFileName, String logoFileName) {
        super(parent, title, iconFileName, logoFileName, new TransferBean(), JsamsApplicationContext
                .getTransferService());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initComponents() {
        SourceChooserWizardPanel sourceChooserPanel = new SourceChooserWizardPanel(this, getComponent(), getModel(),
                new SourceValidator());
        getComponent().addPanel(FIRST_PANEL, sourceChooserPanel);
        DestinationChooserWizardPanel destinationChooserPanel = new DestinationChooserWizardPanel(this, getComponent(),
                getModel(), new DestinationValidator());
        getComponent().addPanel(SECOND_PANEL, destinationChooserPanel);
        TransferModeChooserWizardPanel chooserPanel = new TransferModeChooserWizardPanel(this, getComponent(),
                getModel(), new TransferModeValidator());
        getComponent().addPanel(THIRD_PANEL, chooserPanel);

        DocumentChooserWizardPanel documentChooserPanel = new DocumentChooserWizardPanel(this, getComponent(),
                getModel(), new DocumentValidator());
        getComponent().addPanel(FIRTH_PANEL_FULL_MODE, documentChooserPanel);
        DocumentChooserWizardPanel documentChooserPanel2 = new DocumentChooserWizardPanel(this, getComponent(),
                getModel(), new DocumentValidator());
        getComponent().addPanel(FIRTH_PANEL_PARTIAL_MODE, documentChooserPanel2);
        DocumentsChooserWizardPanel documentsChooserPanel = new DocumentsChooserWizardPanel(this, getComponent(),
                getModel(), new DocumentsValidator());
        getComponent().addPanel(FIRTH_PANEL_FULL_GROUPED_MODE, documentsChooserPanel);
        DocumentsChooserWizardPanel documentsChooserPanel2 = new DocumentsChooserWizardPanel(this, getComponent(),
                getModel(), new DocumentsValidator());
        getComponent().addPanel(FIRTH_PANEL_PARTIAL_GROUPED_MODE, documentsChooserPanel2);
        
        DocumentDetailsChooserWizardPanel documentDetailsChooserPanel = new DocumentDetailsChooserWizardPanel(this,
                getComponent(), getModel(), new DocumentDetailsValidator());
        getComponent().addPanel(FIFTH_PANEL_PARTIAL_MODE, documentDetailsChooserPanel);
        DocumentDetailsChooserWizardPanel documentDetailsChooserPanel2 = new DocumentDetailsChooserWizardPanel(this,
                getComponent(), getModel(), new DocumentDetailsValidator());
        getComponent().addPanel(FIFTH_PANEL_PARTIAL_GROUPED_MODE, documentDetailsChooserPanel2);

        SummaryTransferWizardPanel summaryPanel = new SummaryTransferWizardPanel(this,
                getComponent(), getModel(), new SummaryTransferValidator());
        getComponent().addPanel(SUMMARY_PANEL, summaryPanel);
//        ValidationComponentUtils.updateComponentTreeMandatoryBorder(this);
        // pack();
    }

}
