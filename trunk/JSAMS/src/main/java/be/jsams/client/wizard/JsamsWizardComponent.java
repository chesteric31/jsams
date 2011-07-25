package be.jsams.client.wizard;

import java.beans.PropertyChangeListener;

import javax.swing.JPanel;

import be.jsams.client.swing.component.JsamsButton;
import be.jsams.client.wizard.action.CancelAction;
import be.jsams.client.wizard.action.FinishAction;

/**
 * 
 *
 * @author ebinard
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface JsamsWizardComponent extends JsamsWizard {

    String CURRENT_PANEL_PROPERTY = "currentPanel";

    /**
     * Add the {@link PropertyChangeListener}.
     * 
     * @param listener the {@link PropertyChangeListener} to add
     */
    void addPropertyChangeListener(PropertyChangeListener listener);

    /**
     * Remove the {@link PropertyChangeListener}.
     * 
     * @param listener the {@link PropertyChangeListener} to remove
     */
    void removePropertyChangeListener(PropertyChangeListener listener);
    
    /**
     * Add a {@link JsamsWizardPanel} after an another one.
     * 
     * @param panelToBePlacedAfter the {@link JsamsWizardPanel} to place after
     * @param panel the referenced {@link JsamsWizardPanel}
     */
    void addPanelAfter(JsamsWizardPanel<?> panelToBePlacedAfter, JsamsWizardPanel<?> panel);

    /**
     * Add a {@link JsamsWizardPanel} before an another one.
     * 
     * @param panelToBePlacedBefore the {@link JsamsWizardPanel} to place before
     * @param panel the referenced {@link JsamsWizardPanel}
     */
    void addPanelBefore(JsamsWizardPanel<?> panelToBePlacedBefore, JsamsWizardPanel<?> panel);

    /**
     * Add a {@link JsamsWizardPanel} after the current {@link JsamsWizardPanel}.
     * 
     * @param panel the {@link JsamsWizardPanel} to add
     */
    void addPanelAfterCurrent(JsamsWizardPanel<?> panel);

    /**
     * Remove a {@link JsamsWizardPanel} after an another one.
     * 
     * @param panel the {@link JsamsWizardPanel} after to remove
     * @return the removed {@link JsamsWizardPanel}
     */
    JsamsWizardPanel<?> removeWizardPanelAfter(JsamsWizardPanel<?> panel);
    

    /**
     * Remove a {@link JsamsWizardPanel} before an another one.
     * 
     * @param panel the {@link JsamsWizardPanel} before to remove
     * @return the removed {@link JsamsWizardPanel}
     */
    JsamsWizardPanel<?> removeWizardPanelBefore(JsamsWizardPanel<?> panel);

    /**
     * Get the index of the panel.
     * 
     * @param panel the {@link JsamsWizardPanel} to identify the index
     * @return the index of the {@link JsamsWizardPanel} 
     */
    int getIndexOfPanel(JsamsWizardPanel<?> panel);

    /**
     * Update the components.
     */
    void updateComponents();

    /**
     * Get the current {@link JsamsWizardPanel}.
     * 
     * @return the current {@link JsamsWizardPanel}
     */
    JsamsWizardPanel<?> getCurrentPanel();

    /**
     * @return the {@link FinishAction}
     */
    FinishAction getFinishAction();

    /**
     * @param aFinishAction the {@link FinishAction} to set
     */
    void setFinishAction(FinishAction aFinishAction);

    /**
     * @return the {@link CancelAction}
     */
    CancelAction getCancelAction();

    /**
     * @param aCancelAction the {@link CancelAction} to set
     */
    void setCancelAction(CancelAction aCancelAction);

    /**
     * @return the current index
     */
    int getCurrentIndex();

    /**
     * @param aCurrentIndex the current index to set
     */
    void setCurrentIndex(int aCurrentIndex);

    /**
     * @return the panel containers of the {@link JsamsWizardPanel} 
     */
    JPanel getPanelsContainer();

    /**
     * @param aWizardPanelsContainer the panel containers to set
     */
    void setWizardPanelsContainer(JPanel aWizardPanelsContainer);

    /**
     * @return the back {@link JsamsButton}
     */
    JsamsButton getBackButton();

    /**
     * @param aBackButton the back {@link JsamsButton} to set
     */
    void setBackButton(JsamsButton aBackButton);

    /**
     * @return the next {@link JsamsButton}
     */
    JsamsButton getNextButton();

    /**
     * @param aNextButton the next {@link JsamsButton} to set
     */
    void setNextButton(JsamsButton aNextButton);

    /**
     * @return the cancel {@link JsamsButton}
     */
    JsamsButton getCancelButton();

    /**
     * @param aCancelButton the cancel {@link JsamsButton} to set
     */
    void setCancelButton(JsamsButton aCancelButton);

    /**
     * @return the finish {@link JsamsButton}
     */
    JsamsButton getFinishButton();

    /**
     * @param aFinishButton the finish {@link JsamsButton} to set
     */
    void setFinishButton(JsamsButton aFinishButton);

    /**
     * Boolean to indicate if we are onto the last panel.
     *  
     * @return true if we are onto the last panel, false otherwise
     */
    boolean onLastPanel();

}
