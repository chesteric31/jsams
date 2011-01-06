package be.jsams.client.model.dialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.client.desktop.JsamsMainFrame;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.swing.component.JsamsButtonsInterface;
import be.jsams.client.swing.component.JsamsButtonsPanel;
import be.jsams.client.swing.component.JsamsDialog;
import be.jsams.client.swing.component.JsamsLabel;
import be.jsams.client.swing.component.JsamsStatusBar;
import be.jsams.client.swing.component.JsamsTextField;
import be.jsams.server.model.AbstractIdentity;
import be.jsams.server.service.Service;

import com.jgoodies.validation.Severity;
import com.jgoodies.validation.ValidationMessage;
import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.ValidationResultModel;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.DefaultValidationResultModel;
import com.jgoodies.validation.view.ValidationResultViewFactory;

/**
 * Edit generic panel.
 * 
 * @param <M>
 *            a extension of {@link AbstractIdentity}
 * @param <V>
 *            a extension of {@link Validator}
 * @param <S>
 *            an extension of {@link Service}
 * @author chesteric31
 * @version $$Rev$$ $$Date::                  $$ $$Author$$
 */
public abstract class EditDialog<M extends AbstractIdentity, V extends Validator<M>, S extends Service<M>> extends
        JsamsDialog implements JsamsButtonsInterface {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 5146784638798425733L;

    private static final Log LOGGER = LogFactory.getLog(EditDialog.class);

    private M model;

    private JsamsButtonsPanel buttonsPanel;

    private ValidationResultModel validationResultModel = new DefaultValidationResultModel();

    private JsamsStatusBar statusBar;

    private JPanel southPanel;

    private Validator<M> validator;

    private Service<M> service;

    /**
     * Constructor
     * 
     * @param parent
     *            the {@link JsamsMainFrame} parent
     * @param title
     *            the {@link I18nString} translatable String
     * @param iconFileName
     *            the icon path file mane
     */
    public EditDialog(final JsamsMainFrame parent, final I18nString title, final String iconFileName) {
        super(parent, title, iconFileName);
        add(buildSouthPanel(), BorderLayout.SOUTH);
    }

    /**
     * Constructor
     * 
     * @param parent
     *            the {@link JsamsMainFrame} parent
     * @param title
     *            the {@link I18nString} translatable String
     */
    public EditDialog(final JsamsMainFrame parent, final I18nString title) {
        this(parent, title, null);
    }

    /**
     * 
     * @return the service
     */
    public Service<M> getService() {
        return service;
    }

    /**
     * 
     * @param service
     *            the service to set
     */
    public void setService(Service<M> service) {
        this.service = service;
    }

    /**
     * 
     * @return the model
     */
    public M getModel() {
        return model;
    }

    /**
     * 
     * @param model
     *            the model to set
     */
    public void setModel(M model) {
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
     * @param validationResultModel
     *            the {@link ValidationResultModel} to set
     */
    public void setValidationResultModel(ValidationResultModel validationResultModel) {
        this.validationResultModel = validationResultModel;
    }

    /**
     * 
     * @return the {@link Validator}
     */
    public Validator<M> getValidator() {
        return validator;
    }

    /**
     * 
     * @param validator
     *            the {@link Validator} to set
     */
    public void setValidator(Validator<M> validator) {
        this.validator = validator;
    }

    /**
     * Builds the 'south panel' composed by a {@link JsamsButtonsPanel} {@link JsamsStatusBar}
     * 
     * @return the 'south panel'
     */
    private JPanel buildSouthPanel() {
        statusBar = new JsamsStatusBar();
        southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.PAGE_AXIS));
        buttonsPanel = new JsamsButtonsPanel(this, true, true, true);
        southPanel.add(buttonsPanel);
        southPanel.add(statusBar);
        return southPanel;
    }

    /**
     * {@inheritDoc}
     */
    public void performCancel() {
        this.dispose();
    }

    /**
     * {@inheritDoc}
     */
    public void performReset() {
        performReset(this);
    }

    /**
     * Performs the reset for the {@link Container}.
     * 
     * @param container
     *            the contain to 'clean'
     */
    public void performReset(final Container container) {
        for (Component component : container.getComponents()) {
            if (component instanceof Container) {
                this.performReset((Container) component);
            }
            // NOT ELSE IF cause that doesn't work
            if (component instanceof JsamsTextField) {
                ((JsamsTextField) component).setText(null);
            } else if (component instanceof JComboBox) {
                ((JComboBox) component).setSelectedIndex(0);
            } else if (component instanceof JTextArea) {
                ((JTextArea) component).setText(null);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public abstract void performOk();

    /**
     * Method called by the children class for validation and persistence.
     * 
     * @param object
     *            the object to validate and to persist
     * @return true if success of the operations, false otherwise
     */
    protected boolean postPerformOk(final M object) {
        ValidationResult result = validator.validate(object);
        validationResultModel.setResult(result);
        boolean success = false;
        if (result.hasMessages()) {
            statusBar.removeAll();
            statusBar.repaint();
            List<ValidationMessage> messages = validationResultModel.getResult().getMessages();
            for (ValidationMessage message : messages) {
                JsamsLabel label = new JsamsLabel(message.formattedText().replace(".", ""));
                if (message.severity() == Severity.ERROR) {
                    label.setIcon(ValidationResultViewFactory.getErrorIcon());
                } else if (message.severity() == Severity.WARNING) {
                    label.setIcon(ValidationResultViewFactory.getWarningIcon());
                }
                statusBar.addJComponent(label);
            }
            statusBar.revalidate();
        } else {
            if (getModel() == null) {
                service.create(object);
            } else {
                object.setId(getModel().getId());
                service.update(object);
            }
            success = true;
            dispose();
        }
        return success;
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
     * @param statusBar
     *            the {@link JsamsStatusBar} to set
     */
    public void setStatusBar(JsamsStatusBar statusBar) {
        this.statusBar = statusBar;
    }

}
