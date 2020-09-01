package org.monieo.monieoclient;

import java.io.IOException;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class Monieo {
	
	public static UI ui;
	public static double version;
	
	public static void main(String[] args) {
		
		final Properties properties = new Properties();
		try {
			properties.load(Monieo.class.getClassLoader().getResourceAsStream("project.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Version: " + properties.getProperty("version"));
		System.out.println("Please look towards the GUI application.");
		System.out.println("");
		System.out.println("If there are any issues with running the application, all errors will be logged in this window.");
		System.out.println("Please paste the full log of this window when submitting a bug report.");
		
	    String url = "https://api.github.com/repos/Symphonic3/Monieo/releases/latest";

	    try {
	        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
	        HttpGet request = new HttpGet(url);
	        request.addHeader("content-type", "application/json");
	        HttpResponse result = httpClient.execute(request);
	        String json = EntityUtils.toString(result.getEntity(), "UTF-8");

	        JSONObject response = new JSONObject(json);
	        
	        double releaseLatest = Double.valueOf(response.getString("tag_name"));
	        //double myRelease = 0;
	        double myRelease = Double.valueOf(properties.getProperty("version"));
	        
	        if (releaseLatest > myRelease) {
	        	
	        	String link = "https://github.com/Symphonic3/Monieo/releases/tag/" + releaseLatest;
	        	
	        	JOptionPane.showMessageDialog(null, new MessageWithLink("New update available. Please download and use version " + releaseLatest + " from github:"
	        			+ "\n <a href=\"" + link + "\">Click here</a>"), "New update available!", JOptionPane.ERROR_MESSAGE);
	        	System.exit(0);
	        	
	        }
	        
	        version = myRelease;
	        //s
	        
	    } catch (IOException ex) {
	    	
	    	ex.printStackTrace();
	    	
	    }
	    
		ui = new UI();
		ui.Start();
		
	}

}	
