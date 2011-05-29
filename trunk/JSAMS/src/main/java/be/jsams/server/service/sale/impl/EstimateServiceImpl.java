package be.jsams.server.service.sale.impl;

import java.util.ArrayList;
import java.util.List;

import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.sale.EstimateBean;
import be.jsams.server.dao.sale.EstimateDao;
import be.jsams.server.model.sale.Estimate;
import be.jsams.server.service.AbstractService;
import be.jsams.server.service.sale.EstimateService;

/**
 * Estimate service implementation.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EstimateServiceImpl extends AbstractService implements EstimateService {

    private EstimateDao estimateDao;

    /**
     * {@inheritDoc}
     */
    public EstimateBean create(final EstimateBean bean) {
        Estimate estimate = new Estimate(bean);
        Estimate addingEstimate = estimateDao.add(estimate);
        return new EstimateBean(addingEstimate, JsamsDesktop.getInstance().getCurrentSociety(), bean.getCustomer());
    }

    /**
     * {@inheritDoc}
     */
    public void delete(final EstimateBean bean) {
        Estimate estimate = new Estimate(bean);
        delete(estimate);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Long id) {
        Estimate estimate = estimateDao.findById(id);
        delete(estimate);
    }

    /**
     * Delete a {@link Estimate} object and all its EstimateDetail object.
     * 
     * @param estimate the {@link Estimate} to delete
     */
    private void delete(Estimate estimate) {
        estimateDao.delete(estimate);
    }

    /**
     * {@inheritDoc}
     */
    public List<EstimateBean> findAll() {
        SocietyBean currentSociety = JsamsDesktop.getInstance().getCurrentSociety();
        estimateDao.setCurrentSociety(currentSociety);
        List<Estimate> estimates = estimateDao.findAll();
        List<EstimateBean> beans = new ArrayList<EstimateBean>();
        for (Estimate estimate : estimates) {
            CustomerBean customer = getCustomerBeanBuilder().build(estimate.getCustomer(), currentSociety);
            beans.add(new EstimateBean(estimate, currentSociety, customer));
        }
        return beans;
    }

    /**
     * {@inheritDoc}
     */
    public EstimateBean findById(final Long id) {
        SocietyBean currentSociety = JsamsDesktop.getInstance().getCurrentSociety();
        Estimate estimate = estimateDao.findById(id);
        CustomerBean customer = getCustomerBeanBuilder().build(estimate.getCustomer(), currentSociety);
        EstimateBean bean = new EstimateBean(estimate, currentSociety, customer);
        return bean;
    }

    /**
     * {@inheritDoc}
     */
    public void update(final EstimateBean bean) {
        Estimate updatedEstimate = new Estimate(bean);
        estimateDao.update(updatedEstimate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<EstimateBean> findByCriteria(final EstimateBean criteria) {
        // necessary to have the current society in the criteria
        SocietyBean currentSociety = JsamsDesktop.getInstance().getCurrentSociety();
        estimateDao.setCurrentSociety(currentSociety);
        List<Estimate> estimates = estimateDao.findByCriteria(criteria);
        List<EstimateBean> beans = new ArrayList<EstimateBean>();
        for (Estimate estimate : estimates) {
            CustomerBean customer = getCustomerBeanBuilder().build(estimate.getCustomer(), currentSociety);
            beans.add(new EstimateBean(estimate, currentSociety, customer));
        }
        return beans;
    }

    /**
     * @return the estimateDao
     */
    public EstimateDao getEstimateDao() {
        return estimateDao;
    }

    /**
     * @param estimateDao the estimateDao to set
     */
    public void setEstimateDao(EstimateDao estimateDao) {
        this.estimateDao = estimateDao;
    }

}
