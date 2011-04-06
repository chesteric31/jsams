package be.jsams.common.bean.model;


/**
 * Interface for the refreshable beans.
 *
 * @param <B> the bean to refresh
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface Refreshable<B extends AbstractIdentityBean<?, ?>> {

    /**
     * Refresh the current bean with the parameterized bean.
     * 
     * @param bean the bean to use for the refresh
     */
    void refresh(final B bean);

}
