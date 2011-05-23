package be.jsams.server.dao.management.impl;

import java.util.List;

import javax.persistence.Query;

import be.jsams.common.bean.model.CivilityBean;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.server.dao.impl.DaoImpl;
import be.jsams.server.dao.management.AgentDao;
import be.jsams.server.model.management.Agent;

/**
 * Agent DAO implementation.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class AgentDaoImpl extends DaoImpl<Agent> implements AgentDao {

    private SocietyBean currentSociety;
    
    /**
     * Constructor
     * 
     * @param type the class type
     */
    public AgentDaoImpl(final Class<Agent> type) {
        super(type);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Agent> findByCriteria(final AgentBean criteria) {
        StringBuilder queryBuilder = new StringBuilder("FROM Agent a");

        String name = criteria.getName();

        String function = criteria.getFunction();

        String zipCode = criteria.getAddress().getZipCode();
        String city = criteria.getAddress().getCity();

        String phone = criteria.getContactInformation().getPhone();
        CivilityBean civility = (CivilityBean) criteria.getCivility().getSelection();

        queryBuilder.append(" WHERE ");
        queryBuilder.append("a.society.id = " + criteria.getSociety().getId());

        if (name != null) {
            queryBuilder.append(" AND a.name LIKE '%" + name + "%'");
        }
        if (function != null) {
            queryBuilder.append(" AND a.function LIKE '%" + function + "%'");
        }
        if (zipCode != null) {
            queryBuilder.append(" AND a.address.zipCode = " + zipCode);
        }
        if (city != null) {
            queryBuilder.append(" AND a.address.city LIKE '%" + city + "%'");
        }
        if (civility != null) {
            queryBuilder.append(" AND a.civility.id = " + civility.getId());
        }
        if (phone != null) {
            queryBuilder.append(" AND a.contactInformation.phone LIKE '%" + phone + "%'");
        }

        Query query = getEntityManager().createQuery(queryBuilder.toString());
        List<Agent> result = query.getResultList();
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Agent> findAll() {
        StringBuilder queryBuilder = new StringBuilder("FROM Agent a");

        queryBuilder.append(" WHERE ");
        queryBuilder.append("a.society.id = " + getCurrentSociety().getId());

        Query query = getEntityManager().createQuery(queryBuilder.toString());
        List<Agent> result = query.getResultList();
        return result;
    }
    
    /**
     * {@inheritDoc}
     */
    public SocietyBean getCurrentSociety() {
        return currentSociety;
    }

    /**
     * {@inheritDoc}
     */
    public void setCurrentSociety(SocietyBean currentSociety) {
        this.currentSociety = currentSociety;
    }

}
