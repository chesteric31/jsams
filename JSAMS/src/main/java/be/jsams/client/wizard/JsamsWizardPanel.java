package be.jsams.client.wizard;

import java.util.List;

import javax.swing.JPanel;

import be.jsams.client.i18n.I18nString;
import be.jsams.client.model.dialog.AbstractWizardDialog;
import be.jsams.client.swing.component.JsamsLabel;
import be.jsams.client.swing.component.JsamsStatusBar;
import be.jsams.client.swing.utils.DialogUtil;
import be.jsams.client.wizard.transfer.TransferWizardDialog;
import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.view.ViewFactory;

import com.jgoodies.validation.Severity;
import com.jgoodies.validation.ValidationMessage;
import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.ValidationResultModel;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.view.ValidationResultViewFactory;

/**
 * Abstract wizard panel.
 * 
 * @param <B> the {@link AbstractIdentityBean} model
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public abstract class JsamsWizardPanel<B extends AbstractIdentityBean<?, ?>> extends JPanel {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -7161061264822020315L;

    private JsamsWizardComponent wizardComponent;
    private I18nString panelTitle;
    private B model;
    private AbstractWizardDialog<?, ?, ?> parent;

    /**
     * Constructor.
     * 
     * @param parent the {@link AbstractWizardDialog} parent
     * @param wizardComponent the {@link JsamsWizardComponent}
     * @param model the {@link AbstractIdentityBean} model
     * @param panelTitle the {@link I18nString} title
     */
    public JsamsWizardPanel(AbstractWizardDialog<?, ?, ?> parent, JsamsWizardComponent wizardComponent, B model,
            final I18nString panelTitle) {
        this.parent = parent;
        this.wizardComponent = wizardComponent;
        this.model = model;
        this.panelTitle = panelTitle;
    }

    /**
     * Updating
     */
    public void update() {
        TransferWizardDialog parent = (TransferWizardDialog) this.getRootPane().getParent();
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
        return (ViewFactory<B>) getModel().getView().getViewFactory();
    }

    /**
     * Method called by the children class for validation.
     * 
     * @param bean the bean to validate
     * @return true if all is OK to continue, false otherwise
     */
    @SuppressWarnings("unchecked")
    public boolean prePerformNext(B bean) {
        boolean toContinue = true;
        Validator<B> validator = (Validator<B>) parent.getValidator();
        ValidationResult result = validator.validate((B) bean);
        ValidationResultModel validationResultModel = parent.getValidationResultModel();
        validationResultModel.setResult(result);
        if (result.hasMessages()) {
            JsamsStatusBar statusBar = parent.getStatusBar();
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
            toContinue = false;
        }
        return toContinue;
    }

}
