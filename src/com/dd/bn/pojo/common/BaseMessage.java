package com.dd.bn.pojo.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;




@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="BaseMessage", propOrder={"returnCode","returnCodeDescription"})
public class BaseMessage {
	
	private Integer returnCode;
	private String returnCodeDescription;
	public Integer getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}
	public String getReturnCodeDescription() {
		return returnCodeDescription;
	}
	public void setReturnCodeDescription(String returnCodeDescription) {
		this.returnCodeDescription = returnCodeDescription;
	}
	
}
