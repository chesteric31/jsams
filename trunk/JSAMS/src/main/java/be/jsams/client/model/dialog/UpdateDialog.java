package be.jsams.client.model.dialog;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.prefs.Preferences;

import javax.swing.JPanel;
import javax.swing.plaf.FontUIResource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.client.desktop.JsamsMainFrame;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.swing.component.AbstractJsamsFrame;
import be.jsams.client.swing.component.JsamsButton;
import be.jsams.client.swing.component.JsamsDialog;
import be.jsams.client.swing.component.JsamsLabel;
import be.jsams.client.swing.utils.DialogUtil;
import be.jsams.client.swing.utils.IconUtil;
import be.jsams.server.model.rss.Feed;
import be.jsams.server.model.rss.FeedMessage;
import be.jsams.server.service.rss.RSSFeedParser;
import be.jsams.server.service.rss.impl.RSSFeedParserImpl;

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

    private static final Log LOGGER = LogFactory.getLog(UpdateDialog.class);

    /**
     * JSAMS application version properties
     */
    private static final String JSAMS_APPLICATION_VERSION_PROPERTIES = "jsams-application-version.properties";

    /**
     * JSAMS application internal version identifier
     */
    private static final String APPLICATION_INTERNALVERSION_IDENTIFIER = "application.internalversion.identifier";

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

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Properties properties = new Properties();
        try {
            properties.load(classLoader.getResourceAsStream(JSAMS_APPLICATION_VERSION_PROPERTIES));
        } catch (FileNotFoundException e) {
            LOGGER.error(e);
            return;
        } catch (IOException e) {
            LOGGER.error(e);
            return;
        }
        String installedVersion = String.valueOf(properties.get(APPLICATION_INTERNALVERSION_IDENTIFIER));
        String availableVersion = getAvailableVersion();
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
            JsamsButton updateButton = new JsamsButton(JsamsI18nLabelResource.LABEL_APPLICATION_VERSION_TO_UPDATE);
            builder.appendI15d("", updateButton);
//            int confirm = JOptionPane.showConfirmDialog(null, JsamsI18nResource.CONFIRMATION_UPDATE);
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

    /**
     * 
     * @return the available version
     */
    private String getAvailableVersion() {
        String version = "";
        Preferences prefsRoot = Preferences.userRoot();
        Preferences jsamsPrefs = prefsRoot.node("be.jsams");

        System.getProperties().put("proxySet", jsamsPrefs.get("proxySet", "false"));
        System.getProperties().put("proxyHost", jsamsPrefs.get("proxyHost", ""));
        System.getProperties().put("proxyPort", jsamsPrefs.get("proxyPort", ""));
        RSSFeedParser parser = new RSSFeedParserImpl(
        // "http://jsams.googlecode.com/files/updates.rss"
                "file:\\C:\\Tools\\workspace-jsams\\JSAMS\\updates.rss");
        Feed feed = parser.readFeed();
        for (FeedMessage message : feed.getMessages()) {
            version = message.getVersion();
        }
        return version;
    }

}
