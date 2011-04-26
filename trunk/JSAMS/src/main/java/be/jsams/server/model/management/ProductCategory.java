package be.jsams.server.model.management;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import be.jsams.common.bean.model.management.ProductCategoryBean;
import be.jsams.server.model.AbstractTranslatableIdentity;
import be.jsams.server.model.Society;

/**
 * Product category entity object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@Entity
@Table(name = "PRODUCT_CATEGORY")
public class ProductCategory extends AbstractTranslatableIdentity {

    private Society society;

    /**
     * Constructor
     */
    public ProductCategory() {
        super();
    }

    /**
     * Constructor
     * 
     * @param bean
     *            the {@link ProductCategoryBean}
     */
    public ProductCategory(ProductCategoryBean bean) {
        super(bean);
        setSociety(new Society(bean.getSociety()));
    }

    /**
     * @return the society
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_SOCIETY")
    public Society getSociety() {
        return society;
    }

    /**
     * @param society
     *            the society to set
     */
    public void setSociety(Society society) {
        this.society = society;
    }

}
