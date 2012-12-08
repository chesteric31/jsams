package be.jsams.server.service.transfer.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.sale.AbstractDocumentBean;
import be.jsams.common.bean.model.sale.CommandBean;
import be.jsams.common.bean.model.sale.DeliveryOrderBean;
import be.jsams.common.bean.model.sale.DeliveryOrderMediator;
import be.jsams.common.bean.model.sale.detail.CommandDetailBean;
import be.jsams.common.bean.model.sale.detail.DeliveryOrderDetailBean;
import be.jsams.common.bean.model.transfer.TransferBean;
import be.jsams.server.service.sale.CommandService;
import be.jsams.server.service.sale.DeliveryOrderService;
import be.jsams.server.service.transfer.AbstractTransferService;
import be.jsams.server.service.transfer.CommandDeliveryOrderTransferService;

/**
 * Service to transfer an {@link CommandBean} to a {@link DeliveryOrderBean}.
 * 
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class CommandDeliveryOrderTransferServiceImpl extends AbstractTransferService<CommandBean, DeliveryOrderBean>
        implements CommandDeliveryOrderTransferService {

    private CommandService commandService;
    private DeliveryOrderService deliveryOrderService;

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    protected List<DeliveryOrderBean> createNewDocuments(TransferBean model) {
        List<DeliveryOrderBean> newDocuments = new ArrayList<DeliveryOrderBean>();
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
    protected void persistNewDocuments(List<DeliveryOrderBean> newDocuments) {
        for (DeliveryOrderBean document : newDocuments) {
            deliveryOrderService.create(document);
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
     * Transfers a list of {@link CommandDetailBean} to delivery order in
     * partial transfer.
     * 
     * @param list a list of {@link CommandDetailBean} to transfer
     * @return the built new {@link DeliveryOrderBean}
     */
    private DeliveryOrderBean partialTransfer(List<CommandDetailBean> list) {
        CommandBean command = list.get(0).getCommand();
        CustomerBean customer = command.getCustomer();
        DeliveryOrderBean newBean = new DeliveryOrderBean(command.getSociety(), customer);
        DeliveryOrderMediator mediator = new DeliveryOrderMediator();
        newBean.setMediator(mediator);
        mediator.setDeliveryOrderBean(newBean);
        newBean.setCustomer(customer);
        newBean.setCreationDate(new Date());
        newBean.setDeliveryAddress(command.getDeliveryAddress());
        // to force to create a new delivery address
        newBean.getDeliveryAddress().setId(null);
        List<DeliveryOrderDetailBean> details = new ArrayList<DeliveryOrderDetailBean>();
        for (CommandDetailBean detail : list) {
            DeliveryOrderDetailBean bean = new DeliveryOrderDetailBean();
            bean.setMediator(newBean.getMediator());
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
        return newBean;
    }

    /**
     * Transfers a command to delivery order in full transfer.
     * 
     * @param command the {@link CommandBean} to transfer
     * @return the built new {@link DeliveryOrderBean}
     */
    private DeliveryOrderBean fullTransfer(CommandBean command) {
        CustomerBean customer = command.getCustomer();
        DeliveryOrderBean newBean = new DeliveryOrderBean(command.getSociety(), customer);
        DeliveryOrderMediator mediator = new DeliveryOrderMediator();
        newBean.setMediator(mediator);
        mediator.setDeliveryOrderBean(newBean);
        newBean.setCreationDate(new Date());
        newBean.setDeliveryAddress(customer.getDeliveryAddress());
        // to force to create a new delivery address
        newBean.getDeliveryAddress().setId(null);
        List<DeliveryOrderDetailBean> details = new ArrayList<DeliveryOrderDetailBean>();
        for (CommandDetailBean detail : command.getDetails()) {
            if (!detail.isTransferred()) {
                DeliveryOrderDetailBean bean = new DeliveryOrderDetailBean();
                bean.setCommandDetail(detail);
                bean.setMediator(newBean.getMediator());
                bean.setDeliveryOrder(newBean);
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
        newBean.setRemark(command.getRemark());
        newBean.setTransferred(false);
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

}
