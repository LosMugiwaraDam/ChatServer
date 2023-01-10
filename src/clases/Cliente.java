package clases;

import java.io.Serializable;
import java.net.Socket;

public class Cliente implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Usuario usuario;
	public double nEmpl;
	public String passw;
	public Socket socket;
	public Cliente(Usuario usuario, double nEmpl, String passw) {
		super();
		this.usuario = usuario;
		this.nEmpl = nEmpl;
		this.passw = passw;
	}
	
}