package be.jsams.client.model.dialog;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.plaf.FontUIResource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.client.desktop.MainFrame;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.i18n.I18nLabelResource;
import be.jsams.client.i18n.UserContext;
import be.jsams.client.swing.component.AbstractJsamsFrame;
import be.jsams.client.swing.component.JsamsDialog;
import be.jsams.client.swing.component.JsamsLabel;
import be.jsams.client.swing.utils.DialogUtil;
import be.jsams.client.swing.utils.IconUtil;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

/**
 * About dialog.
 * 
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class AboutDialog extends JsamsDialog {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -659086180402913537L;
    
    /**
     * JSAMS application version properties
     */
    private static final String JSAMS_APPLICATION_VERSION_PROPERTIES = "jsams-application-version.properties";

    /**
     * JSAMS application internal version identifier
     */
    private static final String APPLICATION_INTERNALVERSION_IDENTIFIER = "application.internalversion.identifier";

    private static final Log LOGGER = LogFactory.getLog(AboutDialog.class);

    /**
     * Constructor.
     * 
     * @param parent the parent frame
     * @param title the title
     */
    public AboutDialog(MainFrame parent, I18nString title) {
        super(parent, title, IconUtil.TITLE_ICON_PREFIX + "categories/applications-office.png");
        initComponents();
    }

    /**
     * Initializes all the components
     */
    private void initComponents() {
        FormLayout layout = new FormLayout("left:pref, 3dlu", "pref, 5dlu");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, AbstractJsamsFrame.RESOURCE_BUNDLE);
        builder.setDefaultDialogBorder();
        JsamsLabel applicationLabel = new JsamsLabel(I18nLabelResource.LABEL_APPLICATION);
        applicationLabel.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 12));
        builder.append(applicationLabel);
        builder.nextLine();
        builder.appendSeparator();
        builder.nextLine();
        
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        I18nString translation = I18nLabelResource.LABEL_VERSION;
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
        String version = String.valueOf(properties.get(APPLICATION_INTERNALVERSION_IDENTIFIER));
        translation.setArguments(new Object[] {version});
        JsamsLabel versionLabel = new JsamsLabel(translation);
        versionLabel.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 11));
        builder.append(versionLabel);
        builder.nextLine();
        URL aboutUrl = null;
        if (UserContext.isEnglish()) {
            aboutUrl = classLoader.getResource("pages/about.html");
        } else if (UserContext.isFrench()) {
            aboutUrl = classLoader.getResource("pages/about_fr.html");
        } else {
            aboutUrl = classLoader.getResource("pages/about_nl.html");
        }

        JEditorPane area = null;
        try {
            area = new JEditorPane(aboutUrl);
        } catch (IOException e) {
            LOGGER.error(e);
            return;
        }
        area.setEditable(false);
        builder.append(area);
        builder.nextLine();
        JPanel panel = builder.getPanel();
        JPanel mainPanel = new JPanel(new BorderLayout());
        ImageIcon icon = new ImageIcon(IconUtil.buildIcon(IconUtil.TITLE_ICON_PREFIX
                + "categories/applications-office.png"));
        mainPanel.add(new JsamsLabel(icon), BorderLayout.WEST);
        mainPanel.add(panel, BorderLayout.CENTER);
        add(mainPanel);
        pack();
        DialogUtil.centerComponentOnScreen(this);
        setVisible(true);
        setResizable(false);
    }

}
