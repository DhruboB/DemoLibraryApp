package com.msc.project.library.utility;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;



public class HttpPost {
	public void sendPostRequest(String requestUrl, JSONObject payload) {
	    try {
	    	URL url=new URL(requestUrl);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setAllowUserInteraction(true);
	        String userCredentials = HttpConnection.getProperties().getProperty("CLOUDANT_USER")+":"+HttpConnection.getProperties().getProperty("CLOUDANT_PASS");
			String basicAuth = "Basic " + new String(new Base64().encode(userCredentials.getBytes()));
			connection.setRequestProperty ("Authorization", basicAuth);
	        connection.setRequestMethod("POST");
	        connection.setRequestProperty("Accept", "application/json");
	        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
	        connection.setDoOutput(true);
			connection.setDoInput(true);
	        connection.connect();
	        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
	        System.out.println(payload);
	        writer.write(payload.toString());
	        writer.close();
	        int status = connection.getResponseCode();
	        System.out.println("Status: "+status);
	        connection.disconnect();
	    } catch (Exception e) {
	            throw new RuntimeException(e.getMessage());
	    }
	}

}
