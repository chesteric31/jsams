package be.jsams.client.model.dialog;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.plaf.FontUIResource;

import be.jsams.JsamsStart;
import be.jsams.client.desktop.JsamsMainFrame;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.swing.component.AbstractJsamsFrame;
import be.jsams.client.swing.component.JsamsDialog;
import be.jsams.client.swing.component.JsamsLabel;
import be.jsams.client.swing.utils.DialogUtil;
import be.jsams.client.swing.utils.IconUtil;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

/**
 * 
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
     * Constructor.
     * 
     * @param parent the parent frame
     * @param title the title
     */
    public AboutDialog(JsamsMainFrame parent, I18nString title) {
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
        JsamsLabel applicationLabel = new JsamsLabel(JsamsI18nLabelResource.LABEL_APPLICATION);
        applicationLabel.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 12));
        builder.append(applicationLabel);
        builder.nextLine();
        builder.appendSeparator();
        builder.nextLine();
        I18nString translation = JsamsI18nLabelResource.LABEL_VERSION;
        String version = JsamsStart.class.getPackage().getImplementationVersion();
        translation.setArguments(new Object[] {version});
        JsamsLabel versionLabel = new JsamsLabel(translation);
        versionLabel.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 11));
        builder.append(versionLabel);
        builder.nextLine();
        JEditorPane area = new JEditorPane("text/html", retrieveAboutText());
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

    /**
     * @return a small about text
     */
    private String retrieveAboutText() {
        StringBuilder builder = new StringBuilder();
        builder.append("<i>This program came from an idea of 2005.<br>");
        builder.append("The goal was to explore the possibilities of Java world.<br>");
        builder.append("With its old, new technologies and the current design patterns.<br>");
        builder.append("<p>In one word, this project is a lab.<br>");
        builder.append("<p>Feel free to explore, modify and improve this project...");
        builder.append("<p>Finally, I would like to thank all my parents and especially my wife.</i>");
        return builder.toString();
    }

}
