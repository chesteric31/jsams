package be.jsams.common.bean.model;

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

    public static final String PHONE_PROPERTY = "phone";
    public static final String FAX_PROPERTY = "fax";
    public static final String MOBILE_PROPERTY = "mobile";
    public static final String EMAIL_PROPERTY = "email";
    public static final String WEBSITE_PROPERTY = "website";

    /**
     * Default constructor
     */
    public ContactInformationBean() {
        super();
        setView(buildView());
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
        this.mobile = model.getMobile();
        this.phone = model.getPhone();
        this.website = model.getWebsite();
        setView(buildView());
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
     * {@inheritDoc}
     */
    @Override
    public ContactInformationBeanView buildView() {
        return new ContactInformationBeanView(this);
    }

    /**
     * {@inheritDoc}
     */
    public void clear() {
        setEmail(null);
        setFax(null);
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
        setMobile(other.getMobile());
        setPhone(other.getPhone());
        setSelection(other.getSelection());
        setWebsite(other.getWebsite());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result;
        if (email == null) {
            result += 0;
        } else {
            result += email.hashCode();
        }
        if (fax == null) {
            result += 0;
        } else {
            result += fax.hashCode();
        }
        if (mobile == null) {
            result += 0;
        } else {
            result += mobile.hashCode();
        }
        if (phone == null) {
            result += 0;
        } else {
            result += phone.hashCode();
        }
        if (website == null) {
            result += 0;
        } else {
            result += website.hashCode();
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof ContactInformationBean)) {
            return false;
        }
        ContactInformationBean other = (ContactInformationBean) obj;
        if (email == null) {
            if (other.email != null) {
                return false;
            }
        } else if (!email.equals(other.email)) {
            return false;
        }
        if (fax == null) {
            if (other.fax != null) {
                return false;
            }
        } else if (!fax.equals(other.fax)) {
            return false;
        }
        if (mobile == null) {
            if (other.mobile != null) {
                return false;
            }
        } else if (!mobile.equals(other.mobile)) {
            return false;
        }
        if (phone == null) {
            if (other.phone != null) {
                return false;
            }
        } else if (!phone.equals(other.phone)) {
            return false;
        }
        if (website == null) {
            if (other.website != null) {
                return false;
            }
        } else if (!website.equals(other.website)) {
            return false;
        }
        return true;
    }

}
