package be.jsams.server.service;

import be.jsams.common.bean.builder.ProductBeanBuilder;
import be.jsams.common.bean.builder.SocietyBeanBuilder;
import be.jsams.common.bean.builder.management.CustomerBeanBuilder;

/**
 * Abstract service to keep a reference to {@link SocietyBeanBuilder},
 * {@link CustomerBeanBuilder} and {@link ProductBeanBuilder}.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public abstract class AbstractService {

    private SocietyBeanBuilder societyBeanBuilder;
    private CustomerBeanBuilder customerBeanBuilder;
    private ProductBeanBuilder productBeanBuilder;

    /**
     * @return the societyBeanBuilder
     */
    public SocietyBeanBuilder getSocietyBeanBuilder() {
        return societyBeanBuilder;
    }

    /**
     * @param societyBeanBuilder the societyBeanBuilder to set
     */
    public void setSocietyBeanBuilder(SocietyBeanBuilder societyBeanBuilder) {
        this.societyBeanBuilder = societyBeanBuilder;
    }

    /**
     * @return the customerBeanBuilder
     */
    public CustomerBeanBuilder getCustomerBeanBuilder() {
        return customerBeanBuilder;
    }

    /**
     * @param customerBeanBuilder the customerBeanBuilder to set
     */
    public void setCustomerBeanBuilder(CustomerBeanBuilder customerBeanBuilder) {
        this.customerBeanBuilder = customerBeanBuilder;
    }

    /**
     * @return the productBeanBuilder
     */
    public ProductBeanBuilder getProductBeanBuilder() {
        return productBeanBuilder;
    }

    /**
     * @param productBeanBuilder the productBeanBuilder to set
     */
    public void setProductBeanBuilder(ProductBeanBuilder productBeanBuilder) {
        this.productBeanBuilder = productBeanBuilder;
    }

}
