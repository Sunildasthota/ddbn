package com.dd.bn.mongodb;


import com.dd.bn.exceptions.UserFoundException;
import com.dd.bn.pojo.common.BloodNetworkUser;
import com.mongodb.BasicDBList;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCursor;


import com.mongodb.DBCollection;

import com.mongodb.DBObject;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import java.util.ArrayList;
import java.util.List;
import static com.dd.bn.constants.BloodNetworkConstants.BLOOD_NETWORK_COLLECTION;
import static com.dd.bn.constants.BloodNetworkConstants.BLOOD_NETWORK_DB;
import static com.dd.bn.constants.BloodNetworkConstants.TYPE;
import static com.dd.bn.constants.BloodNetworkConstants.COORDINATES;
import static com.dd.bn.constants.BloodNetworkConstants.RATING;
import static com.dd.bn.constants.BloodNetworkConstants.ADDRESS;
import static com.dd.bn.constants.BloodNetworkConstants.BLOOD_GROUP;
import static com.dd.bn.constants.BloodNetworkConstants.EMAIL;
import static com.dd.bn.constants.BloodNetworkConstants.FIRST_NAME;
import static com.dd.bn.constants.BloodNetworkConstants.INITIAL_BLOOD_RATING;
import static com.dd.bn.constants.BloodNetworkConstants.LAST_NAME;

import static com.dd.bn.constants.BloodNetworkConstants.LOCATION;
import static com.dd.bn.constants.BloodNetworkConstants.PHONE_NUMBER;



/**
 * An example of how to use a geospacial index.
 */
public final class MongoDBInstance {

       
    private MongoClient	mongoClient;
    private DB mongoDb;
    public MongoDBInstance() throws Exception {
        
    	mongoClient	=	new MongoClient();
        mongoDb	=	mongoClient.getDB(BLOOD_NETWORK_DB);
        getCollection().createIndex(new BasicDBObject(LOCATION, "2dsphere"));
    }
    
    
       
    public List<BloodNetworkUser> getNearByUsers(BasicDBList centerOfTheCircle, double radius, String phoneNumber, String bloodGroup) {

    	List<BloodNetworkUser>	nearByUsers	=	new ArrayList<BloodNetworkUser>();
    	BloodNetworkUser	bloodNetworkUser;
    	BasicDBObject	query	=	new BasicDBObject(LOCATION,
								    			new BasicDBObject("$near",
								    					new BasicDBObject("$geometry",
								    							new BasicDBObject(TYPE, "Point")
								                            	.append(COORDINATES, centerOfTheCircle))
								                     	.append("$maxDistance",  radius)
								    					)
								    			);
    	//remove the person who is requesting it
    	query.append(PHONE_NUMBER,new BasicDBObject("$ne",phoneNumber));
    	//add blood group
    	query.append(BLOOD_GROUP,bloodGroup);
    	
    	DBCursor	results	=	getCollection().find(query);
    	for(DBObject	nearestPerson	:	results){
    		bloodNetworkUser	=	populateObjectFromMongoDB(nearestPerson);
    		nearByUsers.add(bloodNetworkUser);
    	}
    	System.out.println("Near By users:"+nearByUsers);
    	return nearByUsers;
    }
    
    private BloodNetworkUser populateObjectFromMongoDB(DBObject nearestPerson) {
    	BloodNetworkUser	bloodNetworkUser	=	new BloodNetworkUser();
    	bloodNetworkUser.setAddress(nearestPerson.get(ADDRESS).toString());
    	bloodNetworkUser.setBloodGroup(nearestPerson.get(BLOOD_GROUP).toString());
    	bloodNetworkUser.setEmail(nearestPerson.get(EMAIL).toString());
    	bloodNetworkUser.setFirstName(nearestPerson.get(FIRST_NAME).toString());
    	bloodNetworkUser.setLastName(nearestPerson.get(LAST_NAME).toString());
    	BasicDBObject	location	=	(BasicDBObject)nearestPerson.get(LOCATION);
    	BasicDBList		coordinates	=	(BasicDBList)location.get(COORDINATES);
    	bloodNetworkUser.setLatitude(Double.valueOf(coordinates.get(0).toString()));
    	bloodNetworkUser.setLongitude(Double.valueOf(coordinates.get(1).toString()));
    	bloodNetworkUser.setPhoneNumber(nearestPerson.get(PHONE_NUMBER).toString());
    	bloodNetworkUser.setRating(nearestPerson.get(RATING).toString());
		return bloodNetworkUser;
	}

    public BloodNetworkUser	getUserInformation(String phoneNumber){
    	DBObject	bloodNetworkUser	=	getUser(phoneNumber);
    	return populateObjectFromMongoDB(bloodNetworkUser);
    }
	public DBObject getUser(String phoneNumber) {
    	DBObject	myDoc = getCollection().findOne(new BasicDBObject(PHONE_NUMBER,phoneNumber));
    	return myDoc;
    }

    private DBCollection getCollection() {
        return mongoDb.getCollection(BLOOD_NETWORK_COLLECTION);
    }
    public boolean insertUser(BloodNetworkUser user) throws UserFoundException{
    	BasicDBList coordinates = new BasicDBList();
    	coordinates.put(0, user.getLatitude());
    	coordinates.put(1, user.getLongitude());
    	
    	if(userIsAlreadyPresent(user)){
    		throw new UserFoundException("user found already");
    	}
    		getCollection().insert(new BasicDBObject(FIRST_NAME,user.getFirstName())
    								.append(LAST_NAME, user.getLastName())
    								.append(PHONE_NUMBER, user.getPhoneNumber())
    								.append(LOCATION, new BasicDBObject(TYPE,"Point").append(COORDINATES, coordinates))
    								.append(ADDRESS, user.getAddress())
    								.append(BLOOD_GROUP, user.getBloodGroup())
    								.append(EMAIL, user.getEmail())
    								.append(RATING, INITIAL_BLOOD_RATING));
    	
    	
    	System.out.println("Das"+getCollection());
    	for(DBObject	object:getCollection().find().toArray()){
    		System.out.println(object+" Sunil");
    	}
    	return true;
    }


	private boolean userIsAlreadyPresent(BloodNetworkUser user) {
		final BasicDBObject query= new BasicDBObject(PHONE_NUMBER, user.getPhoneNumber());
		Cursor	cursor	=	getCollection().find(query);
		while(cursor.hasNext()){
			return true;
		}
		return false;
	}
}
