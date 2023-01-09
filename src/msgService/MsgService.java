package msgService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import clases.Mensaje;
import conexiones.Puertos;
import controllers.MensajesController;

public class MsgService extends Thread {

	public MsgService() {
		super();
		start();
	}

	public void run() {
		try {
			ServerSocket skServer = new ServerSocket(Puertos.puertoRecepcionMensaje);

			while (true) {
				Socket skClient = skServer.accept();

				ObjectInputStream oisClient = new ObjectInputStream(skClient.getInputStream());

				Mensaje m = (Mensaje) oisClient.readObject();
				
				MensajesController.enviar(m);
				
				oisClient.close();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
