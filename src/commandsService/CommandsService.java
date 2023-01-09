package commandsService;

import java.util.Scanner;

import controllers.ConnectController;
import controllers.UsuariosController;

public class CommandsService extends Thread{

	Scanner scan = new Scanner(System.in);
	
	public CommandsService () {
		super();
		start();
	}
	
	public void run() {
		while(true) {
			String s = scan.nextLine();
			
			if(s.equals("conn"))UsuariosController.showConn();
			
			if(s.equals("save"))ConnectController.guardar();
		}
	}
}
