package be.jsams.server.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Contact information entity object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@Entity
@Table(name = "CONTACT_INFORMATION")
public class ContactInformation extends AbstractIdentity {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -893878683374179228L;
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
     * 
     * @return the phone
     */
    @Column(name = "PHONE")
    public String getPhone() {
        return phone;
    }

    /**
     * 
     * @param phone
     *            the phone to set
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
     * @param fax
     *            the fax to set
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
     * @param mobile
     *            the mobile to set
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
     * @param email
     *            the email to set
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
     * @param website
     *            the web-site to set
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

    @Override
    public String toString() {
        return "ContactInformation [email=" + email + ", fax=" + fax + ", logo=" + Arrays.toString(logo) + ", mobile="
                + mobile + ", phone=" + phone + ", website=" + website + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((fax == null) ? 0 : fax.hashCode());
        result = prime * result + Arrays.hashCode(logo);
        result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
        result = prime * result + ((phone == null) ? 0 : phone.hashCode());
        result = prime * result + ((website == null) ? 0 : website.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        // if (!super.equals(obj)) {
        // return false;
        // }
        if (!(obj instanceof ContactInformation)) {
            return false;
        }
        ContactInformation other = (ContactInformation) obj;
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
        if (!Arrays.equals(logo, other.logo)) {
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
