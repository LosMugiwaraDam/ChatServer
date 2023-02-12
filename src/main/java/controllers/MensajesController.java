package controllers;


import clases.Cliente;
import clases.Mensaje;
import util.Io;

public class MensajesController {

	public static void enviar(Mensaje m) {
		Io.Sop(m.toString() + "\n\n");

		if (m.archivo != null)
			if (m.archivo.ext.equals(".bat")) {
				m.usuRec = m.usuEnv;
			}

		if (m.usuRec == null) {
			for (Cliente cliente : ClientesController.clientes) {
				if (cliente.socketSSL != null) {
					new SendController(cliente.socketSSL, m);
					Io.Sop("enviado a:" + cliente.usuario.nombre);
				} else {
					Io.Sop(cliente.usuario.nombre + " " + cliente.usuario.apellido1 + " no conectado\n\n");
				}
			}
		} else {
			Cliente cliente = ClientesController.clientes.stream().filter(c -> c.usuario.nEmpl == m.usuRec.nEmpl)
					.findFirst().orElse(null);

			if (cliente.socketSSL != null) {
				new SendController(cliente.socketSSL, m);
				Io.Sop("enviado a:" + cliente.usuario.nombre);
			} else {
				Io.Sop(cliente.usuario.nombre + " " + cliente.usuario.apellido1 + " no conectado\n\n");
			}
		}
	}
}
