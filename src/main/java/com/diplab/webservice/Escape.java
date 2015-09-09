package com.diplab.webservice;

import javax.xml.ws.Endpoint;

import com.diplab.serviceImp.HelloService129Impl;


public class Escape {
	
	public static void main(String[] args) {
		Endpoint.publish("http://0.0.0.0:9005/webservice/sayHello",
				new HelloService129Impl());
		
		System.out.println("Escape");

	}

}
