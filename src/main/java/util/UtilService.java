package util;

public class UtilService extends Thread {

	public UtilService() {
		super();
		start();
	}

	public void run() {
		new ClientChargeHandler();
		new ClimaHandler();
	}
}
