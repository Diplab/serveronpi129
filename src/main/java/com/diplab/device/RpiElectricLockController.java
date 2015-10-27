package com.diplab.device;

import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;

public class RpiElectricLockController {

	private static GpioPinDigitalOutput pin = GpioFactory.getInstance()
			.provisionDigitalOutputPin(RaspiPin.GPIO_25);

	public static void off() {
		pin.low();
		System.out.println("LOCK!");
	}

	public static void on() {
		pin.high();
		System.out.println("UNLOCK!");
	}

	public static void main(String[] args) throws InterruptedException {
		while(true) {
			on();
			Thread.sleep(5000);
			off();
			Thread.sleep(5000);
		}
	}
}
