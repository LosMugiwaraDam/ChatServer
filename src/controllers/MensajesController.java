package controllers;

import java.io.ObjectOutputStream;

import clases.Cliente;
import clases.Mensaje;
import main.Io;

public class MensajesController {

	public synchronized static void enviar(Mensaje m) {
		Io.Sop(m.toString() + "\n\n");
		if (m.usuRec == null) {
			for (Cliente cliente : ClientesController.clientes) {
				if (cliente.socketSSL != null) {
					try {
						ObjectOutputStream oosServer = new ObjectOutputStream(cliente.socketSSL.getOutputStream());
						oosServer.writeObject(m);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				} else {
					Io.Sop(cliente.usuario.nombre + " " + cliente.usuario.apellido1 + " no conectado\n\n");
				}
			}
		} else {
			Cliente cliente = ClientesController.clientes.stream().filter(c -> c.usuario.nEmpl == m.usuRec.nEmpl).findFirst().orElse(null);

			if (cliente.socketSSL != null) {
				try {
					ObjectOutputStream oosServer = new ObjectOutputStream(cliente.socketSSL.getOutputStream());
					oosServer.writeObject(m);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} else {
				Io.Sop(cliente.usuario.nombre + " " + cliente.usuario.apellido1 + " no conectado\n\n");
			}
		}
	}
}
