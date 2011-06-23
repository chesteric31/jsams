package be.jsams.client.wizard;

import javax.swing.JPanel;

import be.jsams.client.i18n.I18nString;

/**
 * 
 *
 * @author ebinard
 * @version $Rev$ $Date::                  $ $Author$
 */
public abstract class JsamsWizardPanel extends JPanel {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -7161061264822020315L;

    private JsamsWizardComponent wizardComponent;
    private I18nString title;
    
    /**
     * Constructor.
     * 
     * @param wizardComponent the {@link JsamsWizardComponent}
     */
    public JsamsWizardPanel(JsamsWizardComponent wizardComponent) {
        this(wizardComponent, null);
    }

    /**
     * Constructor.
     * 
     * @param wizardComponent the {@link JsamsWizardComponent}
     * @param title the {@link I18nString} title
     */
    public JsamsWizardPanel(JsamsWizardComponent wizardComponent, final I18nString title) {
        this.title = title;
        this.wizardComponent = wizardComponent;
    }

    /**
     * Updating
     */
    public void update() {
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
    public I18nString getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(I18nString title) {
        this.title = title;
    }

}
