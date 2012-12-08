package be.jsams.server.service.transfer.impl;

import be.jsams.common.bean.model.transfer.TransferBean;
import be.jsams.server.service.transfer.BillCreditNoteTransferService;
import be.jsams.server.service.transfer.CommandBillTransferService;
import be.jsams.server.service.transfer.CommandDeliveryOrderTransferService;
import be.jsams.server.service.transfer.DeliveryOrderBillTransferService;
import be.jsams.server.service.transfer.EstimateBillTransferService;
import be.jsams.server.service.transfer.EstimateCommandTransferService;
import be.jsams.server.service.transfer.TransferService;

/**
 * Implementation the documents transfer service.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class TransferServiceImpl implements TransferService {

    private EstimateCommandTransferService estimateCommandTransferService;
    private EstimateBillTransferService estimateBillTransferService;
    private CommandDeliveryOrderTransferService commandDeliveryOrderTransferService;
    private CommandBillTransferService commandBillTransferService;
    private DeliveryOrderBillTransferService deliveryOrderBillTransferService;
    private BillCreditNoteTransferService billCreditNoteTransferService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void transfer(TransferBean model) {
        int sourceType = model.getSourceType();
        int destinationType = model.getDestinationType();
        switch (sourceType) {
        case 1:
            if (destinationType == 1) {
                estimateCommandTransferService.transfer(model);
            } else if (destinationType == 3) {
                estimateBillTransferService.transfer(model);
            }
            break;
        case 2:
            if (destinationType == 2) {
                commandDeliveryOrderTransferService.transfer(model);
            } else if (destinationType == 3) {
                commandBillTransferService.transfer(model);
            }
            break;
        case 3:
            if (destinationType == 3) {
                deliveryOrderBillTransferService.transfer(model);
            }
            break;
        case 4:
            if (destinationType == 4) {
                billCreditNoteTransferService.transfer(model);
            }
            break;
        default:
            break;
        }
    }

    /**
     * @return the estimateCommandTransferService
     */
    public EstimateCommandTransferService getEstimateCommandTransferService() {
        return estimateCommandTransferService;
    }

    /**
     * @param estimateCommandTransferService the estimateCommandTransferService
     *            to set
     */
    public void setEstimateCommandTransferService(EstimateCommandTransferService estimateCommandTransferService) {
        this.estimateCommandTransferService = estimateCommandTransferService;
    }

    /**
     * @return the estimateBillTransferService
     */
    public EstimateBillTransferService getEstimateBillTransferService() {
        return estimateBillTransferService;
    }

    /**
     * @param estimateBillTransferService the estimateBillTransferService to set
     */
    public void setEstimateBillTransferService(EstimateBillTransferService estimateBillTransferService) {
        this.estimateBillTransferService = estimateBillTransferService;
    }

    /**
     * @return the commandDeliveryOrderTransferService
     */
    public CommandDeliveryOrderTransferService getCommandDeliveryOrderTransferService() {
        return commandDeliveryOrderTransferService;
    }

    /**
     * @param commandDeliveryOrderTransferService the
     *            commandDeliveryOrderTransferService to set
     */
    public void setCommandDeliveryOrderTransferService(
            CommandDeliveryOrderTransferService commandDeliveryOrderTransferService) {
        this.commandDeliveryOrderTransferService = commandDeliveryOrderTransferService;
    }

    /**
     * @return the commandBillTransferService
     */
    public CommandBillTransferService getCommandBillTransferService() {
        return commandBillTransferService;
    }

    /**
     * @param commandBillTransferService the commandBillTransferService to set
     */
    public void setCommandBillTransferService(CommandBillTransferService commandBillTransferService) {
        this.commandBillTransferService = commandBillTransferService;
    }

    /**
     * @return the deliveryOrderBillTransferService
     */
    public DeliveryOrderBillTransferService getDeliveryOrderBillTransferService() {
        return deliveryOrderBillTransferService;
    }

    /**
     * @param deliveryOrderBillTransferService the
     *            deliveryOrderBillTransferService to set
     */
    public void setDeliveryOrderBillTransferService(DeliveryOrderBillTransferService deliveryOrderBillTransferService) {
        this.deliveryOrderBillTransferService = deliveryOrderBillTransferService;
    }

    /**
     * @return the billCreditNoteTransferService
     */
    public BillCreditNoteTransferService getBillCreditNoteTransferService() {
        return billCreditNoteTransferService;
    }

    /**
     * @param billCreditNoteTransferService the billCreditNoteTransferService to
     *            set
     */
    public void setBillCreditNoteTransferService(BillCreditNoteTransferService billCreditNoteTransferService) {
        this.billCreditNoteTransferService = billCreditNoteTransferService;
    }

}
