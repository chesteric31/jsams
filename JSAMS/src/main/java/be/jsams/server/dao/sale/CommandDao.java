package be.jsams.server.dao.sale;

import java.util.List;

import be.jsams.common.bean.model.sale.CommandBean;
import be.jsams.server.dao.Dao;
import be.jsams.server.model.sale.Command;

/**
 * Command DAO interface.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface CommandDao extends Dao<Command> {

    /**
     * Find a list of {@link Command} following the criteria model.
     * 
     * @param criteria the criteria model
     * @return a list of {@link Command}
     */
    List<Command> findByCriteria(final CommandBean criteria);

}
