package com.dd.bn.pojo.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="BNCreateResponseMessage", propOrder={"baseMessage","payload"})
public class BNCreateResponseMessage {
	
	@XmlElement(required=true)
	private BaseMessage	baseMessage;
	@XmlElement(required=true)	
	private BNCreateResponse payload;
	
	public BaseMessage getBaseMessage() {
		return baseMessage;
	}
	public void setBaseMessage(BaseMessage baseMessage) {
		this.baseMessage = baseMessage;
	}
	public BNCreateResponse getPayload() {
		return payload;
	}
	public void setPayload(BNCreateResponse payload) {
		this.payload = payload;
	}
}
