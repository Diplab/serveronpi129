package com.diplab.device;

import java.io.*;

public class w1 {
	  //This directory created by 1-wire kernel modules
	  static String w1DirPath = "/sys/bus/w1/devices";
	private static BufferedReader br;
	private static String filename = "28-0000051f2e41";
	  
	  public static double getTemperature() throws IOException{
		  double value = 0;
		  File dir = new File(w1DirPath);
		    File[] files = dir.listFiles(new DirectoryFileFilter());
		    if (files != null) {
		      while(true) {
		        for(File file: files) {
		          System.out.print(file.getName() + ": ");
		   // Device data in w1_slave file
		          String filePath = w1DirPath + "/" + filename + "/w1_slave";
		          File f = new File(filePath);
		          br = new BufferedReader(new FileReader(f));
		          try {
		            String output = br.readLine();
		            if (output.endsWith("YES")) {
		                String tempLine = br.readLine();
		                int equals = tempLine.indexOf('=');
		                double tempC = Double.parseDouble(tempLine.substring(equals + 1));
		                value = 0.001 * tempC;
		                System.out.format("T = %.3f %n", value);
		            
		               
//		            while(output!= null) {
//		              int idx = output.indexOf("t=");
//		              if(idx > -1) {
//		                // Temp data (multiplied by 1000) in 5 chars after t=
//		            	double tempC = Double.parseDouble(output.substring(output.indexOf("t=") + 2));
//		                // Divide by 1000 to get degrees Celsius
//		            	value = tempC/1000;
//		            	System.out.println(String.format("%.3f ", value));
//		            	System.out.format("T = %.3f %n", value);
//		
//		              }
		            }
		          }
		          catch (IOException e) {
		              e.printStackTrace();
		          }
		        }
		      }
		   }
		    br.close();
			return value;
	  }
	  
	  public static void main(String[] args) throws Exception {
		  while (true) {
		  System.out.println("Test");
		  System.out.format("T = %.3f %n", w1.getTemperature());
		  }
		  
	  }
	  
}	