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
import be.jsams.common.bean.model.sale.DeliveryOrderBean;
import be.jsams.common.bean.model.sale.detail.BillDetailBean;
import be.jsams.common.bean.model.sale.detail.DeliveryOrderDetailBean;
import be.jsams.common.bean.model.transfer.TransferBean;
import be.jsams.server.service.sale.BillService;
import be.jsams.server.service.sale.DeliveryOrderService;
import be.jsams.server.service.transfer.AbstractTransferService;
import be.jsams.server.service.transfer.DeliveryOrderBillTransferService;

/**
 * Service to transfer an {@link DeliveryOrderBean} to a {@link BillBean}.
 * 
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class DeliveryOrderBillTransferServiceImpl extends AbstractTransferService<DeliveryOrderBean, BillBean>
        implements DeliveryOrderBillTransferService {

    private DeliveryOrderService deliveryOrderService;
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
        Map<Long, List<DeliveryOrderDetailBean>> details = model.getDeliveryOrderDetails();
        switch (transferMode) {
        case 1:
        case 3:
            List<DeliveryOrderBean> deliveryOrders = new ArrayList<DeliveryOrderBean>();
            deliveryOrders.addAll((List<DeliveryOrderBean>) documents);
            for (DeliveryOrderBean bean : deliveryOrders) {
                newDocuments.add(fullTransfer(bean));
            }
            break;
        case 2:
        case 4:
            Set<Entry<Long, List<DeliveryOrderDetailBean>>> set = details.entrySet();
            for (Entry<Long, List<DeliveryOrderDetailBean>> item : set) {
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
    protected void updateOriginalDocuments(List<DeliveryOrderBean> list) {
        for (DeliveryOrderBean document : list) {
            boolean allDetailsTransferred = true;
            for (DeliveryOrderDetailBean detail : document.getDetails()) {
                if (!detail.isTransferred()) {
                    allDetailsTransferred = false;
                    break;
                }
            }
            if (allDetailsTransferred) {
                document.setTransferred(true);
            }
            deliveryOrderService.update(document);
        }
    }

    /**
     * Transfers a list of {@link DeliveryOrderDetailBean} to bill in full
     * transfer.
     * 
     * @param list the list of {@link DeliveryOrderDetailBean} to transfer
     * @return the built new {@link BillBean}
     */
    private BillBean partialTransfer(List<DeliveryOrderDetailBean> list) {
        DeliveryOrderBean deliveryOrder = list.get(0).getDeliveryOrder();
        CustomerBean customer = deliveryOrder.getCustomer();
        PaymentModeBean paymentMode = customer.getPaymentMode();
        BillBean newBean = new BillBean(deliveryOrder.getSociety(), customer, paymentMode);
        BillMediator mediator = new BillMediator();
        newBean.setMediator(mediator);
        mediator.setBillBean(newBean);
        newBean.setBillingAddress(customer.getBillingAddress());
        newBean.getBillingAddress().setId(null);
        newBean.setClosed(false);
        Date creationDate = new Date();
        newBean.setCreationDate(creationDate);
        Date firstRemember = calculateDate(creationDate, getDays("firstRememberDays"));
        newBean.setFirstRememberDate(firstRemember);
        Date secondRemember = calculateDate(creationDate, getDays("firstRememberDays") + getDays("secondRememberDays"));
        newBean.setSecondRememberDate(secondRemember);
        Date formalNotice = calculateDate(creationDate, getDays("firstRememberDays") + getDays("secondRememberDays")
                + getDays("formalNoticeDays"));
        newBean.setFormalNoticeDate(formalNotice);
        List<BillDetailBean> details = new ArrayList<BillDetailBean>();
        for (DeliveryOrderDetailBean detail : list) {
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
        newBean.setDetails(details);
        newBean.setDiscountRate(deliveryOrder.getDiscountRate());
        Date dueDate = calculateDueDate(newBean.getCreationDate(), paymentMode.getDaysNumber(),
                paymentMode.isMonthEnd(), paymentMode.getAdditionalDays());
        newBean.setDueDate(dueDate);
        newBean.setRemark(deliveryOrder.getRemark());
        return newBean;
    }

    /**
     * Transfers a delivery order to bill in full transfer.
     * 
     * @param deliveryOrder the {@link DeliveryOrderBean} to transfer
     * @return the built new {@link BillBean}
     */
    private BillBean fullTransfer(DeliveryOrderBean deliveryOrder) {
        CustomerBean customer = deliveryOrder.getCustomer();
        PaymentModeBean paymentMode = customer.getPaymentMode();
        BillBean newBean = new BillBean(deliveryOrder.getSociety(), customer, paymentMode);
        BillMediator mediator = new BillMediator();
        newBean.setMediator(mediator);
        mediator.setBillBean(newBean);
        newBean.setBillingAddress(customer.getBillingAddress());
        newBean.getBillingAddress().setId(null);
        newBean.setClosed(false);
        Date creationDate = new Date();
        newBean.setCreationDate(creationDate);
        Date firstRemember = calculateDate(creationDate, getDays("firstRememberDays"));
        newBean.setFirstRememberDate(firstRemember);
        Date secondRemember = calculateDate(creationDate, getDays("firstRememberDays") + getDays("secondRememberDays"));
        newBean.setSecondRememberDate(secondRemember);
        Date formalNotice = calculateDate(creationDate, getDays("firstRememberDays") + getDays("secondRememberDays")
                + getDays("formalNoticeDays"));
        newBean.setFormalNoticeDate(formalNotice);
        List<BillDetailBean> details = new ArrayList<BillDetailBean>();
        for (DeliveryOrderDetailBean detail : deliveryOrder.getDetails()) {
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
        newBean.setDiscountRate(deliveryOrder.getDiscountRate());
        Date dueDate = calculateDueDate(newBean.getCreationDate(), paymentMode.getDaysNumber(),
                paymentMode.isMonthEnd(), paymentMode.getAdditionalDays());
        newBean.setDueDate(dueDate);
        newBean.setRemark(deliveryOrder.getRemark());
        return newBean;
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
