package be.jsams.server.dao;

import java.util.List;

import be.jsams.common.bean.model.sale.CommandBean;
import be.jsams.server.model.Command;

/**
 * Command DAO interface.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface CommandDao extends Dao<Command> {

    /**
     * Finds a list of {@link Command} following the criteria model.
     * 
     * @param criteria the criteria model
     * @return a list of {@link Command}
     */
    List<Command> findByCriteria(final CommandBean criteria);

}
