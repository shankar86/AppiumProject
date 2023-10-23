package com.zee5.tata.cucumber.appium.stepdefs;

import com.sun.jersey.api.client.Client;

public enum ClientProvider {
	REST_CLIENT;
	Client client ;
	private  ClientProvider(){
		client = Client.create();
	}
	
	public Client getClient(){
		return client;
	}
}
