package com.diplab.serviceImp;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import com.diplab.device.RunLIRC;
import com.diplab.service.ACService;

@WebService(endpointInterface = "com.diplab.service.ACService129")
public class ACServiceImpl implements ACService {

	@Override
	public String sayHello(String name) {
		System.out.format("in sayHello: Receive %s ", name);
		return "JAVA-WS " + name;
	}

	public static void main(String[] args) {
		System.out.println("AC");
		Endpoint.publish("http://0.0.0.0:9003/webservice/sayAC",
				new ACServiceImpl());
	}

	@Override
	public void executeAC() {
		RunLIRC.executeAC();
		return;

	}

}
