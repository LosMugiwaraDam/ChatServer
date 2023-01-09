package clientService;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import clases.Empleado;

import conexiones.Puertos;

public class ClientService extends Thread {

	public ClientService() {
		super();
		start();
	}

	public void run() {
		try {
			ServerSocket skServidor = new ServerSocket(Puertos.puertoLogin);

			while (true) {
				Socket skCliente = skServidor.accept();
				new Login(skCliente);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
