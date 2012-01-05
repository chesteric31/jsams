package be.jsams.server.dao.management.impl;

import java.util.List;

import javax.persistence.Query;

import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.ProductCategoryBean;
import be.jsams.server.dao.impl.DaoImpl;
import be.jsams.server.dao.management.ProductCategoryDao;
import be.jsams.server.model.management.ProductCategory;

import com.mysql.jdbc.StringUtils;

/**
 * Product Category DAO implementation.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class ProductCategoryDaoImpl extends DaoImpl<ProductCategory> implements ProductCategoryDao {

    private SocietyBean currentSociety;

    /**
     * Constructor
     * 
     * @param type the class type
     */
    public ProductCategoryDaoImpl(final Class<ProductCategory> type) {
        super(type);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<ProductCategory> findByCriteria(final ProductCategoryBean criteria) {
        StringBuilder queryBuilder = new StringBuilder("FROM ProductCategory c");

        String name = criteria.getLabel();
        String nameFr = criteria.getLabelFr();
        String nameNl = criteria.getLabelNl();

        queryBuilder.append(WHERE);
        queryBuilder.append("c.society.id = " + getCurrentSociety().getId());

        if (!StringUtils.isNullOrEmpty(name)) {
            queryBuilder.append(AND + "c.label LIKE '%" + name + "%'");
        }
        if (!StringUtils.isNullOrEmpty(nameFr)) {
            queryBuilder.append(AND + "c.labelFr LIKE '%" + nameFr + "%'");
        }
        if (!StringUtils.isNullOrEmpty(nameNl)) {
            queryBuilder.append(AND + "c.labelNl LIKE '%" + nameNl + "%'");
        }
        Query query = getEntityManager().createQuery(queryBuilder.toString());
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<ProductCategory> findAll() {
        StringBuilder queryBuilder = new StringBuilder("FROM ProductCategory c");

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
