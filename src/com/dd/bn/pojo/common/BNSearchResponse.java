package com.dd.bn.pojo.common;


import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="BNSearchResponse")
public class BNSearchResponse {
	
	public List<BloodNetworkUser> getResponse() {
		return searchResponse;
	}

	public void setResponse(List<BloodNetworkUser> response) {
		this.searchResponse = response;
	}

	private List<BloodNetworkUser> searchResponse;

	
}
