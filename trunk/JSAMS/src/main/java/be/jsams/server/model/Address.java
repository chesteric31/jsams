package be.jsams.server.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Address information embed object.
 * 
 * 
 * @author chesteric31
 * @version $Revision:$ $Date:$ $Author:$
 */
@Embeddable
public class Address {

	private String street;
	private String number;
	private Integer zipCode;
	private String box;
	private String city;
	private String country;

	public Address() {
		super();
	}

	@Column(name = "STREET")
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@Column(name = "NUMBER")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Column(name = "ZIP_CODE")
	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}

	@Column(name = "BOX")
	public String getBox() {
		return box;
	}

	public void setBox(String box) {
		this.box = box;
	}

	@Column(name = "CITY")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "COUNTRY")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String toString() {
		return street + " " + number + " " + zipCode + " " + box + " " + city
				+ " " + country;
	}

}
