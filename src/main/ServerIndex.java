package main;

import clientService.ClientService;
import commandsService.CommandsService;
import msgService.MsgService;

public class ServerIndex {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ClientService();
		new MsgService();
		new CommandsService();
	}

}
