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
import be.jsams.common.bean.model.sale.CommandMediator;
import be.jsams.common.bean.model.sale.EstimateBean;
import be.jsams.common.bean.model.sale.detail.CommandDetailBean;
import be.jsams.common.bean.model.sale.detail.EstimateDetailBean;
import be.jsams.common.bean.model.transfer.TransferBean;
import be.jsams.server.service.sale.CommandService;
import be.jsams.server.service.sale.EstimateService;
import be.jsams.server.service.transfer.AbstractTransferService;
import be.jsams.server.service.transfer.EstimateCommandTransferService;

/**
 * 
 * 
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class EstimateCommandTransferServiceImpl extends AbstractTransferService<EstimateBean, CommandBean> implements
        EstimateCommandTransferService {

    private EstimateService estimateService;
    private CommandService commandService;

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    protected List<CommandBean> createNewDocuments(TransferBean model) {
        List<CommandBean> newDocuments = new ArrayList<CommandBean>();
        int transferMode = model.getTransferMode();
        List<? extends AbstractDocumentBean<?, ?>> documents = model.getDocuments();
        Map<Long, List<EstimateDetailBean>> details = model.getEstimateDetails();
        switch (transferMode) {
        case 1:
            EstimateBean estimate = (EstimateBean) documents.get(0);
            newDocuments.add(fullTransfer(estimate));
            break;
        case 2:
        case 4:
            Set<Entry<Long, List<EstimateDetailBean>>> set = details.entrySet();
            for (Entry<Long, List<EstimateDetailBean>> item : set) {
                newDocuments.add(partialTransfer(details.get(item.getKey())));
            }
            break;
        case 3:
            List<EstimateBean> estimates = new ArrayList<EstimateBean>();
            estimates.addAll((List<EstimateBean>) documents);
            for (EstimateBean bean : estimates) {
                newDocuments.add(fullTransfer(bean));
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
    protected void persistNewDocuments(List<CommandBean> newDocuments) {
        for (CommandBean document : newDocuments) {
            commandService.create(document);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void updateOriginalDocuments(List<EstimateBean> list) {
        for (EstimateBean document : list) {
            boolean allDetailsTransferred = true;
            for (EstimateDetailBean detail : document.getDetails()) {
                if (!detail.isTransferred()) {
                    allDetailsTransferred = false;
                    break;
                }
            }
            if (allDetailsTransferred) {
                document.setTransferred(true);
            }
            estimateService.update(document);
        }
    }

    /**
     * Transfers a list of {@link EstimateDetailBean} to command in partial
     * transfer.
     * 
     * @param list a list of {@link EstimateDetailBean} to transfer
     * 
     * @return the built new {@link CommandBean}
     */
    private CommandBean partialTransfer(List<EstimateDetailBean> list) {
        EstimateBean estimate = list.get(0).getEstimate();
        CustomerBean customer = estimate.getCustomer();
        CommandBean newBean = new CommandBean(estimate.getSociety(), customer, estimate.getAgent());
        CommandMediator mediator = new CommandMediator();
        newBean.setMediator(mediator);
        mediator.setCommandBean(newBean);
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
            bean.setMediator(newBean.getMediator());
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
        return newBean;
    }

    /**
     * Transfers an estimate to command in full transfer.
     * 
     * @param estimate the {@link EstimateBean} to transfer
     * 
     * @return the built new {@link CommandBean}
     */
    private CommandBean fullTransfer(EstimateBean estimate) {
        CustomerBean customer = estimate.getCustomer();
        CommandBean newBean = new CommandBean(estimate.getSociety(), customer, estimate.getAgent());
        CommandMediator mediator = new CommandMediator();
        newBean.setMediator(mediator);
        mediator.setCommandBean(newBean);
        newBean.setBillingAddress(estimate.getBillingAddress());
        // to force to create a new billing address
        newBean.getBillingAddress().setId(null);
        newBean.setCreationDate(new Date());
        newBean.setDeliveryAddress(customer.getDeliveryAddress());
        // to force to create a new delivery address
        newBean.getDeliveryAddress().setId(null);
        List<CommandDetailBean> details = new ArrayList<CommandDetailBean>();
        for (EstimateDetailBean detail : estimate.getDetails()) {
            if (!detail.isTransferred()) {
                CommandDetailBean bean = new CommandDetailBean();
                bean.setMediator(newBean.getMediator());
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
        }
        newBean.setDetails(details);
        newBean.setDiscountRate(estimate.getDiscountRate());
        newBean.setRemark(estimate.getRemark());
        newBean.setTransferred(false);
        return newBean;
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

}
