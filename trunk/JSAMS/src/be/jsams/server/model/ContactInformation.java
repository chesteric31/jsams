package be.jsams.server.model;

import javax.persistence.Embeddable;

@Embeddable
public class ContactInformation {

	private String phone;
	private String fax;
	private String gsm;
	private String email;
	private String website;

	public ContactInformation() {
		super();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getGsm() {
		return gsm;
	}

	public void setGsm(String gsm) {
		this.gsm = gsm;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String toString() {
		return phone + " " + fax + " " + gsm + " " + email + " " + website;
	}

}
