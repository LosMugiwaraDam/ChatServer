package clientService;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

import conexiones.Puertos;
import util.Io;

public class ClientService extends Thread {

	public ClientService() {
		super();
		start();
	}

	public void run() {
		try {
			System.setProperty("javax.net.ssl.keyStore", "certificadosJie/AlmacenSSL");
			System.setProperty("javax.net.ssl.keyStorePassword","1234567");
			
			SSLServerSocketFactory sfact=(SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
			SSLServerSocket skServidorSSL=(SSLServerSocket) sfact.createServerSocket(Puertos.puerto);

			while (true) {
				SSLSocket skClienteSSL = (SSLSocket) skServidorSSL.accept();
				new Login(skClienteSSL);
			}
		} catch (Exception e) {
			Io.Sop(e.getMessage());
		}
	}
}
