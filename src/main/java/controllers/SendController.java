package controllers;

import java.io.ObjectOutputStream;

import javax.net.ssl.SSLSocket;


public class SendController extends Thread{

	SSLSocket socketSSL;
	Object o;

	public SendController(SSLSocket socketSSL, Object o) {
		super();
		this.socketSSL = socketSSL;
		this.o = o;
		start();
	}

	public void run() {
		try {
			ObjectOutputStream oosServer = new ObjectOutputStream(socketSSL.getOutputStream());
			oosServer.writeObject(o);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
