package be.jsams.client.model.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.plaf.FontUIResource;

import be.jsams.client.desktop.JsamsMainFrame;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.swing.component.JsamsDialog;
import be.jsams.client.swing.component.JsamsLabel;
import be.jsams.client.swing.component.JsamsStatusBar;
import be.jsams.client.swing.utils.DialogUtil;
import be.jsams.client.swing.utils.IconUtil;
import be.jsams.client.wizard.DefaultJsamsWizardComponent;
import be.jsams.client.wizard.JsamsWizardButtonPanel;
import be.jsams.client.wizard.JsamsWizardComponent;
import be.jsams.client.wizard.JsamsWizardPanel;
import be.jsams.client.wizard.action.CancelAction;
import be.jsams.client.wizard.action.FinishAction;
import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.server.service.transfer.TransferService;

/**
 * Wizard generic dialog.
 * 
 * @param <B> an extension of {@link AbstractIdentityBean}
 * @param <S> an extension of {@link TransferService}
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public abstract class AbstractWizardDialog<B extends AbstractIdentityBean<?, ?>, S extends TransferService> extends
        JsamsDialog {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 2855036709879288192L;

    private JsamsWizardComponent component;
    private I18nString title;
    private JPanel buttonsPanel;
    private String logoFileName;
    private JsamsLabel label;
    
    private B model;
    private JsamsStatusBar statusBar;
    private JPanel southPanel;
    private TransferService service;

    /**
     * Constructor.
     * 
     * @param parent the {@link JsamsMainFrame} parent
     * @param title the {@link I18nString} translatable String
     * @param iconFileName the icon path name of the dialog
     * @param logoFileName the file name to the logo to display
     * @param model the {@link AbstractIdentityBean} to use as wrapper
     * @param service the {@link TransferService} to use to persist/update entities
     */
    public AbstractWizardDialog(final JsamsMainFrame parent, I18nString title, String iconFileName,
            String logoFileName, B model, TransferService service) {
        super(parent, title, IconUtil.TITLE_ICON_PREFIX + iconFileName);
        this.model = model;
        this.service = service;
        this.component = new DefaultJsamsWizardComponent();
        this.logoFileName = logoFileName;
        preInitComponents();
        initComponents();
//        setLocationRelativeTo(null);
        setResizable(false);
        component.updateComponents();
        setVisible(true);
    }
    
    /**
     * Initializes all the components.
     */
    public abstract void initComponents();

    /**
     * Pre-Initialize all the components.
     */
    private void preInitComponents() {
        this.setLayout(new BorderLayout());
        label = new JsamsLabel();
        label.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 12));
        JPanel titlePanel = new JPanel(new BorderLayout());
        label.setAlignmentY(CENTER_ALIGNMENT);
        titlePanel.add(label, BorderLayout.CENTER);
        titlePanel.add(new JSeparator(), BorderLayout.SOUTH);
        add(titlePanel, BorderLayout.NORTH);
        JPanel centerPanel = new JPanel();
        if (logoFileName != null) {
            Image defaultlogo = IconUtil.buildIcon(logoFileName);
            JPanel logoPanel = new JPanel(new BorderLayout());
            logoPanel.add(new JsamsLabel(new ImageIcon(defaultlogo)));
            logoPanel.setBackground(Color.WHITE);
            centerPanel.add(logoPanel);
        }
        centerPanel.add(component.getPanelsContainer());
        add(centerPanel, BorderLayout.CENTER);
        
        buttonsPanel = new JsamsWizardButtonPanel(component);
        component.setFinishAction(new FinishAction(component) {
            public void performAction() {
                dispose();
            }
        });
        component.setCancelAction(new CancelAction(component) {
            public void performAction() {
                dispose();
            }
        });
        statusBar = new JsamsStatusBar();
        southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.PAGE_AXIS));
        southPanel.add(buttonsPanel);
        southPanel.add(statusBar);
        add(southPanel, BorderLayout.SOUTH);

        // set automatically the new title
        component.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent event) {
                setPanelTitle(((JsamsWizardPanel<?, ?>) event.getNewValue()).getPanelTitle());
            }
        });
        this.pack();
        DialogUtil.centerComponentOnScreen(this);
    }
    
//    /**
//     * Display the dialog.
//     */
//    public void display() {
//        component.updateComponents();
//        super.setVisible(true);
//    }

    /**
     * @return the component
     */
    public JsamsWizardComponent getComponent() {
        return component;
    }

    /**
     * @param component the component to set
     */
    public void setComponent(JsamsWizardComponent component) {
        this.component = component;
    }

    /**
     * @return the title
     */
    public I18nString getPanelTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setPanelTitle(I18nString title) {
        label.setText(title);
    }

    /**
     * @return the model
     */
    public B getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(B model) {
        this.model = model;
    }

    /**
     * @return the service
     */
    public TransferService getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(TransferService service) {
        this.service = service;
    }

    /**
     * @return the statusBar
     */
    public JsamsStatusBar getStatusBar() {
        return statusBar;
    }

    /**
     * @param statusBar the statusBar to set
     */
    public void setStatusBar(JsamsStatusBar statusBar) {
        this.statusBar = statusBar;
    }
    
}
