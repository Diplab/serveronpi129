package com.diplab.device;

public class RunLIRC {

	static enum Function {
		KEY_PLAY, KEY_UP, KEY_DOWN
	}

	public static void executeAC() {
		exec(Function.KEY_PLAY);
	}

	static void exec(Function fn) {
		try {
			Runtime runtime = Runtime.getRuntime();
			Process process = runtime.exec(new String[] { "/usr/bin/irsend",
					"SEND_ONCE", "/home/pi/lircd.conf", fn.name() });
			int exitValue = process.waitFor();
			System.out.println("exit value: " + exitValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println(Function.KEY_PLAY.name());
	}

}
