package msgService;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import clases.Cliente;
import controllers.ClientesController;
import main.Io;

public class MsgService extends Thread {

	public MsgService() {
		super();
		start();
	}

	public void run() {
		try {
			File fich = new File("clientes.obj");
			if (fich.exists()) {
				ObjectInputStream ois = null;
				Cliente c;
				try {
					ois = new ObjectInputStream(new FileInputStream(fich));
					while (true) {
						c = (Cliente) ois.readObject();
						ClientesController.clientes.add(c);
					}
				} catch (ClassCastException | EOFException e) {					
					Io.Sop("Clientes cargados\n");
				}
				ois.close();
			} else
				System.out.println("El fichero todavía NO existe");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
