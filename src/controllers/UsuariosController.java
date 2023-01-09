package controllers;

import java.net.ConnectException;
import java.net.Socket;
import java.util.ArrayList;

import clases.Empleado;
import clases.UserConn;
import conexiones.Puertos;

public class UsuariosController {

	public static ArrayList<UserConn> usuariosConectados = new ArrayList<UserConn>();

	public static void connect(UserConn u) {
		usuariosConectados.add(u);
	}

	public static void disconnect(UserConn u) {
		System.out.println("Desconectando: "+ u.getIp());
		usuariosConectados.remove(u);
	}

	public static boolean isConnected(Empleado empl) {
		UserConn usuario = usuariosConectados.stream().filter(u -> u.getEmpleado().equals(empl)).findAny().orElse(null);

		if (usuario != null) {
			try {
				Socket skServer= new Socket(usuario.getIp(), (int) (Puertos.puertoPruebaConn + usuario.getEmpleado().getnEmpl()));
				skServer.close();
			} catch (ConnectException e) {
				System.out.println("Error al conectar con: "+usuario.getIp());
				UsuariosController.disconnect(usuario);
				return false;
			} catch ( Exception e) {
				System.out.println(e);
			}
			return true;
		}
		return false;
	}
	public static void showConn() {
		int length = usuariosConectados.size();
		for (int i = 0; i < length; i++) {
			isConnected(usuariosConectados.get(i).getEmpleado());
		}
		
		for (UserConn userConn : usuariosConectados) {
			System.out.println(userConn.toString());
		}
	}
	
}
