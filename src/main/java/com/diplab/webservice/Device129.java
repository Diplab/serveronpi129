package com.diplab.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Endpoint;

import com.diplab.service.CO2Service;
import com.diplab.service.COService;
import com.diplab.service.EscapeService;
import com.diplab.service.SmokeService;
import com.diplab.service.SwitchService;
import com.diplab.service.TemperatureService;
import com.diplab.serviceImp.CO2ServiceImpl;
import com.diplab.serviceImp.COServiceImpl;
import com.diplab.serviceImp.EscapeServiceImpl;
import com.diplab.serviceImp.SmokeServiceImpl;
import com.diplab.serviceImp.SwitchServiceImpl;
import com.diplab.serviceImp.TemperatureServiceImpl;
import com.pi4j.io.gpio.PinState;

@WebService
@SOAPBinding(style = Style.RPC)
public class Device129 {

	CO2Service co2Service = new CO2ServiceImpl();
	COService coService = new COServiceImpl();
	EscapeService escapeService = new EscapeServiceImpl();
	SmokeService smokeService = new SmokeServiceImpl();
	SwitchService switchService = new SwitchServiceImpl();
	TemperatureService temperatureService = new TemperatureServiceImpl();

	@WebMethod
	public double CO2ppm() {
		return co2Service.CO2ppm();
	}

	@WebMethod
	public double COppm() {
		return coService.COppm();
	}

	@WebMethod
	public void unlock() {
		escapeService.unlock();
	}

	@WebMethod
	public void lock() {
		escapeService.lock();
	}

	@WebMethod
	public double getSmokePpm() {
		return smokeService.getSmokePpm();
	}

	@WebMethod
	public void off() {
		switchService.off();
	}

	@WebMethod
	public void on() {
		switchService.on();
	}

	@WebMethod
	public void toggle() {
		switchService.toggle();
	}

	@WebMethod
	public PinState getState() {
		return switchService.getState();
	}

	@WebMethod
	public double readTemperature() {
		return temperatureService.readTemperature();
	}

	public static void main(String[] args) {
		Endpoint.publish("http://0.0.0.0:9005/webservice/Device129",
				new Device129());

		System.out.println("open webservice129");

	}

}
