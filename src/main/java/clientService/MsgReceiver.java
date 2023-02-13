package clientService;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.SocketException;

import javax.net.ssl.SSLSocket;

import clases.Pidove;
import clases.Cliente;
import clases.Mensaje;
import controllers.ClientesController;
import controllers.MensajesController;
import controllers.SendController;
import util.Clima;
import util.ClimaHandler;
import util.Io;

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
					Pidove ac = (Pidove) m;
					if (ac.action == 1) {
						socketSSL.close();
						break;
					}
					if (ac.action == 2) {
						Cliente cliente =  ClientesController.clientes.stream().filter(c -> c.usuario.nEmpl == ac.usuario.nEmpl).findFirst().get();
						
						String climaStr = ClimaHandler.clima.getClima();
						
						new SendController(cliente.socketSSL, new Pidove(2,null, climaStr));
						
						Io.Sop("Desde movil: "+climaStr+"\n");
					}
				}
			}
		} catch (SocketException | EOFException e) {
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
			if (ois != null)
				ois.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
