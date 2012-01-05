package be.jsams.server.dao.management.impl;

import java.util.List;

import javax.persistence.Query;

import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.ContactInformationBean;
import be.jsams.common.bean.model.LegalFormBean;
import be.jsams.common.bean.model.PaymentModeBean;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.server.dao.impl.DaoImpl;
import be.jsams.server.dao.management.CustomerDao;
import be.jsams.server.model.management.Customer;

import com.mysql.jdbc.StringUtils;

/**
 * Customer DAO implementation.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CustomerDaoImpl extends DaoImpl<Customer> implements CustomerDao {

    private SocietyBean currentSociety;

    /**
     * Constructor
     * 
     * @param type the class type
     */
    public CustomerDaoImpl(final Class<Customer> type) {
        super(type);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Customer> findByCriteria(final CustomerBean criteria) {
        StringBuilder queryBuilder = new StringBuilder("FROM Customer c");

        String name = criteria.getName();
        AddressBean billingAddress = criteria.getBillingAddress();
        String zipCode = billingAddress.getZipCode();
        ContactInformationBean contactInformation = criteria.getContactInformation();
        String phone = contactInformation.getPhone();
        PaymentModeBean paymentMode = (PaymentModeBean) criteria.getPaymentMode().getSelection();
        LegalFormBean legalForm = (LegalFormBean) criteria.getLegalForm().getSelection();

        queryBuilder.append(WHERE);
        queryBuilder.append("c.society.id = " + getCurrentSociety().getId());

        if (!StringUtils.isNullOrEmpty(name)) {
            queryBuilder.append(AND + "c.name LIKE '%" + name + "%'");
        }
        if (!StringUtils.isNullOrEmpty(zipCode)) {
            queryBuilder.append(AND + "c.billingAddress.zipCode = " + zipCode);
        }
        if (paymentMode != null) {
            queryBuilder.append(AND + "c.paymentMode.id = " + paymentMode.getId());
        }
        if (legalForm != null) {
            queryBuilder.append(AND + "c.legalForm.id = " + legalForm.getId());
        }
        if (!StringUtils.isNullOrEmpty(phone)) {
            queryBuilder.append(AND + "c.contactInformation.phone LIKE '%" + phone + "%'");
        }

        Query query = getEntityManager().createQuery(queryBuilder.toString());
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Customer> findAll() {
        StringBuilder queryBuilder = new StringBuilder("FROM Customer c");

        queryBuilder.append(WHERE);
        queryBuilder.append("c.society.id = " + getCurrentSociety().getId());

        Query query = getEntityManager().createQuery(queryBuilder.toString());
        return query.getResultList();
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
