package com.diplab.webservice;

import javax.xml.ws.Endpoint;

import com.diplab.serviceImp.EscapeServiceImpl;


public class Escape {
	
	public static void main(String[] args) {
		Endpoint.publish("http://0.0.0.0:9005/webservice/Escape",
				new EscapeServiceImpl());
		
		System.out.println("open webservice129 Escape");

	}

}
