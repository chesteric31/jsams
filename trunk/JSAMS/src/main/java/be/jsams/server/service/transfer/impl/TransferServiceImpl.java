package be.jsams.server.service.transfer.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.prefs.Preferences;

import be.jsams.common.bean.model.PaymentModeBean;
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
 * Implementation the documents transfer service.
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
        Map<Long, List<EstimateDetailBean>> details = model.getEstimateDetails();
        switch (transferMode) {
        case 1:
            EstimateBean estimate = (EstimateBean) documents.get(0);
            estimateToCommandFullTransfer(estimate);
            break;
        case 2:
        case 4:
            Set<Long> ids = (Set<Long>) details.keySet();
            for (Long id : ids) {
                estimateToCommandPartialTransfer(details.get(id));
            }
            break;
        case 3:
            List<EstimateBean> estimates = new ArrayList<EstimateBean>();
            estimates.addAll((List<EstimateBean>) documents);
            for (EstimateBean bean : estimates) {
                estimateToCommandFullTransfer(bean);
            }
            break;
        default:
            break;
        }
    }

    /**
     * Transfer a list of {@link EstimateDetailBean} to command in partial transfer.
     * 
     * @param list a list of {@link EstimateDetailBean} to transfer
     */
    private void estimateToCommandPartialTransfer(List<EstimateDetailBean> list) {
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
        Map<Long, List<EstimateDetailBean>> details = model.getEstimateDetails();
        switch (transferMode) {
        case 1:
            EstimateBean estimate = (EstimateBean) documents.get(0);
            estimateToBillFullTransfer(estimate);
            break;
        case 2:
        case 4:
            Set<Long> ids = (Set<Long>) details.keySet();
            for (Long id : ids) {
                estimateToBillPartialTransfer(details.get(id));
            }
            break;
        case 3:
            List<EstimateBean> estimates = new ArrayList<EstimateBean>();
            estimates.addAll((List<EstimateBean>) documents);
            for (EstimateBean bean : estimates) {
                estimateToBillFullTransfer(bean);
            }
            break;
        default:
            break;
        }
    }

    /**
     * Transfer a list of {@link EstimateDetailBean} to bill in partial transfer.
     * 
     * @param list a list of {@link EstimateDetailBean} to transfer
     */
    private void estimateToBillPartialTransfer(List<EstimateDetailBean> list) {
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
     * Transfer an estimate to bill in full transfer.
     * 
     * @param estimate the {@link EstimateBean} to transfer
     */
    private void estimateToBillFullTransfer(EstimateBean estimate) {
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
     * Calculate due date following the creation date of the {@link BillBean},
     * the days number, the boolean endMonth and the additional days.
     * 
     * @param creationDate the creation date of the {@link BillBean}
     * @param daysNumber the days number
     * @param endMonth the boolean end month
     * @param additionalDays the additional days
     * @return the calculated due date
     */
    private Date calculateDueDate(Date creationDate, int daysNumber, boolean endMonth, int additionalDays) {
        Date dueDate = null;
        Calendar instance = GregorianCalendar.getInstance();
        instance.setTime(creationDate);
        instance.add(Calendar.DAY_OF_MONTH, daysNumber);
        if (endMonth) {
            int maximumDaysOfMonth = instance.getActualMaximum(Calendar.DAY_OF_MONTH);
            int dayOfMonth = instance.get(Calendar.DAY_OF_MONTH);
            int daysToAdd = maximumDaysOfMonth - dayOfMonth;
            instance.add(Calendar.DAY_OF_YEAR, daysToAdd);
        }
        instance.add(Calendar.DAY_OF_YEAR, additionalDays);
        dueDate = instance.getTime();
        return dueDate;
    }

    /**
     * Calculate a date following the initial date of the {@link BillBean},
     * the days number, the boolean endMonth and the additional days.
     * 
     * @param initialDate the initial date to use
     * @param daysNumber the days number
     * @return the calculated date with the days number to add
     */
    private Date calculateDate(Date initialDate, int daysNumber) {
        Date dueDate = null;
        Calendar instance = GregorianCalendar.getInstance();
        instance.setTime(initialDate);
        instance.add(Calendar.DAY_OF_MONTH, daysNumber);
        dueDate = instance.getTime();
        return dueDate;
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
        Map<Long, List<CommandDetailBean>> details = model.getCommandDetails();
        switch (transferMode) {
        case 1:
            CommandBean command = (CommandBean) documents.get(0);
            commandToDeliveryOrderFullTransfer(command);
            break;
        case 2:
        case 4:
            Set<Long> ids = (Set<Long>) details.keySet();
            for (Long id : ids) {
                commandToDeliveryOrderPartialTransfer(details.get(id));
            }
            break;
        case 3:
            List<CommandBean> commands = new ArrayList<CommandBean>();
            commands.addAll((List<CommandBean>) documents);
            for (CommandBean bean : commands) {
                commandToDeliveryOrderFullTransfer(bean);
            }
            break;
        default:
            break;
        }
    }
    
    /**
     * Transfer a list of {@link CommandDetailBean} to delivery order in partial transfer.
     * 
     * @param list a list of {@link CommandDetailBean} to transfer
     */
    private void commandToDeliveryOrderPartialTransfer(List<CommandDetailBean> list) {
        CommandBean command = list.get(0).getCommand();
        CustomerBean customer = command.getCustomer();
        DeliveryOrderBean newBean = new DeliveryOrderBean(command.getSociety(), customer);
        newBean.setCustomer(customer);
        newBean.setCreationDate(new Date());
        newBean.setDeliveryAddress(command.getDeliveryAddress());
        // to force to create a new delivery address
        newBean.getDeliveryAddress().setId(null);
        List<DeliveryOrderDetailBean> details = new ArrayList<DeliveryOrderDetailBean>();
        for (CommandDetailBean detail : list) {
            DeliveryOrderDetailBean bean = new DeliveryOrderDetailBean();
            bean.setDeliveryOrder(newBean);
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
        newBean.setDiscountRate(command.getDiscountRate());
        newBean.setRemark(command.getRemark());
        deliveryOrderService.create(newBean);
        // TODO review
        boolean allDetailTransferred = true;
        for (CommandDetailBean detailBean : command.getDetails()) {
            if (!detailBean.isTransferred()) {
                allDetailTransferred = false;
                break;
            }
        }
        if (allDetailTransferred) {
            command.setTransferred(true);
        }
        commandService.update(command);
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
        Map<Long, List<CommandDetailBean>> details = model.getCommandDetails();
        switch (transferMode) {
        case 1:
            CommandBean command = (CommandBean) documents.get(0);
            commandToBillFullTransfer(command);
            break;
        case 2:
        case 4:
            Set<Long> ids = (Set<Long>) details.keySet();
            for (Long id : ids) {
                commandToBillPartialTransfer(details.get(id));
            }
            break;
        case 3:
            List<CommandBean> commands = new ArrayList<CommandBean>();
            commands.addAll((List<CommandBean>) documents);
            for (CommandBean bean : commands) {
                commandToBillFullTransfer(bean);
            }
            break;
        default:
            break;
        }
    }
    
    /**
     * Transfer a list of {@link CommandDetailBean} to bill in partial transfer.
     * 
     * @param list a list of {@link CommandDetailBean} to transfer
     */
    private void commandToBillPartialTransfer(List<CommandDetailBean> list) {
        CommandBean command = list.get(0).getCommand();
        CustomerBean customer = command.getCustomer();
        PaymentModeBean paymentMode = customer.getPaymentMode();
        BillBean newBean = new BillBean(command.getSociety(), customer, paymentMode);
        newBean.setBillingAddress(command.getBillingAddress());
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
        for (CommandDetailBean detail : list) {
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
        newBean.setDiscountRate(command.getDiscountRate());
        Date dueDate = calculateDueDate(newBean.getCreationDate(), paymentMode.getDaysNumber(),
                paymentMode.isMonthEnd(), paymentMode.getAdditionalDays());
        newBean.setDueDate(dueDate);
        newBean.setPaid(false);
        newBean.setRemark(command.getRemark());
        billService.create(newBean);
        // TODO review
        boolean allDetailTransferred = true;
        for (CommandDetailBean detailBean : command.getDetails()) {
            if (!detailBean.isTransferred()) {
                allDetailTransferred = false;
                break;
            }
        }
        if (allDetailTransferred) {
            command.setTransferred(true);
        }
        commandService.update(command);
    }

    /**
     * Transfer a command to bill in full transfer.
     * 
     * @param command the {@link CommandBean} to transfer
     */
    private void commandToBillFullTransfer(CommandBean command) {
        CustomerBean customer = command.getCustomer();
        PaymentModeBean paymentMode = customer.getPaymentMode();
        BillBean newBean = new BillBean(command.getSociety(), customer, paymentMode);
        newBean.setBillingAddress(command.getBillingAddress());
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
        Date dueDate = calculateDueDate(newBean.getCreationDate(), paymentMode.getDaysNumber(),
                paymentMode.isMonthEnd(), paymentMode.getAdditionalDays());
        newBean.setDueDate(dueDate);
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
        Map<Long, List<BillDetailBean>> details = model.getBillDetails();
        switch (transferMode) {
        case 1:
            BillBean bill = (BillBean) documents.get(0);
            billToCreditNoteFullTransfer(bill);
            break;
        case 2:
        case 4:
            Set<Long> ids = (Set<Long>) details.keySet();
            for (Long id : ids) {
                billToCreditNotePartialTransfer(details.get(id));
            }
            break;
        case 3:
            List<BillBean> bills = new ArrayList<BillBean>();
            bills.addAll((List<BillBean>) documents);
            for (BillBean bean : bills) {
                billToCreditNoteFullTransfer(bean);
            }
            break;
        default:
            break;
        }
    }
    
    /**
     * Transfer a list of {@link BillDetailBean} to credit note in full transfer.
     * 
     * @param list the list of {@link BillDetailBean} to transfer
     */
    private void billToCreditNotePartialTransfer(List<BillDetailBean> list) {
        BillBean bill = list.get(0).getBill();
        CustomerBean customer = bill.getCustomer();
        CreditNoteBean newBean = new CreditNoteBean(bill.getSociety(), customer);
        newBean.setCreationDate(new Date());
        newBean.setBillingAddress(bill.getBillingAddress());
        // to force to create a new billing address
        newBean.getBillingAddress().setId(null);
        List<CreditNoteDetailBean> details = new ArrayList<CreditNoteDetailBean>();
        for (BillDetailBean detail : list) {
            CreditNoteDetailBean bean = new CreditNoteDetailBean();
            bean.setCreditNote(newBean);
            bean.setDiscountRate(detail.getDiscountRate());
            bean.setPrice(detail.getPrice());
            bean.setProduct(detail.getProduct());
            bean.setQuantity(detail.getQuantity());
            bean.setVatApplicable(detail.getVatApplicable());
            detail.setTransferred(true);
            details.add(bean);
        }
        newBean.setDetails(details);
        newBean.setRemark(bill.getRemark());
        creditNoteService.create(newBean);
        // bill.setClosed(true);
        billService.update(bill);
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
            detail.setTransferred(true);
            details.add(bean);
        }
        newBean.setDetails(details);
        newBean.setRemark(bill.getRemark());
        creditNoteService.create(newBean);
        // bill.setClosed(true);
        billService.update(bill);
    }

    // public static void main(String[] args) {
    // TransferServiceImpl serviceImpl = new TransferServiceImpl();
    // Date today = new Date();
    // Date date = serviceImpl.calculateDueDate(today, 0, false, 0);
    // System.out.println(serviceImpl.dateEquals(date, today));
    // Date date2 = serviceImpl.calculateDueDate(today, 30, false, 0);
    // Calendar instance = GregorianCalendar.getInstance();
    // instance.set(Calendar.DAY_OF_MONTH, 29);
    // instance.set(Calendar.MONTH, 7);
    // instance.set(Calendar.YEAR, 2011);
    // Date time = instance.getTime();
    // System.out.println(serviceImpl.dateEquals(date2, time));
    // Date date3 = serviceImpl.calculateDueDate(today, 60, false, 0);
    // instance.set(Calendar.DAY_OF_MONTH, 28);
    // instance.set(Calendar.MONTH, 8);
    // instance.set(Calendar.YEAR, 2011);
    // time = instance.getTime();
    // System.out.println(serviceImpl.dateEquals(date3, time));
    // Date date4 = serviceImpl.calculateDueDate(today, 90, false, 0);
    // instance.set(Calendar.DAY_OF_MONTH, 28);
    // instance.set(Calendar.MONTH, 9);
    // instance.set(Calendar.YEAR, 2011);
    // time = instance.getTime();
    // System.out.println(serviceImpl.dateEquals(date4, time));
    // Date date5 = serviceImpl.calculateDueDate(today, 30, true, 0);
    // instance.set(Calendar.DAY_OF_MONTH, 31);
    // instance.set(Calendar.MONTH, 7);
    // instance.set(Calendar.YEAR, 2011);
    // time = instance.getTime();
    // System.out.println(serviceImpl.dateEquals(date5, time));
    // Date date6 = serviceImpl.calculateDueDate(today, 60, true, 0);
    // instance.set(Calendar.DAY_OF_MONTH, 30);
    // instance.set(Calendar.MONTH, 8);
    // instance.set(Calendar.YEAR, 2011);
    // time = instance.getTime();
    // System.out.println(serviceImpl.dateEquals(date6, time));
    // Date date7 = serviceImpl.calculateDueDate(today, 90, true, 0);
    // instance.set(Calendar.DAY_OF_MONTH, 31);
    // instance.set(Calendar.MONTH, 9);
    // instance.set(Calendar.YEAR, 2011);
    // time = instance.getTime();
    // System.out.println(serviceImpl.dateEquals(date7, time));
    //
    // Date date8 = serviceImpl.calculateDueDate(today, 30, true, 10);
    // instance.set(Calendar.DAY_OF_MONTH, 10);
    // instance.set(Calendar.MONTH, 8);
    // instance.set(Calendar.YEAR, 2011);
    // time = instance.getTime();
    // System.out.println(serviceImpl.dateEquals(date8, time));
    // Date date9 = serviceImpl.calculateDueDate(today, 60, true, 10);
    // instance.set(Calendar.DAY_OF_MONTH, 10);
    // instance.set(Calendar.MONTH, 9);
    // instance.set(Calendar.YEAR, 2011);
    // time = instance.getTime();
    // System.out.println(serviceImpl.dateEquals(date9, time));
    // Date date10 = serviceImpl.calculateDueDate(today, 90, true, 10);
    // instance.set(Calendar.DAY_OF_MONTH, 10);
    // instance.set(Calendar.MONTH, 10);
    // instance.set(Calendar.YEAR, 2011);
    // time = instance.getTime();
    // System.out.println(serviceImpl.dateEquals(date10, time));
    // }
    //
    // /**
    // * Checks the day, month and year are equal.
    // */
    // public boolean dateEquals(Date d1, Date d2) {
    // if (d1 == null || d2 == null) {
    // return false;
    // }
    // return d1.getDate() == d2.getDate() && d1.getMonth() == d2.getMonth() &&
    // d1.getYear() == d2.getYear();
    // }

    /**
     * @param model the wrapper contains all the beans to be transferred
     */
    private void deliveryOrderTransfer(TransferBean model) {
        int destinationType = model.getDestinationType();
        if (destinationType == 3) {
            deliveryOrderToBillTransfer(model);
        }
    }

    /**
     * @param model the wrapper contains all the beans to be transferred
     */
    @SuppressWarnings("unchecked")
    private void deliveryOrderToBillTransfer(TransferBean model) {
        int transferMode = model.getTransferMode();
        List<? extends AbstractDocumentBean<?, ?>> documents = model.getDocuments();
        Map<Long, List<DeliveryOrderDetailBean>> details = model.getDeliveryOrderDetails();
        switch (transferMode) {
        case 1:
            DeliveryOrderBean deliveryOrder = (DeliveryOrderBean) documents.get(0);
            deliveryOrderToBillFullTransfer(deliveryOrder);
            break;
        case 2:
        case 4:
            Set<Long> ids = (Set<Long>) details.keySet();
            for (Long id : ids) {
                deliveryOrderToBillPartialTransfer(details.get(id));
            }
            break;
        case 3:
            List<DeliveryOrderBean> deliveryOrders = new ArrayList<DeliveryOrderBean>();
            deliveryOrders.addAll((List<DeliveryOrderBean>) documents);
            for (DeliveryOrderBean bean : deliveryOrders) {
                deliveryOrderToBillFullTransfer(bean);
            }
            break;
        default:
            break;
        }
    }
    
    /**
     * Transfer a list of {@link DeliveryOrderDetailBean} to bill in full transfer.
     * 
     * @param list the list of {@link DeliveryOrderDetailBean} to transfer
     */
    private void deliveryOrderToBillPartialTransfer(List<DeliveryOrderDetailBean> list) {
        DeliveryOrderBean deliveryOrder = list.get(0).getDeliveryOrder();
        CustomerBean customer = deliveryOrder.getCustomer();
        PaymentModeBean paymentMode = customer.getPaymentMode();
        BillBean newBean = new BillBean(deliveryOrder.getSociety(), customer, paymentMode);
        newBean.setBillingAddress(customer.getBillingAddress());
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
        for (DeliveryOrderDetailBean detail : list) {
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
        newBean.setDiscountRate(deliveryOrder.getDiscountRate());
        Date dueDate = calculateDueDate(newBean.getCreationDate(), paymentMode.getDaysNumber(),
                paymentMode.isMonthEnd(), paymentMode.getAdditionalDays());
        newBean.setDueDate(dueDate);
        newBean.setPaid(false);
        newBean.setRemark(deliveryOrder.getRemark());
        billService.create(newBean);
        deliveryOrder.setTransferred(true);
        deliveryOrderService.update(deliveryOrder);
    }

    /**
     * Transfer a delivery order to bill in full transfer.
     * 
     * @param deliveryOrder the {@link DeliveryOrderBean} to transfer
     */
    private void deliveryOrderToBillFullTransfer(DeliveryOrderBean deliveryOrder) {
        CustomerBean customer = deliveryOrder.getCustomer();
        PaymentModeBean paymentMode = customer.getPaymentMode();
        BillBean newBean = new BillBean(deliveryOrder.getSociety(), customer, paymentMode);
        newBean.setBillingAddress(customer.getBillingAddress());
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
        for (DeliveryOrderDetailBean detail : deliveryOrder.getDetails()) {
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
        newBean.setDiscountRate(deliveryOrder.getDiscountRate());
        Date dueDate = calculateDueDate(newBean.getCreationDate(), paymentMode.getDaysNumber(),
                paymentMode.isMonthEnd(), paymentMode.getAdditionalDays());
        newBean.setDueDate(dueDate);
        newBean.setPaid(false);
        newBean.setRemark(deliveryOrder.getRemark());
        billService.create(newBean);
        deliveryOrder.setTransferred(true);
        deliveryOrderService.update(deliveryOrder);
    }

    /**
     * 
     * @param key the key to use, firstRememberDays, secondRememberDays or formalNoticeDays 
     * @return the number of days for the first remember, second remember &
     *         formal notice following the stored preferences
     */
    private int getDays(final String key) {
        Preferences prefsRoot = Preferences.userRoot();
        Preferences jsamsPrefs = prefsRoot.node("be.jsams");
        return Integer.valueOf(jsamsPrefs.get(key, "0"));
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