package com.diplab.serviceImp;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import com.diplab.device.RpiTrunLightController;
import com.diplab.service.LightService129;


@WebService(endpointInterface = "com.diplab.service.LightService129")
public class LightService129Impl implements LightService129 {

	@Override
	public String sayHello(String name) {
		System.out.format("in sayHello: Receive %s ", name);
		return "JAVA-WS " + name;
	}

	public static void main(String[] args) {
		System.out.println("Light");
		Endpoint.publish("http://0.0.0.0:9006/webservice/sayLight",
				new LightService129Impl());
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

}
