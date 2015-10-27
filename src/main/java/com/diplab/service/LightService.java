package com.diplab.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface LightService {

	@WebMethod
	String sayHello(String name);
	
	@WebMethod
	void off();

	@WebMethod
	void on();

	@WebMethod
	void toggle();

}
