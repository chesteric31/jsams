package be.jsams.common.bean.view;

import javax.swing.JPanel;

/**
 * Interface to give wizard possibilities to a bean.
 * 
 * @param <W> an extension of {@link JPanel} for wizard purpose
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface Wizardable<W extends JPanel> {

    /**
     * Create a {@link JPanel} wizard view of the current bean.
     * 
     * @return the {@link JPanel}
     */
    W createWizardView();

}
