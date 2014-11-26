package com.dd.bn.helper;



import java.util.List;

import com.dd.bn.constants.BloodNetworkConstants;
import com.dd.bn.mongodb.MongoDBInstance;
import com.dd.bn.pojo.common.BloodNetworkUser;
import com.dd.bn.thirdparty.GoogleMapsAPIClient;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class BloodNetworkRegistrationHelper {

	private MongoDBInstance dbinstance;
	public BloodNetworkRegistrationHelper() throws Exception{
		dbinstance	=	new MongoDBInstance();
	}
	public boolean createUser(BloodNetworkUser bloodNetworkUser) throws Exception {
		
		if(bloodNetworkUser.getLatitude()	==	null	
				||	bloodNetworkUser.getLongitude()	==	null){
			double []	latlong	=	GoogleMapsAPIClient.getGeoCoordinates(bloodNetworkUser.getAddress());
			bloodNetworkUser.setLatitude(latlong[0]);
			bloodNetworkUser.setLongitude(latlong[1]);
			
			System.out.println("Latitude and longitude conversion:"+latlong[0]+"::"+latlong[1]);
		}
		dbinstance.insertUser(bloodNetworkUser);
		return true;
	}
	public List<BloodNetworkUser> getNearByUsers(String phoneNumber){
		DBObject	user	=	dbinstance.getUser(phoneNumber);
		BasicDBObject	locationOfUser	=	(BasicDBObject)user.get(BloodNetworkConstants.LOCATION);
    	BasicDBList	userCoordinates			=	(BasicDBList)locationOfUser.get("coordinates");
    	String bloodGroup	=	user.get(BloodNetworkConstants.BLOOD_GROUP).toString();
    	System.out.println("Blood group:"+bloodGroup);
		System.out.println("User cooordinates:"+userCoordinates.get(0));
		System.out.println("User cooordinates:"+userCoordinates.get(1));
		return dbinstance.getNearByUsers(userCoordinates, 20000, phoneNumber, bloodGroup);
	}
	public BloodNetworkUser getUserInformation(String phoneNumber){
		return dbinstance.getUserInformation(phoneNumber);
	}
	public static void main(String x[]) throws Exception{

		BloodNetworkRegistrationHelper	helper =	new BloodNetworkRegistrationHelper();
//		
//		BloodNetworkUser	user	=	new BloodNetworkUser();
//		user.setAddress("Carnegie PA");
//		user.setBloodGroup("A+");
//		user.setEmail("sunildasthota@yahoo.com");
//		user.setFirstName("Pavan");
//		user.setLastName("Thota");
//		user.setPhoneNumber("4121547371");
//		helper.createUser(user);
//		System.out.println("Success");

		helper.getNearByUsers("3303284491");
	}
}
