package be.jsams.server.service.sale.impl;

import java.util.ArrayList;
import java.util.List;

import be.jsams.common.bean.builder.management.AgentBeanBuilder;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.AgentBean;
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
    private AgentBeanBuilder agentBeanBuilder;

    /**
     * {@inheritDoc}
     */
    public EstimateBean create(final EstimateBean bean) {
        Estimate estimate = new Estimate(bean);
        Estimate persistedEstimate = estimateDao.add(estimate);
        AgentBean agent = agentBeanBuilder.build(persistedEstimate.getAgent(), bean.getSociety());
        return new EstimateBean(persistedEstimate, bean.getSociety(), bean.getCustomer(), agent);
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
    public List<EstimateBean> findAll(SocietyBean currentSociety) {
        List<Estimate> estimates = estimateDao.findAll(currentSociety.getId());
        List<EstimateBean> beans = new ArrayList<EstimateBean>();
        for (Estimate estimate : estimates) {
            CustomerBean customer = getCustomerBeanBuilder().build(estimate.getCustomer(), currentSociety);
            AgentBean agent = agentBeanBuilder.build(estimate.getAgent(), currentSociety);
            beans.add(new EstimateBean(estimate, currentSociety, customer, agent));
        }
        return beans;
    }

    /**
     * {@inheritDoc}
     */
    public EstimateBean findById(final Long id) {
        Estimate estimate = estimateDao.findById(id);
        SocietyBean society = new SocietyBean(estimate.getCustomer().getSociety());
        CustomerBean customer = getCustomerBeanBuilder().build(estimate.getCustomer(), society);
        AgentBean agent = agentBeanBuilder.build(estimate.getAgent(), society);
        return new EstimateBean(estimate, society, customer, agent);
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
        SocietyBean society = criteria.getSociety();
        List<Estimate> estimates = estimateDao.findByCriteria(society.getId(), criteria);
        List<EstimateBean> beans = new ArrayList<EstimateBean>();
        for (Estimate estimate : estimates) {
            CustomerBean customer = getCustomerBeanBuilder().build(estimate.getCustomer(), society);
            AgentBean agent = agentBeanBuilder.build(estimate.getAgent(), society);
            beans.add(new EstimateBean(estimate, society, customer, agent));
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

    /**
     * @return the agentBeanBuilder
     */
    public AgentBeanBuilder getAgentBeanBuilder() {
        return agentBeanBuilder;
    }

    /**
     * @param agentBeanBuilder the agentBeanBuilder to set
     */
    public void setAgentBeanBuilder(AgentBeanBuilder agentBeanBuilder) {
        this.agentBeanBuilder = agentBeanBuilder;
    }

}
