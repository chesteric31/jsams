package be.jsams.client.wizard;

import java.util.List;

/**
 * Interface for all methods focused on panels level of a wizard component.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface JsamsWizard {

    /**
     * @return the {@link List} of {@link JsamsWizardPanel}
     */
    List<JsamsWizardPanel<?, ?>> getPanelList();

    /**
     * @param panelList the list of {@link JsamsWizardPanel} to set
     */
    void setWizardPanelList(List<JsamsWizardPanel<?, ?>> panelList);

    /**
     * Adds a {@link JsamsWizardPanel} to the current wizard.
     * 
     * @param panel the {@link JsamsWizardPanel} to add
     */
    void addPanel(JsamsWizardPanel<?, ?> panel);

    /**
     * Adds a {@link JsamsWizardPanel} to the current wizard at the specific
     * index.
     * 
     * @param index the index to add the {@link JsamsWizardPanel} to add
     * @param panel the {@link JsamsWizardPanel} to add
     */
    void addPanel(int index, JsamsWizardPanel<?, ?> panel);

    /**
     * Removes a {@link JsamsWizardPanel} defined as parameter.
     * 
     * @param panel the {@link JsamsWizardPanel} to remove
     * @return the removed {@link JsamsWizardPanel}
     */
    JsamsWizardPanel<?, ?> removePanel(JsamsWizardPanel<?, ?> panel);

    /**
     * Removes a {@link JsamsWizardPanel} defined at the index.
     * 
     * @param index the index where is the {@link JsamsWizardPanel} to remove
     * @return the removed {@link JsamsWizardPanel}
     */
    JsamsWizardPanel<?, ?> removePanel(int index);

    /**
     * Retrieves the {@link JsamsWizardPanel} at the defined index.
     * 
     * @param index the index of the {@link JsamsWizardPanel} to retrieve
     * @return the researched {@link JsamsWizardPanel}
     */
    JsamsWizardPanel<?, ?> getPanel(int index);

}
