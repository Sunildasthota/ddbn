package com.dd.bn.pojo.common;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="BNContactRequest", 
propOrder=
{	"phoneNumbersList"})
public class BNContactRequest {

	private List<String> phoneNumbersList;
	
	public List<String> getPhoneNumbersList() {
		return phoneNumbersList;
	}

	public void setPhoneNumbersList(List<String> phoneNumbersList) {
		this.phoneNumbersList = phoneNumbersList;
	}

	@Override
	public String toString(){
		StringBuffer sb	=	new StringBuffer();
		sb.append("Phone numbers list:"+phoneNumbersList+"\n");
		return sb.toString();
	}
}
