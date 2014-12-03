package com.dd.bn.pojo.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="BNContactsBloodGroupsResponseMessage", propOrder={"baseMessage","payload"})
public class BNContactsBloodGroupsResponseMessage {
	
	@XmlElement(required=true)
	private BaseMessage	baseMessage;
	@XmlElement(required=true)	
	private BNContactsBloodGroupsResponse payload;
	
	public BaseMessage getBaseMessage() {
		return baseMessage;
	}
	public void setBaseMessage(BaseMessage baseMessage) {
		this.baseMessage = baseMessage;
	}
	public BNContactsBloodGroupsResponse getPayload() {
		return payload;
	}
	public void setPayload(BNContactsBloodGroupsResponse payload) {
		this.payload = payload;
	}
}
