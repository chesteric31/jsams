package be.jsams.client.wizard.action;

import be.jsams.client.wizard.JsamsWizardComponent;

/**
 * 
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public abstract class CancelAction implements Action {

    private JsamsWizardComponent component;

    /**
     * Constructor.
     * 
     * @param component the {@link JsamsWizardComponent}
     */
    public CancelAction(JsamsWizardComponent component) {
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
