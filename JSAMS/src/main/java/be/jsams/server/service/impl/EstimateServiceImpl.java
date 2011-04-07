package be.jsams.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import be.jsams.common.bean.model.sale.EstimateBean;
import be.jsams.common.bean.model.sale.EstimateDetailBean;
import be.jsams.server.dao.EstimateDao;
import be.jsams.server.dao.EstimateDetailDao;
import be.jsams.server.model.Estimate;
import be.jsams.server.model.EstimateDetail;
import be.jsams.server.service.EstimateService;

/**
 * Estimate service implementation.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EstimateServiceImpl implements EstimateService {
    
    private EstimateDao estimateDao;
    private EstimateDetailDao estimateDetailDao;
    
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

    /**
     * @return the estimateDetailDao
     */
    public EstimateDetailDao getEstimateDetailDao() {
        return estimateDetailDao;
    }

    /**
     * @param estimateDetailDao the estimateDetailDao to set
     */
    public void setEstimateDetailDao(EstimateDetailDao estimateDetailDao) {
        this.estimateDetailDao = estimateDetailDao;
    }

    /**
     * {@inheritDoc}
     */
    public EstimateBean create(final EstimateBean bean) {
        Estimate estimate = new Estimate(bean);
        Estimate addingEstimate = estimateDao.add(estimate);
        List<EstimateDetailBean> details = bean.getDetails();
        List<EstimateDetail> addingDetails = new ArrayList<EstimateDetail>();
        for (EstimateDetailBean detail : details) {
            EstimateDetail detailObject = new EstimateDetail(detail);
            detailObject.setEstimate(addingEstimate);
            addingDetails.add((estimateDetailDao.add(detailObject)));
        }
        addingEstimate.setDetails(addingDetails);
        return new EstimateBean(addingEstimate);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(final EstimateBean bean) {
        Estimate estimate = new Estimate(bean);
        List<EstimateDetail> details = estimate.getDetails();
        for (EstimateDetail detail : details) {
            estimateDetailDao.delete(detail);
        }
        estimateDao.delete(estimate);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Long id) {
        Estimate estimate = estimateDao.findById(id);
        List<EstimateDetail> details = estimate.getDetails();
        for (EstimateDetail detail : details) {
            estimateDetailDao.delete(detail);
        }
        estimateDao.delete(id);
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
        Estimate estimate = new Estimate(bean);
        estimateDao.update(estimate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<EstimateBean> findByCriteria(EstimateBean criteria) {
        List<Estimate> estimates = estimateDao.findByCriteria(criteria);
        List<EstimateBean> beans = new ArrayList<EstimateBean>();
        for (Estimate estimate : estimates) {
            beans.add(new EstimateBean(estimate));
        }
        return beans;
    }

}
