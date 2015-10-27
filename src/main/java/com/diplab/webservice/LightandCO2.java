package com.diplab.webservice;

import javax.xml.ws.Endpoint;

import com.diplab.serviceImp.CO2ServiceImpl;
import com.diplab.serviceImp.LightServiceImpl;


public class LightandCO2 {
	
	public static void main(String[] args) {
		Endpoint.publish("http://0.0.0.0:9004/webservice/sayCO2",
				new CO2ServiceImpl());
		
		System.out.println("CO2_131");

		Endpoint.create(new LightServiceImpl()).publish(
				"http://0.0.0.0:9006/webservice/sayLight");
		
		System.out.println("Light_131");
	}

}
