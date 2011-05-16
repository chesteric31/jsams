package be.jsams.common.bean.model;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.common.bean.builder.LegalFormBeanBuilder;
import be.jsams.common.bean.view.SocietyBeanView;
import be.jsams.server.dao.LegalFormDao;
import be.jsams.server.model.LegalForm;
import be.jsams.server.model.Society;

import com.jgoodies.common.collect.ObservableList;

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

    /**
     * Default constructor
     */
    public SocietyBean() {
        super();
        address = new AddressBean();

        legalFormBuilder = new LegalFormBeanBuilder();
        legalFormBuilder.setDao(getLegalFormDao());
        setLegalForm(legalFormBuilder.build());

        setContactInformation(new ContactInformationBean());
    }

    /**
     * Constructor
     * 
     * @param model the {@link Society}
     */
    public SocietyBean(Society model) {
        super(model);
        address = new AddressBean();

        legalFormBuilder = new LegalFormBeanBuilder();
        legalFormBuilder.setDao(getLegalFormDao());
        LegalForm form = model.getLegalForm();
        if (form != null) {
            legalFormBuilder.setModel(form);
        }
        setLegalForm(legalFormBuilder.build());

        setContactInformation(new ContactInformationBean(model.getContactInformation()));
        setActivity(model.getActivity());
        setAddress(new AddressBean(model.getAddress()));
        setCapital(model.getCapital());
        setResponsible(model.getResponsible());
        setVatNumber(model.getVatNumber());
    }

    /**
     * Constructor.
     * 
     * @param list the {@link ObservableList}
     * @param model the {@link Society} object
     */
    public SocietyBean(ObservableList<SocietyBean> list, Society model) {
        this(model);
        setListModel(list);
        setSelection(this);
    }

    /**
     * Constructor.
     * 
     * @param list the {@link ObservableList}
     */
    public SocietyBean(ObservableList<SocietyBean> list) {
        super();
        setListModel(list);
        if (list != null && !list.isEmpty()) {
            setSelection(list.get(0));
        }
    }

    /**
     * @return the capital
     */
    public Double getCapital() {
        return capital;
    }

    /**
     * @param capital the capital to set
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
     * @param activity the activity to set
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
     * @param responsible the responsible to set
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
     * @param vatNumber the vatNumber to set
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
     * @param address the address to set
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
     * @param legalForm the legalForm to set
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
     * @param contactInformation the contactInformation to set
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
        StringBuilder builder = new StringBuilder();
        builder.append("SocietyBean [activity=");
        builder.append(activity);
        builder.append(", address=");
        builder.append(address);
        builder.append(", capital=");
        builder.append(capital);
        builder.append(", contactInformation=");
        builder.append(contactInformation);
        builder.append(", legalForm=");
        builder.append(legalForm);
        builder.append(", legalFormBuilder=");
        builder.append(legalFormBuilder);
        builder.append(", responsible=");
        builder.append(responsible);
        builder.append(", vatNumber=");
        builder.append(vatNumber);
        builder.append("]");
        return builder.toString();
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
