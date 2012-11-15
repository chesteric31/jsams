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
import be.jsams.server.service.transfer.DeliveryOrderTransferService;

/**
 * 
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class DeliveryOrderTransferServiceImpl extends AbstractTransferService implements DeliveryOrderTransferService {

    private DeliveryOrderService deliveryOrderService;
    private BillService billService;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void transfer(TransferBean model) {
        int destinationType = model.getDestinationType();
        if (destinationType == 3) {
            toBillTransfer(model);
        }
    }
    /**
     * @param model the wrapper contains all the beans to be transferred
     */
    @SuppressWarnings("unchecked")
    private void toBillTransfer(TransferBean model) {
        int transferMode = model.getTransferMode();
        List<? extends AbstractDocumentBean<?, ?>> documents = model.getDocuments();
        Map<Long, List<DeliveryOrderDetailBean>> details = model.getDeliveryOrderDetails();
        switch (transferMode) {
        case 1:
            DeliveryOrderBean deliveryOrder = (DeliveryOrderBean) documents.get(0);
            toBillFullTransfer(deliveryOrder);
            break;
        case 2:
        case 4:
            Set<Entry<Long, List<DeliveryOrderDetailBean>>> set = details.entrySet();
            for (Entry<Long, List<DeliveryOrderDetailBean>> item : set) {
                toBillPartialTransfer(details.get(item.getKey()));
            }
            break;
        case 3:
            List<DeliveryOrderBean> deliveryOrders = new ArrayList<DeliveryOrderBean>();
            deliveryOrders.addAll((List<DeliveryOrderBean>) documents);
            for (DeliveryOrderBean bean : deliveryOrders) {
                toBillFullTransfer(bean);
            }
            break;
        default:
            break;
        }
    }

    /**
     * Transfers a list of {@link DeliveryOrderDetailBean} to bill in full
     * transfer.
     * 
     * @param list the list of {@link DeliveryOrderDetailBean} to transfer
     */
    private void toBillPartialTransfer(List<DeliveryOrderDetailBean> list) {
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
        newBean.setDateFirstRemember(firstRemember);
        Date secondRemember = calculateDate(creationDate, getDays("firstRememberDays") + getDays("secondRememberDays"));
        newBean.setDateSecondRemember(secondRemember);
        Date formalNotice = calculateDate(creationDate, getDays("firstRememberDays") + getDays("secondRememberDays")
                + getDays("formalNoticeDays"));
        newBean.setDateFormalNotice(formalNotice);
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
        // TODO review
        boolean allDetailTransferred = true;
        for (DeliveryOrderDetailBean detailBean : deliveryOrder.getDetails()) {
            if (!detailBean.isTransferred()) {
                allDetailTransferred = false;
                break;
            }
        }
        if (allDetailTransferred) {
            deliveryOrder.setTransferred(true);
        }
        deliveryOrderService.update(deliveryOrder);
    }

    /**
     * Transfers a delivery order to bill in full transfer.
     * 
     * @param deliveryOrder the {@link DeliveryOrderBean} to transfer
     */
    private void toBillFullTransfer(DeliveryOrderBean deliveryOrder) {
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
        newBean.setDateFirstRemember(firstRemember);
        Date secondRemember = calculateDate(creationDate, getDays("firstRememberDays") + getDays("secondRememberDays"));
        newBean.setDateSecondRemember(secondRemember);
        Date formalNotice = calculateDate(creationDate, getDays("firstRememberDays") + getDays("secondRememberDays")
                + getDays("formalNoticeDays"));
        newBean.setDateFormalNotice(formalNotice);
        List<BillDetailBean> details = new ArrayList<BillDetailBean>();
        for (DeliveryOrderDetailBean detail : deliveryOrder.getDetails()) {
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
        newBean.setDiscountRate(deliveryOrder.getDiscountRate());
        Date dueDate = calculateDueDate(newBean.getCreationDate(), paymentMode.getDaysNumber(),
                paymentMode.isMonthEnd(), paymentMode.getAdditionalDays());
        newBean.setDueDate(dueDate);
        newBean.setPaid(false);
        newBean.setRemark(deliveryOrder.getRemark());
        billService.create(newBean);
        deliveryOrderService.update(deliveryOrder);
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
