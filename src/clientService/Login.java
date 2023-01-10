package clientService;

import java.io.*;
import java.net.*;
import java.util.*;

import clases.*;
import controllers.ClientesController;
import controllers.MensajesController;
import main.Io;

public class Login extends Thread {

	Socket skCliente;

	public Login(Socket sk) {
		super();
		this.skCliente = sk;
		start();
	}

	public void run() {
		try {

			Io.Sop("Entrada de solicitud de inicio de sesion\n\n");

			DataInputStream disClient = new DataInputStream(this.skCliente.getInputStream());

			double nEmpl = disClient.readLong();
			String passw = disClient.readUTF();

			Cliente cliente = null;

			cliente = ClientesController.clientes.stream().filter(e -> e.nEmpl == nEmpl && e.passw.equals(passw)).findFirst().orElse(null);
			ObjectOutputStream oosClient = new ObjectOutputStream(this.skCliente.getOutputStream());

			if (cliente == null) {
				oosClient.writeObject(null);
				Io.Sop("Nº Empleado o Contraseña Incorrecta\n\n");
			} else {
				oosClient.writeObject(cliente.usuario);
				ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
				for (Cliente c : ClientesController.clientes) {
					if(c != cliente)
					usuarios.add(c.usuario);
				}
				oosClient.writeObject(usuarios);
				Io.Sop("Usuario " + cliente.usuario.nombre + " " + cliente.usuario.apellido1 + " logeado con exito\n\n");
				cliente.socket = this.skCliente;
				
				new MsgReceiver(skCliente, cliente);
			}
		} catch (Exception e) {	
			Io.Sop(e+"");
		}
	}
}
