package be.jsams.server.service.transfer.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.sale.AbstractDocumentBean;
import be.jsams.common.bean.model.sale.BillBean;
import be.jsams.common.bean.model.sale.CommandBean;
import be.jsams.common.bean.model.sale.CreditNoteBean;
import be.jsams.common.bean.model.sale.DeliveryOrderBean;
import be.jsams.common.bean.model.sale.EstimateBean;
import be.jsams.common.bean.model.sale.detail.BillDetailBean;
import be.jsams.common.bean.model.sale.detail.CommandDetailBean;
import be.jsams.common.bean.model.sale.detail.CreditNoteDetailBean;
import be.jsams.common.bean.model.sale.detail.DeliveryOrderDetailBean;
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
        case 3:
            estimateToBillTransfer(model);
            break;
        default:
            break;
        }
    }

    /**
     * @param model the wrapper contains all the beans to be transferred
     */
    @SuppressWarnings("unchecked")
    private void estimateToCommandTransfer(TransferBean model) {
        int transferMode = model.getTransferMode();
        List<? extends AbstractDocumentBean<?, ?>> documents = model.getDocuments();
        switch (transferMode) {
        case 1:
            EstimateBean estimate = (EstimateBean) documents.get(0);
            estimateToCommandFullTransfer(estimate);
            break;
        case 2:
//            estimateToCommandPartialTransfer(model);
            break;
        case 3:
            List<EstimateBean> estimates = new ArrayList<EstimateBean>();
            estimates.addAll((List<EstimateBean>) documents);
            for (EstimateBean bean : estimates) {
                estimateToCommandFullTransfer(bean);
            }
            break;
        case 4:
//            estimateToCommandPartialGroupedTransfer(model);
            break;
        default:
            break;
        }
    }

    /**
     * Transfer an estimate to command in full transfer.
     * 
     * @param estimate the {@link EstimateBean} to transfer
     */
    private void estimateToCommandFullTransfer(EstimateBean estimate) {
        CustomerBean customer = estimate.getCustomer();
        CommandBean newBean = new CommandBean(estimate.getSociety(), customer, estimate.getAgent());
        newBean.setBillingAddress(estimate.getBillingAddress());
        // to force to create a new billing address
        newBean.getBillingAddress().setId(null);
        newBean.setCreationDate(new Date());
        newBean.setDeliveryAddress(customer.getDeliveryAddress());
        // to force to create a new delivery address
        newBean.getDeliveryAddress().setId(null);
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
        for (EstimateDetailBean bean : estimate.getDetails()) {
            bean.setTransferred(true);
        }
        estimateService.update(estimate);
    }

    /**
     * @param model the wrapper contains all the beans to be transferred
     */
    @SuppressWarnings("unchecked")
    private void estimateToBillTransfer(TransferBean model) {
        int transferMode = model.getTransferMode();
        List<? extends AbstractDocumentBean<?, ?>> documents = model.getDocuments();
        switch (transferMode) {
        case 1:
            EstimateBean estimate = (EstimateBean) documents.get(0);
            estimateToBillFullTransfer(estimate);
            break;
        case 2:
//            estimateToDeliveryOrderPartialTransfer(model);
            break;
        case 3:
            List<EstimateBean> estimates = new ArrayList<EstimateBean>();
            estimates.addAll((List<EstimateBean>) documents);
            for (EstimateBean bean : estimates) {
                estimateToBillFullTransfer(bean);
            }
            break;
        case 4:
//            estimateToDeliveryOrderPartialGroupedTransfer(model);
            break;
        default:
            break;
        }
    }

    /**
     * Transfer an estimate to bill in full transfer.
     * 
     * @param estimate the {@link EstimateBean} to transfer
     */
    private void estimateToBillFullTransfer(EstimateBean estimate) {
        CustomerBean customer = estimate.getCustomer();
        BillBean newBean = new BillBean(estimate.getSociety(), customer, customer.getPaymentMode());
        newBean.setBillingAddress(estimate.getBillingAddress());
        newBean.getBillingAddress().setId(null);
        newBean.setClosed(false);
        newBean.setCreationDate(new Date());
        // TODO implement the dates management
//        newBean.setDateFirstRemember(dateFirstRemember);
//        newBean.setDateFormalNotice(dateFormalNotice);
//        newBean.setDateSecondRemember(dateSecondRemember);
        List<BillDetailBean> details = new ArrayList<BillDetailBean>();
        for (EstimateDetailBean detail : estimate.getDetails()) {
            BillDetailBean bean = new BillDetailBean();
            bean.setBill(newBean);
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
//        newBean.setDueDate(dueDate);
        newBean.setPaid(false);
        newBean.setRemark(estimate.getRemark());
        billService.create(newBean);
        estimate.setTransferred(true);
        for (EstimateDetailBean bean : estimate.getDetails()) {
            bean.setTransferred(true);
        }
        estimateService.update(estimate);
    }

    /**
     * @param model the wrapper contains all the beans to be transferred
     */
    private void commandTransfer(TransferBean model) {
        int destinationType = model.getDestinationType();
        switch (destinationType) {
        case 2:
            commandToDeliveryOrderTransfer(model);
            break;
        case 3:
            commandToBillTransfer(model);
            break;
        default:
            break;
        }
    }

    /**
     * @param model the wrapper contains all the beans to be transferred
     */
    @SuppressWarnings("unchecked")
    private void commandToDeliveryOrderTransfer(TransferBean model) {
        int transferMode = model.getTransferMode();
        List<? extends AbstractDocumentBean<?, ?>> documents = model.getDocuments();
        switch (transferMode) {
        case 1:
            CommandBean command = (CommandBean) documents.get(0);
            commandToDeliveryOrderFullTransfer(command);
            break;
        case 2:
//            commandToDeliveryOrderPartialTransfer(model);
            break;
        case 3:
            List<CommandBean> commands = new ArrayList<CommandBean>();
            commands.addAll((List<CommandBean>) documents);
            for (CommandBean bean : commands) {
                commandToDeliveryOrderFullTransfer(bean);
            }
            break;
        case 4:
//            estimateToCommandPartialGroupedTransfer(model);
            break;
        default:
            break;
        }
    }

    /**
     * Transfer a command to delivery order in full transfer.
     * 
     * @param command the {@link CommandBean} to transfer
     */
    private void commandToDeliveryOrderFullTransfer(CommandBean command) {
        CustomerBean customer = command.getCustomer();
        DeliveryOrderBean newBean = new DeliveryOrderBean(command.getSociety(), customer);
        newBean.setCreationDate(new Date());
        newBean.setDeliveryAddress(customer.getDeliveryAddress());
        // to force to create a new delivery address
        newBean.getDeliveryAddress().setId(null);
        List<DeliveryOrderDetailBean> details = new ArrayList<DeliveryOrderDetailBean>();
        for (CommandDetailBean detail : command.getDetails()) {
            DeliveryOrderDetailBean bean = new DeliveryOrderDetailBean();
            bean.setCommandDetail(detail);
            bean.setDeliveryOrder(newBean);
            bean.setDiscountRate(detail.getDiscountRate());
            bean.setPrice(detail.getPrice());
            bean.setProduct(detail.getProduct());
            bean.setQuantity(detail.getQuantity());
            bean.setTransferred(false);
            bean.setVatApplicable(detail.getVatApplicable());
            details.add(bean);
        }
        newBean.setDetails(details);
        newBean.setDiscountRate(command.getDiscountRate());
        newBean.setRemark(command.getRemark());
        newBean.setTransferred(false);
        deliveryOrderService.create(newBean);
        command.setTransferred(true);
        for (CommandDetailBean bean : command.getDetails()) {
            bean.setTransferred(true);
        }
        commandService.update(command);
    }
    
    /**
     * @param model the wrapper contains all the beans to be transferred
     */
    @SuppressWarnings("unchecked")
    private void commandToBillTransfer(TransferBean model) {
        int transferMode = model.getTransferMode();
        List<? extends AbstractDocumentBean<?, ?>> documents = model.getDocuments();
        switch (transferMode) {
        case 1:
            CommandBean command = (CommandBean) documents.get(0);
            commandToBillFullTransfer(command);
            break;
        case 2:
//            commandToDeliveryOrderPartialTransfer(model);
            break;
        case 3:
            List<CommandBean> commands = new ArrayList<CommandBean>();
            commands.addAll((List<CommandBean>) documents);
            for (CommandBean bean : commands) {
                commandToBillFullTransfer(bean);
            }
            break;
        case 4:
//            estimateToCommandPartialGroupedTransfer(model);
            break;
        default:
            break;
        }
    }

    /**
     * Transfer a command to bill in full transfer.
     * 
     * @param command the {@link CommandBean} to transfer
     */
    private void commandToBillFullTransfer(CommandBean command) {
        CustomerBean customer = command.getCustomer();
        BillBean newBean = new BillBean(command.getSociety(), customer, customer.getPaymentMode());
        newBean.setBillingAddress(command.getBillingAddress());
        newBean.getBillingAddress().setId(null);
        newBean.setClosed(false);
        newBean.setCreationDate(new Date());
        // TODO implement the dates management
//        newBean.setDateFirstRemember(dateFirstRemember);
//        newBean.setDateFormalNotice(dateFormalNotice);
//        newBean.setDateSecondRemember(dateSecondRemember);
        List<BillDetailBean> details = new ArrayList<BillDetailBean>();
        for (CommandDetailBean detail : command.getDetails()) {
            BillDetailBean bean = new BillDetailBean();
            bean.setBill(newBean);
            bean.setDiscountRate(detail.getDiscountRate());
            bean.setPrice(detail.getPrice());
            bean.setProduct(detail.getProduct());
            bean.setQuantity(detail.getQuantity());
            bean.setTransferred(false);
            bean.setVatApplicable(detail.getVatApplicable());
            details.add(bean);
        }
        newBean.setDetails(details);
        newBean.setDiscountRate(command.getDiscountRate());
//        newBean.setDueDate(dueDate);
        newBean.setPaid(false);
        newBean.setRemark(command.getRemark());
        billService.create(newBean);
        command.setTransferred(true);
        for (CommandDetailBean bean : command.getDetails()) {
            bean.setTransferred(true);
        }
        commandService.update(command);
    }

    /**
     * @param model the wrapper contains all the beans to be transferred
     */
    private void billTransfer(TransferBean model) {
        int destinationType = model.getDestinationType();
        switch (destinationType) {
        case 4:
            billToCreditNoteTransfer(model);
            break;
        default:
            break;
        }
    }

    /**
     * @param model the wrapper contains all the beans to be transferred
     */
    @SuppressWarnings("unchecked")
    private void billToCreditNoteTransfer(TransferBean model) {
        int transferMode = model.getTransferMode();
        List<? extends AbstractDocumentBean<?, ?>> documents = model.getDocuments();
        switch (transferMode) {
        case 1:
            BillBean bill = (BillBean) documents.get(0);
            billToCreditNoteFullTransfer(bill);
            break;
        case 2:
//            billToCreditNotePartialTransfer(model);
            break;
        case 3:
            List<BillBean> bills = new ArrayList<BillBean>();
            bills.addAll((List<BillBean>) documents);
            for (BillBean bean : bills) {
                billToCreditNoteFullTransfer(bean);
            }
            break;
        case 4:
//            billToCreditNotePartialGroupedTransfer(model);
            break;
        default:
            break;
        }
    }

    /**
     * Transfer a bill to credit note in full transfer.
     * 
     * @param bill the {@link BillBean} to transfer
     */
    private void billToCreditNoteFullTransfer(BillBean bill) {
        CustomerBean customer = bill.getCustomer();
        CreditNoteBean newBean = new CreditNoteBean(bill.getSociety(), customer);
        newBean.setCreationDate(new Date());
        newBean.setBillingAddress(bill.getBillingAddress());
        // to force to create a new billing address
        newBean.getBillingAddress().setId(null);
        List<CreditNoteDetailBean> details = new ArrayList<CreditNoteDetailBean>();
        for (BillDetailBean detail : bill.getDetails()) {
            CreditNoteDetailBean bean = new CreditNoteDetailBean();
            bean.setCreditNote(newBean);
            bean.setDiscountRate(detail.getDiscountRate());
            bean.setPrice(detail.getPrice());
            bean.setProduct(detail.getProduct());
            bean.setQuantity(detail.getQuantity());
            bean.setVatApplicable(detail.getVatApplicable());
            details.add(bean);
        }
        newBean.setDetails(details);
        newBean.setRemark(bill.getRemark());
        creditNoteService.create(newBean);
//        bill.setClosed(true);
        for (BillDetailBean bean : bill.getDetails()) {
            bean.setTransferred(true);
        }
        billService.update(bill);
    }

    /**
     * @param model the wrapper contains all the beans to be transferred
     */
    private void deliveryOrderTransfer(TransferBean model) {
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
