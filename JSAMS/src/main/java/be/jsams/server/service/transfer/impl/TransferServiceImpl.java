package be.jsams.server.service.transfer.impl;

import be.jsams.server.dao.sale.BillDao;
import be.jsams.server.dao.sale.CommandDao;
import be.jsams.server.dao.sale.CreditNoteDao;
import be.jsams.server.dao.sale.DeliveryOrderDao;
import be.jsams.server.dao.sale.EstimateDao;
import be.jsams.server.service.transfer.TransferService;

/**
 * 
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class TransferServiceImpl implements TransferService {
    
    private EstimateDao estimateDao;
    private CommandDao commandDao;
    private DeliveryOrderDao deliveryOrderDao;
    private BillDao billDao;
    private CreditNoteDao creditNoteDao;

    /**
     * @return the estimateDao
     */
    public EstimateDao getEstimateDao() {
        return estimateDao;
    }

    /**
     * @param estimateDao the estimateDao to set
     */
    public void setEstimateDao(EstimateDao estimateDao) {
        this.estimateDao = estimateDao;
    }

    /**
     * @return the commandDao
     */
    public CommandDao getCommandDao() {
        return commandDao;
    }

    /**
     * @param commandDao the commandDao to set
     */
    public void setCommandDao(CommandDao commandDao) {
        this.commandDao = commandDao;
    }

    /**
     * @return the deliveryOrderDao
     */
    public DeliveryOrderDao getDeliveryOrderDao() {
        return deliveryOrderDao;
    }

    /**
     * @param deliveryOrderDao the deliveryOrderDao to set
     */
    public void setDeliveryOrderDao(DeliveryOrderDao deliveryOrderDao) {
        this.deliveryOrderDao = deliveryOrderDao;
    }

    /**
     * @return the billDao
     */
    public BillDao getBillDao() {
        return billDao;
    }

    /**
     * @param billDao the billDao to set
     */
    public void setBillDao(BillDao billDao) {
        this.billDao = billDao;
    }

    /**
     * @return the creditNoteDao
     */
    public CreditNoteDao getCreditNoteDao() {
        return creditNoteDao;
    }

    /**
     * @param creditNoteDao the creditNoteDao to set
     */
    public void setCreditNoteDao(CreditNoteDao creditNoteDao) {
        this.creditNoteDao = creditNoteDao;
    }
    
    

}
