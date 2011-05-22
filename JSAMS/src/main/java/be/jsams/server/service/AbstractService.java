package be.jsams.server.service;

import be.jsams.common.bean.builder.SocietyBeanBuilder;

/**
 * Abstract service to keep a reference to {@link SocietyBeanBuilder}.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public abstract class AbstractService {

    private SocietyBeanBuilder societyBeanBuilder;

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

}
