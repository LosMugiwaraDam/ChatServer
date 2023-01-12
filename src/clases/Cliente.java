package clases;

import java.io.Serializable;

import javax.net.ssl.SSLSocket;

public class Cliente implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Usuario usuario;
	public String passw;
	public SSLSocket socketSSL;
	public Cliente(Usuario usuario, String passw) {
		super();
		this.usuario = usuario;
		this.passw = passw;
		this.socketSSL = null;
	}
	
}