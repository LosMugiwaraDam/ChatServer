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
		try {
			while (true) {
				ObjectInputStream oisClient = new ObjectInputStream(this.socket.getInputStream());
				Mensaje m = (Mensaje) oisClient.readObject();
				MensajesController.enviar(m);
			}
		} catch (SocketException e) {
			Io.Sop(cliente.usuario.nombre + " " + cliente.usuario.apellido1 + " desconectado");
			cliente.socket = null;
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
