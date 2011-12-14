package be.jsams.client.wizard;

import java.util.List;

import javax.swing.JPanel;

import be.jsams.client.i18n.I18nString;
import be.jsams.client.model.dialog.AbstractWizardDialog;
import be.jsams.client.swing.component.JsamsLabel;
import be.jsams.client.swing.component.JsamsStatusBar;
import be.jsams.client.swing.utils.DialogUtil;
import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.view.ViewFactory;

import com.jgoodies.validation.Severity;
import com.jgoodies.validation.ValidationMessage;
import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.ValidationResultModel;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.DefaultValidationResultModel;
import com.jgoodies.validation.view.ValidationResultViewFactory;

/**
 * Abstract wizard panel.
 * 
 * @param <B> the {@link AbstractIdentityBean} model
 * @param <V> the {@link Validator} for the click onto next button
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public abstract class JsamsWizardPanel<B extends AbstractIdentityBean<?, ?>, V extends Validator<B>> extends JPanel {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -7161061264822020315L;

    private JsamsWizardComponent wizardComponent;
    private I18nString panelTitle;
    private B model;
    private AbstractWizardDialog<?, ?> parent;
    private V validator;
    private ValidationResultModel validationResultModel = new DefaultValidationResultModel();

    /**
     * Constructor.
     * 
     * @param parent the {@link AbstractWizardDialog} parent
     * @param wizardComponent the {@link JsamsWizardComponent}
     * @param model the {@link AbstractIdentityBean} model
     * @param panelTitle the {@link I18nString} title
     * @param validator the {@link Validator} for the next button click
     */
    public JsamsWizardPanel(AbstractWizardDialog<?, ?> parent, JsamsWizardComponent wizardComponent, B model,
            final I18nString panelTitle, V validator) {
        this.parent = parent;
        this.wizardComponent = wizardComponent;
        this.model = model;
        this.panelTitle = panelTitle;
        this.validator = validator;
    }

    /**
     * Updating
     */
    public void update() {
        parent.pack();
        DialogUtil.centerComponentOnScreen(parent);
    }

    /**
     * Go next.
     */
    public void next() {
        goNext();
    }

    /**
     * Go back.
     */
    public void back() {
        goBack();
    }

    /**
     * @return the wizardComponent
     */
    public JsamsWizardComponent getWizardComponent() {
        return wizardComponent;
    }

    /**
     * @param wizardComponent the wizardComponent to set
     */
    public void setWizardComponent(JsamsWizardComponent wizardComponent) {
        this.wizardComponent = wizardComponent;
    }

    /**
     * Go to the next component.
     * 
     * @return true if all is right, false otherwise
     */
    protected boolean goNext() {
        int nextIndex = wizardComponent.getCurrentIndex() + 1;
        if (wizardComponent.getPanelList().size() > nextIndex) {
            wizardComponent.setCurrentIndex(nextIndex);
            wizardComponent.updateComponents();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Go to the previous component.
     * 
     * @return true if all is right, false otherwise
     */
    protected boolean goBack() {
        int previousIndex = wizardComponent.getCurrentIndex() - 1;
        if (previousIndex >= 0) {
            wizardComponent.setCurrentIndex(previousIndex);
            wizardComponent.updateComponents();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Change the current index with the panel index.
     * 
     * @param panelIndex the panel index
     */
    protected void switchPanel(int panelIndex) {
        wizardComponent.setCurrentIndex(panelIndex);
        wizardComponent.updateComponents();
    }

    /**
     * Set the back button to enabled/disabled.
     * 
     * @param set the boolean to apply
     */
    protected void setBackButtonEnabled(boolean set) {
        wizardComponent.getBackButton().setEnabled(set);
    }

    /**
     * Set the next button to enabled/disabled.
     * 
     * @param set the boolean to apply
     */
    protected void setNextButtonEnabled(boolean set) {
        wizardComponent.getNextButton().setEnabled(set);
    }

    /**
     * Set the finish button to enabled/disabled.
     * 
     * @param set the boolean to apply
     */
    protected void setFinishButtonEnabled(boolean set) {
        wizardComponent.getFinishButton().setEnabled(set);
    }

    /**
     * @return the title
     */
    public I18nString getPanelTitle() {
        return this.panelTitle;
    }

    /**
     * @param panelTitle the title to set
     */
    public void setPanelTitle(I18nString panelTitle) {
        this.panelTitle = panelTitle;
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
     * @return the {@link ViewFactory}
     */
    @SuppressWarnings("unchecked")
    public ViewFactory<B> getViewFactory() {
        return (ViewFactory<B>) getModel().buildView().getViewFactory();
    }

    /**
     * Method called before the next button click to validate the data.
     * 
     * @return true if all is OK to continue, false otherwise
     */
    protected boolean prePerformNext() {
        boolean toContinue = true;
        ValidationResult result = validator.validate(getModel());
        validationResultModel.setResult(result);
        JsamsStatusBar statusBar = parent.getStatusBar();
        if (result.hasMessages()) {
            List<ValidationMessage> messages = validationResultModel.getResult().getMessages();
            for (ValidationMessage message : messages) {
                JsamsLabel label = new JsamsLabel(message.formattedText().replace(".", ""));
                Severity severity = message.severity();
                if (severity == Severity.ERROR) {
                    label.setIcon(ValidationResultViewFactory.getErrorIcon());
                } else if (severity == Severity.WARNING) {
                    label.setIcon(ValidationResultViewFactory.getWarningIcon());
                }
//                statusBar.addComponent(label);
                statusBar.setTextWithIcon(label);
            }
            toContinue = false;
        } else {
            statusBar.clear();
        }
        return toContinue;
    }

    /**
     * @return the validationResultModel
     */
    public ValidationResultModel getValidationResultModel() {
        return validationResultModel;
    }

    /**
     * @param validationResultModel the validationResultModel to set
     */
    public void setValidationResultModel(ValidationResultModel validationResultModel) {
        this.validationResultModel = validationResultModel;
    }

    /**
     * @return the validator
     */
    public V getValidator() {
        return validator;
    }

    /**
     * @param validator the validator to set
     */
    public void setValidator(V validator) {
        this.validator = validator;
    }

    /**
     * @return the parent
     */
    public AbstractWizardDialog<?, ?> getParentDialog() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParentDialog(AbstractWizardDialog<?, ?> parent) {
        this.parent = parent;
    }
    
}
