package clientService;

import java.io.*;
import java.net.*;

import javax.net.ssl.SSLSocket;

import clases.*;
import controllers.ClientesController;
import main.Io;

public class Login extends Thread {

	SSLSocket skClienteSSL;

	public Login(SSLSocket sk) {
		super();
		this.skClienteSSL = sk;
		start();
	}

	public void run() {
		try {

			Io.Sop("Entrada de solicitud de inicio de sesion\n\n");

			DataInputStream disClient = new DataInputStream(this.skClienteSSL.getInputStream());

			double nEmpl = disClient.readLong();
			String passw = disClient.readUTF();

			Cliente cliente = null;

			cliente = ClientesController.clientes.stream().filter(c -> c.usuario.nEmpl == nEmpl && c.passw.equals(passw)).findFirst().orElse(null);
			ObjectOutputStream oosClient = new ObjectOutputStream(this.skClienteSSL.getOutputStream());
			DataOutputStream dosClient = new DataOutputStream(this.skClienteSSL.getOutputStream());
			
		
			
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
				if(cliente.socketSSL != null) {					
				ObjectOutputStream oosClientOld = new ObjectOutputStream(cliente.socketSSL.getOutputStream());
				oosClientOld.writeObject(new Pidove(1));
				}
				
				cliente.socketSSL = this.skClienteSSL;
				
				new MsgReceiver(skClienteSSL, cliente);
			}
		} catch (Exception e) {	
			Io.Sop(e+"");
		}
	}
}
