package br.com.gerencianet;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONObject;
import org.json.JSONTokener;

public class Credentials 
{
	private String clientId;
	private String clientSecret;
	private boolean sandbox;
	private boolean debug;
	
	public Credentials() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream credentialsFile = classLoader.getResourceAsStream("credentials.json");
		JSONTokener tokener = new JSONTokener(credentialsFile);
		JSONObject credentials = new JSONObject(tokener);
		try {
			credentialsFile.close();
		} catch (IOException e) {
			System.out.println("Impossible to close file credentials.json");
		}
		
		this.clientId = credentials.getString("client_id");
		this.clientSecret = credentials.getString("client_secret");
		this.sandbox = credentials.getBoolean("sandbox");
		this.debug = credentials.getBoolean("debug");
	}

	public String getClientId() {
		return clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public boolean isSandbox() {
		return sandbox;
	}

	public boolean isDebug() {
		return debug;
	}
	
	
}
