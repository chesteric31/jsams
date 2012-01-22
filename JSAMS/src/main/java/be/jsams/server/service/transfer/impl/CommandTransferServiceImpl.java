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
import be.jsams.common.bean.model.sale.DeliveryOrderBean;
import be.jsams.common.bean.model.sale.detail.BillDetailBean;
import be.jsams.common.bean.model.sale.detail.CommandDetailBean;
import be.jsams.common.bean.model.sale.detail.DeliveryOrderDetailBean;
import be.jsams.common.bean.model.transfer.TransferBean;
import be.jsams.server.service.sale.BillService;
import be.jsams.server.service.sale.CommandService;
import be.jsams.server.service.sale.DeliveryOrderService;
import be.jsams.server.service.transfer.AbstractTransferService;
import be.jsams.server.service.transfer.CommandTransferService;

/**
 * 
 * 
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class CommandTransferServiceImpl extends AbstractTransferService implements CommandTransferService {

    private CommandService commandService;
    private DeliveryOrderService deliveryOrderService;
    private BillService billService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void transfer(TransferBean model) {
        int destinationType = model.getDestinationType();
        switch (destinationType) {
        case 2:
            toDeliveryOrderTransfer(model);
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
    private void toDeliveryOrderTransfer(TransferBean model) {
        int transferMode = model.getTransferMode();
        List<? extends AbstractDocumentBean<?, ?>> documents = model.getDocuments();
        Map<Long, List<CommandDetailBean>> details = model.getCommandDetails();
        switch (transferMode) {
        case 1:
            CommandBean command = (CommandBean) documents.get(0);
            toDeliveryOrderFullTransfer(command);
            break;
        case 2:
        case 4:
            Set<Entry<Long, List<CommandDetailBean>>> set = details.entrySet();
            for (Entry<Long, List<CommandDetailBean>> item : set) {
                toDeliveryOrderPartialTransfer(details.get(item.getKey()));
            }
            break;
        case 3:
            List<CommandBean> commands = new ArrayList<CommandBean>();
            commands.addAll((List<CommandBean>) documents);
            for (CommandBean bean : commands) {
                toDeliveryOrderFullTransfer(bean);
            }
            break;
        default:
            break;
        }
    }

    /**
     * Transfers a list of {@link CommandDetailBean} to delivery order in partial
     * transfer.
     * 
     * @param list a list of {@link CommandDetailBean} to transfer
     */
    private void toDeliveryOrderPartialTransfer(List<CommandDetailBean> list) {
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
     * Transfers a command to delivery order in full transfer.
     * 
     * @param command the {@link CommandBean} to transfer
     */
    private void toDeliveryOrderFullTransfer(CommandBean command) {
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
    private void toBillTransfer(TransferBean model) {
        int transferMode = model.getTransferMode();
        List<? extends AbstractDocumentBean<?, ?>> documents = model.getDocuments();
        Map<Long, List<CommandDetailBean>> details = model.getCommandDetails();
        switch (transferMode) {
        case 1:
            CommandBean command = (CommandBean) documents.get(0);
            toBillFullTransfer(command);
            break;
        case 2:
        case 4:
            Set<Entry<Long, List<CommandDetailBean>>> set = details.entrySet();
            for (Entry<Long, List<CommandDetailBean>> item : set) {
                toBillPartialTransfer(details.get(item.getKey()));
            }
            break;
        case 3:
            List<CommandBean> commands = new ArrayList<CommandBean>();
            commands.addAll((List<CommandBean>) documents);
            for (CommandBean bean : commands) {
                toBillFullTransfer(bean);
            }
            break;
        default:
            break;
        }
    }

    /**
     * Transfers a list of {@link CommandDetailBean} to bill in partial transfer.
     * 
     * @param list a list of {@link CommandDetailBean} to transfer
     */
    private void toBillPartialTransfer(List<CommandDetailBean> list) {
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
     * Transfers a command to bill in full transfer.
     * 
     * @param command the {@link CommandBean} to transfer
     */
    private void toBillFullTransfer(CommandBean command) {
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

}
