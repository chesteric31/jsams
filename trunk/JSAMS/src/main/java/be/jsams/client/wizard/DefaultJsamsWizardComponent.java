package be.jsams.client.wizard;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import be.jsams.client.i18n.I18nString;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.swing.component.JsamsButton;
import be.jsams.client.wizard.action.CancelAction;
import be.jsams.client.wizard.action.FinishAction;

/**
 * 
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class DefaultJsamsWizardComponent implements JsamsWizardComponent {

    private JsamsButton backButton;
    private JsamsButton nextButton;
    private JsamsButton cancelButton;
    private JsamsButton finishButton;

    private FinishAction finishAction;
    private CancelAction cancelAction;

    private List<JsamsWizardPanel<?, ?>> panelList;
    private int currentIndex;
    private JPanel panelsContainer;
    private PropertyChangeSupport propertyChangeListeners;

    /**
     * Constructor
     */
    public DefaultJsamsWizardComponent() {
        initComponents();
    }

    /**
     * Initialize all the components.
     */
    private void initComponents() {
        this.propertyChangeListeners = new PropertyChangeSupport(this);
        I18nString wizardButtonBack = JsamsI18nResource.WIZARD_BUTTON_BACK;
        backButton = new JsamsButton(wizardButtonBack);
        backButton.setMnemonic(wizardButtonBack.getTranslation().charAt(2)); 
        I18nString wizardButtonNext = JsamsI18nResource.WIZARD_BUTTON_NEXT;
        nextButton = new JsamsButton(wizardButtonNext);
        nextButton.setMnemonic(wizardButtonNext.getTranslation().charAt(0)); 
        I18nString wizardButtonCancel = JsamsI18nResource.WIZARD_BUTTON_CANCEL;
        cancelButton = new JsamsButton(wizardButtonCancel);
        cancelButton.setMnemonic(wizardButtonCancel.getTranslation().charAt(0)); 
        I18nString wizardButtonFinish = JsamsI18nResource.WIZARD_BUTTON_FINISH;
        finishButton = new JsamsButton(wizardButtonFinish);
        finishButton.setMnemonic(wizardButtonFinish.getTranslation().charAt(0)); 
        
        panelList = new ArrayList<JsamsWizardPanel<?, ?>>();
        currentIndex = 0;
        panelsContainer = new JPanel(new CardLayout());

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getCurrentPanel().back();
            }
        });

        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getCurrentPanel().next();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getCancelAction().performAction();
            }
        });

        finishButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getFinishAction().performAction();
            }
        });
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<JsamsWizardPanel<?, ?>> getPanelList() {
        return this.getPanelList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWizardPanelList(List<JsamsWizardPanel<?, ?>> panelList) {
        this.panelList = panelList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPanel(JsamsWizardPanel<?, ?> panel) {
        panelList.add(panel);
        panelsContainer.add(panel, panelList.size() - 1 + "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPanel(int index, JsamsWizardPanel<?, ?> panel) {
        panelList.add(index, panel);
        panelsContainer.add(panel, index + "", index);
        if (index < panelList.size() - 1) {
            for (int i = index + 1; i < panelList.size(); i++) {
                JsamsWizardPanel<?, ?> panelToMove = panelList.get(i);
                panelsContainer.add(panelToMove, i);
//                panelsContainer.add(panelToMove, i + "");
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsamsWizardPanel<?, ?> removePanel(JsamsWizardPanel<?, ?> panel) {
        int index = panelList.indexOf(panel);
        panelList.remove(panel);
        panelsContainer.remove(panel);
        // TODO verify this loop
        for (int i = index; i < panelList.size(); i++) {
            JsamsWizardPanel<?, ?> panelToMove = panelList.get(i);
            panelsContainer.add(panelToMove, i);
//            panelsContainer.add(panelToMove, i + "");
        }
        return panel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsamsWizardPanel<?, ?> removePanel(int index) {
        panelsContainer.remove(index);
        JsamsWizardPanel<?, ?> panel = panelList.remove(index);
        for (int i = index; i < panelList.size(); i++) {
            JsamsWizardPanel<?, ?> panelToMove = panelList.get(i);
            panelsContainer.add(panelToMove, i);
//            panelsContainer.add(panelToMove, i + "");
        }
        return panel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsamsWizardPanel<?, ?> getPanel(int index) {
        return getPanelList().get(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeListeners.addPropertyChangeListener(listener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeListeners.removePropertyChangeListener(listener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPanelAfter(JsamsWizardPanel<?, ?> panelToBePlacedAfter, JsamsWizardPanel<?, ?> panel) {
        addPanel(panelList.indexOf(panelToBePlacedAfter) + 1, panel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPanelBefore(JsamsWizardPanel<?, ?> panelToBePlacedBefore, JsamsWizardPanel<?, ?> panel) {
        addPanel(panelList.indexOf(panelToBePlacedBefore) - 1, panel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPanelAfterCurrent(JsamsWizardPanel<?, ?> panel) {
        addPanel(currentIndex + 1, panel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsamsWizardPanel<?, ?> removeWizardPanelAfter(JsamsWizardPanel<?, ?> panel) {
        return removePanel(panelList.indexOf(panel) + 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsamsWizardPanel<?, ?> removeWizardPanelBefore(JsamsWizardPanel<?, ?> panel) {
        return removePanel(panelList.indexOf(panel) - 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getIndexOfPanel(JsamsWizardPanel<?, ?> panel) {
        return panelList.indexOf(panel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateComponents() {
        CardLayout layout = (CardLayout) panelsContainer.getLayout();
        layout.show(panelsContainer, currentIndex + "");

        if (currentIndex == 0) {
            backButton.setEnabled(false);
        } else {
            backButton.setEnabled(true);
        }

        if (onLastPanel()) {
            nextButton.setEnabled(false);
            finishButton.setEnabled(true);
        } else {
            finishButton.setEnabled(false);
            nextButton.setEnabled(true);
        }
        // let panel to update itself
        getCurrentPanel().update();

        // inform PropertyChangeListeners
        PropertyChangeEvent event = new PropertyChangeEvent(this, CURRENT_PANEL_PROPERTY, null, getCurrentPanel());
        propertyChangeListeners.firePropertyChange(event);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsamsWizardPanel<?, ?> getCurrentPanel() {
        return panelList.get(currentIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FinishAction getFinishAction() {
        return this.finishAction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFinishAction(FinishAction aFinishAction) {
        this.finishAction = aFinishAction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CancelAction getCancelAction() {
        return this.cancelAction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCancelAction(CancelAction aCancelAction) {
        this.cancelAction = aCancelAction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCurrentIndex() {
        return this.currentIndex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCurrentIndex(int aCurrentIndex) {
        this.currentIndex = aCurrentIndex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getPanelsContainer() {
        return this.panelsContainer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWizardPanelsContainer(JPanel aWizardPanelsContainer) {
        this.panelsContainer = aWizardPanelsContainer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsamsButton getBackButton() {
        return this.backButton;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBackButton(JsamsButton aBackButton) {
        this.backButton = aBackButton;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsamsButton getNextButton() {
        return this.nextButton;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNextButton(JsamsButton aNextButton) {
        this.nextButton = aNextButton;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsamsButton getCancelButton() {
        return this.cancelButton;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCancelButton(JsamsButton aCancelButton) {
        this.cancelButton = aCancelButton;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsamsButton getFinishButton() {
        return this.finishButton;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFinishButton(JsamsButton aFinishButton) {
        this.finishButton = aFinishButton;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onLastPanel() {
        int size = panelList.size();
        return (getCurrentIndex() == size - 1);
    }

}
