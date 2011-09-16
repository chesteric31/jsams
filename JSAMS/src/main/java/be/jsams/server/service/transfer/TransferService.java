package be.jsams.server.service.transfer;

import be.jsams.common.bean.model.transfer.TransferBean;

/**
 * Service to implement the documents transfer.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface TransferService {

    /**
     * Perform the transfer process.
     * 
     * @param model the wrapper contains all the beans to be transferred
     */
    void transfer(TransferBean model);

}
