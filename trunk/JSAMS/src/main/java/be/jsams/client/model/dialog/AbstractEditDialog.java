package be.jsams.client.model.dialog;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import be.jsams.client.desktop.MainFrame;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.swing.component.JsamsButtonsInterface;
import be.jsams.client.swing.component.JsamsButtonsPanel;
import be.jsams.client.swing.component.JsamsDialog;
import be.jsams.client.swing.component.JsamsLabel;
import be.jsams.client.swing.component.JsamsStatusBar;
import be.jsams.client.swing.utils.DialogUtil;
import be.jsams.client.swing.utils.IconUtil;
import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.server.service.Service;

import com.jgoodies.validation.Severity;
import com.jgoodies.validation.ValidationMessage;
import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.ValidationResultModel;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.DefaultValidationResultModel;
import com.jgoodies.validation.view.ValidationResultViewFactory;

/**
 * Edit generic dialog.
 * 
 * @param <B> an extension of {@link AbstractIdentityBean}
 * @param <V> an extension of {@link Validator}
 * @param <S> an extension of {@link Service}
 * @author chesteric31
 * @version $$Rev$$ $$Date::                  $$ $$Author$$
 */
public abstract class AbstractEditDialog<B extends AbstractIdentityBean<?, ?>, V extends Validator<B>,
        S extends Service<B>> extends JsamsDialog implements JsamsButtonsInterface {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 5146784638798425733L;

    private B model;
    private B originalModel;
    private JsamsButtonsPanel buttonsPanel;
    private ValidationResultModel validationResultModel = new DefaultValidationResultModel();
    private JsamsStatusBar statusBar;
    private JPanel southPanel;
    private Validator<B> validator;
    private Service<B> service;

    /**
     * Constructor
     * 
     * @param parent the {@link MainFrame} parent
     * @param title the {@link I18nString} translatable String
     * @param iconFileName the icon path file name
     */
    public AbstractEditDialog(final MainFrame parent, final I18nString title, final String iconFileName) {
        super(parent, title, IconUtil.TITLE_ICON_PREFIX + iconFileName);
        buttonsPanel = new JsamsButtonsPanel(this, true, true, true);
        setDefaultKeyActions();
        add(buildSouthPanel(), BorderLayout.SOUTH);
    }

    /**
     * Constructor
     * 
     * @param parent the {@link MainFrame} parent
     * @param title the {@link I18nString} translatable String
     */
    public AbstractEditDialog(final MainFrame parent, final I18nString title) {
        this(parent, title, "actions/document-new.png");
    }

    /**
     * Constructor
     * 
     * @param parent the {@link MainFrame} parent
     * @param title the {@link I18nString} translatable String
     * @param model the {@link AbstractIdentityBean}
     * @param validator the {@link Validator}
     * @param service the {@link Service}
     */
    public AbstractEditDialog(final MainFrame parent, I18nString title, B model, V validator, S service) {
        this(parent, title);
        this.model = model;
        this.validator = validator;
        this.service = service;
        initComponents();
        DialogUtil.centerComponentOnScreen(this);
        setResizable(true);
        setVisible(true);
    }

    /**
     * Initializes all the components.
     */
    public abstract void initComponents();

    /**
     * 
     * @return the service
     */
    public Service<B> getService() {
        return service;
    }

    /**
     * 
     * @param service the service to set
     */
    public void setService(Service<B> service) {
        this.service = service;
    }

    /**
     * 
     * @return the model
     */
    public B getModel() {
        return model;
    }

    /**
     * 
     * @param model the model to set
     */
    public void setModel(B model) {
        this.model = model;
    }

    /**
     * 
     * @return the {@link ValidationResultModel}
     */
    public ValidationResultModel getValidationResultModel() {
        return validationResultModel;
    }

    /**
     * 
     * @param validationResultModel the {@link ValidationResultModel} to set
     */
    public void setValidationResultModel(ValidationResultModel validationResultModel) {
        this.validationResultModel = validationResultModel;
    }

    /**
     * 
     * @return the {@link Validator}
     */
    public Validator<B> getValidator() {
        return validator;
    }

    /**
     * 
     * @param validator the {@link Validator} to set
     */
    public void setValidator(Validator<B> validator) {
        this.validator = validator;
    }

    /**
     * Build the 'south panel' composed by a {@link JsamsButtonsPanel} {@link JsamsStatusBar}
     * 
     * @return the 'south panel'
     */
    private JPanel buildSouthPanel() {
        statusBar = new JsamsStatusBar();
        southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.PAGE_AXIS));
        southPanel.add(buttonsPanel);
        southPanel.add(statusBar);
        return southPanel;
    }

    /**
     * {@inheritDoc}
     */
    public void performCancel() {
        B currentModel = getModel();
        currentModel.getView().release();
        currentModel.refresh(this.originalModel);
        this.dispose();
    }

    /**
     * {@inheritDoc}
     */
    public void performReset() {
        model.clear();
    }

    /**
     * {@inheritDoc}
     */
    public abstract void performOk();

    /**
     * Method called by the children class for validation and persistence.
     * 
     * @param object the object to validate and to persist
     * @return the result B object (updated or created), null if an error
     *         occurred
     */
    protected B postPerformOk(B object) {
        getModel().getView().release();
        ValidationResult result = validator.validate(object);
        validationResultModel.setResult(result);
        B persistedObject = null;
        if (result.hasMessages()) {
            statusBar.removeAll();
            statusBar.repaint();
            List<ValidationMessage> messages = validationResultModel.getResult().getMessages();
            for (ValidationMessage message : messages) {
                JsamsLabel label = new JsamsLabel(message.formattedText().replace(".", ""));
                Severity severity = message.severity();
                if (severity == Severity.ERROR) {
                    label.setIcon(ValidationResultViewFactory.getErrorIcon());
                } else if (severity == Severity.WARNING) {
                    label.setIcon(ValidationResultViewFactory.getWarningIcon());
                }
                statusBar.addComponent(label);
            }
            statusBar.validate();
        } else {
            if (getModel().getId() == null) {
                persistedObject = service.create(object);
            } else {
                // object.setId(getModel().getId());
                service.update(object);
                persistedObject = object;
            }
            dispose();
        }
        return persistedObject;
    }

    /**
     * Set the default keys actions.
     */
    private void setDefaultKeyActions() {
        // Automatically choose OK when Enter Key is pressed
        int noModifiers = 0;
        KeyStroke okKey = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, noModifiers, false);
        InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(okKey, JsamsButtonsPanel.OK_ACTION_KEY);
        rootPane.getActionMap().put(JsamsButtonsPanel.OK_ACTION_KEY, buttonsPanel.getButtonOk().getAction());

        KeyStroke cancelKey = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, noModifiers, false);
        inputMap.put(cancelKey, JsamsButtonsPanel.CANCEL_ACTION_KEY);
        rootPane.getActionMap().put(JsamsButtonsPanel.CANCEL_ACTION_KEY, buttonsPanel.getButtonCancel().getAction());
    }

    /**
     * 
     * @return the {@link JsamsStatusBar}
     */
    public JsamsStatusBar getStatusBar() {
        return statusBar;
    }

    /**
     * 
     * @param statusBar the {@link JsamsStatusBar} to set
     */
    public void setStatusBar(JsamsStatusBar statusBar) {
        this.statusBar = statusBar;
    }

    /**
     * @return the originalModel, this is the model before any modifications, a sort of backup
     */
    public B getOriginalModel() {
        return originalModel;
    }

    /**
     * @param originalModel the originalModel to set
     */
    public void setOriginalModel(B originalModel) {
        this.originalModel = originalModel;
    }

}
