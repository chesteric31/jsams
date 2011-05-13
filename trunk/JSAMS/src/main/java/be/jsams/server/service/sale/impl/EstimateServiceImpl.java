package be.jsams.server.service.sale.impl;

import java.util.ArrayList;
import java.util.List;

import be.jsams.common.bean.model.sale.EstimateBean;
import be.jsams.server.dao.sale.EstimateDao;
import be.jsams.server.model.sale.Estimate;
import be.jsams.server.service.sale.EstimateService;

/**
 * Estimate service implementation.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EstimateServiceImpl implements EstimateService {

    private EstimateDao estimateDao;

    /**
     * @return the estimateDao
     */
    public EstimateDao getEstimateDao() {
        return estimateDao;
    }

    /**
     * @param estimateDao
     *            the estimateDao to set
     */
    public void setEstimateDao(EstimateDao estimateDao) {
        this.estimateDao = estimateDao;
    }

    /**
     * {@inheritDoc}
     */
    public EstimateBean create(final EstimateBean bean) {
        Estimate estimate = new Estimate(bean);
        Estimate addingEstimate = estimateDao.add(estimate);
        return new EstimateBean(addingEstimate);
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
        List<Estimate> estimates = estimateDao.findAll();
        List<EstimateBean> beans = new ArrayList<EstimateBean>();
        for (Estimate estimate : estimates) {
            beans.add(new EstimateBean(estimate));
        }
        return beans;
    }

    /**
     * {@inheritDoc}
     */
    public EstimateBean findById(final Long id) {
        Estimate estimate = estimateDao.findById(id);
        EstimateBean bean = new EstimateBean(estimate);
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
//        criteria.setSociety(JsamsDesktop.getInstance().getCurrentSociety());
        List<Estimate> estimates = estimateDao.findByCriteria(criteria);
        List<EstimateBean> beans = new ArrayList<EstimateBean>();
        for (Estimate estimate : estimates) {
            beans.add(new EstimateBean(estimate));
        }
        return beans;
    }

}
