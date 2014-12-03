package com.dd.bn.resources;


import java.util.Map;

import javax.ws.rs.Consumes;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import com.dd.bn.helper.BloodNetworkHelper;

import com.dd.bn.pojo.common.BNContactRequest;
import com.dd.bn.pojo.common.BNContactsBloodGroupsResponse;
import com.dd.bn.pojo.common.BNContactsBloodGroupsResponseMessage;
import com.dd.bn.pojo.common.BaseMessage;

@Path("contacts")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class ContactsResource {

	
	@POST
	@Path("bloodgroups")
	public Response getBloodGroupsForContacts(BNContactRequest	contactsRequest){
	
		ResponseBuilder builder	=	null;
		BNContactsBloodGroupsResponseMessage	contactsResponse	=	createDummyContactsResponse();
		BloodNetworkHelper bloodNetworkHelper;
		
		System.out.println("Contacts Request:"+contactsRequest);
		
		try {
			bloodNetworkHelper = new BloodNetworkHelper();
			
			if(contactsRequest.getPhoneNumbersList()!=null	&&	 contactsRequest.getPhoneNumbersList().size()>0){
				Map<String, String>	contactsToBloodGroupMapping	=	bloodNetworkHelper.getBloodGroupsForContacts(contactsRequest.getPhoneNumbersList());
				contactsResponse.getPayload().setResponse(contactsToBloodGroupMapping);
				contactsResponse.getBaseMessage().setReturnCode(Status.OK.getStatusCode());
				contactsResponse.getBaseMessage().setReturnCodeDescription("OK");
			}
			else{
				throw new Exception("Payload Empty, no phone number data requested");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			contactsResponse.getBaseMessage().setReturnCode(Status.BAD_REQUEST.getStatusCode());
			contactsResponse.getBaseMessage().setReturnCodeDescription(e1.getMessage());
		}
		builder	=	Response.ok(contactsResponse);
		builder.entity(contactsResponse);
		return builder.build();
		
	}
	private BNContactsBloodGroupsResponseMessage createDummyContactsResponse() {
		BNContactsBloodGroupsResponseMessage	response	=	new BNContactsBloodGroupsResponseMessage();
		BNContactsBloodGroupsResponse	payload	=	new BNContactsBloodGroupsResponse();
		BaseMessage	baseMessage	=	new BaseMessage();
		response.setBaseMessage(baseMessage);
		response.setPayload(payload);
		return response;	
	}
}

