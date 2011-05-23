package be.jsams.server.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import be.jsams.common.bean.model.ContactInformationBean;

/**
 * Contact information entity object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@Entity
@Table(name = "CONTACT_INFORMATION")
public class ContactInformation extends AbstractIdentity {

    private String phone;
    private String fax;
    private String mobile;
    private String email;
    private String website;
    private byte[] logo;

    /**
     * Constructor.
     */
    public ContactInformation() {
        super();
    }

    /**
     * Constructor
     * 
     * @param bean the {@link ContactInformationBean}
     */
    public ContactInformation(ContactInformationBean bean) {
        super(bean);
        this.email = bean.getEmail();
        this.fax = bean.getFax();
        // TODO
        // this.logo = bean.getLogo();
        this.mobile = bean.getMobile();
        this.phone = bean.getPhone();
        this.website = bean.getWebsite();
    }

    /**
     * 
     * @return the phone
     */
    @Column(name = "PHONE")
    public String getPhone() {
        return phone;
    }

    /**
     * 
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 
     * @return the fax
     */
    @Column(name = "FAX")
    public String getFax() {
        return fax;
    }

    /**
     * 
     * @param fax the fax to set
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * 
     * @return the mobile
     */
    @Column(name = "MOBILE")
    public String getMobile() {
        return mobile;
    }

    /**
     * 
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 
     * @return the email
     */
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    /**
     * 
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     * @return the web-site
     */
    @Column(name = "WEBSITE")
    public String getWebsite() {
        return website;
    }

    /**
     * 
     * @param website the web-site to set
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * 
     * @return the logo
     */
    @Column(name = "LOGO")
    public byte[] getLogo() {
        return logo;
    }

    /**
     * 
     * @param logo the logo to set
     */
    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ContactInformation [email=");
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

}
