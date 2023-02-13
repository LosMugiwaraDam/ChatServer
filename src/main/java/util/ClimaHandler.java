package util;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ClimaHandler extends Thread{
	private String ip = "localhost"; //hamachi pc Jie

	public static Clima clima;
	
	public ClimaHandler() {
		super();
		try {
			ClimaHandler.clima = new Clima();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		start();
	}
	
	public void run() {
		try {
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