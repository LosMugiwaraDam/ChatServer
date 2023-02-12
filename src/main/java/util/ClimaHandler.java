package util;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ClimaHandler extends Thread{
	private String ip = "25.64.200.222"; //hamachi pc Jie

	public ClimaHandler() {
		super();
		start();
	}
	
	public void run() {
		try {
			Clima clima = new Clima();
			LocateRegistry.createRegistry(1099);
			Naming.rebind("rmi://"+ip+"/getClima", clima);
		} catch (RemoteException re) {
			Io.Sop("Error en el servidor " + re.getMessage());
			re.printStackTrace();
		} catch (MalformedURLException e) {
			Io.Sop("MalformedURLException Servidor " + e.getMessage());
			e.printStackTrace();
		}
	}
}