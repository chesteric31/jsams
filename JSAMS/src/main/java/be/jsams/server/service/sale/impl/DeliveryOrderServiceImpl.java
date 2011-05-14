package be.jsams.server.service.sale.impl;

import java.util.ArrayList;
import java.util.List;

import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.common.bean.model.sale.DeliveryOrderBean;
import be.jsams.server.dao.sale.DeliveryOrderDao;
import be.jsams.server.model.sale.DeliveryOrder;
import be.jsams.server.service.sale.DeliveryOrderService;

/**
 * Delivery Order service implementation.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class DeliveryOrderServiceImpl implements DeliveryOrderService {

    private DeliveryOrderDao deliveryOrderDao;
    
    /**
     * @return the deliveryOrderDao
     */
    public DeliveryOrderDao getDeliveryOrderDao() {
        return deliveryOrderDao;
    }

    /**
     * @param deliveryOrderDao the deliveryOrderDao to set
     */
    public void setDeliveryOrderDao(DeliveryOrderDao deliveryOrderDao) {
        this.deliveryOrderDao = deliveryOrderDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DeliveryOrderBean create(DeliveryOrderBean bean) {
        DeliveryOrder deliveryOrder = new DeliveryOrder(bean);
        DeliveryOrder addingDeliveryOrder = deliveryOrderDao.add(deliveryOrder);
        return new DeliveryOrderBean(addingDeliveryOrder);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(DeliveryOrderBean bean) {
        DeliveryOrder deliveryOrder = deliveryOrderDao.findById(bean.getId());
        deliveryOrderDao.delete(deliveryOrder);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        DeliveryOrder deliveryOrder = deliveryOrderDao.findById(id);
        deliveryOrderDao.delete(deliveryOrder);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(DeliveryOrderBean bean) {
        DeliveryOrder deliveryOrder = new DeliveryOrder(bean);
        deliveryOrderDao.update(deliveryOrder);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DeliveryOrderBean findById(Long id) {
        DeliveryOrder deliveryOrder = deliveryOrderDao.findById(id);
        DeliveryOrderBean bean = new DeliveryOrderBean(deliveryOrder);
        return bean;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DeliveryOrderBean> findAll() {
        List<DeliveryOrder> deliveryOrders = deliveryOrderDao.findAll();
        List<DeliveryOrderBean> beans = new ArrayList<DeliveryOrderBean>();
        for (DeliveryOrder deliveryOrder : deliveryOrders) {
            beans.add(new DeliveryOrderBean(deliveryOrder));
        }
        return beans;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DeliveryOrderBean> findByCriteria(DeliveryOrderBean criteria) {
        criteria.setSociety(JsamsDesktop.getInstance().getCurrentSociety());
        List<DeliveryOrder> deliveryOrders = deliveryOrderDao.findByCriteria(criteria);
        List<DeliveryOrderBean> beans = new ArrayList<DeliveryOrderBean>();
        for (DeliveryOrder deliveryOrder : deliveryOrders) {
            beans.add(new DeliveryOrderBean(deliveryOrder));
        }
        return beans;
    }

}
