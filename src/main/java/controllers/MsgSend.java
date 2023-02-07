package controllers;

import java.io.ObjectOutputStream;

import javax.net.ssl.SSLSocket;

import clases.Mensaje;

public class MsgSend extends Thread{

	SSLSocket socketSSL;
	Mensaje m;

	public MsgSend(SSLSocket socketSSL, Mensaje m) {
		super();
		this.socketSSL = socketSSL;
		this.m = m;
		start();
	}

	public void run() {
		try {
			ObjectOutputStream oosServer = new ObjectOutputStream(socketSSL.getOutputStream());
			oosServer.writeObject(m);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
