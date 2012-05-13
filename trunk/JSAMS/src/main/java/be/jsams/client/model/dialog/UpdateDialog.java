package be.jsams.client.model.dialog;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.plaf.FontUIResource;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.desktop.JsamsMainFrame;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.swing.action.DownloadUpdateAction;
import be.jsams.client.swing.component.AbstractJsamsFrame;
import be.jsams.client.swing.component.JsamsButton;
import be.jsams.client.swing.component.JsamsDialog;
import be.jsams.client.swing.component.JsamsLabel;
import be.jsams.client.swing.utils.DialogUtil;
import be.jsams.client.swing.utils.IconUtil;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Update dialog, to search if a update is available for the current version
 * application.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class UpdateDialog extends JsamsDialog {

    /**
     * Serial UID
     */
    private static final long serialVersionUID = -1586475516079019236L;

    /**
     * Constructor.
     * 
     * @param parent the parent {@link JsamsMainFrame}
     * @param title the title
     */
    public UpdateDialog(JsamsMainFrame parent, I18nString title) {
        super(parent, title, IconUtil.TITLE_ICON_PREFIX + "status/software-update-available.png");
        initComponents();
    }

    /**
     * Initializes all the components.
     */
    private void initComponents() {
        FormLayout layout = new FormLayout("left:pref, 3dlu, pref", "pref");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, AbstractJsamsFrame.RESOURCE_BUNDLE);
        builder.setDefaultDialogBorder();

        String installedVersion = JsamsApplicationContext.getPropertyHolder().retrieveInstalledVersion();
        String availableVersion = JsamsApplicationContext.getDownloaderService().retrieveAvailableUpdateVersion();
        JsamsLabel labelInstalled = new JsamsLabel(installedVersion);
        FontUIResource font = new FontUIResource(Font.SANS_SERIF, Font.BOLD, 13);
        labelInstalled.setFont(font);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_APPLICATION_VERSION_INSTALLED.getKey(), labelInstalled);
        JsamsLabel labelAvailable = new JsamsLabel(availableVersion);
        labelAvailable.setFont(font);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_APPLICATION_VERSION_AVAILABLE.getKey(), labelAvailable);
        builder.nextLine();
        boolean updateAvailable = !installedVersion.equalsIgnoreCase(availableVersion);
        if (!updateAvailable) {
            builder.appendI15d(JsamsI18nLabelResource.LABEL_APPLICATION_VERSION_UP_TO_DATE.getKey());
        } else {
            JsamsButton updateButton = new JsamsButton(JsamsI18nResource.BUTTON_APPLICATION_VERSION_TO_UPDATE);
            updateButton.setAction(new DownloadUpdateAction(updateButton.getText(), updateButton.getIcon()));
            builder.appendI15d("", updateButton);
        }
        JPanel panel = builder.getPanel();
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(panel, BorderLayout.CENTER);
        add(mainPanel);
        pack();
        DialogUtil.centerComponentOnScreen(this);

        setVisible(true);
        setResizable(false);
    }

}
