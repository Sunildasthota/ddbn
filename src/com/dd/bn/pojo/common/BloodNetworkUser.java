package com.dd.bn.pojo.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="BloodNetworkUser", 
propOrder=
{	"phoneNumber",
	"firstName",
	"lastName",
	"address",
	"rating",
	"latitude",
	"longitude",
	"bloodGroup",
	"email"})
public class BloodNetworkUser {

	private String phoneNumber;
	private String firstName;
	private String lastName;
	private String address;
	private String rating;
	private Double latitude;
	private Double longitude;
	private String bloodGroup;
	private String email;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	@Override
	public String toString(){
		StringBuffer sb	=	new StringBuffer();
		sb.append("Phone number:"+phoneNumber+"\n");
		sb.append("First name:"+firstName+"\n");
		sb.append("Last Name:"+lastName+"\n");
		sb.append("Address:"+address+"\n");
		sb.append("Rating:"+rating+"\n");
		sb.append("Latitude:"+latitude+"\n");
		sb.append("Longitude:"+longitude+"\n");
		sb.append("Blood Group:"+bloodGroup+"\n");
		sb.append("Email:"+email+"\n");
		return sb.toString();
	}
}
