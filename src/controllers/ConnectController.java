package controllers;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import clases.UserConn;
import conexiones.Puertos;

public class ConnectController {

	private static ArrayList<UserConn> usuariosHistorial = new ArrayList<UserConn>();

	public static void add(UserConn newUser) {
		usuariosHistorial.add(newUser);
	}
	public static void guardar() {
		try {
			File fich = new File("connHistory.obj");

			if (fich.exists()) {
				ObjectInputStream ois = null;
				UserConn u;
				try {
					ois = new ObjectInputStream(new FileInputStream(fich));
					while (true) {
						u = (UserConn) ois.readObject();
						usuariosHistorial.add(u);
					}
				} catch (ClassCastException|EOFException e) {
					ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fich));
					for (UserConn userConn : usuariosHistorial) {
						oos.writeObject(userConn);
					}
					oos.close();
				}
				ois.close();
			} else
				System.out.println("El fichero todavía NO existe");
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("Conexiones guardas");
		usuariosHistorial.clear();
	}
}
