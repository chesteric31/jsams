package be.jsams.common.bean.view;

import javax.swing.JComponent;

/**
 * Interface to give edition possibilities to a bean.
 * 
 * @param <E> an extension of {@link JComponent} for editing purpose
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface Editable<E extends JComponent> {

    /**
     * Creates a {@link JComponent} editing view of the current bean.
     * 
     * @return the {@link JComponent}
     */
    E createEditView();

}
