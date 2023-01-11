package controllers;

import java.io.IOException;
import java.util.ArrayList;

import clases.Cliente;
import main.Io;

public class ClientesController {

	public static ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	
	public static boolean disconnect(Cliente cliente) {
		Io.Sop(cliente.usuario.nombre + " " + cliente.usuario.apellido1 + " desconectado");
		try {
			cliente.socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		cliente.socket = null;
		
		return true;
	}
}
