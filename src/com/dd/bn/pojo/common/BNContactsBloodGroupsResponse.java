package com.dd.bn.pojo.common;



import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="BNContactsBloodGroupsResponse")
public class BNContactsBloodGroupsResponse {
	
	public Map<String, String> getResponse() {
		return searchResponse;
	}

	public void setResponse(Map<String, String> response) {
		this.searchResponse = response;
	}

	private Map<String, String> searchResponse;

	
}
