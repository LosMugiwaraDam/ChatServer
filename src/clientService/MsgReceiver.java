package clientService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;

import clases.Cliente;
import clases.Mensaje;
import controllers.MensajesController;
import main.Io;

public class MsgReceiver extends Thread {

	private Socket socket;
	private Cliente cliente;

	public MsgReceiver(Socket socket, Cliente cliente) {
		super();
		this.socket = socket;
		this.cliente = cliente;
		start();
	}

	public void run() {
		ObjectInputStream oisClient = null;
		try {
			while (true) {
				oisClient = new ObjectInputStream(this.socket.getInputStream());
				Object m = oisClient.readObject();

				if (m instanceof Mensaje)
					MensajesController.enviar((Mensaje) m);
				else {
					socket.close();
					break;
				}
			}
		} catch (SocketException e) {
			Io.Sop(cliente.usuario.nombre + " " + cliente.usuario.apellido1 + " desconectado\n");
			this.closeS(cliente.socket, oisClient);
			cliente.socket = null;
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void closeS(Socket s, ObjectInputStream ois) {
		try {
			s.close();
			ois.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
