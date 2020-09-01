package org.monieo.monieoclient;

import java.io.IOException;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class Monieo {

	public static void main(String[] args) {
		
		final Properties properties = new Properties();
		try {
			properties.load(Monieo.class.getClassLoader().getResourceAsStream("project.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Version: " + properties.getProperty("version"));
		
	    String url = "https://api.github.com/repos/Symphonic3/Monieo/releases/latest";

	    try {
	        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
	        HttpGet request = new HttpGet(url);
	        request.addHeader("content-type", "application/json");
	        HttpResponse result = httpClient.execute(request);
	        String json = EntityUtils.toString(result.getEntity(), "UTF-8");

	        System.out.println(json);
	        
	        JSONObject response = new JSONObject(json);
	    } catch (IOException ex) {
	    }
		
	}

}	
