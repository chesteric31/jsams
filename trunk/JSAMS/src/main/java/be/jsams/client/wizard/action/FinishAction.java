package be.jsams.client.wizard.action;

import be.jsams.client.wizard.JsamsWizardComponent;

/**
 * 
 *
 * @author ebinard
 * @version $Rev$ $Date::                  $ $Author$
 */
public abstract class FinishAction implements Action {

    private JsamsWizardComponent component;

    /**
     * Constructor.
     * 
     * @param component the {@link JsamsWizardComponent}
     */
    public FinishAction(JsamsWizardComponent component) {
        this.component = component;
    }

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
    
}
