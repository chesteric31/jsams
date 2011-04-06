package be.jsams.server.service.impl;

import java.util.List;

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
    public void create(final Estimate estimate) {
        estimateDao.add(estimate);
        List<EstimateDetail> details = estimate.getDetails();
        for (EstimateDetail detail : details) {
            estimateDetailDao.add(detail);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete(final Estimate estimate) {
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
        Estimate estimate = findById(id);
        List<EstimateDetail> details = estimate.getDetails();
        for (EstimateDetail detail : details) {
            estimateDetailDao.delete(detail);
        }
        estimateDao.delete(id);
    }

    /**
     * {@inheritDoc}
     */
    public List<Estimate> findAll() {
        return estimateDao.findAll();
    }

    /**
     * {@inheritDoc}
     */
    public Estimate findById(final Long id) {
        return estimateDao.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    public void update(final Estimate estimate) {
        estimateDao.update(estimate);
    }

}
