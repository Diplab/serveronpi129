package com.diplab.serviceImp;

import java.io.IOException;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import com.diplab.device.RpiCO2;
import com.diplab.device.RpiTemperature;
import com.diplab.device.RpiTrunLightController;
import com.diplab.device.RunLIRC;
import com.diplab.service.HelloService129;

@WebService(endpointInterface = "com.diplab.service.HelloService129")
public class HelloService129Impl implements HelloService129 {

	@Override
	public String sayHello(String name) {
		System.out.format("in sayHello: Receive %s ", name);
		return "JAVA-WS " + name;
	}

	public static void main(String[] args) {
		System.out.println("HI");
		Endpoint.publish("http://0.0.0.0:9005/webservice/sayHello",
				new HelloService129Impl());
	}
	
	@Override
	public double random() {
		return (Math.random()*26)+15;
	}


	@Override
	public double CO2ppm() {
		return RpiCO2.get();
	}

	@Override
	public void off() {
		RpiTrunLightController.off();
		return;

	}

	@Override
	public void on() {
		RpiTrunLightController.on();
		return;
	}

	@Override
	public void toggle() {
		RpiTrunLightController.toggle();
		return;
	}

	@Override
	public void executeAC() {
		RunLIRC.executeAC();
		return;

	}

	@Override
	public double readTemperature() throws IOException {
		return RpiTemperature.getTemperature();
		
	}

}
