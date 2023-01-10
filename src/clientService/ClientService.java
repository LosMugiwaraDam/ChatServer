package clientService;

import java.net.ServerSocket;
import java.net.Socket;

import conexiones.Puertos;
import main.Io;

public class ClientService extends Thread {

	public ClientService() {
		super();
		start();
	}

	public void run() {
		try {
			ServerSocket skServidor = new ServerSocket(Puertos.puerto);

			while (true) {
				Socket skCliente = skServidor.accept();
				new Login(skCliente);
			}
		} catch (Exception e) {
			Io.Sop(e.getMessage());
		}
	}
}
