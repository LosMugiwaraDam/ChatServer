package controllers;

import java.io.ObjectOutputStream;

import clases.Cliente;
import clases.Mensaje;
import main.Io;

public class MensajesController {

	public synchronized static void enviar(Mensaje m) {
		Io.Sop(m.toString());
		if(m.usuRec == null) {
			for (Cliente cliente : ClientesController.clientes) {
				try {
					ObjectOutputStream oosServer = new ObjectOutputStream(cliente.socket.getOutputStream());
					oosServer.writeObject(m);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
}
