package com.diplab.serviceImp;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import com.diplab.device.RpiCO2;
import com.diplab.device.RpiElectricLockController;
import com.diplab.device.RpiTemperature;
import com.diplab.device.RpiTrunLightController;
import com.diplab.device.RunLIRC;
import com.diplab.service.EscapeService;

@WebService(endpointInterface = "com.diplab.service.EscapeService")
public class EscapeServiceImpl implements EscapeService {

	public static void main(String[] args) {
		System.out.println("HI");
		Endpoint.publish("http://0.0.0.0:9005/webservice/Escape",
				new EscapeServiceImpl());
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
	public double readTemperature() {
		return RpiTemperature.getTemperature();
		
	}

	@Override
	public void unlock() {
		RpiElectricLockController.on();
		return;
	}

	@Override
	public void lock() {
		RpiElectricLockController.off();
		return;
	}

}
