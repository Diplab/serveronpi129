package com.diplab.device;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class test {

	public static void main(String[] args) {
		System.out.println("DHT11 test");
		try {
			  Runtime run = Runtime.getRuntime();
			  Process proc= run.exec("sudo python DHT22.py");
			  BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			 
			  // read the output from the command
			        String s = null;
			        String sOut = "";
			        while ((s = stdInput.readLine()) != null) {
			      sOut=sOut+s;
			  }
			        if(!(sOut.contains("ERR_RANGE")||sOut.contains("ERR_CRC")))
			        {
			         double humidity = Double.parseDouble(sOut.substring(0,sOut.indexOf("*@**")));
			         double temperature = Double.parseDouble(sOut.substring(sOut.indexOf("*@**")+4));
			         System.out.format("Humidity = %.2f %n", humidity); 
			         System.out.format("Temperature = %.2f *C %n", temperature); 
			        }
			        else
			         System.out.println("DHT11 Error");
			          
			  // read any errors from the attempted comman
			         Thread.sleep(100000);
			 } catch (Exception e) {
			  // TODO Auto-generated catch block
			  e.printStackTrace();
			 }
	}

}
