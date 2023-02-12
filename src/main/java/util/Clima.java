package util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import ModeloBD_Vista.ClimaInterface;
import io.github.eliux.mega.Mega;
import io.github.eliux.mega.MegaSession;

public class Clima extends UnicastRemoteObject implements ClimaInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MegaSession sessionMega;

	public Clima()throws RemoteException {
		sessionMega = Mega.init();
	}
	
	@Override
	public String getClima() throws RemoteException {
		
		File file = new File("txtTiempo.txt");
		file.delete();

		sessionMega.get("vbFiles/txtTiempo.txt","./").run();
		
		Path txtTiempo = Paths.get("txtTiempo.txt");
		List<String> tiempoStr = null;
		try {
			tiempoStr = Files.readAllLines(txtTiempo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tiempoStr.get(0);
	}
	
}
