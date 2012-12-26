package be.jsams.server.service.sale.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import be.jsams.common.bean.builder.management.AgentBeanBuilder;
import be.jsams.common.bean.model.LegalFormBean;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.sale.EstimateBean;
import be.jsams.server.dao.sale.EstimateDao;
import be.jsams.server.model.Society;
import be.jsams.server.model.sale.Estimate;
import be.jsams.server.model.sale.detail.EstimateDetail;
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
        return new EstimateBean(persistedEstimate, bean.getSociety(), bean.getCustomer(), agent,
                getProductBeanBuilder());
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
        return mapModelToBean(currentSociety, estimates);
    }

    /**
     * {@inheritDoc}
     */
    public EstimateBean findById(final Long id) {
        Estimate estimate = estimateDao.findById(id);
        if (estimate != null) {
            Society model = estimate.getCustomer().getSociety();
            SocietyBean society = new SocietyBean(model);
            society.setLegalForm(new LegalFormBean(model.getLegalForm()));
            CustomerBean customer = getCustomerBeanBuilder().build(estimate.getCustomer(), society);
            AgentBean agent = agentBeanBuilder.build(estimate.getAgent(), society);
            return new EstimateBean(estimate, society, customer, agent, getProductBeanBuilder());
        } else {
            return null;
        }
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
        return mapModelToBean(society, estimates);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Double, List<EstimateBean>> findNotTransferredEstimates(SocietyBean society) {
        EstimateBean criteria = new EstimateBean(society, null, null);
        criteria.setTransferred(false);
        return findByCriteriaWithSum(society, criteria);
    }
    
    /**
     * Builds the map with sum and result of criteria query.
     * 
     * @param society the {@link SocietyBean} to use
     * @param criteria the criteria to use
     * @return the built map with sum and result of criteria query
     */
    private Map<Double, List<EstimateBean>> findByCriteriaWithSum(SocietyBean society, EstimateBean criteria) {
        List<Estimate> estimates = estimateDao.findByCriteria(society.getId(), criteria);
        return buildMap(society, estimates);
    }

    /**
     * Builds the map with sum and the estimates list.
     * 
     * @param society the {@link SocietyBean} to use
     * @param estimates the estimates list to use
     * @return the built map with sum and the estimates list
     */
    private Map<Double, List<EstimateBean>> buildMap(SocietyBean society, List<Estimate> estimates) {
        BigDecimal sum = calculateSum(estimates);
        List<EstimateBean> list = mapModelToBean(society, estimates);
        Map<Double, List<EstimateBean>> map = new HashMap<Double, List<EstimateBean>>();
        map.put(sum.doubleValue(), list);
        return map;
    }

    /**
     * Maps the estimates to the estimateBeans.
     * 
     * @param society the {@link SocietyBean} to use
     * @param estimates the estimates to map
     * @return the mapped estimateBeans.
     */
    private List<EstimateBean> mapModelToBean(SocietyBean society, List<Estimate> estimates) {
        List<EstimateBean> beans = new ArrayList<EstimateBean>();
        for (Estimate estimate : estimates) {
            CustomerBean customer = getCustomerBeanBuilder().build(estimate.getCustomer(), society);
            AgentBean agent = agentBeanBuilder.build(estimate.getAgent(), society);
            beans.add(new EstimateBean(estimate, society, customer, agent, getProductBeanBuilder()));
        }
        return beans;
    }

    /**
     * Calculates sum of the estimates specified.
     * 
     * @param estimates the list of estimates to use
     * @return the {@link BigDecimal} for the sum of all the estimates
     */
    public BigDecimal calculateSum(List<Estimate> estimates) {
        BigDecimal sum = new BigDecimal(0D);
        if (estimates != null && !estimates.isEmpty()) {
            for (Estimate estimate : estimates) {
                List<EstimateDetail> details = estimate.getDetails();
                if (details != null && !details.isEmpty()) {
                    for (EstimateDetail detail : details) {
                        Double discountRate = detail.getDiscountRate();
                        Double price = detail.getPrice();
                        int quantity = detail.getQuantity();
                        BigDecimal totalEt = BigDecimal.valueOf(price * quantity);
                        if (discountRate != null) {
                            double percentage = discountRate / 100;
                            totalEt = totalEt.multiply(BigDecimal.valueOf(1 - percentage));
                        }
                        sum = sum.add(totalEt);
                    }
                }
            }
        }
        return sum;
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
