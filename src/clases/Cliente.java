package clases;

import java.io.Serializable;
import java.net.Socket;

public class Cliente implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Usuario usuario;
	public String passw;
	public Socket socket;
	public Cliente(Usuario usuario, String passw) {
		super();
		this.usuario = usuario;
		this.passw = passw;
	}
	
}