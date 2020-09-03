package org.monieo.monieoclient;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.monieo.monieoclient.gui.MessageWithLink;
import org.monieo.monieoclient.gui.UI;

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

		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
		request.addHeader("content-type", "application/json");
		HttpResponse result = null;
		try {
			result = httpClient.execute(request);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (result.getStatusLine().getStatusCode() != 200)
			throw new RuntimeException("Could not parse github API");
		String json = null;
		try {
			json = EntityUtils.toString(result.getEntity(), "UTF-8");
		} catch (ParseException | IOException e1) {
			e1.printStackTrace();
		}

		JSONObject response = new JSONObject(json);

		double releaseLatest = Double.valueOf(response.getString("tag_name"));
		version = Double.valueOf(properties.getProperty("version"));

		if (releaseLatest > version) {

			String link = "https://github.com/Symphonic3/Monieo/releases/tag/" + releaseLatest;

			int res = JOptionPane.showOptionDialog(null,
					new MessageWithLink(
							"New update available. Press 'OK' to automatically download, or download and use version "
									+ releaseLatest + " from github:" + "\n <a href=\"" + link + "\">Click here</a>"),
					"New update available!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);

			if (res == 0) {

				JSONObject assetInfo = response.getJSONArray("assets").getJSONObject(0);
				String downloadURL = assetInfo.getString("browser_download_url");
				String fileName = assetInfo.getString("name");

				String jarFol = null;
				try {
					jarFol = new File(Monieo.class.getProtectionDomain().getCodeSource().getLocation().toURI())
							.getParent();
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}

				File newJar = new File(jarFol + "/" + fileName);

				try {
					FileUtils.copyURLToFile(new URL(downloadURL), newJar);
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

			System.exit(0);

		}
		
		// rsa key pair generation and writing stuff
		try {
			
			String appdata = System.getenv("APPDATA");
			
			File directory = new File(appdata + "/monieo");
			if (!directory.exists()) {
				directory.mkdir();
			}
			
			File prvFile = new File(appdata + "/monieo/private.key");
			File pubFile = new File(appdata + "/monieo/public.key");
			if (!prvFile.exists()) {
				
				Base64.Encoder encoder = Base64.getEncoder();
				
				KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
				kpg.initialize(2048);
				KeyPair kp = kpg.generateKeyPair();
				
				prvFile.createNewFile();
				Writer prv = new FileWriter(prvFile);
				prv.write("-----BEGIN RSA PRIVATE KEY-----\n");
				prv.write(encoder.encodeToString(kp.getPrivate().getEncoded()));
				prv.write("\n-----END RSA PRIVATE KEY-----\n");
				prv.close();
				
				pubFile.createNewFile();
				Writer pub = new FileWriter(pubFile);
				pub.write("-----BEGIN RSA PUBLIC KEY-----\n");
				pub.write(encoder.encodeToString(kp.getPublic().getEncoded()));
				pub.write("\n-----END RSA PUBLIC KEY-----\n");
				pub.close();
				
			}
			
		} catch (NoSuchAlgorithmException | IOException e2) {
			e2.printStackTrace();
		}

		ui = new UI();
		ui.initialize();

	}
	
}
