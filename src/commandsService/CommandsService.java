package commandsService;

import main.Io;

public class CommandsService extends Thread{	
	public CommandsService () {
		super();
		start();
	}
	
	public void run() {
		while(true) {
			String s = Io.leerString("");
			
			if(s.equals("conn"));
		}
	}
}
