package com.diplab.device;

import java.util.concurrent.TimeUnit;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalMultipurpose;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinMode;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;


public class DHT11 {
	private static final Pin PIN = RaspiPin.GPIO_04;
	private static final int MAXTIMINGS = 85;
	private static int[] dht22_dat = { 0, 0, 0, 0, 0 };

	private static GpioPinDigitalMultipurpose dht22Pin;

	static {
		init(PIN);
	}
	
	public static void init(Pin pin) {
		final GpioController gpio = GpioFactory.getInstance();
		dht22Pin = gpio.provisionDigitalMultipurposePin(PIN,
				PinMode.DIGITAL_INPUT, PinPullResistance.PULL_UP);
		
		// create and register gpio pin listener
		dht22Pin.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(
					GpioPinDigitalStateChangeEvent event) {
				// display pin state on console
				System.out.println(" --> GPIO PIN STATE CHANGE: "
						+ event.getPin() + " = " + event.getState());
					}
				});

		// configure the pin shutdown behavior; these settings will be
		// automatically applied to the pin when the application is terminated
		dht22Pin.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				System.out.println("Oops!");
				gpio.shutdown();
				System.out.println("Exiting nicely.");
			}
		});
	}
	
	public static double getTemperature() {
		
		PinState laststate = PinState.HIGH;
		int j = 0 ;
		dht22_dat[0] = dht22_dat[1] = dht22_dat[2] = dht22_dat[3] = dht22_dat[4] = 0;
		StringBuilder value = new StringBuilder();
		
		try {
			
			// pull pin down for 18 milliseconds
			dht22Pin.setMode(PinMode.DIGITAL_OUTPUT);
			dht22Pin.high();
			Thread.sleep(10);
			dht22Pin.low();
			Thread.sleep(18);
			// then pull it up for 40 microseconds
			dht22Pin.high();
			TimeUnit.MICROSECONDS.sleep(40);
			// prepare to read the pin
			dht22Pin.setMode(PinMode.DIGITAL_INPUT);
			
			// detect change and read data
				for (int i = 0; i < MAXTIMINGS; i++) {
					int counter = 0;
					while (dht22Pin.getState() == laststate) {
						counter++;
						TimeUnit.MICROSECONDS.sleep(1);
						if (counter == 255) {
							break;
						}
					}
					
					laststate = dht22Pin.getState();
					
					if (counter == 255) {
						System.out.println("i= "+i);
						break;
					}
					
					/* ignore first 3 transitions */
					if ((i >= 4) && (i % 2 == 0)) {
						System.out.println("OK!");
						/* shove each bit into the storage bytes */
						dht22_dat[j / 8] <<= 1;
						if (counter > 16) {
							dht22_dat[j / 8] |= 1;
						}
						j++;
					}
				}
				System.out.println("j = "+ j );
					// check we read 40 bits (8bit x 5 ) + verify checksum in the last byte
					if ((j >= 40) && checkParity()) {
				
						value.append(dht22_dat[2]).append(".").append(dht22_dat[3]);
						System.out.println("temperature value readed: "
								+ value.toString());
						double t, h;
						h = (double) dht22_dat[0] * 256 + (double) dht22_dat[1];
						h /= 10;
						t = (double) (dht22_dat[2] & 0x7F) * 256
								+ (double) dht22_dat[3];
						t /= 10.0;
						if ((dht22_dat[2] & 0x80) != 0)
							t *= -1;

						System.out.format(
								"Humidity = %.2f     Temperature = %.2f *C %n", h, t);
						return 1;
					}
	
		} catch (InterruptedException e) {
			System.out.println("InterruptedException: " + e.getMessage());
		}
		
		if (value.toString().isEmpty()) {
			System.out.println("value is empty");
			value.append(-1);
		}
		return Double.parseDouble(value.toString());
	}
	
	private static boolean checkParity() {
		return (dht22_dat[4] == ((dht22_dat[0] + dht22_dat[1] + dht22_dat[2] + dht22_dat[3]) & 0xFF));
	}
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Raspberry Pi wiringPi DHT11 Temperature test program");
		System.out.println(DHT11.getTemperature());
	}
}
