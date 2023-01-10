package main;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import clases.Cliente;
import clases.Usuario;
import controllers.ClientesController;

public class testServer {

	public static void main(String[] args) {

		Cliente c1 = new Cliente(new Usuario("Jie", "Zhang", "Xiang"), (double) 1, "1233");
		Cliente c2 = new Cliente(new Usuario("Ander", "Lopez", "Gracia"), (double) 2, "1233");
		Cliente c3 = new Cliente(new Usuario("Cano", "Olatx", "Mendes"), (double) 3, "1233");

		ClientesController.clientes.add(c1);
		ClientesController.clientes.add(c2);
		ClientesController.clientes.add(c3);

		try {
			File fich = new File("clientes.obj");
			if (fich.exists()) {
				ObjectInputStream ois = null;
				Cliente c;
				try {
					ois = new ObjectInputStream(new FileInputStream(fich));
					while (true) {
						c = (Cliente) ois.readObject();
						//ClientesController.clientes.add(c);
					}
				} catch (ClassCastException | EOFException e) {
					ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fich));
					for (Cliente cliente : ClientesController.clientes) {
						oos.writeObject(cliente);
					}
					oos.close();
				}
				ois.close();
				Io.Sop("ok");
			} else
				System.out.println("El fichero todavía NO existe");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
