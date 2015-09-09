package com.diplab.webservice;

import javax.xml.ws.Endpoint;

import com.diplab.serviceImp.ACService129Impl;
import com.diplab.serviceImp.CO2Service129Impl;


public class ACandCO2 {
	
	public static void main(String[] args) {
		Endpoint.publish("http://0.0.0.0:9004/webservice/sayCO2",
				new CO2Service129Impl());
		System.out.println("CO2_129");

		Endpoint.create(new ACService129Impl()).publish(
				"http://0.0.0.0:9003/webservice/sayAC");
		System.out.println("AC_129");
	}

}
