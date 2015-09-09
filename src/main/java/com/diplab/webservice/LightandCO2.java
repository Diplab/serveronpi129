package com.diplab.webservice;

import javax.xml.ws.Endpoint;

import com.diplab.serviceImp.CO2Service129Impl;
import com.diplab.serviceImp.LightService129Impl;


public class LightandCO2 {
	
	public static void main(String[] args) {
		Endpoint.publish("http://0.0.0.0:9004/webservice/sayCO2",
				new CO2Service129Impl());
		
		System.out.println("CO2_129");

		Endpoint.create(new LightService129Impl()).publish(
				"http://0.0.0.0:9006/webservice/sayLight");
		
		System.out.println("Light_129");
	}

}
