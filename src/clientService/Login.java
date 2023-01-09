package clientService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import clases.Empleado;
import clases.UserConn;
import controllers.ConnectController;
import controllers.UsuariosController;

public class Login extends Thread {

	Socket skCliente;

	public Login(Socket sk) {
		super();
		this.skCliente = sk;
		start();
	}

	public void run() {
		try {

			File fich = new File("empleado.obj");

			System.out.println("Entrada de solicitud de inicio de sesion");

			DataInputStream disClient = new DataInputStream(this.skCliente.getInputStream());

			Long nEmpl = disClient.readLong();
			String contr = disClient.readUTF();
			String ip = disClient.readUTF();

			Empleado empleado = null;

			ArrayList<Empleado> empleados = new ArrayList<Empleado>();

			ObjectInputStream oisFile = new ObjectInputStream(new FileInputStream(fich));
			if (fich.exists()) {
				try {
					while (true) {
						empleados.add((Empleado) oisFile.readObject());
					}
				} catch (EOFException ex) {
					oisFile.close();
				}
			} else
				System.out.println("El fichero todavía NO existe");

			empleado = empleados.stream()
					.filter(e -> e.getnEmpl().equals(nEmpl) && e.getContr().equals(contr) && e.getActivo()).findFirst()
					.orElse(null);

			ObjectOutputStream oosClient = new ObjectOutputStream(this.skCliente.getOutputStream());
			DataOutputStream dosClient = new DataOutputStream(this.skCliente.getOutputStream());

			oosClient.writeObject(empleado);
			Boolean isConnected = UsuariosController.isConnected(empleado);
			dosClient.writeBoolean(isConnected);
			if (empleado == null)
				System.out.println("Nº Empleado o Contraseña Incorrecta");
			else {
				
				if (isConnected) {
					System.out.println("Usuario ya ha iniciado sesion en otro cliente");
					skCliente.close();
				} else {
					skCliente.close();
					System.out.println(
							"Usuario " + empleado.getNombre() + " " + empleado.getApellido1() + " logeado con exito");
					UserConn uConectado = new UserConn(empleado, ip);
					ConnectController.add(uConectado);
					UsuariosController.connect(uConectado);
					System.out.println(uConectado.toString());
				}
			}
			disClient.close();
			oosClient.close();
			dosClient.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
