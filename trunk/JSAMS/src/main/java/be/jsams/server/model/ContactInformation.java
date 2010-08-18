package be.jsams.server.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Contact information embed object.
 * 
 * 
 * @author chesteric31
 * @version $Revision:$ $Date:$ $Author:$
 */
@Embeddable
public class ContactInformation {

	private String phone;
	private String fax;
	private String mobile;
	private String email;
	private String website;
	private byte logo;

	public ContactInformation() {
		super();
	}

	@Column(name = "PHONE")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "FAX")
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "MOBILE")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "WEBSITE")
	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Column(name = "LOGO")
	public byte getLogo() {
		return logo;
	}

	public void setLogo(byte logo) {
		this.logo = logo;
	}

	public String toString() {
		return phone + " " + fax + " " + mobile + " " + email + " " + website;
	}

}
