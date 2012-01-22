package be.jsams.server.service.transfer.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import be.jsams.common.bean.model.PaymentModeBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.sale.AbstractDocumentBean;
import be.jsams.common.bean.model.sale.BillBean;
import be.jsams.common.bean.model.sale.CommandBean;
import be.jsams.common.bean.model.sale.EstimateBean;
import be.jsams.common.bean.model.sale.detail.BillDetailBean;
import be.jsams.common.bean.model.sale.detail.CommandDetailBean;
import be.jsams.common.bean.model.sale.detail.EstimateDetailBean;
import be.jsams.common.bean.model.transfer.TransferBean;
import be.jsams.server.service.sale.BillService;
import be.jsams.server.service.sale.CommandService;
import be.jsams.server.service.sale.EstimateService;
import be.jsams.server.service.transfer.AbstractTransferService;
import be.jsams.server.service.transfer.EstimateTransferService;

/**
 * 
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class EstimateTransferServiceImpl extends AbstractTransferService implements EstimateTransferService {
    
    private EstimateService estimateService;
    private CommandService commandService;
    private BillService billService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void transfer(TransferBean model) {
        int destinationType = model.getDestinationType();
        switch (destinationType) {
        case 1:
            toCommandTransfer(model);
            break;
        case 3:
            toBillTransfer(model);
            break;
        default:
            break;
        }
    }

    /**
     * @param model the wrapper contains all the beans to be transferred
     */
    @SuppressWarnings("unchecked")
    private void toCommandTransfer(TransferBean model) {
        int transferMode = model.getTransferMode();
        List<? extends AbstractDocumentBean<?, ?>> documents = model.getDocuments();
        Map<Long, List<EstimateDetailBean>> details = model.getEstimateDetails();
        switch (transferMode) {
        case 1:
            EstimateBean estimate = (EstimateBean) documents.get(0);
            toCommandFullTransfer(estimate);
            break;
        case 2:
        case 4:
            Set<Entry<Long, List<EstimateDetailBean>>> set = details.entrySet();
            for (Entry<Long, List<EstimateDetailBean>> item : set) {
                toCommandPartialTransfer(details.get(item.getKey()));
            }
            break;
        case 3:
            List<EstimateBean> estimates = new ArrayList<EstimateBean>();
            estimates.addAll((List<EstimateBean>) documents);
            for (EstimateBean bean : estimates) {
                toCommandFullTransfer(bean);
            }
            break;
        default:
            break;
        }
    }

    /**
     * Transfers a list of {@link EstimateDetailBean} to command in partial
     * transfer.
     * 
     * @param list a list of {@link EstimateDetailBean} to transfer
     */
    private void toCommandPartialTransfer(List<EstimateDetailBean> list) {
        EstimateBean estimate = list.get(0).getEstimate();
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
        for (EstimateDetailBean detail : list) {
            CommandDetailBean bean = new CommandDetailBean();
            bean.setCommand(newBean);
            bean.setDiscountRate(detail.getDiscountRate());
            bean.setPrice(detail.getPrice());
            bean.setProduct(detail.getProduct());
            bean.setQuantity(detail.getQuantity());
            bean.setTransferred(false);
            bean.setVatApplicable(detail.getVatApplicable());
            details.add(bean);
            detail.setTransferred(true);
        }
        newBean.setDetails(details);
        newBean.setDiscountRate(estimate.getDiscountRate());
        newBean.setRemark(estimate.getRemark());
        newBean.setTransferred(false);
        commandService.create(newBean);
        // TODO review
        boolean allDetailTransferred = true;
        for (EstimateDetailBean detailBean : estimate.getDetails()) {
            if (!detailBean.isTransferred()) {
                allDetailTransferred = false;
                break;
            }
        }
        if (allDetailTransferred) {
            estimate.setTransferred(true);
        }
        estimateService.update(estimate);
    }

    /**
     * Transfers an estimate to command in full transfer.
     * 
     * @param estimate the {@link EstimateBean} to transfer
     */
    private void toCommandFullTransfer(EstimateBean estimate) {
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
    private void toBillTransfer(TransferBean model) {
        int transferMode = model.getTransferMode();
        List<? extends AbstractDocumentBean<?, ?>> documents = model.getDocuments();
        Map<Long, List<EstimateDetailBean>> details = model.getEstimateDetails();
        switch (transferMode) {
        case 1:
            EstimateBean estimate = (EstimateBean) documents.get(0);
            toBillFullTransfer(estimate);
            break;
        case 2:
        case 4:
            Set<Entry<Long, List<EstimateDetailBean>>> set = details.entrySet();
            for (Entry<Long, List<EstimateDetailBean>> item : set) {
                toBillPartialTransfer(details.get(item.getKey()));
            }
            break;
        case 3:
            List<EstimateBean> estimates = new ArrayList<EstimateBean>();
            estimates.addAll((List<EstimateBean>) documents);
            for (EstimateBean bean : estimates) {
                toBillFullTransfer(bean);
            }
            break;
        default:
            break;
        }
    }

    /**
     * Transfers a list of {@link EstimateDetailBean} to bill in partial
     * transfer.
     * 
     * @param list a list of {@link EstimateDetailBean} to transfer
     */
    private void toBillPartialTransfer(List<EstimateDetailBean> list) {
        EstimateBean estimate = list.get(0).getEstimate();
        CustomerBean customer = estimate.getCustomer();
        PaymentModeBean paymentMode = customer.getPaymentMode();
        BillBean newBean = new BillBean(estimate.getSociety(), customer, paymentMode);
        newBean.setBillingAddress(estimate.getBillingAddress());
        newBean.getBillingAddress().setId(null);
        newBean.setClosed(false);
        Date creationDate = new Date();
        newBean.setCreationDate(creationDate);
        Date firstRemember = calculateDate(creationDate, getDays("firstRememberDays"));
        newBean.setDateFirstRemember(firstRemember);
        Date secondRemember = calculateDate(creationDate, getDays("firstRememberDays") + getDays("secondRememberDays"));
        newBean.setDateSecondRemember(secondRemember);
        Date formalNotice = calculateDate(creationDate, getDays("firstRememberDays") + getDays("secondRememberDays")
                + getDays("formalNoticeDays"));
        newBean.setDateFormalNotice(formalNotice);
        List<BillDetailBean> details = new ArrayList<BillDetailBean>();
        for (EstimateDetailBean detail : list) {
            BillDetailBean bean = new BillDetailBean();
            bean.setBill(newBean);
            bean.setDiscountRate(detail.getDiscountRate());
            bean.setPrice(detail.getPrice());
            bean.setProduct(detail.getProduct());
            bean.setQuantity(detail.getQuantity());
            bean.setTransferred(false);
            bean.setVatApplicable(detail.getVatApplicable());
            detail.setTransferred(true);
            details.add(bean);
        }
        newBean.setDetails(details);
        newBean.setDiscountRate(estimate.getDiscountRate());
        Date dueDate = calculateDueDate(newBean.getCreationDate(), paymentMode.getDaysNumber(),
                paymentMode.isMonthEnd(), paymentMode.getAdditionalDays());
        newBean.setDueDate(dueDate);
        newBean.setPaid(false);
        newBean.setRemark(estimate.getRemark());
        billService.create(newBean);
        // TODO review
        boolean allDetailTransferred = true;
        for (EstimateDetailBean detailBean : estimate.getDetails()) {
            if (!detailBean.isTransferred()) {
                allDetailTransferred = false;
                break;
            }
        }
        if (allDetailTransferred) {
            estimate.setTransferred(true);
        }
        estimateService.update(estimate);
    }

    /**
     * Transfers an estimate to bill in full transfer.
     * 
     * @param estimate the {@link EstimateBean} to transfer
     */
    private void toBillFullTransfer(EstimateBean estimate) {
        CustomerBean customer = estimate.getCustomer();
        PaymentModeBean paymentMode = customer.getPaymentMode();
        BillBean newBean = new BillBean(estimate.getSociety(), customer, paymentMode);
        newBean.setBillingAddress(estimate.getBillingAddress());
        newBean.getBillingAddress().setId(null);
        newBean.setClosed(false);
        Date creationDate = new Date();
        newBean.setCreationDate(creationDate);
        Date firstRemember = calculateDate(creationDate, getDays("firstRememberDays"));
        newBean.setDateFirstRemember(firstRemember);
        Date secondRemember = calculateDate(creationDate, getDays("firstRememberDays") + getDays("secondRememberDays"));
        newBean.setDateSecondRemember(secondRemember);
        Date formalNotice = calculateDate(creationDate, getDays("firstRememberDays") + getDays("secondRememberDays")
                + getDays("formalNoticeDays"));
        newBean.setDateFormalNotice(formalNotice);
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
            detail.setTransferred(true);
            details.add(bean);
        }
        newBean.setDetails(details);
        newBean.setDiscountRate(estimate.getDiscountRate());
        Date dueDate = calculateDueDate(newBean.getCreationDate(), paymentMode.getDaysNumber(),
                paymentMode.isMonthEnd(), paymentMode.getAdditionalDays());
        newBean.setDueDate(dueDate);
        newBean.setPaid(false);
        newBean.setRemark(estimate.getRemark());
        billService.create(newBean);
        estimate.setTransferred(true);
        estimateService.update(estimate);
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

}
