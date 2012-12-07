package be.jsams.server.service.transfer.impl;

import be.jsams.common.bean.model.transfer.TransferBean;
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
//            if (destinationType == 1) {
//                estimateCommandTransferService.transfer(model);
//            } else if (destinationType == 3) {
//                estimateBillTransferService.transfer(model);
//            }
            break;
        case 3:
//            if (destinationType == 1) {
//                estimateCommandTransferService.transfer(model);
//            } else if (destinationType == 3) {
//                estimateBillTransferService.transfer(model);
//            }
            break;
        case 4:
//            if (destinationType == 1) {
//                estimateCommandTransferService.transfer(model);
//            } else if (destinationType == 3) {
//                estimateBillTransferService.transfer(model);
//            }
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
     * @param estimateCommandTransferService the estimateCommandTransferService to set
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

}
