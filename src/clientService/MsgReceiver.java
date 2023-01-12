package clientService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.SocketException;

import javax.net.ssl.SSLSocket;

import clases.Cliente;
import clases.Mensaje;
import controllers.MensajesController;
import main.Io;

public class MsgReceiver extends Thread {

	private SSLSocket socketSSL;
	private Cliente cliente;

	public MsgReceiver(SSLSocket socketSSL, Cliente cliente) {
		super();
		this.socketSSL = socketSSL;
		this.cliente = cliente;
		start();
	}

	public void run() {
		ObjectInputStream oisClient = null;
		try {
			while (true) {
				oisClient = new ObjectInputStream(this.socketSSL.getInputStream());
				Object m = oisClient.readObject();

				if (m instanceof Mensaje)
					MensajesController.enviar((Mensaje) m);
				else {
					socketSSL.close();
					break;
				}
			}
		} catch (SocketException e) {
			Io.Sop(cliente.usuario.nombre + " " + cliente.usuario.apellido1 + " desconectado\n");
			this.closeS(cliente.socketSSL, oisClient);
			cliente.socketSSL = null;
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void closeS(SSLSocket s, ObjectInputStream ois) {
		try {
			s.close();
			ois.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
