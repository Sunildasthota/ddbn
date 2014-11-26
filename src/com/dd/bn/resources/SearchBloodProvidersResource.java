package com.dd.bn.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import com.dd.bn.helper.BloodNetworkRegistrationHelper;

import com.dd.bn.pojo.common.BNSearchResponse;
import com.dd.bn.pojo.common.BNSearchResponseMessage;
import com.dd.bn.pojo.common.BaseMessage;
import com.dd.bn.pojo.common.BloodNetworkUser;
import static com.dd.bn.constants.BloodNetworkConstants.PHONE_NUMBER;

@Path("search")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class SearchBloodProvidersResource {

	
	@GET
	public Response searchUsersForBloodType(@QueryParam(PHONE_NUMBER) String phoneNumber ){
	
		ResponseBuilder builder	=	null;
		BNSearchResponseMessage	searchResponse	=	createDummySearchResponse();
		BloodNetworkRegistrationHelper bloodNetworkHelper;
		
		try {
			bloodNetworkHelper = new BloodNetworkRegistrationHelper();
			List<BloodNetworkUser>	nearByUsers	=	bloodNetworkHelper.getNearByUsers(phoneNumber);
			if(nearByUsers!=null	&&	 nearByUsers.size()>0){
				searchResponse.getPayload().setResponse(nearByUsers);
				searchResponse.getBaseMessage().setReturnCode(Status.OK.getStatusCode());
				searchResponse.getBaseMessage().setReturnCodeDescription("OK");
			}
			else{
				throw new Exception("No nearby users found");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			searchResponse.getBaseMessage().setReturnCode(Status.BAD_REQUEST.getStatusCode());
			searchResponse.getBaseMessage().setReturnCodeDescription(e1.getMessage());
		}
		builder	=	Response.ok(searchResponse);
		builder.entity(searchResponse);
		return builder.build();
		
	}
	private BNSearchResponseMessage createDummySearchResponse() {
		BNSearchResponseMessage	response	=	new BNSearchResponseMessage();
		BNSearchResponse	payload	=	new BNSearchResponse();
		BaseMessage	baseMessage	=	new BaseMessage();
		response.setBaseMessage(baseMessage);
		response.setPayload(payload);
		return response;	
	}
}

