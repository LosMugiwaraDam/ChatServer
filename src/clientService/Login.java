package clientService;

import java.io.*;
import java.net.*;

import clases.*;
import controllers.ClientesController;
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

			cliente = ClientesController.clientes.stream().filter(c -> c.usuario.nEmpl == nEmpl && c.passw.equals(passw)).findFirst().orElse(null);
			ObjectOutputStream oosClient = new ObjectOutputStream(this.skCliente.getOutputStream());
			DataOutputStream dosClient = new DataOutputStream(this.skCliente.getOutputStream());
			
		
			
			if (cliente == null) {
				oosClient.writeObject(null);
				Io.Sop("Nº Empleado o Contraseña Incorrecta\n\n");
			} else {
				oosClient.writeObject(cliente.usuario);
				
				dosClient.writeInt(ClientesController.clientes.size());
				
				for (Cliente c : ClientesController.clientes) {
					if(c != cliente)
					oosClient.writeObject(c.usuario);
				}
				Io.Sop("Usuario " + cliente.usuario.nombre + " " + cliente.usuario.apellido1 + " logeado con exito\n\n");
				if(cliente.socket != null) {					
				ObjectOutputStream oosClientOld = new ObjectOutputStream(cliente.socket.getOutputStream());
				oosClientOld.writeObject(new Action());
				}
				
				cliente.socket = this.skCliente;
				
				new MsgReceiver(skCliente, cliente);
			}
		} catch (Exception e) {	
			Io.Sop(e+"");
		}
	}
}
