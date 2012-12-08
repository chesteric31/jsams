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
import be.jsams.common.bean.model.sale.BillMediator;
import be.jsams.common.bean.model.sale.CommandBean;
import be.jsams.common.bean.model.sale.detail.BillDetailBean;
import be.jsams.common.bean.model.sale.detail.CommandDetailBean;
import be.jsams.common.bean.model.transfer.TransferBean;
import be.jsams.server.service.sale.BillService;
import be.jsams.server.service.sale.CommandService;
import be.jsams.server.service.transfer.AbstractTransferService;
import be.jsams.server.service.transfer.CommandBillTransferService;

/**
 * Service to transfer an {@link CommandBean} to a {@link BillBean}.
 * 
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class CommandBillTransferServiceImpl extends AbstractTransferService<CommandBean, BillBean> implements
        CommandBillTransferService {

    private CommandService commandService;
    private BillService billService;

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    protected List<BillBean> createNewDocuments(TransferBean model) {
        List<BillBean> newDocuments = new ArrayList<BillBean>();
        int transferMode = model.getTransferMode();
        List<? extends AbstractDocumentBean<?, ?>> documents = model.getDocuments();
        Map<Long, List<CommandDetailBean>> details = model.getCommandDetails();
        switch (transferMode) {
        case 1:
        case 3:
            List<CommandBean> commands = new ArrayList<CommandBean>();
            commands.addAll((List<CommandBean>) documents);
            for (CommandBean bean : commands) {
                newDocuments.add(fullTransfer(bean));
            }
            break;
        case 2:
        case 4:
            Set<Entry<Long, List<CommandDetailBean>>> set = details.entrySet();
            for (Entry<Long, List<CommandDetailBean>> item : set) {
                newDocuments.add(partialTransfer(details.get(item.getKey())));
            }
            break;
        default:
            break;
        }
        return newDocuments;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void persistNewDocuments(List<BillBean> newDocuments) {
        for (BillBean document : newDocuments) {
            billService.create(document);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void updateOriginalDocuments(List<CommandBean> list) {
        for (CommandBean document : list) {
            boolean allDetailsTransferred = true;
            for (CommandDetailBean detail : document.getDetails()) {
                if (!detail.isTransferred()) {
                    allDetailsTransferred = false;
                    break;
                }
            }
            if (allDetailsTransferred) {
                document.setTransferred(true);
            }
            commandService.update(document);
        }
    }

    /**
     * Transfers a list of {@link CommandDetailBean} to bill in partial
     * transfer.
     * 
     * @param list a list of {@link CommandDetailBean} to transfer
     * @return the built new {@link BillBean}
     */
    private BillBean partialTransfer(List<CommandDetailBean> list) {
        CommandBean command = list.get(0).getCommand();
        CustomerBean customer = command.getCustomer();
        PaymentModeBean paymentMode = customer.getPaymentMode();
        BillBean newBean = new BillBean(command.getSociety(), customer, paymentMode);
        BillMediator mediator = new BillMediator();
        newBean.setMediator(mediator);
        mediator.setBillBean(newBean);
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
            bean.setMediator(newBean.getMediator());
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
        return newBean;
    }

    /**
     * Transfers a command to bill in full transfer.
     * 
     * @param command the {@link CommandBean} to transfer
     * @return the built new {@link BillBean}
     */
    private BillBean fullTransfer(CommandBean command) {
        CustomerBean customer = command.getCustomer();
        PaymentModeBean paymentMode = customer.getPaymentMode();
        BillBean newBean = new BillBean(command.getSociety(), customer, paymentMode);
        BillMediator mediator = new BillMediator();
        newBean.setMediator(mediator);
        mediator.setBillBean(newBean);
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
            if (!detail.isTransferred()) {
                BillDetailBean bean = new BillDetailBean();
                bean.setMediator(newBean.getMediator());
                bean.setBill(newBean);
                bean.setDiscountRate(detail.getDiscountRate());
                bean.setPrice(detail.getPrice());
                bean.setProduct(detail.getProduct());
                bean.setQuantity(detail.getQuantity());
                bean.setTransferred(false);
                bean.setVatApplicable(detail.getVatApplicable());
                details.add(bean);
                detail.setTransferred(true);
            }
        }
        newBean.setDetails(details);
        newBean.setDiscountRate(command.getDiscountRate());
        Date dueDate = calculateDueDate(newBean.getCreationDate(), paymentMode.getDaysNumber(),
                paymentMode.isMonthEnd(), paymentMode.getAdditionalDays());
        newBean.setDueDate(dueDate);
        newBean.setPaid(false);
        newBean.setRemark(command.getRemark());
        return newBean;
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
