package be.jsams.server.dao.impl;

import java.util.List;

import javax.persistence.Query;

import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.common.bean.model.AgentBean;
import be.jsams.common.bean.model.CivilityBean;
import be.jsams.server.dao.AgentDao;
import be.jsams.server.model.Agent;

/**
 * Agent DAO implementation.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class AgentDaoImpl extends DaoImpl<Agent> implements AgentDao {

    /**
     * Constructor
     * 
     * @param type
     *            the class type
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
        
        Long societyId = JsamsDesktop.getInstance().getCurrentSociety().getId();
        
        queryBuilder.append(" WHERE ");
        queryBuilder.append("a.society.id = " + societyId);
        
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
            queryBuilder.append(" AND a.address.city = " + city);
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

}
