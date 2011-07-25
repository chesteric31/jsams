package be.jsams.common.bean.view;

import javax.swing.JPanel;

/**
 * Interface to give searching possibilities to a bean.
 * 
 * @param <S> an extension of {@link JPanel} for searching purpose
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface Searchable<S extends JPanel> {

    /**
     * Create a {@link JPanel} searching view of the current bean.
     * 
     * @return the {@link JPanel}
     */
    S createSearchView();

}
