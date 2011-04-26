package be.jsams.server.service.sale;

import java.util.List;

import be.jsams.common.bean.model.sale.CommandBean;
import be.jsams.server.service.Service;

/**
 * Command service interface.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface CommandService extends Service<CommandBean> {

    /**
     * Finds all {@link CommandBean} following the criteria {@link CommandBean}.
     * 
     * @param criteria
     *            the criteria {@link CommandBean}
     * @return a list of {@link CommandBean}
     */
    List<CommandBean> findByCriteria(final CommandBean criteria);

}
