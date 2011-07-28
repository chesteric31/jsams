package be.jsams.server.service.transfer.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.sale.AbstractDocumentBean;
import be.jsams.common.bean.model.sale.CommandBean;
import be.jsams.common.bean.model.sale.EstimateBean;
import be.jsams.common.bean.model.sale.detail.CommandDetailBean;
import be.jsams.common.bean.model.sale.detail.EstimateDetailBean;
import be.jsams.common.bean.model.transfer.TransferBean;
import be.jsams.server.service.sale.BillService;
import be.jsams.server.service.sale.CommandService;
import be.jsams.server.service.sale.CreditNoteService;
import be.jsams.server.service.sale.DeliveryOrderService;
import be.jsams.server.service.sale.EstimateService;
import be.jsams.server.service.transfer.TransferService;

/**
 * 
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class TransferServiceImpl implements TransferService {
    
    private EstimateService estimateService;
    private CommandService commandService;
    private DeliveryOrderService deliveryOrderService;
    private BillService billService;
    private CreditNoteService creditNoteService;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void transfer(TransferBean model) {
        int sourceType = model.getSourceType();

        switch (sourceType) {
        case 1:
            estimateTransfer(model);
            break;
        case 2:
            commandTransfer(model);
            break;
        case 3:
            deliveryOrderTransfer(model);
            break;
        case 4:
            billTransfer(model);
            break;
        default:
            break;
        }
    }

    /**
     * @param model the wrapper contains all the beans to be transferred
     */
    private void estimateTransfer(TransferBean model) {
        int destinationType = model.getDestinationType();
        switch (destinationType) {
        case 1:
            estimateToCommandTransfer(model);
            break;
        case 2:
            estimateToDeliveryOrderTransfer(model);
            break;
        case 3:
            estimateToBillTransfer(model);
            break;
        case 4:
            estimateToCreditNoteTransfer(model);
            break;
        default:
            break;
        }
    }

    /**
     * @param model the wrapper contains all the beans to be transferred
     */
    private void estimateToCommandTransfer(TransferBean model) {
        int transferMode = model.getTransferMode();
        switch (transferMode) {
        case 1:
            List<? extends AbstractDocumentBean<?, ?>> documents = model.getDocuments();
            EstimateBean estimate = (EstimateBean) documents.get(0);
            CustomerBean customer = estimate.getCustomer();
            CommandBean newBean = new CommandBean(estimate.getSociety(), customer, estimate.getAgent());
            newBean.setBillingAddress(estimate.getBillingAddress());
            newBean.setCreationDate(new Date());
            newBean.setDeliveryAddress(customer.getDeliveryAddress());
            List<CommandDetailBean> details = new ArrayList<CommandDetailBean>();
            for (EstimateDetailBean detail : estimate.getDetails()) {
                CommandDetailBean bean = new CommandDetailBean();
                bean.setCommand(newBean);
                bean.setDiscountRate(detail.getDiscountRate());
                bean.setPrice(detail.getPrice());
                bean.setProduct(detail.getProduct());
                bean.setQuantity(detail.getQuantity());
                bean.setTransferred(false);
                bean.setVatApplicable(detail.getVatApplicable());
                details.add(bean);
            }
            newBean.setDetails(details);
            newBean.setDiscountRate(estimate.getDiscountRate());
            newBean.setRemark(estimate.getRemark());
            newBean.setTransferred(false);
            commandService.create(newBean);
            estimate.setTransferred(true);
            estimateService.update(estimate);
            break;
        case 2:
//            estimateToCommandPartialTransfer(model);
            break;
        case 3:
//            estimateToCommandFullGroupedTransfer(model);
            break;
        case 4:
//            estimateToCommandPartialGroupedTransfer(model);
            break;
        default:
            break;
        }
    }

    /**
     * @param model the wrapper contains all the beans to be transferred
     */
    private void estimateToDeliveryOrderTransfer(TransferBean model) {
        int transferMode = model.getTransferMode();
        switch (transferMode) {
        case 1:
//            estimateToDeliveryOrderFullTransfer(model);
            break;
        case 2:
//            estimateToDeliveryOrderPartialTransfer(model);
            break;
        case 3:
//            estimateToDeliveryOrderFullGroupedTransfer(model);
            break;
        case 4:
//            estimateToDeliveryOrderPartialGroupedTransfer(model);
            break;
        default:
            break;
        }
    }

    /**
     * @param model the wrapper contains all the beans to be transferred
     */
    private void estimateToBillTransfer(TransferBean model) {
        int transferMode = model.getTransferMode();
        switch (transferMode) {
        case 1:
//            estimateToBillFullTransfer(model);
            break;
        case 2:
//            estimateToDeliveryOrderPartialTransfer(model);
            break;
        case 3:
//            estimateToDeliveryOrderFullGroupedTransfer(model);
            break;
        case 4:
//            estimateToDeliveryOrderPartialGroupedTransfer(model);
            break;
        default:
            break;
        }
    }

    /**
     * @param model the wrapper contains all the beans to be transferred
     */
    private void estimateToCreditNoteTransfer(TransferBean model) {
        int transferMode = model.getTransferMode();
        switch (transferMode) {
        case 1:
//            estimateToCreditNoteFullTransfer(model);
            break;
        case 2:
//            estimateToCreditNotePartialTransfer(model);
            break;
        case 3:
//            estimateToCreditNoteFullGroupedTransfer(model);
            break;
        case 4:
//            estimateToCreditNotePartialGroupedTransfer(model);
            break;
        default:
            break;
        }
    }

    /**
     * @param model the wrapper contains all the beans to be transferred
     */
    private void billTransfer(TransferBean model) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    /**
     * @param model the wrapper contains all the beans to be transferred
     */
    private void deliveryOrderTransfer(TransferBean model) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    /**
     * @param model the wrapper contains all the beans to be transferred
     */
    private void commandTransfer(TransferBean model) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    /**
     * @return the estimateService
     */
    public EstimateService getEstimateService() {
        return estimateService;
    }

    /**
     * @param estimateService the estimateService to set
     */
    public void setEstimateService(EstimateService estimateService) {
        this.estimateService = estimateService;
    }

    /**
     * @return the commandService
     */
    public CommandService getCommandService() {
        return commandService;
    }

    /**
     * @param commandService the commandService to set
     */
    public void setCommandService(CommandService commandService) {
        this.commandService = commandService;
    }

    /**
     * @return the deliveryOrderService
     */
    public DeliveryOrderService getDeliveryOrderService() {
        return deliveryOrderService;
    }

    /**
     * @param deliveryOrderService the deliveryOrderService to set
     */
    public void setDeliveryOrderService(DeliveryOrderService deliveryOrderService) {
        this.deliveryOrderService = deliveryOrderService;
    }

    /**
     * @return the billService
     */
    public BillService getBillService() {
        return billService;
    }

    /**
     * @param billService the billService to set
     */
    public void setBillService(BillService billService) {
        this.billService = billService;
    }

    /**
     * @return the creditNoteService
     */
    public CreditNoteService getCreditNoteService() {
        return creditNoteService;
    }

    /**
     * @param creditNoteService the creditNoteService to set
     */
    public void setCreditNoteService(CreditNoteService creditNoteService) {
        this.creditNoteService = creditNoteService;
    }

}
