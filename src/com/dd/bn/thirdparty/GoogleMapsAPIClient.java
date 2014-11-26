package com.dd.bn.thirdparty;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;

import java.util.Properties;
import javax.ws.rs.core.UriBuilder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;

import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;




public class GoogleMapsAPIClient {

	public static Properties props;
	public final static String PROPERTY_FILE_NAME="googlemapsapi.properties";
	public final static String MAPS_API_URI	=	"apiURI";
	public final static String MAPS_API_KEY	=	"apiKey";
	public static String uri;
	private static String apiKey;
	public final static String ADDRESS		=	"address";
	public final static String API_KEY		=	"key";
	public final static String	LATITUDE	=	"/GeocodeResponse/result/geometry/location/lat";
	public final static String	LONGITUDE	=	"/GeocodeResponse/result/geometry/location/lng";
	static{
		try {
			loadProperties();
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static double[] getGeoCoordinates(String address) throws Exception{
		URI	uriObject	=	UriBuilder.fromUri(uri).queryParam(ADDRESS, address).queryParam(API_KEY, apiKey).build();
		System.out.println("URI::"+uriObject.toString());
		ClientRequest clientRequest = new ClientRequest(uriObject.toString());
        ClientResponse<String> response= clientRequest.get(String.class);
        
		
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
		}
		 
		String output = response.getEntity();
		System.out.println(output);
		return parseXML(output);
		
		
	}
	public static double[] parseXML(String apiResponse) throws Exception{
		 
		double []latlong	=	new double[2];
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
	    DocumentBuilder builder;  
	    String	xmlOutput	=	"";
	    try  
	    {  
	        builder = factory.newDocumentBuilder();  
	        Document document = builder.parse( new InputSource( new StringReader( apiResponse ) ) );  
	        XPathFactory	xFactory	=	XPathFactory.newInstance();
	        XPath	xpath	=	xFactory.newXPath();
	        XPathExpression expr = xpath.compile(LATITUDE);
	        xmlOutput	=	expr.evaluate(document);
	        latlong[0]	=	Double.valueOf(xmlOutput);
	        expr = xpath.compile(LONGITUDE);
	        xmlOutput	=	expr.evaluate(document);
	        latlong[1]	=	Double.valueOf(xmlOutput);
	    } catch (Exception e) {  
	        e.printStackTrace();  
	        throw e;
	    }
		return latlong; 
	}
	private static void loadProperties() throws FileNotFoundException, IOException {
		props	=	new Properties();
		try{
			props.load(new FileInputStream(PROPERTY_FILE_NAME));
			uri	=	props.getProperty(MAPS_API_URI);
			apiKey	=	props.getProperty(MAPS_API_KEY);
		}catch(Exception e){
			uri = "https://maps.googleapis.com/maps/api/geocode/xml";
			apiKey="AIzaSyBElpSMZ__9M4PC8V-lHxOcIPIVo2qz_Zc";
		}
	}
	public static void main(String x[]){
		
		try {
			double[]	latlong	=	GoogleMapsAPIClient	.getGeoCoordinates("1600 Amphitheatre Parkway MountainView CA");
			System.out.println("Total distance::"+latlong[0]+"::"+latlong[1]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
