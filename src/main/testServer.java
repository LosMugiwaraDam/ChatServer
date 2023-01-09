package main;

import java.io.IOException;
import java.net.Socket;

import clientService.ClientService;
import msgService.MsgService;

public class testServer {

	public static void main(String[] args) {

		try {
			Socket sk = new Socket ("192.168.1.133" ,123);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
