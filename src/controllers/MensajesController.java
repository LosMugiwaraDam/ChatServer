package controllers;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import clases.Mensaje;
import clases.UserConn;
import conexiones.Puertos;

public class MensajesController {

	public static ArrayList<Mensaje> mensajes = new ArrayList<Mensaje>();

	public synchronized static void enviar(Mensaje m) {

		mensajes.add(m);

		System.out.println(m.toString());

		int length = UsuariosController.usuariosConectados.size();
		for (int i = 0; i < length; i++) {
			UsuariosController.isConnected(UsuariosController.usuariosConectados.get(i).getEmpleado());
		}
		for (UserConn usuario : UsuariosController.usuariosConectados) {
			try {
				Socket skServer = new Socket(usuario.getIp(),
						(int) (Puertos.puertoEnvioMensaje - usuario.getEmpleado().getnEmpl()));

				ObjectOutputStream oosClient = new ObjectOutputStream(skServer.getOutputStream());

				oosClient.writeObject(m);

				oosClient.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
