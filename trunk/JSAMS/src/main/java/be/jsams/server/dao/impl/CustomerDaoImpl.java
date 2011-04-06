package be.jsams.server.dao.impl;

import java.util.List;

import javax.persistence.Query;

import be.jsams.server.dao.CustomerDao;
import be.jsams.server.model.Address;
import be.jsams.server.model.ContactInformation;
import be.jsams.server.model.Customer;
import be.jsams.server.model.LegalForm;
import be.jsams.server.model.PaymentMode;

/**
 * Customer DAO implementation.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CustomerDaoImpl extends DaoImpl<Customer> implements CustomerDao {

    /**
     * Constructor
     * 
     * @param type
     *            the class type
     */
    public CustomerDaoImpl(final Class<Customer> type) {
        super(type);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Customer> findByCriteria(final Customer criteria) {
        StringBuilder queryBuilder = new StringBuilder("FROM Customer c");

        boolean isFirst = true;

        String name = criteria.getName();
        Address billingAddress = criteria.getBillingAddress();
        int zipCode = -1;
        if (billingAddress != null) {
            zipCode = billingAddress.getZipCode();
        }
        ContactInformation contactInformation = criteria.getContactInformation();
        String phone = null;
        if (contactInformation != null) {
            phone = contactInformation.getPhone();
        }
        PaymentMode paymentMode = criteria.getPaymentMode();
        Long paymentModeId = 0L;
        if (paymentMode != null) {
            paymentModeId = paymentMode.getId();
        }
        LegalForm legalForm = criteria.getLegalForm();
        Long legalFormId = 0L;
        if (legalForm != null) {
            legalFormId = legalForm.getId();
        }
        
        if (name != null) {
            if (isFirst) {
                queryBuilder.append(" WHERE");
                isFirst = false;
            }
            queryBuilder.append(" c.name LIKE '%" + name + "%'");
        }
        if (zipCode != -1) {
            if (isFirst) {
                queryBuilder.append(" WHERE");
                isFirst = false;
            } else {
                queryBuilder.append(" AND");
            }
            queryBuilder.append(" c.billingAddress.zipCode = " + zipCode);
        }
        if (!paymentModeId.equals(0L)) {
            if (isFirst) {
                queryBuilder.append(" WHERE");
                isFirst = false;
            } else {
                queryBuilder.append(" AND");
            }
            queryBuilder.append(" c.paymentMode.id = " + paymentModeId);
        }
        if (!legalFormId.equals(0L)) {
            if (isFirst) {
                queryBuilder.append(" WHERE");
                isFirst = false;
            } else {
                queryBuilder.append(" AND");
            }
            queryBuilder.append(" c.legalForm.id = " + legalFormId);
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
        List<Customer> result = query.getResultList();
        return result;
    }

}
