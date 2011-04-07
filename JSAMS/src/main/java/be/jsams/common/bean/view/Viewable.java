package be.jsams.common.bean.view;

import javax.swing.JComponent;

/**
 * View interface that composes a {@link JComponent} for customized the current bean.
 * 
 * @param <J>
 *            an extension of {@link JComponent} for editing
 * @param <K>
 *            an extension of {@link JComponent} for searching
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface Viewable<J extends JComponent, K extends JComponent> {
    
    /**
     * Creates a {@link JComponent} editing view of the current bean.
     * 
     * @return the {@link JComponent}
     */
    J createEditView();

    /**
     * Creates a {@link JComponent} searching view of the current bean.
     * 
     * @return the {@link JComponent}
     */
    K createSearchView();

}
