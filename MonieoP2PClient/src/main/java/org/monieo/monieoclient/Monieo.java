package org.monieo.monieoclient;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

import javax.management.RuntimeErrorException;
import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
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
	        if (result.getStatusLine().getStatusCode() != 200) throw new RuntimeException("Could not parse github API");
	        String json = EntityUtils.toString(result.getEntity(), "UTF-8");

	        JSONObject response = new JSONObject(json);
	        
	        double releaseLatest = Double.valueOf(response.getString("tag_name"));
	        //double myRelease = 0;
	        double myRelease = Double.valueOf(properties.getProperty("version"));
	        
	        if (releaseLatest > myRelease) {
	        	
	        	String link = "https://github.com/Symphonic3/Monieo/releases/tag/" + releaseLatest;
	        	
	        	int res = JOptionPane.showOptionDialog(null, new MessageWithLink("New update available. Press 'OK' to automatically download, or download and use version " + releaseLatest + " from github:"
	        			+ "\n <a href=\"" + link + "\">Click here</a>"), "New update available!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
	        	
	        	if (res == 0) {
	        		
	        		JSONObject assetInfo = response.getJSONArray("assets").getJSONObject(0);
	        		String downloadURL = assetInfo.getString("browser_download_url");
	        		String fileName = assetInfo.getString("name");
	        		
	        		String jarFol = null;
					try {
						jarFol = new File(Monieo.class.getProtectionDomain().getCodeSource().getLocation()
							    .toURI()).getParent();
					} catch (URISyntaxException e) {
						e.printStackTrace();
					}
	        		
	        		File newJar = new File(jarFol + "/" + fileName);
	        		
	        		FileUtils.copyURLToFile(new URL(downloadURL), newJar);
	        		
	        	}
	        	
	        	System.exit(0);
	        	
	        }
	        
	        version = myRelease;
	        
	    } catch (IOException ex) {
	    	
	    	ex.printStackTrace();
	    	
	    }
	    
		ui = new UI();
		ui.initialize();
		
	}

}	
