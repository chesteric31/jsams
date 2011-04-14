package be.jsams.common.bean.model.management;

import java.util.List;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.common.bean.builder.LegalFormBeanBuilder;
import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.model.AbstractNamedIdentityBean;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.ContactInformationBean;
import be.jsams.common.bean.model.LegalFormBean;
import be.jsams.common.bean.view.SocietyBeanView;
import be.jsams.server.dao.LegalFormDao;
import be.jsams.server.model.LegalForm;
import be.jsams.server.model.Society;

import com.jgoodies.common.collect.ArrayListModel;

/**
 * Bean model for {@link Society} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class SocietyBean extends AbstractNamedIdentityBean<Society, SocietyBeanView> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 7260186195157771763L;

    private Double capital;
    private String activity;
    private String responsible;
    private String vatNumber;

    private AddressBean address;
    private LegalFormBean legalForm;
    private ContactInformationBean contactInformation;

    private LegalFormBeanBuilder legalFormBuilder;

    public static final String CAPITAL_PROPERTY = "capital";
    public static final String ACTIVITY_PROPERTY = "activity";
    public static final String RESPONSIBLE_PROPERTY = "responsible";
    public static final String VATNUMBER_PROPERTY = "vatNumber";

    private static ArrayListModel<SocietyBean> list = new ArrayListModel<SocietyBean>();

    /**
     * Default constructor
     */
    public SocietyBean() {
        super();
        address = new AddressBean();
        
        legalFormBuilder = new LegalFormBeanBuilder();
        legalFormBuilder.setDao(JsamsApplicationContext.getLegalFormDao());
        setLegalForm(legalFormBuilder.build());
        
        contactInformation = new ContactInformationBean();
        initList();
    }

    /**
     * Initializes the ListModel and the eventually the selection element.
     */
    private void initList() {
        List<SocietyBean> beans = JsamsApplicationContext.getSocietyService().findAll();
        list.clear();
        list.addAll(beans);
        setListModel(list);
        if (!list.isEmpty()) {
            setSelection(list.get(0));
        }
    }

    /**
     * Constructor.
     * 
     * @param model
     *            the {@link Society}
     */
    public SocietyBean(Society model) {
        super(model);
        setActivity(model.getActivity());
        setAddress(new AddressBean(model.getAddress()));
        setCapital(model.getCapital());
        setContactInformation(new ContactInformationBean(model.getContactInformation()));
        
        legalFormBuilder = new LegalFormBeanBuilder();
        legalFormBuilder.setDao(getLegalFormDao());
        LegalForm form = model.getLegalForm();
        if (form != null) {
            legalFormBuilder.setModel(form);
        }
        
        setLegalForm(legalFormBuilder.build());
        setResponsible(model.getResponsible());
        setVatNumber(model.getVatNumber());
        setListModel(list);
        setSelection(this);
    }

    /**
     * @return the capital
     */
    public Double getCapital() {
        return capital;
    }

    /**
     * @param capital
     *            the capital to set
     */
    public void setCapital(Double capital) {
        Double oldValue = this.capital;
        this.capital = capital;
        firePropertyChange(CAPITAL_PROPERTY, oldValue, this.capital);
    }

    /**
     * @return the activity
     */
    public String getActivity() {
        return activity;
    }

    /**
     * @param activity
     *            the activity to set
     */
    public void setActivity(String activity) {
        String oldValue = this.activity;
        this.activity = activity;
        firePropertyChange(ACTIVITY_PROPERTY, oldValue, this.activity);
    }

    /**
     * @return the responsible
     */
    public String getResponsible() {
        return responsible;
    }

    /**
     * @param responsible
     *            the responsible to set
     */
    public void setResponsible(String responsible) {
        String oldValue = this.responsible;
        this.responsible = responsible;
        firePropertyChange(RESPONSIBLE_PROPERTY, oldValue, this.responsible);
    }

    /**
     * @return the vatNumber
     */
    public String getVatNumber() {
        return vatNumber;
    }

    /**
     * @param vatNumber
     *            the vatNumber to set
     */
    public void setVatNumber(String vatNumber) {
        String oldValue = this.vatNumber;
        this.vatNumber = vatNumber;
        firePropertyChange(VATNUMBER_PROPERTY, oldValue, this.vatNumber);
    }

    /**
     * @return the address
     */
    public AddressBean getAddress() {
        return address;
    }

    /**
     * @param address
     *            the address to set
     */
    public void setAddress(AddressBean address) {
        this.address = address;
    }

    /**
     * @return the legalForm
     */
    public LegalFormBean getLegalForm() {
        return this.legalForm;
    }

    /**
     * @param legalForm
     *            the legalForm to set
     */
    public void setLegalForm(LegalFormBean legalForm) {
         this.legalForm = legalForm;
    }

    /**
     * @return the contactInformation
     */
    public ContactInformationBean getContactInformation() {
        return contactInformation;
    }

    /**
     * @param contactInformation
     *            the contactInformation to set
     */
    public void setContactInformation(ContactInformationBean contactInformation) {
        this.contactInformation = contactInformation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SocietyBeanView getView() {
        return new SocietyBeanView(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "SocietyBean [activity=" + activity + ", address=" + address + ", capital=" + capital
                + ", contactInformation=" + contactInformation + ", legalForm=" + legalForm + ", responsible="
                + responsible + ", vatNumber=" + vatNumber + "]";
    }

    /**
     * {@inheritDoc}
     */
    public void clear() {
        super.clear();
        setActivity(null);
        address.clear();
        setCapital(null);
        contactInformation.clear();
        legalForm.clear();
        setResponsible(null);
        setVatNumber(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh(AbstractIdentityBean<?, ?> bean) {
        super.refresh(bean);
        SocietyBean other = (SocietyBean) bean;
        setActivity(other.getActivity());
        address.refresh(other.getAddress());
        setCapital(other.getCapital());
        contactInformation.refresh(other.getContactInformation());
        legalForm.refresh(other.getLegalForm());
        setListModel(other.getListModel());
        setResponsible(other.getResponsible());
        setSelection(other.getSelection());
        setVatNumber(other.getVatNumber());
        setId(other.getId());
    }

    /**
     * 
     * @return the {@link LegalFormDao} necessary for test for now
     */
    public LegalFormDao getLegalFormDao() {
        return JsamsApplicationContext.getLegalFormDao();
    }

}
