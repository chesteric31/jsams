package be.jsams.common.bean.builder;

import java.util.List;

import be.jsams.common.bean.model.PaymentModeBean;
import be.jsams.server.dao.PaymentModeDao;
import be.jsams.server.model.PaymentMode;

import com.jgoodies.common.collect.ArrayListModel;
import com.jgoodies.common.collect.ObservableList;

/**
 * Builder that makes a {@link PaymentModeBean} from DAO list model and entity model. 
 *
 * @author chesteric31
 * @version $$Rev$$ $$Date::                  $$ $$Author$$
 */
public class PaymentModeBeanBuilder {
    
    private PaymentMode model;
    
    private ObservableList<PaymentModeBean> listModel;
    
    private PaymentModeDao dao;

    /**
     * Build the {@link PaymentModeBean}.
     * 
     * @return the built {@link PaymentModeBean}
     */
    public PaymentModeBean build() {
        List<PaymentMode> modes = dao.findAll();
        listModel = new ArrayListModel<PaymentModeBean>();
        for (PaymentMode mode : modes) {
            listModel.add(new PaymentModeBean(mode));
        }
        if (model != null) {
            return new PaymentModeBean(listModel, model);
        } else {
            listModel.add(0, null);
            return new PaymentModeBean(listModel);
        }
    }

    /**
     * @return the model
     */
    public PaymentMode getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(PaymentMode model) {
        this.model = model;
    }

    /**
     * @return the listModel
     */
    public ObservableList<PaymentModeBean> getListModel() {
        return listModel;
    }

    /**
     * @param listModel the listModel to set
     */
    public void setListModel(ObservableList<PaymentModeBean> listModel) {
        this.listModel = listModel;
    }

    /**
     * @return the dao
     */
    public PaymentModeDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(PaymentModeDao dao) {
        this.dao = dao;
    }

}
