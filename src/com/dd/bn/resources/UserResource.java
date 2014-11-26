package com.dd.bn.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;



import com.dd.bn.helper.BloodNetworkRegistrationHelper;
import com.dd.bn.pojo.common.BNCreateResponse;
import com.dd.bn.pojo.common.BNCreateResponseMessage;
import com.dd.bn.pojo.common.BNUserResponseMessage;
import com.dd.bn.pojo.common.BaseMessage;
import com.dd.bn.pojo.common.BloodNetworkUser;
import static com.dd.bn.constants.BloodNetworkConstants.PHONE_NUMBER;

@Path("/user")
@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public class UserResource {
	
	@POST
	@Path("/register")
	public Response createUser(BloodNetworkUser	bloodNetworkUser){
		
		System.out.println("Sunil Request:"+bloodNetworkUser);
		ResponseBuilder builder	=	null;
		BNCreateResponseMessage	response	=	createDummyCreateResponse();
		BloodNetworkRegistrationHelper bloodNetworkHelper;
		boolean success = false;
		try {
			bloodNetworkHelper = new BloodNetworkRegistrationHelper();
			success = bloodNetworkHelper.createUser(bloodNetworkUser);
			response.getBaseMessage().setReturnCode(Status.OK.getStatusCode());
			response.getBaseMessage().setReturnCodeDescription("OK");
		} catch (Exception e1) {
			e1.printStackTrace();
			response.getBaseMessage().setReturnCode(Status.BAD_REQUEST.getStatusCode());
			response.getBaseMessage().setReturnCodeDescription(e1.getMessage());
		}
		response.getPayload().setResponse(String.valueOf(success));
		builder	=	Response.ok(response);
		builder.entity(response);
		return builder.build();
		
	}
	
	@GET
	public Response getUser(@QueryParam(PHONE_NUMBER) String phoneNumber ){
		System.out.println("Sunil getUser:"+phoneNumber);
		ResponseBuilder builder	=	null;
		BNUserResponseMessage	response	=	createDummyUserResponse();
		BloodNetworkRegistrationHelper bloodNetworkHelper;
		BloodNetworkUser	bloodNetworkUser	=	null;
		try {
			bloodNetworkHelper = new BloodNetworkRegistrationHelper();
			bloodNetworkUser	=	bloodNetworkHelper.getUserInformation(phoneNumber);
			response.getBaseMessage().setReturnCode(Status.OK.getStatusCode());
			response.getBaseMessage().setReturnCodeDescription("OK");
			response.setPayload(bloodNetworkUser);
		}catch (Exception e1) {
			e1.printStackTrace();
			response.getBaseMessage().setReturnCode(Status.BAD_REQUEST.getStatusCode());
			response.getBaseMessage().setReturnCodeDescription("User Not found");
		}
		builder	=	Response.ok(response);
		builder.entity(response);
		return builder.build();
		
	}
	private BNCreateResponseMessage createDummyCreateResponse() {
		BNCreateResponseMessage	response	=	new BNCreateResponseMessage();
		BNCreateResponse	payload	=	new BNCreateResponse();
		payload.setResponse("success");
		BaseMessage	baseMessage	=	new BaseMessage();
		response.setBaseMessage(baseMessage);
		response.setPayload(payload);
		return response;
	}
	private BNUserResponseMessage createDummyUserResponse() {
		BNUserResponseMessage	response	=	new BNUserResponseMessage();
		BloodNetworkUser	payload	=	new BloodNetworkUser();
		BaseMessage	baseMessage	=	new BaseMessage();
		response.setBaseMessage(baseMessage);
		response.setPayload(payload);
		return response;
	}
	@GET
	@Path("/test")
	public Response getTestInfo(){
		String text	=	"Hello JAX RS";
		BNCreateResponseMessage	response	=	new BNCreateResponseMessage();
		BNCreateResponse	payload	=	new BNCreateResponse();
		payload.setResponse(text);
		response.setPayload(payload);
		System.out.println("Hello JAX RS");
		
		ResponseBuilder builder	=	Response.ok(response);
		builder.entity(response);
		return builder.build();
	}
	
}
