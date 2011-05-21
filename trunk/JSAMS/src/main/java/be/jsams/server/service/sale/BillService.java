package be.jsams.server.service.sale;

import java.util.List;

import be.jsams.common.bean.model.sale.BillBean;
import be.jsams.server.service.Service;

/**
 * Bill service interface.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface BillService extends Service<BillBean> {

    /**
     * Find all {@link BillBean} following the criteria {@link BillBean}.
     * 
     * @param criteria the criteria {@link BillBean}
     * @return a list of {@link BillBean}
     */
    List<BillBean> findByCriteria(final BillBean criteria);

}
