package be.jsams.common.bean.model;

import java.util.Arrays;

import be.jsams.common.bean.view.ContactInformationBeanView;
import be.jsams.server.model.ContactInformation;

/**
 * Bean model for {@link ContactInformation} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class ContactInformationBean extends AbstractIdentityBean<ContactInformation, ContactInformationBeanView> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -2480229641229650242L;

    private String phone;
    private String fax;
    private String mobile;
    private String email;
    private String website;
    private byte[] logo;

    public static final String PHONE_PROPERTY = "phone";
    public static final String FAX_PROPERTY = "fax";
    public static final String MOBILE_PROPERTY = "mobile";
    public static final String EMAIL_PROPERTY = "email";
    public static final String WEBSITE_PROPERTY = "website";
    public static final String LOGO_PROPERTY = "logo";

    /**
     * Default constructor
     */
    public ContactInformationBean() {
        super();
    }

    /**
     * Constructor.
     * 
     * @param model the {@link ContactInformation}
     */
    public ContactInformationBean(ContactInformation model) {
        super(model);
        this.email = model.getEmail();
        this.fax = model.getFax();
        this.logo = model.getLogo();
        this.mobile = model.getMobile();
        this.phone = model.getPhone();
        this.website = model.getWebsite();
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        String oldValue = this.phone;
        this.phone = phone;
        firePropertyChange(PHONE_PROPERTY, oldValue, this.phone);
    }

    /**
     * @return the fax
     */
    public String getFax() {
        return fax;
    }

    /**
     * @param fax the fax to set
     */
    public void setFax(String fax) {
        String oldValue = this.fax;
        this.fax = fax;
        firePropertyChange(FAX_PROPERTY, oldValue, this.fax);
    }

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        String oldValue = this.mobile;
        this.mobile = mobile;
        firePropertyChange(MOBILE_PROPERTY, oldValue, this.mobile);
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        String oldValue = this.email;
        this.email = email;
        firePropertyChange(EMAIL_PROPERTY, oldValue, this.email);
    }

    /**
     * @return the website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * @param website the website to set
     */
    public void setWebsite(String website) {
        String oldValue = this.website;
        this.website = website;
        firePropertyChange(WEBSITE_PROPERTY, oldValue, this.website);
    }

    /**
     * @return the logo
     */
    public byte[] getLogo() {
        return logo;
    }

    /**
     * @param logo the logo to set
     */
    public void setLogo(byte[] logo) {
        byte[] oldValue = this.logo;
        this.logo = logo;
        firePropertyChange(LOGO_PROPERTY, oldValue, this.logo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ContactInformationBeanView getView() {
        return new ContactInformationBeanView(this);
    }

    /**
     * {@inheritDoc}
     */
    public void clear() {
        setEmail(null);
        setFax(null);
        setLogo(null);
        setMobile(null);
        setPhone(null);
        setWebsite(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ContactInformationBean [email=");
        builder.append(email);
        builder.append(", fax=");
        builder.append(fax);
        builder.append(", logo=");
        builder.append(Arrays.toString(logo));
        builder.append(", mobile=");
        builder.append(mobile);
        builder.append(", phone=");
        builder.append(phone);
        builder.append(", website=");
        builder.append(website);
        builder.append("]");
        return builder.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh(AbstractIdentityBean<?, ?> bean) {
        ContactInformationBean other = (ContactInformationBean) bean;
        setEmail(other.getEmail());
        setFax(other.getFax());
        setListModel(other.getListModel());
        setLogo(other.getLogo());
        setMobile(other.getMobile());
        setPhone(other.getPhone());
        setSelection(other.getSelection());
        setWebsite(other.getWebsite());
    }

}
