package be.jsams.server.dao.impl;

import java.util.List;

import javax.persistence.Query;

import be.jsams.server.dao.AgentDao;
import be.jsams.server.model.Address;
import be.jsams.server.model.Agent;
import be.jsams.server.model.Civility;
import be.jsams.server.model.ContactInformation;

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
    public List<Agent> findByCriteria(Agent criteria) {
        StringBuilder queryBuilder = new StringBuilder("FROM Agent a");

        boolean isFirst = true;

        String name = criteria.getName();

        String function = criteria.getFunction();
        
        Address address = criteria.getAddress();
        int zipCode = -1;
        String city = null;
        if (address != null) {
            zipCode = address.getZipCode();
            city = address.getCity();
        }
        
        ContactInformation contactInformation = criteria.getContactInformation();
        String phone = null;
        if (contactInformation != null) {
            phone = contactInformation.getPhone();
        }
        Civility civility = criteria.getCivility();
        Long civilityId = 0L;
        if (civility != null) {
            civilityId = civility.getId();
        }
        
        if (name != null) {
            if (isFirst) {
                queryBuilder.append(" WHERE");
                isFirst = false;
            }
            queryBuilder.append(" c.name LIKE '%" + name + "%'");
        }
        if (function != null) {
            if (isFirst) {
                queryBuilder.append(" WHERE");
                isFirst = false;
            }
            queryBuilder.append(" c.function LIKE '%" + function + "%'");
        }
        if (zipCode != -1) {
            if (isFirst) {
                queryBuilder.append(" WHERE");
                isFirst = false;
            } else {
                queryBuilder.append(" AND");
            }
            queryBuilder.append(" c.address.zipCode = " + zipCode);
        }
        if (city != null) {
            if (isFirst) {
                queryBuilder.append(" WHERE");
                isFirst = false;
            } else {
                queryBuilder.append(" AND");
            }
            queryBuilder.append(" c.address.city = " + city);
        }
        if (!civilityId.equals(0L)) {
            if (isFirst) {
                queryBuilder.append(" WHERE");
                isFirst = false;
            } else {
                queryBuilder.append(" AND");
            }
            queryBuilder.append(" c.civility.id = " + civilityId);
        }
        if (phone != null) {
            if (isFirst) {
                queryBuilder.append(" WHERE");
                isFirst = false;
            } else {
                queryBuilder.append(" AND");
            }
            queryBuilder.append(" c.contactInformation.phone LIKE '%" + phone + "%'");
        }

        Query query = getEntityManager().createQuery(queryBuilder.toString());
        List<Agent> result = query.getResultList();
        return result;
    }

}
