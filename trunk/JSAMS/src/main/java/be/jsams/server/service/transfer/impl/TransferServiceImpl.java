package be.jsams.server.service.transfer.impl;

import be.jsams.common.bean.model.transfer.TransferBean;
import be.jsams.server.service.transfer.BillTransferService;
import be.jsams.server.service.transfer.CommandTransferService;
import be.jsams.server.service.transfer.DeliveryOrderTransferService;
import be.jsams.server.service.transfer.EstimateTransferService;
import be.jsams.server.service.transfer.TransferService;

/**
 * Implementation the documents transfer service.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class TransferServiceImpl implements TransferService {

    private EstimateTransferService estimateTransferService;
    private CommandTransferService commandTransferService;
    private DeliveryOrderTransferService deliveryOrderTransferService;
    private BillTransferService billTransferService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void transfer(TransferBean model) {
        int sourceType = model.getSourceType();
        switch (sourceType) {
        case 1:
            estimateTransferService.transfer(model);
            break;
        case 2:
            commandTransferService.transfer(model);
            break;
        case 3:
            deliveryOrderTransferService.transfer(model);
            break;
        case 4:
            billTransferService.transfer(model);
            break;
        default:
            break;
        }
    }

    /**
     * @return the estimateTransferService
     */
    public EstimateTransferService getEstimateTransferService() {
        return estimateTransferService;
    }

    /**
     * @param estimateTransferService the estimateTransferService to set
     */
    public void setEstimateTransferService(EstimateTransferService estimateTransferService) {
        this.estimateTransferService = estimateTransferService;
    }

    /**
     * @return the commandTransferService
     */
    public CommandTransferService getCommandTransferService() {
        return commandTransferService;
    }

    /**
     * @param commandTransferService the commandTransferService to set
     */
    public void setCommandTransferService(CommandTransferService commandTransferService) {
        this.commandTransferService = commandTransferService;
    }

    /**
     * @return the deliveryOrderTransferService
     */
    public DeliveryOrderTransferService getDeliveryOrderTransferService() {
        return deliveryOrderTransferService;
    }

    /**
     * @param deliveryOrderTransferService the deliveryOrderTransferService to
     *            set
     */
    public void setDeliveryOrderTransferService(DeliveryOrderTransferService deliveryOrderTransferService) {
        this.deliveryOrderTransferService = deliveryOrderTransferService;
    }

    /**
     * @return the billTransferService
     */
    public BillTransferService getBillTransferService() {
        return billTransferService;
    }

    /**
     * @param billTransferService the billTransferService to set
     */
    public void setBillTransferService(BillTransferService billTransferService) {
        this.billTransferService = billTransferService;
    }

}
