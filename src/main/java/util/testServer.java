package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import clases.Cliente;
import clases.Usuario;
import controllers.ClientesController;

public class testServer {

	public static void main(String[] args) {

		Cliente c1 = new Cliente(new Usuario((double) 1, "Jie", "Zhang", "Xiang"), "1233");
		Cliente c2 = new Cliente(new Usuario((double) 2, "Ander", "Lopez", "Gracia"), "1233");
		Cliente c3 = new Cliente(new Usuario((double) 3, "Cano", "Olatx", "Mendes"), "1233");

		ClientesController.clientes.add(c1);
		ClientesController.clientes.add(c2);
		ClientesController.clientes.add(c3);

		try {
			File fich = new File("clientes.obj");
			if (fich.exists()) {
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fich));
				for (Cliente cliente : ClientesController.clientes) {
					oos.writeObject(cliente);
				}
				oos.close();
			} else
				System.out.println("El fichero todavía NO existe");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
