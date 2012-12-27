package be.jsams.server.dao.management.impl;

import java.util.List;

import javax.persistence.Query;

import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.ContactInformationBean;
import be.jsams.common.bean.model.LegalFormBean;
import be.jsams.common.bean.model.PaymentModeBean;
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

    /**
     * Constructor.
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
    public List<Customer> findByCriteria(Long currentSocietyId, final CustomerBean criteria) {
        StringBuilder queryBuilder = new StringBuilder("FROM Customer c");

        String name = criteria.getName();
        AddressBean billingAddress = criteria.getBillingAddress();
        String zipCode = billingAddress.getZipCode();
        ContactInformationBean contactInformation = criteria.getContactInformation();
        String phone = contactInformation.getPhone();
        PaymentModeBean paymentMode = (PaymentModeBean) criteria.getPaymentMode().getSelection();
        LegalFormBean legalForm = (LegalFormBean) criteria.getLegalForm().getSelection();

        queryBuilder.append(WHERE);
        queryBuilder.append("c.society.id = " + currentSocietyId);

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
    public List<Customer> findAll(Long currentSocietyId) {
        StringBuilder queryBuilder = new StringBuilder("FROM Customer c");

        queryBuilder.append(WHERE);
        queryBuilder.append("c.society.id = " + currentSocietyId);

        Query query = getEntityManager().createQuery(queryBuilder.toString());
        return query.getResultList();
    }
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> findTop5CustomersWithBills(Long societyId) {
        StringBuilder queryBuilder = new StringBuilder(
                "select c.id, sum(bd.quantity* bd.price*(1-(coalesce(bd.discountRate, 0)/100))) "
                        + "from Bill b, BillDetail bd, Customer c "
                        + "WHERE b.id = bd.bill.id and b.customer.id = c.id and c.society.id = " + societyId
                        + " group by c.id "
                        + "ORDER BY sum(bd.quantity* bd.price*(1-(coalesce(bd.discountRate, 0)/100))) DESC");

        Query query = getEntityManager().createQuery(queryBuilder.toString());
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> findTop5CustomersWithCreditNotes(Long societyId) {
        StringBuilder queryBuilder = new StringBuilder(
                "select c.id, sum(cnd.quantity* cnd.price) "
                        + "from CreditNote cn, CreditNoteDetail cnd, Customer c "
                        + "WHERE cn.id = cnd.creditNote.id and cn.customer.id = c.id and c.society.id = " + societyId
                        + " group by c.id "
                        + "ORDER BY sum(cnd.quantity* cnd.price) DESC");

        Query query = getEntityManager().createQuery(queryBuilder.toString());
        return query.getResultList();
    }


    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Customer> findWithEstimates(Long societyId) {
        StringBuilder queryBuilder = new StringBuilder("SELECT c FROM Customer c, Estimate e");

        queryBuilder.append(WHERE);
        queryBuilder.append("c.society.id = " + societyId);
        queryBuilder.append(AND);
        queryBuilder.append("e.customer.id = c.id");

        Query query = getEntityManager().createQuery(queryBuilder.toString());
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Customer> findWithBills(Long societyId) {
        StringBuilder queryBuilder = new StringBuilder("SELECT c FROM Customer c, Bill b");

        queryBuilder.append(WHERE);
        queryBuilder.append("c.society.id = " + societyId);
        queryBuilder.append(AND);
        queryBuilder.append("b.customer.id = c.id");

        Query query = getEntityManager().createQuery(queryBuilder.toString());
        return query.getResultList();
    }

}
